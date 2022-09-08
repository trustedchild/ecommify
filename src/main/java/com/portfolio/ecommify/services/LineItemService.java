package com.portfolio.ecommify.services;

import com.portfolio.ecommify.models.LineItem;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidUserException;
import com.portfolio.ecommify.daos.LineItemDAO;

import java.util.List;

public class LineItemService {

    private final LineItemDAO lineItemDAO;

    public LineItemService(LineItemDAO lineItemDAO) {
        this.lineItemDAO = lineItemDAO;
    }

    public List<LineItem> getAllLineItems(){
        return lineItemDAO.getAll();
    }

    public boolean isValidUserInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidUserException("\nInvalid input values");
        return true;
    }

}
