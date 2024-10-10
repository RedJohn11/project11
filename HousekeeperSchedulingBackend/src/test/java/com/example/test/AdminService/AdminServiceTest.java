package com.example.test.AdminService;
 
import com.project.dao.AdminRepo;
import com.project.entity.Admin;
import com.project.service.AdminService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
 
public class AdminServiceTest {
 
    @Mock
    private AdminRepo adminRepo;
 
    @InjectMocks
    private AdminService adminService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testSaveAdmin() {
        Admin admin = new Admin();
        admin.setAdmin_id(1);
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setHostel("hostel");
 
        when(adminRepo.saveAndFlush(any(Admin.class))).thenReturn(admin);
 
        Admin result = adminService.saveAdmin(admin);
 
        assertEquals(1, result.getAdmin_id());
        assertEquals("admin", result.getUsername());
        assertEquals("password", result.getPassword());
        assertEquals("hostel", result.getHostel());
        verify(adminRepo).saveAndFlush(admin);
    }
 
    @Test
    void testGetAllAdmin() {
        Admin admin1 = new Admin();
        admin1.setUsername("admin1");
 
        Admin admin2 = new Admin();
        admin2.setUsername("admin2");
 
        when(adminRepo.findAll()).thenReturn(Arrays.asList(admin1, admin2));
 
        List<Admin> result = adminService.getAllAdmin();
 
        assertEquals(2, result.size());
        assertEquals("admin1", result.get(0).getUsername());
        assertEquals("admin2", result.get(1).getUsername());
        verify(adminRepo).findAll();
    }
 
    @Test
    void testCheckAdmin_Valid() {
        Admin inputAdmin = new Admin();
        inputAdmin.setUsername("admin");
        inputAdmin.setPassword("password");
 
        Admin existingAdmin = new Admin();
        existingAdmin.setUsername("admin");
        existingAdmin.setPassword("password");
 
        when(adminRepo.findAll()).thenReturn(Arrays.asList(existingAdmin));
 
        Admin result = adminService.checkAdmin(inputAdmin);
 
        assertEquals(existingAdmin, result);
        verify(adminRepo).findAll();
    }
 
    @Test
    void testCheckAdmin_Invalid() {
        Admin inputAdmin = new Admin();
        inputAdmin.setUsername("admin");
        inputAdmin.setPassword("wrongpassword");
 
        Admin existingAdmin = new Admin();
        existingAdmin.setUsername("admin");
        existingAdmin.setPassword("password");
 
        when(adminRepo.findAll()).thenReturn(Arrays.asList(existingAdmin));
 
        Admin result = adminService.checkAdmin(inputAdmin);
 
        assertNull(result);
        verify(adminRepo).findAll();
    }
 
    @Test
    void testGetAdminById() {
        Admin admin = new Admin();
        admin.setAdmin_id(1);
        admin.setUsername("admin");
 
        when(adminRepo.findById(1)).thenReturn(Optional.of(admin));
 
        Admin result = adminService.getAdminById(1);
 
        assertEquals("admin", result.getUsername());
        verify(adminRepo).findById(1);
    }
}