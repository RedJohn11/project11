package com.example.test.CleanRequestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.dao.CleanRequestRepo;
import com.project.entity.CleanRequest;
import com.project.entity.Student;
import com.project.service.CleanRequestService;

class CleanRequestServiceTest {

    @InjectMocks
    private CleanRequestService cleanRequestService;

    @Mock
    private CleanRequestRepo cleanRequestRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitRequest_ShouldSaveAndFlushCleanRequest() {
        CleanRequest request = new CleanRequest();
        when(cleanRequestRepo.saveAndFlush(request)).thenReturn(request);

        CleanRequest result = cleanRequestService.submitRequest(request);

        verify(cleanRequestRepo, times(1)).saveAndFlush(request);
        assertEquals(request, result);
    }

    @Test
    void getRequestCount_ShouldReturnRequestCount() {
        long rollno = 12345L;
        List<Integer> expectedCount = Arrays.asList(5);  // Mocked response
        when(cleanRequestRepo.requestCount(rollno)).thenReturn(expectedCount);

        List<Integer> result = cleanRequestService.getRequestCount(rollno);

        verify(cleanRequestRepo, times(1)).requestCount(rollno);
        assertEquals(expectedCount, result);
    }

    @Test
    void getRequestByRollnumber_ShouldReturnCleanRequestsForStudent() {
        Student student = new Student();  // Set up student with necessary properties
        CleanRequest request1 = new CleanRequest();
        request1.setStudent(student);
        CleanRequest request2 = new CleanRequest();
        request2.setStudent(new Student());  // Different student
        List<CleanRequest> allRequests = Arrays.asList(request1, request2);
        when(cleanRequestRepo.findAllByOrderByDateDesc()).thenReturn(allRequests);

        List<CleanRequest> result = cleanRequestService.getRequestByRollnumber(student);

        verify(cleanRequestRepo, times(1)).findAllByOrderByDateDesc();
        assertTrue(result.contains(request1));
        assertFalse(result.contains(request2));
    }

    @Test
    void getAllCleanRequest_ShouldReturnCleanRequestsForHostel() {
        String hostel = "HostelA";
        Student student1 = new Student();
        student1.setHostel(hostel);
        CleanRequest request1 = new CleanRequest();
        request1.setStudent(student1);
        Student student2 = new Student();
        student2.setHostel("HostelB");
        CleanRequest request2 = new CleanRequest();
        request2.setStudent(student2);
        List<CleanRequest> allRequests = Arrays.asList(request1, request2);
        when(cleanRequestRepo.findAllByOrderByDateDesc()).thenReturn(allRequests);

        List<CleanRequest> result = cleanRequestService.getAllCleanRequest(hostel);

        verify(cleanRequestRepo, times(1)).findAllByOrderByDateDesc();
        assertTrue(result.contains(request1));
        assertFalse(result.contains(request2));
    }

    @Test
    void getAllCleanRequestCount_ShouldReturnCountForHostel() {
        String hostel = "HostelA";
        Student student1 = new Student();
        student1.setHostel(hostel);
        CleanRequest request1 = new CleanRequest();
        request1.setStudent(student1);
        Student student2 = new Student();
        student2.setHostel("HostelB");
        CleanRequest request2 = new CleanRequest();
        request2.setStudent(student2);
        List<CleanRequest> allRequests = Arrays.asList(request1, request2);
        when(cleanRequestRepo.findAll()).thenReturn(allRequests);

        Integer result = cleanRequestService.getAllCleanRequestCount(hostel);

        verify(cleanRequestRepo, times(1)).findAll();
        assertEquals(1, result);
    }

    @Test
    void getCleanRequestById_ShouldReturnCleanRequestById() {
        long id = 1L;
        CleanRequest request = new CleanRequest();
        when(cleanRequestRepo.findById(id)).thenReturn(Optional.of(request));

        CleanRequest result = cleanRequestService.getCleanRequestById(id);

        verify(cleanRequestRepo, times(1)).findById(id);
        assertEquals(request, result);
    }

    @Test
    void getCleanRequestById_ShouldThrowExceptionWhenNotFound() {
        long id = 1L;
        when(cleanRequestRepo.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cleanRequestService.getCleanRequestById(id);
        });

        assertEquals("CleanRequest not found with id: " + id, exception.getMessage());
    }
}
