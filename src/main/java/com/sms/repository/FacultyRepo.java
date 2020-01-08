package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.Faculty;

public interface FacultyRepo extends JpaRepository<Faculty, String> {
  Faculty findByFacultyId(String facultyId);
}
