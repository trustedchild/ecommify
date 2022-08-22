package com.revature.ecommify.daos;

import com.revature.ecommify.models.Product;
import com.revature.ecommify.models.User;
import com.revature.ecommify.utils.custom_exceptions.InvalidSQLException;
import com.revature.ecommify.utils.database.ConnectionFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements CrudDAO<Product>{
    @Override
    public void save(Product obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO products (id, warehouse_id, category_id, name, description, brand, image, created_at, updated_at, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getWarehouse_id());
            ps.setString(3, obj.getCategory_id());
            ps.setString(4, obj.getName());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getBrand());
            ps.setString(7, obj.getImage());
            ps.setInt(8, obj.getQuantity());
            ps.setDouble(9, obj.getPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while saving to database.");
        }
    }

    @Override
    public void update(Product obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET (warehouse_id=?, category_id=?, name=?, description=?, brand=?, image=?, quantity=?, price=?) WHERE id=?");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getWarehouse_id());
            ps.setString(3, obj.getCategory_id());
            ps.setString(4, obj.getName());
            ps.setString(5, obj.getDescription());
            ps.setString(6, obj.getBrand());
            ps.setString(7, obj.getImage());
            ps.setInt(8, obj.getQuantity());
            ps.setDouble(9, obj.getPrice());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while updating record in database.");
        }
    }

    @Override
    public void delete(Product obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE * FROM products, WHERE id =?");
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while deleting record in database.");
        }
    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Product prod = new Product(rs.getString("id"), rs.getString("warehouse_id"), rs.getString("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("brand"), rs.getString("image"), rs.getInt("quantity"), rs.getDouble("price"));
                products.add(prod);
            }
        }catch (SQLException e){
            throw new InvalidSQLException("Error while retrieving from database.");
        }
        return products;
    }

//    public List<Product> getAllProductById(){
//        List<Product> products = new ArrayList<>();
//
//        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM products");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()){
//                Product prod = new Product(rs.getString("id"), rs.getString("warehouse_id"), rs.getString("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("brand"), rs.getString("image"), rs.getString("created_at"), rs.getString("updated_at"), rs.getInt("quantity"), rs.getDouble("price"));
//                products.add(prod);
//            }
//        }catch (SQLException e){
//            throw new InvalidSQLException("Error while retrieving from database.");
//        }
//        return products;
//    }

    public List<Product> getAllByWarehouseId(String id) {
        List<Product> products = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE warehouse_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product prod = new Product(rs.getString("id"), rs.getString("warehouse_id"), rs.getString("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("brand"), rs.getString("image"), rs.getInt("quantity"), rs.getDouble("price"));
                products.add(prod);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while retrieving products by warehouse from the database.");
        }

        return products;
    }

    public List<Product> getAllByCategoryId(String id) {
        List<Product> products = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE category_id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product prod = new Product(rs.getString("id"), rs.getString("warehouse_id"), rs.getString("category_id"), rs.getString("name"), rs.getString("description"), rs.getString("brand"), rs.getString("image"), rs.getInt("quantity"), rs.getDouble("price"));
                products.add(prod);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred while retrieving products by category from the database.");
        }

        return products;
    }


    public void updateProductQtyById(Product obj) throws IOException {
        try(Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE products SET quantity=? WHERE id=?");
            ps.setInt(1, obj.getQuantity());
            ps.setString(2, obj.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InterruptedIOException("Error while updating record in database.");
        }
    }
}
