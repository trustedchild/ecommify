package com.portfolio.ecommify.services;

import com.portfolio.ecommify.utils.custom_exceptions.InvalidUserException;
import com.portfolio.ecommify.daos.OrderDAO;
import com.portfolio.ecommify.models.Order;

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
