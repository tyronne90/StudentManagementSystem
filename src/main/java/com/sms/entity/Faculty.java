package com.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "faculty")
public class Faculty implements Serializable{
  @Id
  @Column(name = "faculty_id", columnDefinition = "VARCHAR(8)", unique = true)
  private String facultyId;
  @Column(name = "faculty_name", columnDefinition = "VARCHAR(50)")
  private String facultyName;
  
  public String getFacultyId() {
    return facultyId;
  }
  public void setFacultyId(String facultyId) {
    this.facultyId = facultyId;
  }
  public String getFacultyName() {
    return facultyName;
  }
  public void setFacultyName(String facultyName) {
    this.facultyName = facultyName;
  }
  
}
