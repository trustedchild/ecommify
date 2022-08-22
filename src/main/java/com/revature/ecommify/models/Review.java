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

    public Review() {
    }

    public Review(String id, String user_id, String product_id, String comment, String created_at, int rating) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.comment = comment;
        this.created_at = created_at;
        this.rating = rating;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", comment='" + comment + '\'' +
                ", created_at='" + created_at + '\'' +
                ", rating=" + rating +
                '}';
    }
}


