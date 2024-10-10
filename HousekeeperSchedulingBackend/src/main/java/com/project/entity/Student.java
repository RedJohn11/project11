package com.project.entity;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
 

 
@Entity
public class Student {
 
    @Id
    private long rollnumber;
    private String password;
    private int floor;
    private String hostel;
    private String room;
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;
   
    //mappedBy = "student",
    @OneToMany( cascade =  { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    //@JsonManagedReference(value="student_request")
    private List<CleanRequest> request_id;
   
   
   
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    public List<CleanRequest> getRequest_id() {
        return request_id;
    }
    public void setRequest_id(List<CleanRequest> request_id) {
        this.request_id = request_id;
    }
    public String getRoom() {
        return room;
    }
    public void setRoom(String room) {
        this.room = room;
    }
   
    public long getRollnumber() {
        return rollnumber;
    }
    public void setRollnumber(long rollnumber) {
        this.rollnumber = rollnumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public String getHostel() {
        return hostel;
    }
    public void setHostel(String hostel) {
        this.hostel = hostel;
    }
   
   
 
}