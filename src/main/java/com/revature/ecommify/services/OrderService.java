package com.revature.ecommify.services;

import com.revature.ecommify.daos.OrderDAO;
import com.revature.ecommify.models.Order;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;

import java.io.IOException;
import java.util.List;

public class OrderService {
    public final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Order> getAllOrders(){
        return orderDAO.getAll();
    }

    public List<Order> getAllOrdersByUserId(String id){
        return orderDAO.getAllByUserId(id);
    }

    public boolean isValidUserInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidUserException("\nInvalid input values");
        return true;
    }

}
