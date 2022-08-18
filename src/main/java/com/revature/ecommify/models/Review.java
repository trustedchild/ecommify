package com.revature.ecommify.models;

public class Review {
    //belongs to user
    //belongs to product

    private String id;
    private String user_id;
    private String product_id;
    private String comment;
    private String created_at;

    private int rating;

}
