package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ClientDemo {
    
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        
        try {
            // I. Insert records using a persistent object
            System.out.println("\n=== I. INSERTING RECORDS ===");
            insertRestaurants(factory);
            
            // II. Update fields using HQL with named parameters
            System.out.println("\n=== II. UPDATING RECORDS USING HQL ===");
            updateRestaurantStatus(factory);
            
            // III. Retrieve records using HQL queries
            System.out.println("\n=== III. RETRIEVING RECORDS USING HQL ===");
            retrieveAllRestaurants(factory);
            retrieveByStatus(factory);
            retrieveByName(factory);
            
        } finally {
            factory.close();
        }
    }
    
    // Method I: Insert records
    private static void insertRestaurants(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            // Insert first restaurant
            Restaurant r1 = new Restaurant();
            r1.setRname("Pizza Palace");
            r1.setLocation("Downtown")
            r1.setRdate(new Date());
            r1.setStatus("Active");
            r1.setContact("9876543210");
            r1.setEmail("pizzapalace@email.com");
            r1.setCuisineType("Italian");
            r1.setRating(4.5);
            session.save(r1);
            System.out.println("Restaurant 1 inserted: " + r1.getRname());
            
            // Insert second restaurant
            Restaurant r2 = new Restaurant();
            r2.setRname("Spice Kitchen");
            r2.setLocation("Mall Road");
            r2.setRdate(new Date());
            r2.setStatus("Active");
            r2.setContact("9876543211");
            r2.setEmail("spicekitchen@email.com");
            r2.setCuisineType("Indian");
            r2.setRating(4.2);
            session.save(r2);
            System.out.println("Restaurant 2 inserted: " + r2.getRname());
            
            // Insert third restaurant
            Restaurant r3 = new Restaurant();
            r3.setRname("Burger Barn");
            r3.setLocation("Highway");
            r3.setRdate(new Date());
            r3.setStatus("Inactive");
            r3.setContact("9876543212");
            r3.setEmail("burgerbarn@email.com");
            r3.setCuisineType("American");
            r3.setRating(3.8);
            session.save(r3);
            System.out.println("Restaurant 3 inserted: " + r3.getRname());
            
            transaction.commit();
            System.out.println("All records inserted successfully!");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    // Method II: Update using HQL with named parameters
    private static void updateRestaurantStatus(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            // Update status using HQL with named parameters
            String hql = "UPDATE Restaurant SET status = :newStatus WHERE rname = :rname";
            Query query = session.createQuery(hql);
            query.setParameter("newStatus", "Inactive");
            query.setParameter("rname", "Pizza Palace");
            
            int rowsUpdated = query.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            
            transaction.commit();
            System.out.println("Update successful!");
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    // Method III: Retrieve all restaurants
    private static void retrieveAllRestaurants(SessionFactory factory) {
        Session session = factory.openSession();
        
        try {
            String hql = "FROM Restaurant";
            Query<Restaurant> query = session.createQuery(hql, Restaurant.class);
            List<Restaurant> restaurants = query.getResultList();
            
            System.out.println("\nAll Restaurants:");
            for (Restaurant r : restaurants) {
                System.out.println("ID: " + r.getRid() + ", Name: " + r.getRname() 
                        + ", Status: " + r.getStatus() + ", Rating: " + r.getRating());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    // Retrieve by status
    private static void retrieveByStatus(SessionFactory factory) {
        Session session = factory.openSession();
        
        try {
            String hql = "FROM Restaurant WHERE status = :status";
            Query<Restaurant> query = session.createQuery(hql, Restaurant.class);
            query.setParameter("status", "Active");
            List<Restaurant> restaurants = query.getResultList();
            
            System.out.println("\nActive Restaurants:");
            for (Restaurant r : restaurants) {
                System.out.println("Name: " + r.getRname() + ", Cuisine: " + r.getCuisineType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    // Retrieve by name containing
    private static void retrieveByName(SessionFactory factory) {
        Session session = factory.openSession();
        
        try {
            String hql = "FROM Restaurant WHERE rname LIKE :name";
            Query<Restaurant> query = session.createQuery(hql, Restaurant.class);
            query.setParameter("name", "%Burger%");
            List<Restaurant> restaurants = query.getResultList();
            
            System.out.println("\nRestaurants with 'Burger' in name:");
            for (Restaurant r : restaurants) {
                System.out.println("Name: " + r.getRname() + ", Location: " + r.getLocation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
