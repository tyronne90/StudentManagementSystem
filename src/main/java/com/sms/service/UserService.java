package com.sms.service;

import java.util.List;
import com.sms.entity.Users;

public interface UserService {
  public List<Users> listUsers(Integer pageNo, Integer pageSize, String sortBy);
  public void saveUsers(Users user);
}
