package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;
    private String username;
    private String password;
    private String hostel;
    public int getAdmin_id() {
        return admin_id;
       
    }
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getHostel() {
        return hostel;
    }
    public void setHostel(String hostel) {
        this.hostel = hostel;
    }
    @Override
    public String toString() {
        return "Admin [admin_id=" + admin_id + ", username=" + username + ", password=" + password + ", hostel="
                + hostel + "]";
    }
   
   
   
}
 