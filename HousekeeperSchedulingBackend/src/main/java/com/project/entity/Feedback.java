package com.project.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Feedback {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedback_id;
   
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="rollnumber")
    private Student student;
   
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    private CleanRequest cleanRequest;
   
    @Column(length = 3000)
    private String feedback;
   
    public long getFeedback_id() {
        return feedback_id;
    }
    public void setFeedback_id(long feedback_id) {
        this.feedback_id = feedback_id;
    }
   
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public CleanRequest getCleanRequest() {
        return cleanRequest;
    }
    public void setCleanRequest(CleanRequest cleanRequest) {
        this.cleanRequest = cleanRequest;
    }
    public String getFeedback() {
        return feedback;
    }
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}