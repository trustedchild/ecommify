package com.revature.ecommify.daos;

import com.revature.ecommify.models.User;

import java.io.IOException;
import java.util.List;

public interface CrudDAO<T> {
    void save(T obj) throws IOException;

    void update(T obj) throws IOException;;

    void delete(T obj) throws IOException;

    User getById(String id);

    List<T> getAll();
}
