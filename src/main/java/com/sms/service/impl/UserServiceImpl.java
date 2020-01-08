package com.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sms.entity.Users;
import com.sms.repository.UserRepo;
import com.sms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
  private UserRepo userRepo;
  
  @Autowired
  public UserServiceImpl(UserRepo userRepo) {
    super();
    this.userRepo = userRepo;
  }

  @Override
  public List<Users> listUsers(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Users> pagedResult = userRepo.findAll(paging);

    if (pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }

  @Override
  public void saveUsers(Users user) {
    userRepo.save(user);

  }

}
