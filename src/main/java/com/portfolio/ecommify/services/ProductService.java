package com.portfolio.ecommify.services;

import com.portfolio.ecommify.daos.ProductDAO;
import com.portfolio.ecommify.models.Product;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidInputException;

import java.io.IOException;
import java.util.List;

public class ProductService {

    private final ProductDAO productDAO;


    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public List<Product> getAllProducts(){
        return productDAO.getAll();
    }

    public List<Product> getAllProductsByWarehouseId(String id) {
        return productDAO.getAllByWarehouseId(id);
    }

    public List<Product> getAllProductsByCategoryId(String id){
        return productDAO.getAllByCategoryId(id);
    }

    public void update(Product product) throws IOException {
        productDAO.update(product);
    }

    public boolean isValidInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidInputException("\nInvalid input values");
        return true;
    }

    public void updateProductQtyById(Product obj) throws IOException {
        productDAO.updateProductQtyById(obj);
    }
}
