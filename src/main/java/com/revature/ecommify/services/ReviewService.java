package com.revature.ecommify.services;

import com.revature.ecommify.daos.ReviewDAO;
import com.revature.ecommify.models.Product;
import com.revature.ecommify.models.Review;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class ReviewService {

    private final ReviewDAO reviewDAO;
    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public List<Review> getAllReviews(){
        return reviewDAO.getAll();
    }

    public boolean isValidUserInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidUserException("\nInvalid input values");
        return true;
    }

}
