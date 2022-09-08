package com.portfolio.ecommify.services;

import com.portfolio.ecommify.daos.ReviewDAO;
import com.portfolio.ecommify.models.Review;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidUserException;

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
