package com.portfolio.ecommify.ui;

import com.portfolio.ecommify.daos.*;
import com.portfolio.ecommify.services.*;
import com.revature.ecommify.daos.*;
import com.portfolio.ecommify.models.User;
import com.revature.ecommify.services.*;
import com.portfolio.ecommify.utils.custom_exceptions.InvalidUserException;
import com.vdurmont.emoji.EmojiManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.UUID;

public class LoginMenu implements StartMenu {
    private final UserService userService;

    EmojiManager emojiManager;

    public LoginMenu(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void start() {

        String userInput = "";
        Scanner scan = new Scanner(System.in);


        exit:
        {
            while (true) {
                System.out.println("\nWelcome to Ecommify!");
                System.out.println("[1] Login");
                System.out.println("[2] Signup");
                System.out.println("[x] Close App!");

                System.out.print("\nEnter: ");
                userInput = scan.nextLine();

                switch (userInput) {
                    case "1":
                        login();
                        break;
                    case "2":
                        User user = signup();
                        userService.register(user);
                        //new MainMenu(user, new UserService(new UserDAO()), new RestaurantService(new RestaurantDAO()), new ReviewService(new ReviewDAO())).start();
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


    private void login() {
        String username = "";
        String password = "";
        String last_sign_in = "";
        Scanner scan = new Scanner(System.in);

        System.out.println("\nLogging in...");

        exit: {
            while (true) {
                System.out.print("\nEnter username: ");
                username = scan.nextLine();

                System.out.print("\nEnter password: ");
                password = scan.nextLine();

                try {
                    User user = userService.login(username, password);
                    if (user.getRole().equals("ADMIN")) new AdminDashboard(user, new UserService(new UserDAO()), new CategoryService(new CategoryDAO()), new WarehouseService(new WarehouseDAO()), new ProductService(new ProductDAO()), new ReviewService(new ReviewDAO()), new OrderService(new OrderDAO()), new LineItemService(new LineItemDAO())).start();
                    else new UserDashboard(user, new UserService(new UserDAO()), new CategoryService(new CategoryDAO()), new WarehouseService(new WarehouseDAO()), new ProductService(new ProductDAO()), new ReviewService(new ReviewDAO()), new OrderService(new OrderDAO()), new LineItemService(new LineItemDAO())).start();
                    break exit;
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private User signup() {
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

        User user;
        Scanner scan = new Scanner(System.in);

        System.out.println("\nCreating account...");

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
                                user = new User(UUID.randomUUID().toString(), first_name, last_name, username, password, email, phone, street_address, city, zip_code, country, avatar, role, last_sign_in,  created_at, updated_at);
                                //user = new User(UUID.randomUUID().toString(), username, password);
                                return user;
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

    private void checkUserInputAndWarn(String s){
        while(!s.matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")){
            Scanner scan = new Scanner(System.in);
            System.out.print("\nPlease enter a valid input: ");
            s = scan.nextLine().toLowerCase().trim();
        }
    }

    private void checkPhoneNumAndWarnUser(String s){
        while(!s.matches("(0/91)?[7-9][0-9]{9}")){
            Scanner scan = new Scanner(System.in);
            System.out.print("\nPlease enter a valid phone number: ");
            s = scan.nextLine().trim();
        }
    }

    private void checkZipcodeAndAndWarnUser(String s){
        while(!s.matches("^[0-9]{5}(?:-[0-9]{4})?$")){
            Scanner scan = new Scanner(System.in);
            System.out.print("\nPlease enter a valid zip code: ");
            s = scan.nextLine().trim();
        }
    }

    private void checkEmailAndWarnUser(String s){
        while(!s.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            Scanner scan = new Scanner(System.in);
            System.out.print("\nPlease enter a valid email: ");
            s = scan.nextLine().toLowerCase().trim();
        }
    }


}
