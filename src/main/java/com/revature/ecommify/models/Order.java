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

    public Order() {
    }

    public Order(String id, String user_id, double total, String created_at) {
        this.id = id;
        this.user_id = user_id;
        this.total = total;
        this.created_at = created_at;
    }

    public Order(String id, String user_id, double total, String status, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
