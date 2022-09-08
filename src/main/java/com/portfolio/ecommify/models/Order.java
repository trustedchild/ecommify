package com.portfolio.ecommify.models;

public class Order {

    //belongs to user
    // belongs to product
    //has many line_items

    private String id;
    private String user_id;
    private String product_id;
    private String status;
    private String created_at;
    private String updated_at;

    public Order() {
    }

    public Order(String id, String user_id, String product_id, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.created_at = created_at;
    }

    public Order(String id, String user_id, String product_id, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
