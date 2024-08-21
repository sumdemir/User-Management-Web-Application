package org.example.service;

import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = DatabaseOperations.provideSessionFactory();
        UserOperations userService = new UserOperations(sessionFactory);

        /*User newUser = new User();
        newUser.setUsername("sevvalkoc");
        newUser.setPassword("sevval123");
        newUser.setEmail("sevvalkoc@example.com");
        newUser.setBirthday(new Date());
        newUser.setSex((short) 0);
        newUser.setEnabled(true);

        userService.insertUser(newUser);

        newUser.setPassword("sevvalkoc456");
        userService.updateUser(newUser);*/

        List<User> users = userService.selectUser();
        for (User user : users) {
            System.out.println(user);
        }

        // userService.deleteUser("sumdemiir");

        users = userService.selectUser();
        for (User user : users) {
            System.out.println(user);
        }
        sessionFactory.close();
    }
}

