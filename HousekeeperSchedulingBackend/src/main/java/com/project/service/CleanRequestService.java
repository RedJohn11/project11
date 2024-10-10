package com.project.service;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CleanRequestRepo;
import com.project.entity.CleanRequest;
import com.project.entity.Student;
 
@Service
public class CleanRequestService {
 
    @Autowired
    private CleanRequestRepo cleanRequestRepo;
 
    public CleanRequest submitRequest(CleanRequest cleanRequest) {
       
        return this.cleanRequestRepo.saveAndFlush(cleanRequest);
    }
 
    public List<Integer> getRequestCount(long rollno) {
       
        return this.cleanRequestRepo.requestCount(rollno);
    }
 
    public List<CleanRequest> getRequestByRollnumber(Student student) {
        List<CleanRequest> findAllByOrderByDateDesc = this.cleanRequestRepo.findAllByOrderByDateDesc();
            List<CleanRequest> collect = findAllByOrderByDateDesc.stream().filter(item -> item.getStudent().equals(student)).collect(Collectors.toList());
            return collect;
            //return this.cleanRequestRepo.findAllByRollnumber(rollno);
    }
 
    public List<CleanRequest> getAllCleanRequest(String hostel) {
        // TODO Auto-generated method stub
         List<CleanRequest> findAllByOrderByDateDesc = this.cleanRequestRepo.findAllByOrderByDateDesc();
        return findAllByOrderByDateDesc.stream().filter(item -> item.getStudent().getHostel().equals(hostel)).collect(Collectors.toList());
    }
 
    public Integer getAllCleanRequestCount(String hostel) {
        // TODO Auto-generated method stub
        List<CleanRequest> findAll = this.cleanRequestRepo.findAll();
        List<CleanRequest> collect = findAll.stream().filter(item -> item.getStudent().getHostel().equals(hostel)).collect(Collectors.toList());
       
        return collect.size();
    }
 
    public CleanRequest getCleanRequestById(long id) {
        // TODO Auto-generated method stub
         Optional<CleanRequest> findById = this.cleanRequestRepo.findById(id);
         return findById.get();
    }
}