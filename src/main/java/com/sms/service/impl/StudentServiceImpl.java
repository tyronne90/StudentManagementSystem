package com.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sms.entity.Student;
import com.sms.repository.StudentRepo;
import com.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
  
  private StudentRepo repo;
  
  @Autowired
  public StudentServiceImpl(StudentRepo repo) {
    this.repo = repo;
  }
  

  @Override
  public List<Student> findAll(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Student> pagedResult = repo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public List<Student> findAll() {
    return repo.findAll();
  }

  @Override
  public Student findOne(String studentId) {
    return repo.findByStudentId(studentId);
  }

  @Override
  public void save(Student student) {
    repo.save(student);
  }

  @Override
  public void saveMany(List<Student> students) {
    repo.saveAll(students);
  }

  @Override
  public void update(Student student) {
    repo.save(student);
  }

  @Override
  public void delete(Long id) {
    repo.deleteById(id);
  }

}
