package com.portfolio.ecommify.daos;

import com.portfolio.ecommify.models.Review;
import com.portfolio.ecommify.models.User;

import java.io.IOException;
import java.util.List;

public class ReviewDAO implements CrudDAO<Review>{
    @Override
    public void save(Review obj) throws IOException {

    }

    @Override
    public void update(Review obj) {

    }

    @Override
    public void delete(Review obj) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<Review> getAll() {
        return null;
    }
}
