package com.sms.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "student")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "student_id", columnDefinition = "VARCHAR(20)", unique = true)
  private String studentId;
  @Column(name = "first_name", columnDefinition = "VARCHAR(20)")
  private String firstName;
  @Column(name = "last_name", columnDefinition = "VARCHAR(20)")
  private String lastName;
  @Pattern(regexp = "^([0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$")
  @Size(min=5, max=50)
  @Email(message = "Email should be valid and unique")
  @Column(name = "email", unique=true)
  private String email;
  @Column(name = "nic", columnDefinition = "VARCHAR(12)",  unique=true)
  private String nic;
  @Column(name = "passport", columnDefinition = "VARCHAR(20)", unique=true)
  private String passport;
  @DateTimeFormat(iso = ISO.DATE)
  @Column(name = "date_of_birth", columnDefinition = "DATE")
  private Date dateOfBirth;
  @Column(name = "gender", columnDefinition = "VARCHAR(1)")
  private char gender;
  @Column(name = "age", columnDefinition = "INT(2)")
  private int age;
  @Column(name = "batch", columnDefinition = "INT(4)")
  private int batch;
  @ManyToOne
  @JoinColumn(name = "course_id", columnDefinition = "VARCHAR(8)", nullable = false)
  private Course course;
  @DateTimeFormat(iso = ISO.DATE)
  @Column(name = "addedOn", columnDefinition = "DATE")
  private Date addedOn;
  @Column(name = "available", columnDefinition = "BOOLEAN")
  private boolean available;
  
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getStudentId() {
    return studentId;
  }
  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getNic() {
    return nic;
  }
  public void setNic(String nic) {
    this.nic = nic;
  }
  public String getPassport() {
    return passport;
  }
  public void setPassport(String passport) {
    this.passport = passport;
  }
  public Date getDateOfBirth() {
    return dateOfBirth;
  }
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  public char getGender() {
    return gender;
  }
  public void setGender(char gender) {
    this.gender = gender;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public int getBatch() {
    return batch;
  }
  public void setBatch(int batch) {
    this.batch = batch;
  }
  public Course getCourse() {
    return course;
  }
  public void setCourse(Course course) {
    this.course = course;
  }
  public Date getAddedOn() {
    return addedOn;
  }
  public void setAddedOn(Date addedOn) {
    this.addedOn = addedOn;
  }
  public boolean isAvailable() {
    return available;
  }
  public void setAvailable(boolean available) {
    this.available = available;
  }



}
