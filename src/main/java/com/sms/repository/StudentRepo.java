package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
  Student findByStudentId(String studentId);
}
