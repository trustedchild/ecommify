package com.revature.ecommify.services;

import com.revature.ecommify.daos.CategoryDAO;
import com.revature.ecommify.models.Category;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class CategoryService {

    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllCategories(){
        return  categoryDAO.getAll();
    }

    public boolean isValidUserInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidUserException("\nInvalid input values");
        return true;
    }
}
