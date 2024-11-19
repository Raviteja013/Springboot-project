package com.student.service;


import com.student.model.Student;

import com.student.Repositary.StudentRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepositary studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long rollNumber, Student studentDetails) {
        Optional<Student> studentOpt = studentRepository.findById(rollNumber);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setName(studentDetails.getName());
            student.setBranch(studentDetails.getBranch());
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found with rollNumber: " + rollNumber);
        }
    }

    public void deleteStudent(Long rollNumber) {
        studentRepository.deleteById(rollNumber);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long rollNumber) {
        return studentRepository.findById(rollNumber);
    }
}
