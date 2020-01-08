package com.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sms.entity.Department;
import com.sms.repository.DepartmentRepo;
import com.sms.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

  private DepartmentRepo repo;

  @Autowired
  public DepartmentServiceImpl(DepartmentRepo repo) {
    this.repo = repo;
  }
  
  @Override
  public List<Department> findAll(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Department> pagedResult = repo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public List<Department> findAll() {
    return repo.findAll();
  }

  @Override
  public void save(Department department) {
    repo.save(department);
  }

  @Override
  public void saveMany(List<Department> departments) {
    repo.saveAll(departments);
  }

  @Override
  public void update(Department department) {
    repo.save(department);
  }

  @Override
  public void delete(String depId) {
    repo.deleteById(depId);
  }

  @Override
  public Department findOne(String depId) {
    return repo.findByDepId(depId);
  }

}
