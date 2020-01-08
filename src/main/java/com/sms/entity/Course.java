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
@Table(name = "course")
public class Course implements Serializable{

  @Id
  @Column(name = "course_id", columnDefinition = "VARCHAR(8)", unique = true)
  private String courseId;
  @Column(name = "course_name", columnDefinition = "VARCHAR(255)")
  private String courseName;
  @ManyToOne
  @JoinColumn(name = "dep_id", columnDefinition = "VARCHAR(8)", nullable = false)
  private Department department;
 
  
  public String getCourseId() {
    return courseId;
  }
  public void setCourseId(String courseId) {
    this.courseId = courseId;
  }
  public String getCourseName() {
    return courseName;
  }
  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }
  public Department getDepartment() {
    return department;
  }
  public void setDepartment(Department department) {
    this.department = department;
  }
  
}
