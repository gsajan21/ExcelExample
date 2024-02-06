package com.sajan.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    // Initialize the SessionFactory when the class is loaded
    static {
        try {
            // Load configuration and mappings
            Configuration configuration = new Configuration().configure();

            // Build the SessionFactory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Provide a method to get the current Session
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    // Provide a method to get a transaction
    public static Transaction beginTransaction(Session session) {
        return session.beginTransaction();
    }



    // Close the SessionFactory
    public static void closeSessionFactory() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}
