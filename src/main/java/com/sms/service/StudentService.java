package com.sms.service;

import java.util.List;
import com.sms.entity.Student;

public interface StudentService {
  public List<Student> findAll(Integer pageNo, Integer pageSize, String sortBy);
  public List<Student> findAll();
  public Student findOne(String studentId);
  public void delete(Long studentId);
  public void save(Student student);
  public void saveMany(List<Student> students);
  public void update(Student student);
}
