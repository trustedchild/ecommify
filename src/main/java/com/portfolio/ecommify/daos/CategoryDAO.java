package com.portfolio.ecommify.daos;

import com.portfolio.ecommify.models.Category;
import com.portfolio.ecommify.models.User;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidSQLException;
import com.portfolio.ecommify.utils.database.ConnectionFactory;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidInputException;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements CrudDAO<Category> {
    @Override
    public void save(Category obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO categories (id, name) VALUES (?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());

            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while saving to database.");
        }
    }

    @Override
    public void update(Category obj) {

    }

    @Override
    public void delete(Category obj) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categories");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Category cat = new Category(rs.getString("id"), rs.getString("name"));
                categories.add(cat);
            }
        }catch (SQLException e){
            throw new InvalidSQLException("Error while retrieving from database.");
        }
        return categories;
    }

    public boolean isValidInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidInputException("\nInvalid input values");
        return true;
    }

}
