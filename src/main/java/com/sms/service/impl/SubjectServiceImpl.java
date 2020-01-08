package com.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sms.entity.Subject;
import com.sms.repository.SubjectRepo;
import com.sms.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

  private SubjectRepo repo;

  @Autowired
  public SubjectServiceImpl(SubjectRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<Subject> findAll(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Subject> pagedResult = repo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }
  
  @Override
  public List<Subject> findAll() {
    return repo.findAll();
  }

  @Override
  public void save(Subject subject) {
   repo.save(subject);
  }

  @Override
  public void saveMany(List<Subject> subjects) {
    repo.saveAll(subjects);
  }

  @Override
  public Subject findOne(String subjectId) {
    return repo.findBySubjectId(subjectId);
  }

  @Override
  public void update(Subject subject) {
    repo.save(subject);
  }

  @Override
  public boolean delete(String subjectId) {
    repo.deleteById(subjectId);
    return false;
  }
}
