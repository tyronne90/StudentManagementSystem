package com.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sms.entity.Course;
import com.sms.repository.CourseRepo;
import com.sms.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
  
  private CourseRepo repo;
  
  @Autowired
  public CourseServiceImpl(CourseRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<Course> findAll(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Course> pagedResult = repo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public List<Course> findAll() {
    return repo.findAll();
  }

  @Override
  public void saveMany(List<Course> courses) {
    repo.saveAll(courses);
  }

  @Override
  public void update(Course course) {
    repo.save(course);
  }

  @Override
  public void save(Course course) {
    repo.save(course);
  }

  @Override
  public Course findOne(String courseId) {
    return repo.findByCourseId(courseId);
  }

  @Override
  public void delete(String courseId) {
    repo.deleteById(courseId);
  }

}
