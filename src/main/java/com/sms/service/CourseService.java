package com.sms.service;

import java.util.List;
import com.sms.entity.Course;

public interface CourseService {
  public List<Course> findAll(Integer pageNo, Integer pageSize, String sortBy);
  public List<Course> findAll();
  public void saveMany(List<Course> courses);
  public void update(Course course);
  public void save(Course course);
  public Course findOne(String courseId);
  public void delete(String courseId);
}
