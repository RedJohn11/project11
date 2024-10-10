package com.project.controller;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.PasswordChange;
import com.project.entity.Student;
import com.project.service.StudentService;
 
@RestController
@CrossOrigin("*")
public class StudentController {
 
    @Autowired
    private StudentService studentService;
   
    @PostMapping("/saveStudent")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);
        if (savedStudent != null) {
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
   
    @PostMapping("/saveStudent/{hostel}")
    public Student saveStudent(@RequestBody Student student,@PathVariable("hostel") String hostel) {
        student.setHostel(hostel);
        student.setPassword(String.valueOf(student.getRollnumber()));
        return this.studentService.saveStudent(student);
    }
   
    @GetMapping("/getStudent/{rollno}")
    public Student getStudent(@PathVariable("rollno") long rollnumber) {
        return this.studentService.getStudent(rollnumber);  
    }
   
   
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent(){
        return this.studentService.getStudent();
    }
   
    @PostMapping("/checkUserLogin")
    public ResponseEntity<Student> checkStudent(@RequestBody Student student){
       
        Student checkStudent = this.studentService.checkStudent(student);
        if(checkStudent!=null) {
            return ResponseEntity.of(Optional.of(checkStudent));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
   
    @PutMapping("/changePasswordOfStudent/{rollnumber}")
    public ResponseEntity<Student> changePasswordOfStudent(@RequestBody PasswordChange student,@PathVariable("rollnumber") long rollnumber){
        Student student2 = this.studentService.getStudent(rollnumber);
       
        if(student2.getPassword().equals(student.getCurrentPassword())) {
            student2.setPassword(student.getNewPassword());
            this.studentService.saveStudent(student2);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("/updateProfile/{rollnumber}")
    public ResponseEntity<Student> updateProfile(@RequestBody Student updatedStudent, @PathVariable("rollnumber") long rollnumber) {
        Student existingStudent = this.studentService.getStudent(rollnumber);
 
        if (existingStudent != null) {
//            existingStudent.setHostel(updatedStudent.getHostel());
            existingStudent.setFloor(updatedStudent.getFloor());
            existingStudent.setRoom(updatedStudent.getRoom());
 
            Student updatedStudentEntity = this.studentService.saveStudent(existingStudent);
            return ResponseEntity.ok(updatedStudentEntity);
        }
 
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}