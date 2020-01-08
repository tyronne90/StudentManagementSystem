package com.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sms.entity.Faculty;
import com.sms.repository.FacultyRepo;
import com.sms.service.FacultyService;

@Service
public class FacultyServiceImpl implements FacultyService {

  private FacultyRepo repo;

  @Autowired
  public FacultyServiceImpl(FacultyRepo repo) {
    this.repo = repo;
  }

  @Override
  public List<Faculty> findAll(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Faculty> pagedResult = repo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public List<Faculty> findAll() {
    return repo.findAll();
  }

  @Override
  public Faculty findOne(String facultyId) {
    return repo.findByFacultyId(facultyId);
  }

  @Override
  public void save(Faculty faculty) {
    repo.save(faculty);
  }

  @Override
  public void saveMany(List<Faculty> faculties) {
    repo.saveAll(faculties);
  }

  @Override
  public void update(Faculty faculty) {
    repo.save(faculty);
  }

  @Override
  public void delete(String facultyId) {
    repo.deleteById(facultyId);
  }
}
