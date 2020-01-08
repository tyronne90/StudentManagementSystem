package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.Subject;

public interface SubjectRepo extends JpaRepository<Subject, String> {
  Subject findBySubjectId(String subjectId);
}
