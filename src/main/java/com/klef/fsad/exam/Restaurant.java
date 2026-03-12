package com.klef.fsad.exam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private int rid;
    
    @Column(name = "rname", length = 50, nullable = false)
    private String rname;
    
    @Column(name = "location", length = 100)
    private String location;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "rdate")
    private Date rdate;
    
    @Column(name = "status", length = 20)
    private String status;
    
    @Column(name = "contact", length = 15)
    private String contact;
    
    @Column(name = "email", length = 50)
    private String email;
    
    @Column(name = "cuisine_type", length = 50)
    private String cuisineType;
    
    @Column(name = "rating")
    private double rating;
    
    // Constructors
    public Restaurant() {
        super();
    }
    
    public Restaurant(int rid, String rname, String location, Date rdate, String status) {
        super();
        this.rid = rid;
        this.rname = rname;
        this.location = location;
        this.rdate = rdate;
        this.status = status;
    }
    
    public Restaurant(String rname, String location, Date rdate, String status, 
                      String contact, String email, String cuisineType, double rating) {
        this.rname = rname;
        this.location = location;
        this.rdate = rdate;
        this.status = status;
        this.contact = contact;
        this.email = email;
        this.cuisineType = cuisineType;
        this.rating = rating;
    }
    
    // Getters and Setters
    public int getRid() {
        return rid;
    }
    
    public void setRid(int rid) {
        this.rid = rid;
    }
    
    public String getRname() {
        return rname;
    }
    
    public void setRname(String rname) {
        this.rname = rname;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Date getRdate() {
        return rdate;
    }
    
    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCuisineType() {
        return cuisineType;
    }
    
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return "Restaurant [rid=" + rid + ", rname=" + rname + ", location=" + location 
               + ", rdate=" + rdate + ", status=" + status + ", contact=" + contact 
               + ", email=" + email + ", cuisineType=" + cuisineType + ", rating=" + rating + "]";
    }
}
