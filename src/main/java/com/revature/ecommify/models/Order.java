package com.revature.ecommify.models;

public class Order {

    //belongs to user
    // belongs to product
    //has many line_items

    private String id;
    private String user_id;
    private double total;
    //private double order_items;
    private String status = "DEFAULT";
    private String created_at;
    private String updated_at;
}
