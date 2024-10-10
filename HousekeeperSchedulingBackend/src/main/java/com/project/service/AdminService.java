package com.project.service;
//package com.example.Service;

import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.AdminRepo;
import com.project.entity.Admin;
 
@Service
public class AdminService {
 
    @Autowired
    private AdminRepo adminRepo;
   
    public Admin saveAdmin(Admin admin) {
        return this.adminRepo.saveAndFlush(admin);
    }
   
    public List<Admin> getAllAdmin(){
        return this.adminRepo.findAll();
    }
 
   
 
    public Admin checkAdmin(Admin admin) {
        // TODO Auto-generated method stub
        List<Admin> findAll = this.adminRepo.findAll();
       
        for(Admin ad:findAll) {
            if(admin.getUsername().equals(ad.getUsername()) && admin.getPassword().equals(ad.getPassword()))
                return ad;
        }
        return null;
    }
 
    public Admin getAdminById(int id) {
        // TODO Auto-generated method stub
        Optional<Admin> findById = this.adminRepo.findById(id);
        return findById.get();
    }
}