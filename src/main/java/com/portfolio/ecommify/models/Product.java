package com.portfolio.ecommify.models;

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

    private int quantity;

    private double price;

    public Product() {
    }

    public Product(String id, String warehouse_id, String category_id, String name, String description, String brand, String image, int quantity, double price) {
        this.id = id;
        this.warehouse_id = warehouse_id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", warehouse_id='" + warehouse_id + '\'' +
                ", category_id='" + category_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

}
