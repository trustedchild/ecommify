package com.revature.ecommify.models;

public class User {

    //has many orders
    //has many reviews

    private String id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private String street_address;
    private String city;
    private String zip_code;
    private String country;
    private String avatar;

    private String role = "DEFAULT";
    private String last_sign_in;
    private String created_at;
    private String updated_at;
}
