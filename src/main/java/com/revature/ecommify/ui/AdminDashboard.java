package com.revature.ecommify.ui;

import com.revature.ecommify.models.*;
import com.revature.ecommify.services.*;
import com.revature.ecommify.utils.custom_exceptions.InvalidUserException;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                System.out.println("[1] Add New user");
                System.out.println("[2] View Users and Orders");
                System.out.println("[3] View and Update Products Inventory");
                System.out.println("[4] View Products by Warehouse");
                System.out.println("[5] View Products by Category");
                //System.out.println("[5] View Products by Brand");
                //System.out.println("[7] Add New Product");
                //System.out.println("[8] Add New Warehouse");
                //System.out.println("[9] Add New Category");
                System.out.println("[x] Signout!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        addNewUser();
                        break;
                    case "2":
                        viewAllUsers();
                        break;
                    case "3":
                        viewAllInventories();
                        break;
                    case "4":
                        viewProductsByWarehouse();
                        break;
                    case "5":
                        viewProductsByCategory();
                        //viewInventoryByBrand();
                        break;
//                    case "6":
//                        //addNewProduct();
//                        break;
//                    case "7":
//                        //addNewUser();
//                        break;
//                    case "8":
//                        //addNewUser();
//                        break;
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
                    System.out.println("[" + (i + 1) + "] " + users.get(i).getFirst_name() + " " + users.get(i).getLast_name());
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


                    //display customer pending order history
                    viewPendingOrders(selectedUser.getId());

                    //display customer completed order history
                    viewCompletedOrders(selectedUser.getId());

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

                //listprodutsExit:
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


                    System.out.println("\nEnter a quantity to update product Qty: ");

                    //update qty in list and then pass to db
                    int newQty = scan.nextInt();
                    if (newQty <= 0){
                        System.out.println("\n Not allow! Qty must be greated than zero.");
                    } else {
                        System.out.println("\n Update successful!");
                        int previousQty = selectedProduct.getQuantity();
                        int updateQty = previousQty + newQty;
                        selectedProduct.setQuantity(updateQty);
                        productService.updateProductQtyById(selectedProduct);
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

    private User addNewUser() {
            //String id;
            String first_name="";
            String last_name="";
            String username="";
            String password;
            String password2 = "";
            String email ="";
            String phone="";
            String street_address="";
            String city="";
            String zip_code="";
            String country="";
            String avatar="";
            String role="";

            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String last_sign_in  = LocalDateTime.now().format(formatDate);
            String created_at = LocalDateTime.now().format(formatDate);;
            String updated_at  = LocalDateTime.now().format(formatDate);;

            User newUser;
            Scanner scan = new Scanner(System.in);

            System.out.println("\nCreating Customer account...");

            exit:
            {

                while (true) {
                    firstnameExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a first name: ");
                            first_name = scan.nextLine().toLowerCase().trim();
                            //checkUserInputAndWarn(first_name);
                            //userService.isValidUserInput(first_name);
                            try {
                                userService.isValidUserInput(first_name);
                                break firstnameExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    lastnameExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a last name: ");
                            last_name = scan.nextLine().toLowerCase().trim();
                            //checkUserInputAndWarn(last_name);
                            //userService.isValidUserInput(last_name);

                            try {
                                userService.isValidUserInput(last_name);
                                break lastnameExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    usernameExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a username: ");
                            username = scan.nextLine();
                            try {
                                userService.isValidUsername(username);
                                userService.isDuplicateUsername(username);
                                break usernameExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    emailExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a email: ");
                            email = scan.nextLine().toLowerCase().trim();
                            //checkEmailAndWarnUser(email);
                            //userService.isValidEmailAddress(email);
                            try {
                                userService.isValidEmailAddress(email);
                                break emailExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    phonenumExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a phone: ");
                            phone = scan.nextLine().trim();
                            //checkPhoneNumAndWarnUser(phone);
                            //userService.isValidPhoneNumber(phone);
                            try {
                                userService.isValidPhoneNumber(phone);
                                break phonenumExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    streetaddressExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a street address: ");
                            street_address = scan.nextLine().trim();

                            try {
                                userService.isValidStreetAddress(street_address);
                                break streetaddressExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    cityExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a city: ");
                            city = scan.nextLine().trim();
                            //checkUserInputAndWarn(city);
                            //userService.isValidUserInput(city);

                            try {
                                userService.isValidUserInput(city);
                                break cityExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    zipExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a zipcode: ");
                            zip_code = scan.nextLine().trim();
                            //checkZipcodeAndAndWarnUser(zip_code);
                            //userService.isValidZipCode(zip_code);

                            try {
                                userService.isValidZipCode(zip_code);
                                break zipExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    countryExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a country: ");
                            country = scan.nextLine().toLowerCase().trim();
                            //checkUserInputAndWarn(country);
                            //userService.isValidUserInput(country);

                            try {
                                userService.isValidUserInput(country);
                                break countryExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    avatarExit:
                    {
                        while (true){
                            System.out.print("\nSelect profile avatar (M/F): ");
                            avatar = scan.nextLine().toLowerCase().trim();
                            for (int i = 0; i < 2; i++){
                                if (avatar.equals("m")){
                                    avatar = "bust_in_silhouette";
                                }else if (avatar.equals("f")){
                                    avatar = "bust_in_silhouette";
                                }else {
                                    avatar = "bust_in_silhouette";
                                }

                                try {
                                    userService.isValidUserInput(country);
                                    break avatarExit;
                                } catch (InvalidUserException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    }

                    roleExit:
                    {
                        while (true) {
                            System.out.print("\nEnter a role: ");
                            role = scan.nextLine().trim();
                            //checkUserInputAndWarn(role);
                            try {
                                userService.isValidUserInput(country);
                                break roleExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    passwordExit:
                    {
                        while (true) {
                            try {
                                System.out.print("\nEnter a password: ");
                                password = scan.nextLine();

                                userService.isValidPassword(password);

                                System.out.print("\nRe enter password: ");
                                password2 = scan.nextLine();

                                userService.isSamePassword(password, password2);
                                break passwordExit;
                            } catch (InvalidUserException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                confirmExit: {
                    while (true) {
                        System.out.println("\nIs this correct (y/n):");
                        System.out.println("Username: " + username + "\nPassword: " + password);
                        System.out.print("\nEnter: ");

                        switch (scan.nextLine().toLowerCase()) {
                            case "y":
                                newUser = new User(UUID.randomUUID().toString(), first_name, last_name, username, password, email, phone, street_address, city, zip_code, country, avatar, role, last_sign_in,  created_at, updated_at);
                                userService.userDAO.save(newUser);
                                return newUser;
                            case "n":
                                System.out.println("\nRestarting...");
                                break confirmExit;
                            default:
                                System.out.println("\nInvalid input!");
                                break;
                        }
                    }
                }
            }
        }
    }

    private void viewPendingOrders(String customerId){
        List<Order> orders = orderService.getAllOrdersByUserId(customerId);

        //System.out.println(user.getAvatar() + " " + user.getUsername() + " " + user.getLast_name());
        System.out.println("\nCart Items: ");

        String orderStatus = "pending";
        int counter =0;

        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().toLowerCase().equals(orderStatus)){
                System.out.println("[" + (counter + 1) + "] "  + "| Date: " + orders.get(i).getCreated_at() + " | Order ID: " + orders.get(i).getId() + " | Product ID: " + orders.get(i).getProduct_id() + " | ");
                counter++;
            }
        }
    }
    private void viewCompletedOrders(String customerId){
        List<Order> orders = orderService.getAllOrdersByUserId(customerId);
        //System.out.println(user.getAvatar() + " " + user.getUsername() + " " + user.getLast_name());
        System.out.println("\nOrder History: ");

        String orderStatus = "completed";
        int counter =0;

        for (int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getStatus().toLowerCase().equals(orderStatus)){
                System.out.println("[" + (counter + 1) + "] "  + "| Date: " + orders.get(i).getCreated_at() + " | Order ID: " + orders.get(i).getId() + " | Product ID: " + orders.get(i).getProduct_id() + " | ");
                counter++;
            }
        }
    }

}
