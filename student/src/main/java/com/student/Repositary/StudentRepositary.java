package com.student.Repositary;


import org.springframework.data.jpa.repository.JpaRepository;

import com.student.model.Student;


public interface StudentRepositary extends JpaRepository<Student, Long> {
}
