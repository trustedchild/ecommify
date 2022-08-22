package com.revature.ecommify;

import com.revature.ecommify.daos.UserDAO;
import com.revature.ecommify.services.UserService;
import com.revature.ecommify.ui.LoginMenu;
import com.revature.ecommify.utils.database.ConnectionFactory;
import com.vdurmont.emoji.EmojiManager;

import java.sql.SQLException;

public class Main {


    public static void main(String[] args) {
        /* dependency injection */
        //UserDAO userDAO = new UserDAO();
        //UserService userService = new UserService(userDAO);
       //new LoginMenu(userService).start();

        //test db connection
        /*
        try {
            System.out.println(ConnectionFactory.getInstance().getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        new LoginMenu(new UserService(new UserDAO())).start();
    }
}
