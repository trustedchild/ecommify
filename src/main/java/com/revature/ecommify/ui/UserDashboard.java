package com.revature.ecommify.ui;

import com.revature.ecommify.models.Product;
import com.revature.ecommify.models.User;
import com.revature.ecommify.services.*;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserDashboard implements StartMenu{

    EmojiManager emojiManager;
    Emoji emoji;// = new Emoji().getUnicode("smiley");
    //String str = String.valueOf(emojiManager.getForAlias("grinning"));


    private final User user;

    private final UserService userService;

    private final CategoryService categoryService;

    private final WarehouseService warehouseService;

    private final ProductService productService;

    private final ReviewService reviewService;

    private final OrderService orderService;

    private final LineItemService lineItemService;

    public UserDashboard(User user, UserService userService, CategoryService categoryService, WarehouseService warehouseService, ProductService productService, ReviewService reviewService, OrderService orderService, LineItemService lineItemService) {
        this.user = user;
        this.userService = userService;
        this.categoryService = categoryService;
        this.warehouseService = warehouseService;
        this.productService = productService;
        this.reviewService = reviewService;
        this.orderService = orderService;
        this.lineItemService = lineItemService;
    }

    @Override
    public void start() {
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        //1. View Products
        //2. View Cart
        //3. View Order History

        exit:
        {
            while (true) {
                String userAvatar = String.valueOf(emojiManager.getForAlias(user.getAvatar()).getUnicode());

                System.out.println("\n | Welcome " + userAvatar  + user.getUsername()  + " |\n");
                System.out.println("[1] View Products");
                System.out.println("[2] View Cart");
                System.out.println("[3] View Order History");
                System.out.println("[x] Signout!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        viewAllProducts();
                        break;
                    case "2":
                        viewAllLineItems();
                        break;
                    case "3":
                        viewAllOrderedHisttory();
                        break;
                    case "x":
                        System.out.println("\nGoodbye!");
                        break exit;
                    default:
                        System.out.println("\nInvalid input!");
                        break;
                }
            }
        }
    }


    private void viewAllLineItems(){

    }

    private void viewAllOrderedHisttory(){

    }

    private void viewAllProducts(){
        String userInput = "";
        Scanner scan = new Scanner(System.in);
        //View Products
        //1. Add to Cart
        //2. Purchase/Buy

        exit:
        {
            while (true) {
                //present list of products to user
                System.out.println("\nList of Products...");

                List<Product> products = productService.getAllProducts();

                for (int i = 0; i < products.size(); i++) {
//                    if(products.get(i).getImage().isEmoji){
//                        //convert image string to emoji unicode
//                        String productImage = String.valueOf(emojiManager.getForAlias(products.get(i).getImage()).getUnicode());
//                    }
                    //show only products that are in stock
                    if(products.get(i).getQuantity() > 0){
                        System.out.println("[" + (i + 1) + "] " + " | " + products.get(i).getImage() + " | " + products.get(i).getName() + " | " + products.get(i).getPrice() + " |"  + " Qty in Stock: " + products.get(i).getQuantity());
                    }
                }

                System.out.print("\nSelect a product to buy one (1): ");
                int index = scan.nextInt() - 1;

                try {
                    Product selectedProduct = products.get(index);

                    //print qty before deduction
                    //System.out.println("\n Before " + selectedProduct.getName() + selectedProduct.getQuantity());

                    //update qty in list and then pass to db
                    int newUpdatedQty = selectedProduct.getQuantity() -1;
                    selectedProduct.setQuantity(newUpdatedQty);
                    productService.updateProductQtyById(selectedProduct);

                    //print qty after deduction
                    System.out.println("\n Purchase successful!");
                    if (selectedProduct.getQuantity() != 0) {
                        System.out.println("\n You can still buy more of " + selectedProduct.getName()  );
                        System.out.println("\nQty in Stock: " + selectedProduct.getQuantity());
                    }else{
                        System.out.println("\n Sold out.");
                        System.out.println("\n Qty in Stock: "  + selectedProduct.getQuantity());
                    }
                    //buyNow(selectedProductId);

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break exit;
            }
        }

    }

    private void addToLineItem(){
        //add to line item
        //view all lineitems by orderid
        //ask user to update cart qty if inventory is less than qty in cart
    }

    private void buyNow(String s){
        //decrement inventory by qty bought
        //decrementSelectedProductQtyById();

        //change order status to completed
        //updateOrderStatusById();

    }

    private void updateOrderStatusById(){

    }



}
