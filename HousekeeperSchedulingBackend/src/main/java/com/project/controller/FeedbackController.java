package com.project.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.CleanRequest;
import com.project.entity.Feedback;
import com.project.entity.Student;
import com.project.service.CleanRequestService;
import com.project.service.FeedbackService;
import com.project.service.StudentService;
 
@RestController
@CrossOrigin("*")
public class FeedbackController {
 
    @Autowired
    private FeedbackService feedbackService;
   
    @Autowired
    private CleanRequestService cleanRequestService;
   
    @Autowired
    private StudentService studentService;
   
    @PostMapping("/submitFeedback/{request_id}")
    public Feedback submitFeedback(@RequestBody Feedback feedback,@PathVariable("request_id") int requestId) {
        CleanRequest cleanRequestById = this.cleanRequestService.getCleanRequestById(requestId);
            cleanRequestById.setFeedback_status(true);
            CleanRequest submitRequest = this.cleanRequestService.submitRequest(cleanRequestById);
        Student student = submitRequest.getStudent();
        feedback.setCleanRequest(submitRequest);
        feedback.setStudent(student);
        return this.feedbackService.submitFeedback(feedback) ;
    }
   
    @GetMapping("/getFeedbackCount/{hostel}")
    public Integer getFeedbackCount(@PathVariable("hostel") String hostel) {
        return this.feedbackService.getAllFeedback(hostel);
    }
   
    @GetMapping("/getFeedbackCountByRollnumber/{rollnumber}")
    public Integer getFeedbackCountByRollnumber(@PathVariable("rollnumber") long rollnumber) {
        Student student = this.studentService.getStudent(rollnumber);
       
        return this.feedbackService.getFeedbackCountByRollnumber(student);
    }
   
    @GetMapping("/getAllFeedbackByHostel/{hostel}")
    public List<Feedback> getAllFeedbackByHostel(@PathVariable("hostel") String hostel){
        return this.feedbackService.getAllFeedbackByStudent(hostel);
    }
}