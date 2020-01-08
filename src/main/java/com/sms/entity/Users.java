package com.sms.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
public class Users implements Serializable{
     
  @Id
  @Column(name = "username", columnDefinition = "VARCHAR(20)", unique = true)
  private String username;
  @Column(name = "password", columnDefinition = "VARCHAR(20)", nullable = false)
  private String password;
  @Column(name = "role", columnDefinition = "VARCHAR(8)", nullable = false)
  private Roles role;
  @NotNull
  @DateTimeFormat(iso=ISO.DATE)
  @Column(name = "created", columnDefinition = "DATE", updatable = false)
  private Date created = java.sql.Date.valueOf(LocalDate.now());
  @DateTimeFormat(iso=ISO.DATE)
  @Column(name = "modified", columnDefinition = "DATE",  nullable = true)
  private Date modified;
  
  public Users() {
  }
  
  
  
  public Users(String username, String password, Roles role, @NotNull Date created, Date modified) {
    super();
    this.username = username;
    this.password = password;
    this.role = role;
    this.created = created;
    this.modified = modified;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public Roles getRole() {
    return role;
  }
  public void setRole(Roles role) {
    this.role = role;
  }
  public Date getCreated() {
    return created;
  }
  public void setCreated(Date created) {
    this.created = created;
  }
  public Date getModified() {
    return modified;
  }
  public void setModified(Date modified) {
    this.modified = modified;
  }
  
  
}
