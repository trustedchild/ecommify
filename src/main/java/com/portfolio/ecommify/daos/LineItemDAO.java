package com.portfolio.ecommify.daos;

import com.portfolio.ecommify.models.LineItem;
import com.portfolio.ecommify.models.User;

import java.io.IOException;
import java.util.List;

public class LineItemDAO implements CrudDAO<LineItem>{
    @Override
    public void save(LineItem obj) throws IOException {

    }

    @Override
    public void update(LineItem obj) {

    }

    @Override
    public void delete(LineItem obj) {

    }

    @Override
    public User getById(String id) {
        return null;
    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }
}
