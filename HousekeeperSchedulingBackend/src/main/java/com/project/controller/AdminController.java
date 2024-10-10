package com.project.controller;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Admin;
import com.project.service.AdminService;
 
@RestController
@CrossOrigin("*")
public class AdminController {
 
    @Autowired
    private AdminService adminService;
 
    @PostMapping("/saveAdmin")
    public ResponseEntity<Admin> saveAdmin(@RequestBody Admin admin) {
        Admin savedAdmin = this.adminService.saveAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }
 
    @GetMapping("/getAdmin")
    public List<Admin> getAdmin() {
        return this.adminService.getAllAdmin();
    }
 
    @PostMapping("/checkAdminLogin")
    public ResponseEntity<Admin> checkAdmin(@RequestBody Admin admin) {
        Admin checkAdmin = this.adminService.checkAdmin(admin);
        if (checkAdmin != null) {
            return ResponseEntity.ok(checkAdmin);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
 
    @GetMapping("/getAdminById/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") int id) {
        Admin admin = this.adminService.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}