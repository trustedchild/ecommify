package com.portfolio.ecommify;

import com.portfolio.ecommify.utils.database.ConnectionFactory;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        /* dependency injection */
        //UserDAO userDAO = new UserDAO();
        //UserService userService = new UserService(userDAO);
       //new LoginMenu(userService).start();

     //test db connection

        try {
            System.out.println(ConnectionFactory.getInstance().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //new LoginMenu(new UserService(new UserDAO())).start();
    }
}
