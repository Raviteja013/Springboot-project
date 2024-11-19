package com.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.student.model.Student;
import com.student.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{rollNumber}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long rollNumber) {
        Optional<Student> student = studentService.getStudentById(rollNumber);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{rollNumber}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long rollNumber, @RequestBody Student studentDetails) {
        try {
            Student updatedStudent = studentService.updateStudent(rollNumber, studentDetails);
            return ResponseEntity.ok(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rollNumber}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long rollNumber) {
        studentService.deleteStudent(rollNumber);
        return ResponseEntity.noContent().build();
    }
}
