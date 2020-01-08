package com.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "department")
public class Department implements Serializable{
  @Id
  @Column(name = "dep_id", columnDefinition = "VARCHAR(8)", unique = true)
  private String depId;
  @Column(name = "dep_name", columnDefinition = "VARCHAR(255)")
  private String depName;
  @ManyToOne
  @JoinColumn(name = "faculty_id", columnDefinition = "VARCHAR(8)", nullable = false)
  private Faculty faculty;

  public String getDepId() {
    return depId;
  }
  public void setDepId(String depId) {
    this.depId = depId;
  }
  public String getDepName() {
    return depName;
  }
  public void setDepName(String depName) {
    this.depName = depName;
  }
  public Faculty getFaculty() {
    return faculty;
  }
  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }
  
  
}
