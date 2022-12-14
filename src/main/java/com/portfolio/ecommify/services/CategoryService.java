package com.portfolio.ecommify.services;

import com.portfolio.ecommify.daos.CategoryDAO;
import com.portfolio.ecommify.models.Category;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidInputException;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidUserException;

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

    public boolean isValidInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidInputException("\nInvalid input values");
        return true;
    }
}
