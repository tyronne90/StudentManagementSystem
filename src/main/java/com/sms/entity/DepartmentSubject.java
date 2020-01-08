package com.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dep_subject")
public class DepartmentSubject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "department", nullable = false)
  private Department department;
  @ManyToOne
  @JoinColumn(name = "lecturer", nullable = false)
  private Lecturer lecturer;
  @ManyToOne
  @JoinColumn(name = "course", nullable = false)
  private Course course;
  @ManyToOne
  @JoinColumn(name = "subject", nullable = false)
  private Subject subject;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Department getDepartment() {
    return department;
  }
  public void setDepartment(Department department) {
    this.department = department;
  }
  public Lecturer getLecturer() {
    return lecturer;
  }
  public void setLecturer(Lecturer lecturer) {
    this.lecturer = lecturer;
  }
  public Course getCourse() {
    return course;
  }
  public void setCourse(Course course) {
    this.course = course;
  }
  public Subject getSubject() {
    return subject;
  }
  public void setSubject(Subject subject) {
    this.subject = subject;
  }
  
  
}
