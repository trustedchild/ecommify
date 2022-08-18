package com.revature.ecommify.models;

public class Product {

    //belongs to category
    //belongs to warehouse
    //has many orders through line-items

    private String id;
    private String warehouse_id;
    private String category_id;
    private String name;
    private String description;
    private String brand;
    private String image;
    private String created_at;
    private String updated_at;

    private int quantity;

    private double price;
}
