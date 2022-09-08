package com.portfolio.ecommify.daos;

import com.portfolio.ecommify.models.User;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidSQLException;
import com.portfolio.ecommify.utils.database.ConnectionFactory;
import com.portfolio.ecommify.models.Order;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements CrudDAO<Order>{
    @Override
    public void save(Order obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO orders (id, user_id, product_id, status, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUser_id());
            ps.setString(3, obj.getProduct_id());
            ps.setString(4, obj.getStatus());
            ps.setString(5, obj.getCreated_at());
            ps.setString(6, obj.getUpdated_at());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while saving to database.");
        }
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
                Order ord = new Order(rs.getString("id"), rs.getString("user_id"), rs.getString("product_id"), rs.getString("status"), rs.getString("created_at"), rs.getString("updated_at"));
                orders.add(ord);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while retrieving orders by user from the database.");
        }
        return orders;
    }


    public void updateProductQtyById(Order obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE orders SET status=? WHERE id=?");
            ps.setString(1, obj.getStatus());
            ps.setString(2, obj.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while creating order in database.");
        }
    }

}
