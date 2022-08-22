package com.revature.ecommify.daos;

import com.revature.ecommify.models.Order;
import com.revature.ecommify.models.Product;
import com.revature.ecommify.models.User;
import com.revature.ecommify.utils.custom_exceptions.InvalidSQLException;
import com.revature.ecommify.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    @Override
    public void save(Order obj) throws IOException {

    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public void delete(Order obj) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    public List<Order> getAllByUserId(String id){
        List<Order> orders = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orders WHERE user_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order ord = new Order(rs.getString("id"), rs.getString("user_id"), rs.getDouble("total"), rs.getString("created_at"));
                orders.add(ord);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while retrieving orders by user from the database.");
        }

        return orders;
    }

    //updateOrderStatusById();

}
