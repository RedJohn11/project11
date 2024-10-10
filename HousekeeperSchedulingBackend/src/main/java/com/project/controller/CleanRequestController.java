package com.project.controller;
 
import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
 
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.CleanRequest;
import com.project.entity.Student;
import com.project.entity.Worker;
import com.project.service.CleanRequestService;
import com.project.service.StudentService;
import com.project.service.WorkerService;
 
@RestController
@CrossOrigin("*")
public class CleanRequestController {
 
    @Autowired
    private CleanRequestService cleanRequestService;
   
    @Autowired
    private StudentService studentService;
   
    @Autowired
    private WorkerService workerService;
   
    @PostMapping("/submitRequest/{rollno}")
    public CleanRequest submitRequest(@PathVariable("rollno") long rollno,@RequestBody CleanRequest cleanRequest) {
       
         Student student = this.studentService.getStudent(rollno);
         cleanRequest.setStudent(student);
         
         CleanRequest submitRequest = this.cleanRequestService.submitRequest(cleanRequest);
 
         return submitRequest;
   
    }
   
    @GetMapping("/requestCount/{rollno}")
    public List<Integer> getRequestCount(@PathVariable("rollno") long rollno){
        return this.cleanRequestService.getRequestCount(rollno);
    }
   
    @GetMapping("/getCleanRequestByRollnumber/{rollno}")
    public List<CleanRequest> getCleanRequestByRollnumber(@PathVariable("rollno") long rollno){
        Student student = this.studentService.getStudent(rollno);
        return this.cleanRequestService.getRequestByRollnumber(student);
    }
   
    @GetMapping("/getAllCleanRequestByHostel/{hostel}")
    public List<CleanRequest> getAllCleanRequest(@PathVariable("hostel") String hostel){
    return this.cleanRequestService.getAllCleanRequest(hostel);
    }
   
    @GetMapping("/getAllCleanRequestCountByHostel/{hostel}")
    public Integer getAllCleanRequestCount(@PathVariable("hostel") String hostel) {
        return this.cleanRequestService.getAllCleanRequestCount(hostel);
    }
   
    @GetMapping("/getRequestById/{id}")
    public CleanRequest getCleanRequestById(@PathVariable("id") int id) {
        return this.cleanRequestService.getCleanRequestById(id);
    }
   
   
    @GetMapping("/allotHouseKeeper/{cleanRequestId}/{workerId}")
    public CleanRequest allotHouseKeeper(@PathVariable("cleanRequestId") int cleanRequestId,@PathVariable("workerId") int workerId) {
       
        CleanRequest cleanRequestById = this.cleanRequestService.getCleanRequestById(cleanRequestId);
        Worker workerById = this.workerService.getWorkerById(workerId);
        cleanRequestById.setWorker(workerById);
        cleanRequestById.setReq_status(true);
        return this.cleanRequestService.submitRequest(cleanRequestById);
       
    }
}