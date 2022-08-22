package com.revature.ecommify.services;

import com.revature.ecommify.daos.LineItemDAO;
import com.revature.ecommify.models.LineItem;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;

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
