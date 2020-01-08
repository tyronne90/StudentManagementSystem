package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.Course;

public interface CourseRepo extends JpaRepository<Course, String> {
  Course findByCourseId(String courseId);
}
