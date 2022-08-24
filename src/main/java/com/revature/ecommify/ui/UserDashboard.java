package com.revature.ecommify.ui;

import com.revature.ecommify.models.Order;
import com.revature.ecommify.models.Product;
import com.revature.ecommify.models.User;
import com.revature.ecommify.services.*;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                        viewPendingOrders();
                        break;
                    case "3":
                        viewCompletedOrders();
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


    //View Products
    //1. Add to Cart - not yet implemented
    //2. Purchase/Buy
    private void viewAllProducts(){
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        //create purchase order
        //Order order;
        String id = UUID.randomUUID().toString();
        String user_id = user.getId();
        String product_id = "";
        String status = "";
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String created_at = LocalDateTime.now().format(formatDate);;
        String updated_at  = LocalDateTime.now().format(formatDate);;

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

                System.out.print("\nSelect a product to view details: ");
                int index = scan.nextInt() - 1;

                try {
                    Product selectedProduct = products.get(index);

                    //convert image string to emoji unicode
                    String productImage = String.valueOf(emojiManager.getForAlias(products.get(index).getImage()).getUnicode());

                    System.out.println("\n"+ productImage);
                    System.out.println("ID: " + selectedProduct.getId());
                    System.out.println("Name: " + selectedProduct.getName());
                    System.out.println("Description: " + selectedProduct.getDescription());
                    System.out.println("Brand: " + selectedProduct.getBrand());
                    System.out.println("Unit Price: " + selectedProduct.getPrice());
                    System.out.println("Qty in Stock: " + selectedProduct.getQuantity());
                    System.out.println("Category ID: " + selectedProduct.getCategory_id());
                    System.out.println("Warehouse ID: " + selectedProduct.getWarehouse_id());

                        System.out.println("\n[1] Buy Now");
                        System.out.println("[2] Add to Cart ");
                        System.out.println("\nSelect an option: ");

                        int inputOption = scan.nextInt();
                        switch (inputOption) {
                            case 1:
                                System.out.println("Enter a quantity to buy: \n");

                                //update qty in list and then pass to db
                                int purchaseQty = scan.nextInt();
                                int stockQty = selectedProduct.getQuantity();

                                if (purchaseQty <= 0){
                                    System.out.println("\n Not allow! Qty must be greater than zero.");
                                } else if (purchaseQty > stockQty){
                                    System.out.println("\n Qty is greater than available! Please choose different qty.");
                                }else {
                                    //update product qty in db
                                    int updateQty =  stockQty - purchaseQty;
                                    selectedProduct.setQuantity(updateQty);
                                    productService.updateProductQtyById(selectedProduct);

                                    //create new order with status completed
                                    product_id = selectedProduct.getId();
                                    createOrder(id, user_id, product_id, created_at, updated_at);

                                    System.out.println("\n Purchase successful!");
                                }
                                break;
                            case 2:
                                System.out.println("\nEnter a quantity to add to cart: ");

                                //update qty in list and then pass to db
                                int addToCartQty = scan.nextInt();
                                int qtyInStock = selectedProduct.getQuantity();

                                if (addToCartQty <= 0){
                                    System.out.println("\n Not allow! Qty must be greater than zero.");
                                } else if (addToCartQty > qtyInStock){
                                    System.out.println("\n Qty is greater than available! Please choose different qty.");
                                }else {

                                    //create new order with status completed
                                    addToCart(id, user_id, product_id, created_at, updated_at);

                                    System.out.println("\n Added to cart successful!");
                                }
                                break;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }


                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break exit;
            }
        }

    }


    //view pending orders in cart
    private void viewPendingOrders(){
        List<Order> orders = orderService.getAllOrdersByUserId(user.getId());

        //System.out.println(user.getAvatar() + " " + user.getUsername() + " " + user.getLast_name());
        System.out.println("\nYour Cart Items: ");

        String orderStatus = "pending";
        int counter = 0;

        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().toLowerCase().equals(orderStatus)){
                System.out.println("[" + (counter + 1) + "] "  + "| Date: " + orders.get(i).getCreated_at() + " | Order ID: " + orders.get(i).getId() + " | Product ID: " + orders.get(i).getProduct_id() + " | ");
                counter++;
            }
        }
    }

    private void viewCompletedOrders(){
        List<Order> orders = orderService.getAllOrdersByUserId(user.getId());
        //System.out.println(user.getAvatar() + " " + user.getUsername() + " " + user.getLast_name());
        System.out.println("\nYour Order History: ");

        String orderStatus = "completed";
        int counter = 0;

        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().toLowerCase().equals(orderStatus)){
                System.out.println("[" + (counter + 1) + "] "  + "| Date: " + orders.get(i).getCreated_at() + " | Order ID: " + orders.get(i).getId() + " | Product ID: " + orders.get(i).getProduct_id() + " | ");
                counter++;
            }
        }
    }

    private Order createOrder(String id, String user_id, String product_id, String created_at, String updated_at) throws IOException {
        Order order;
        String status = "completed";
        order = new Order(id, user_id, product_id, status, created_at, updated_at);
        orderService.orderDAO.save(order);
        return order;
    }

    private Order addToCart(String id, String user_id, String product_id, String created_at, String updated_at) throws IOException {
        Order order;
        String status = "pending";

        order = new Order(id, user_id, product_id, status, created_at, updated_at);
        orderService.orderDAO.save(order);
        
        return order;
    }

}
