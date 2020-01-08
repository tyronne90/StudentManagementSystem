package com.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sms.entity.Users;

public interface UserRepo extends JpaRepository<Users, String> {

}
