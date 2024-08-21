package org.example.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DatabaseOperations {

    public static SessionFactory provideSessionFactory() {
        Configuration config = new Configuration();
        config.configure();
        return config.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        return provideSessionFactory();
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        /*public static Session getSession() {
            return sessionFactory.openSession();
        }*/

        try {
            session.beginTransaction();
            List<User> users = session.createQuery("FROM User", User.class).list();
            for (User user : users) {
                System.out.println("Database connected successfully.");
                System.out.println(user);
            }
            session.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        session.close();
        sessionFactory.close();

    }

}
