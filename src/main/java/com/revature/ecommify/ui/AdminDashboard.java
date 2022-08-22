package com.revature.ecommify.ui;

import com.revature.ecommify.models.*;
import com.revature.ecommify.services.*;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AdminDashboard implements StartMenu {
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

    public AdminDashboard(User user, UserService userService, CategoryService categoryService, WarehouseService warehouseService, ProductService productService, ReviewService reviewService, OrderService orderService, LineItemService lineItemService) {
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


        // admin should be able to do the ff:
        // 1 - View All Product Inventory
        //2 - view product by warehouse
        //3 - view product by category
        //4 - view product by brand
        //5 - add new product
        //6 - add new user/customer
        //2 - view order history of customer

        exit:
        {
            while (true) {
                String userAvatar = String.valueOf(emojiManager.getForAlias(user.getAvatar()).getUnicode());

                System.out.println("\n" + userAvatar  + user.getUsername()  + ", Admin\n");
                System.out.println("[1] View All Users");
                System.out.println("[2] View All Products in Stock");
                System.out.println("[3] View Products by Warehouse");
                System.out.println("[4] View Products by Category");
                System.out.println("[5] View Products by Brand");
                System.out.println("[6] Add New Product");
                System.out.println("[7] Add New user/customer");
                System.out.println("[8] Add New Warehouse");
                System.out.println("[9] Add New Category");
                System.out.println("[x] Signout!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        viewAllUsers();
                        break;
                    case "2":
                        viewAllInventories();
                        break;
                    case "3":
                        viewProductsByWarehouse();
                        break;
                    case "4":
                        viewProductsByCategory();
                        break;
                    case "5":
                        //viewInventoryByBrand();
                        break;
                    case "6":
                        //viewOrderHistoryOfCustomer();
                        break;
                    case "7":
                        //addNewProduct();
                        break;
                    case "8":
                        //addNewUser();
                        break;
                    case "9":
                        //addNewUser();
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


    private void viewAllUsers() {
        Scanner scan = new Scanner(System.in);


        exit: {
            while (true) {
                System.out.println("\nList of Users");
                List<User> users = userService.getAllUsers();

                for (int i = 0; i < users.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + users.get(i).getUsername());
                }

                System.out.print("\nSelect a user to view details: ");
                int index = scan.nextInt() - 1;

                try {
                   User selectedUser = users.get(index);

                    //convert image string to emoji unicode
                    String selectedUserAvatar = String.valueOf(emojiManager.getForAlias(users.get(index).getAvatar()).getUnicode());

                    System.out.println("\n" + selectedUserAvatar);
                    System.out.println("Username: " + selectedUser.getUsername());
                    System.out.println("Full Name: " + selectedUser.getFirst_name() + " " + selectedUser.getLast_name());
                    System.out.println("Role: " + selectedUser.getRole());
                    System.out.println("Email: " + selectedUser.getEmail());
                    System.out.println("Phone: " + selectedUser.getPhone());
                    System.out.println("Street: " + selectedUser.getStreet_address());
                    System.out.println("City: " + selectedUser.getCity());
                    System.out.println("Zip Code: " + selectedUser.getZip_code());
                    System.out.println("Country: " + selectedUser.getCountry());

                    System.out.println("\nOrder history:");

                    List<Order> orders = orderService.getAllOrdersByUserId(selectedUser.getId());

                    for (Order o : orders) {
                        System.out.println("\nDate: " + o.getCreated_at());
                        System.out.println("User ID: " + userService.getUserById(o.getUser_id()).getUsername());
                        //System.out.println("Total: " + o.getAllOrdersTotal());
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                break exit;
            }
        }
    }

    private void viewAllInventories() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nList of Products...");
                List<Product> products = productService.getAllProducts();

                for (int i = 0; i < products.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + products.get(i).getName());
                }

                System.out.print("\nSelect a product to view details: ");
                int index = scan.nextInt() - 1;

                try {
                    Product selectedProduct = products.get(index);

                    //convert image string to emoji unicode
                    String productImage = String.valueOf(emojiManager.getForAlias(products.get(index).getImage()).getUnicode());

                    System.out.println("\n"+ productImage);
                    System.out.println("Name: " + selectedProduct.getName());
                    System.out.println("Description: " + selectedProduct.getDescription());
                    System.out.println("Brand: " + selectedProduct.getBrand());
                    System.out.println("Unit Price: " + selectedProduct.getPrice());
                    System.out.println("Qty in Stock: " + selectedProduct.getQuantity());
                    System.out.println("Category ID: " + selectedProduct.getCategory_id());
                    System.out.println("Warehouse ID: " + selectedProduct.getWarehouse_id());

                    System.out.println("Select a product to update Qty: " + selectedProduct.getWarehouse_id());


                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                break exit;
            }
        }
    }

    private void viewProductsByWarehouse() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nList of Warehouses");
                List<Warehouse> warehouses = warehouseService.getAllWarehouses();

                for (int i = 0; i < warehouses.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + warehouses.get(i).getName());
                }

                System.out.print("\nSelect a warehouse to view products: ");
                int index = scan.nextInt() - 1;

                try {
                    Warehouse selectedWarehouse = warehouses.get(index);

                    System.out.println("Name: " + selectedWarehouse.getName());
                    System.out.println("address: " + selectedWarehouse.getAddress());
                    System.out.println("city: " + selectedWarehouse.getCity());
                    System.out.println("State: " + selectedWarehouse.getState());
                    System.out.println("Zipcode: " + selectedWarehouse.getZipcode());
                    System.out.println("State: " + selectedWarehouse.getState());
                    System.out.println("Country: " + selectedWarehouse.getCountry());
                    System.out.println("Email: " + selectedWarehouse.getEmail());
                    System.out.println("Phone: " + selectedWarehouse.getPhone());

                    List<Product> products = productService.getAllProductsByWarehouseId(selectedWarehouse.getId());

                    for (Product p : products) {
                        //String productImage = String.valueOf(emojiManager.getForAlias(p.getImage()).getUnicode());

                        System.out.println("\n=================================================================================================================================");

                        System.out.print("| ");
                        //System.out.print(productImage + " | ");
                        System.out.print("Name: " + p.getName() + " | ");
                        System.out.print("Description: " + p.getDescription() + " | ");
                        System.out.print("Brand: " + p.getBrand() + " | ");
                        System.out.print("Unit Price: " + p.getPrice() + " | ");
                        System.out.print("Qty in Stock: " + p.getQuantity() + " | ");
                        System.out.print("Category ID: " + p.getCategory_id() + " | ");
                        System.out.print("Warehouse ID: " + p.getWarehouse_id() + " |");
                    }

                    System.out.println("\n=================================================================================================================================");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                break exit;
            }
        }
    }

    private void viewProductsByCategory() {
        Scanner scan = new Scanner(System.in);

        exit: {
            while (true) {
                System.out.println("\nList of Warehouses");
                List<Category> categories = categoryService.getAllCategories();

                for (int i = 0; i < categories.size(); i++) {
                    System.out.println("[" + (i + 1) + "] " + categories.get(i).getName());
                }

                System.out.print("\nSelect a category to view products: ");
                int index = scan.nextInt() - 1;

                try {
                    Category selectedCategory = categories.get(index);

                    System.out.println("Name: " + selectedCategory.getName());

                    List<Product> products = productService.getAllProductsByCategoryId(selectedCategory.getId());

                    for (Product p : products) {
                        //String productImage = String.valueOf(emojiManager.getForAlias(p.getImage()).getUnicode());

                        System.out.println("\n=================================================================================================================================");

                        System.out.print("| ");
                        //System.out.print(productImage + " | ");
                        System.out.print("Name: " + p.getName() + " | ");
                        System.out.print("Description: " + p.getDescription() + " | ");
                        System.out.print("Brand: " + p.getBrand() + " | ");
                        System.out.print("Unit Price: " + p.getPrice() + " | ");
                        System.out.print("Qty in Stock: " + p.getQuantity() + " | ");
                        System.out.print("Category ID: " + p.getCategory_id() + " | ");
                        System.out.print("Warehouse ID: " + p.getWarehouse_id() + " |");
                    }

                    System.out.println("\n=================================================================================================================================");

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\nInvalid input!");
                }

                break exit;
            }
        }
    }

}
