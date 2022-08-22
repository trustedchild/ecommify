package com.revature.ecommify.services;

import com.revature.ecommify.daos.WarehouseDAO;
import com.revature.ecommify.models.Warehouse;
import com.revature.ecommify.utils.custom_exceptions.InvalidInputException;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;

import java.util.List;

public class WarehouseService {

    private final WarehouseDAO warehouseDAO;

    public WarehouseService(WarehouseDAO warehouseDAO) {

        this.warehouseDAO = warehouseDAO;
    }

    public List<Warehouse> getAllWarehouses(){
        return warehouseDAO.getAll();
    }

    public boolean isValidInput(String input) {
        if (!input.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) throw new InvalidInputException("\nInvalid input values");
        return true;
    }
}
