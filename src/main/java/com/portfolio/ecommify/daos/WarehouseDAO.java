package com.portfolio.ecommify.daos;

import com.portfolio.ecommify.models.User;
import com.portfolio.ecommify.models.Warehouse;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidSQLException;
import com.portfolio.ecommify.utils.database.ConnectionFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements CrudDAO<Warehouse> {
    @Override
    public void save(Warehouse obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO warehouses (id, name, address, city, state, zipcode, country, email, phone ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getName());

            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while saving to database.");
        }

    }

    @Override
    public void update(Warehouse obj) {

    }

    @Override
    public void delete(Warehouse obj) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<Warehouse> getAll() {
        List<Warehouse> warehouses = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM warehouses");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Warehouse wh = new Warehouse(rs.getString("id"), rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zipcode"), rs.getString("country"), rs.getString("email"), rs.getString("phone"));
                warehouses.add(wh);
            }
        }catch (SQLException e){
            throw new InvalidSQLException("Error while retrieving from database.");
        }
        return warehouses;
    }
}
