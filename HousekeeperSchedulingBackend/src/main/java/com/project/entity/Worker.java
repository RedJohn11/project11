package com.project.entity;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

 
@Entity
public class Worker {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long worker_id;
    private String name;
    private int floor;
    private String hostel;
   
    @OneToMany(cascade = CascadeType.ALL )
//  @JsonBackReference(value="workercleanrequest")
    private List<CleanRequest> cleanRequest;
   
    public String getHostel() {
        return hostel;
    }
    public void setHostel(String hostel) {
        this.hostel = hostel;
    }
    public void setWorker_id(long worker_id) {
        this.worker_id = worker_id;
    }
    public List<CleanRequest> getCleanRequest() {
        return cleanRequest;
    }
    public void setCleanRequest(List<CleanRequest> cleanRequest) {
        this.cleanRequest = cleanRequest;
    }
    public long getWorker_id() {
        return worker_id;
    }
   
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
   
   
}