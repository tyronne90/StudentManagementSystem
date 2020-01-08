package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, String> {
  Department findByDepId(String depId);
}
