package com.example.test.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.controller.StudentController;
import com.project.dto.PasswordChange;
import com.project.entity.Student;
import com.project.service.StudentService;

class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveStudent_ShouldReturnStudent() {
        Student student = new Student();
        when(studentService.saveStudent(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.saveStudent(student);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void saveStudent_WithHostel_ShouldReturnStudent() {
        Student student = new Student();
        String hostel = "HostelA";
        when(studentService.saveStudent(student)).thenReturn(student);

        Student result = studentController.saveStudent(student, hostel);

        assertEquals(student, result);
        assertEquals(hostel, student.getHostel());
        assertEquals(String.valueOf(student.getRollnumber()), student.getPassword());
    }

    @Test
    void getStudent_ShouldReturnStudent() {
        long rollno = 123L;
        Student student = new Student();
        when(studentService.getStudent(rollno)).thenReturn(student);

        Student result = studentController.getStudent(rollno);

        assertEquals(student, result);
    }

    @Test
    void getAllStudent_ShouldReturnListOfStudents() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentService.getStudent()).thenReturn(students);

        List<Student> result = studentController.getAllStudent();

        assertEquals(students, result);
    }

    @Test
    void checkStudent_ShouldReturnStudent() {
        Student student = new Student();
        when(studentService.checkStudent(student)).thenReturn(student);

        ResponseEntity<Student> response = studentController.checkStudent(student);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
    }

    @Test
    void checkStudent_ShouldReturnNotFound() {
        Student student = new Student();
        when(studentService.checkStudent(student)).thenReturn(null);

        ResponseEntity<Student> response = studentController.checkStudent(student);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

//    @Test
//    void changePasswordOfStudent_ShouldReturnAccepted() {
//        long rollnumber = 123L;
//        PasswordChange passwordChange = new PasswordChange();
//        Student student = new Student();
//        student.setPassword(passwordChange.getCurrentPassword());
//        when(studentService.getStudent(rollnumber)).thenReturn(student);
//        when(studentService.saveStudent(student)).thenReturn(student);
//
//        ResponseEntity<Student> response = studentController.changePasswordOfStudent(passwordChange, rollnumber);
//
//        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
//    }

    @Test
    void changePasswordOfStudent_ShouldReturnNotFound() {
        long rollnumber = 123L;
        PasswordChange passwordChange = new PasswordChange();
        Student student = new Student();
        student.setPassword("wrongPassword");
        when(studentService.getStudent(rollnumber)).thenReturn(student);

        ResponseEntity<Student> response = studentController.changePasswordOfStudent(passwordChange, rollnumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateProfile_ShouldReturnUpdatedStudent() {
        long rollnumber = 123L;
        Student updatedStudent = new Student();
        Student existingStudent = new Student();
        existingStudent.setFloor(1);
        existingStudent.setRoom("101");
        when(studentService.getStudent(rollnumber)).thenReturn(existingStudent);
        when(studentService.saveStudent(existingStudent)).thenReturn(existingStudent);

        ResponseEntity<Student> response = studentController.updateProfile(updatedStudent, rollnumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingStudent, response.getBody());
    }

    @Test
    void updateProfile_ShouldReturnNotFound() {
        long rollnumber = 123L;
        Student updatedStudent = new Student();
        when(studentService.getStudent(rollnumber)).thenReturn(null);

        ResponseEntity<Student> response = studentController.updateProfile(updatedStudent, rollnumber);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
