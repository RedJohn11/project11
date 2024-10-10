package com.project.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.FeedbackRepo;
import com.project.entity.Feedback;
import com.project.entity.Student;
 
@Service
public class FeedbackService {
 
    @Autowired
    private FeedbackRepo feedbackRepo;
 
    public Feedback submitFeedback(Feedback feedback) {
        // TODO Auto-generated method stub
        return this.feedbackRepo.saveAndFlush(feedback);
    }
 
    public Integer getAllFeedback(String hostel) {
        // TODO Auto-generated method stub
            List<Feedback> findAll = this.feedbackRepo.findAll();
            List<Feedback> collect = findAll.stream().filter(item -> item.getStudent().getHostel().equals(hostel)).collect(Collectors.toList());
            return collect.size();
    }
 
    public Integer getFeedbackCountByRollnumber(Student student) {
        // TODO Auto-generated method stub
         List<Feedback> findByStudent = this.feedbackRepo.findByStudent(student);
         return findByStudent.size();
    }
 
 
    public List<Feedback> getAllFeedbackByStudent(String hostel) {
        // TODO Auto-generated method stub
        List<Feedback> findAll = this.feedbackRepo.findAll();
        return findAll.stream().filter(item -> item.getStudent().getHostel().equals(hostel)).collect(Collectors.toList());
    }
   
   
}