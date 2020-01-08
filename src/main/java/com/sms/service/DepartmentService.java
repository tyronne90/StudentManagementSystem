package com.sms.service;

import java.util.List;
import com.sms.entity.Department;

public interface DepartmentService {
  public List<Department> findAll(Integer pageNo, Integer pageSize, String sortBy);
  public List<Department> findAll();
  public void save(Department department);
  public void saveMany(List<Department> departments);
  public void update(Department department);
  public void delete(String depId);
  public Department findOne(String depId);
}
