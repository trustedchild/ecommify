package com.portfolio.ecommify.daos;

import com.portfolio.ecommify.utils.custom_exceptions.InvalidSQLException;
import com.portfolio.ecommify.models.User;
import com.portfolio.ecommify.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User>{

    ConnectionFactory connectionFactory;

    @Override
    public void save(User obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, first_name, last_name, username, password, email, phone, street_address, city, zip_code, country, avatar, role, last_sign_in,  created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getFirst_name());
            ps.setString(3, obj.getLast_name());
            ps.setString(4, obj.getUsername());
            ps.setString(5, obj.getPassword());
            ps.setString(6, obj.getEmail());
            ps.setString(7, obj.getPhone());
            ps.setString(8, obj.getStreet_address());
            ps.setString(9, obj.getCity());
            ps.setString(10, obj.getZip_code());
            ps.setString(11, obj.getCountry());
            ps.setString(12, obj.getAvatar());
            ps.setString(13, obj.getRole());
            ps.setString(14, obj.getLast_sign_in());
            ps.setString(15, obj.getCreated_at());
            ps.setString(16, obj.getUpdated_at());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public User getById(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("avatar"));
            }

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("email"), rs.getString("phone"), rs.getString("street_address"), rs.getString("city"), rs.getString("zip_code"), rs.getString("country"), rs.getString("avatar"), rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while retrieving users from the database.");
        }

        return users;
    }

    public String getUsername(String username) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getString("username");

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
                return new User(rs.getString("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"), rs.getString("avatar"));
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return null;
    }
}
