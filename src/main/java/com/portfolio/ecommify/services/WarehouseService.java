package com.portfolio.ecommify.services;

import com.portfolio.ecommify.daos.WarehouseDAO;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidInputException;
import com.portfolio.ecommify.models.Warehouse;

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
