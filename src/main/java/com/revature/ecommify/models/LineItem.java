package com.revature.ecommify.models;

public class LineItem {

    //belongs to product
    //belongs to order

    private String id;
    private String order_id;
    private String product_id;
    private int line_item_total;
    private int quantity;


    public LineItem() {
    }

    public LineItem(String id, String order_id, String product_id, int line_item_total, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.line_item_total = line_item_total;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getLine_item_total() {
        return line_item_total;
    }

    public void setLine_item_total(int line_item_total) {
        this.line_item_total = line_item_total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id='" + id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", line_item_total=" + line_item_total +
                ", quantity=" + quantity +
                '}';
    }
}
