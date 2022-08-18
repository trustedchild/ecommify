package com.revature.ecommify.models;

public class PurchaseHistory {

    //belongs to user
    //belongs to product
    //belongs to order

    private String id;
    private String name;
    private String description;
    private String brand;
    private String image;

    private String created_at;
    private String address;

    private int quantity;

    private double price;
    private double subTotal;
    private double handlingFee;
    private double grandTotal;


}
