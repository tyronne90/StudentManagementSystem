package com.sms.service;

import java.util.List;
import com.sms.entity.Faculty;

public interface FacultyService {
  public List<Faculty> findAll(Integer pageNo, Integer pageSize, String sortBy);
  public List<Faculty> findAll();
  public Faculty findOne(String facultyId);
  public void save(Faculty faculty);
  public void saveMany(List<Faculty> faculties);
  public void update(Faculty faculty);
  public void delete(String facultyId);
}
