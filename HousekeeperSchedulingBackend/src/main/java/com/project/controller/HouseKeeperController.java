package com.project.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Worker;
import com.project.service.WorkerService;
 
@RestController
@CrossOrigin("*")
public class HouseKeeperController {
 
    @Autowired
    private WorkerService workerService;
 
    @PostMapping("/registerWorker/{hostel}")
    public Worker registerWorker(@RequestBody Worker worker, @PathVariable("hostel") String hostel) {
        worker.setHostel(hostel);
        return this.workerService.registerWorker(worker);
    }
 
    @GetMapping("/getHouseKeeperByFloor/{floor}/{hostel}")
    public List<Worker> getWorkerByFloor(@PathVariable("floor") int floor, @PathVariable("hostel") String hostel) {
        return this.workerService.getWorkerByFloor(floor, hostel);
    }
   
    @GetMapping("/getWorkerByNameAndFloor/{hostel}")
    public List<Worker> getWorkerByNameAndFloor(@PathVariable("hostel") String hostel) {
        return this.workerService.getWorkerByNameAndFloor(hostel);
    }
   
    @DeleteMapping("/deleteWorker/{workerId}")
    public ResponseEntity<String> deleteWorker(@PathVariable("workerId") Long workerId) {
        boolean deleted = workerService.deleteWorker(workerId);
        if (deleted) {
            return ResponseEntity.ok("Worker deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete worker");
        }
    }
}
 