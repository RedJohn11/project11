package com.example.test.FeedbackService;

import com.project.dao.FeedbackRepo;
import com.project.entity.Feedback;
import com.project.entity.Student;
import com.project.service.FeedbackService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeedbackServiceTest {

    @InjectMocks
    private FeedbackService feedbackService;

    @Mock
    private FeedbackRepo feedbackRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void submitFeedback_ShouldSaveAndFlushFeedback() {
        Feedback feedback = new Feedback();
        when(feedbackRepo.saveAndFlush(feedback)).thenReturn(feedback);

        Feedback result = feedbackService.submitFeedback(feedback);

        verify(feedbackRepo, times(1)).saveAndFlush(feedback);
        assertEquals(feedback, result);
    }

    @Test
    void getAllFeedback_ShouldReturnFeedbackCountForHostel() {
        String hostel = "HostelA";
        Student student1 = new Student();
        student1.setHostel(hostel);
        Feedback feedback1 = new Feedback();
        feedback1.setStudent(student1);
        Student student2 = new Student();
        student2.setHostel("HostelB");
        Feedback feedback2 = new Feedback();
        feedback2.setStudent(student2);
        List<Feedback> allFeedbacks = Arrays.asList(feedback1, feedback2);
        when(feedbackRepo.findAll()).thenReturn(allFeedbacks);

        Integer result = feedbackService.getAllFeedback(hostel);

        verify(feedbackRepo, times(1)).findAll();
        assertEquals(1, result);
    }

    @Test
    void getFeedbackCountByRollnumber_ShouldReturnCountByStudent() {
        Student student = new Student();
        Feedback feedback1 = new Feedback();
        feedback1.setStudent(student);
        Feedback feedback2 = new Feedback();
        feedback2.setStudent(student);
        List<Feedback> feedbacks = Arrays.asList(feedback1, feedback2);
        when(feedbackRepo.findByStudent(student)).thenReturn(feedbacks);

        Integer result = feedbackService.getFeedbackCountByRollnumber(student);

        verify(feedbackRepo, times(1)).findByStudent(student);
        assertEquals(2, result);
    }

    @Test
    void getAllFeedbackByStudent_ShouldReturnFeedbacksForHostel() {
        String hostel = "HostelA";
        Student student1 = new Student();
        student1.setHostel(hostel);
        Feedback feedback1 = new Feedback();
        feedback1.setStudent(student1);
        Student student2 = new Student();
        student2.setHostel("HostelB");
        Feedback feedback2 = new Feedback();
        feedback2.setStudent(student2);
        List<Feedback> allFeedbacks = Arrays.asList(feedback1, feedback2);
        when(feedbackRepo.findAll()).thenReturn(allFeedbacks);

        List<Feedback> result = feedbackService.getAllFeedbackByStudent(hostel);

        verify(feedbackRepo, times(1)).findAll();
        assertTrue(result.contains(feedback1));
        assertFalse(result.contains(feedback2));
    }
}
