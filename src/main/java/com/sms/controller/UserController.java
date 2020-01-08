package com.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sms.entity.Users;
import com.sms.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    super();
    this.userService = userService;
  }

  @GetMapping("/users/{pageNo}")
  public List<Users> listUsers(@PathVariable("pageNo") Integer pageNo) throws Throwable {
    try {
      return userService.listUsers(pageNo, 10, "username");
    } catch (Exception ex) {
      throw new NullPointerException("No datas");
    }
  }

  @PostMapping("/user")
  public Map<String, Object> saveUser(@RequestBody Users user) {
    Map<String, Object> response = new HashMap<>();
    try {
      userService.saveUsers(user);
      response.put("STATUS", "SUCCESS");
      response.put("MESSAGE", "SAVE_USER_SUCCESS");
      return response;
    } catch (Exception ex) {
      response.put("STATUS", "FAILED");
      response.put("MESSAGE", ex.getMessage());
      return response;
    }
  }

}
