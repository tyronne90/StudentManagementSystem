package com.sms.service;

import java.util.List;
import com.sms.entity.Subject;

public interface SubjectService {
  public List<Subject> findAll(Integer pageNo, Integer pageSize, String sortBy);
  public List<Subject> findAll();
  public Subject findOne(String subjectId);
  public void save(Subject subject);
  public void saveMany(List<Subject> subjects);
  public void update(Subject subject);
  public boolean delete(String subjectId);
}
