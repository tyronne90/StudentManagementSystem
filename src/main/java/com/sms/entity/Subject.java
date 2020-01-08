package com.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "subject")
public class Subject implements Serializable{
  @Id
  @Column(name = "subject_id", columnDefinition = "VARCHAR(8)", unique = true)
  private String subjectId;
  @Column(name = "subject_name", columnDefinition = "VARCHAR(40)")
  private String subjectName;
  @Column(name = "subject_type", columnDefinition = "VARCHAR(4)")
  private SubjectType subjectType;
  @Column(name = "creadit", columnDefinition = "INT(2)")
  private int creadit;
  @Column(name = "semi", columnDefinition = "INT(2)")
  private int semister;

  public String getSubjectId() {
    return subjectId;
  }
  public void setSubjectId(String subjectId) {
    this.subjectId = subjectId;
  }
  public String getSubjectName() {
    return subjectName;
  }
  public void setSubjectName(String subjectName) {
    this.subjectName = subjectName;
  }
  public SubjectType getSubjectType() {
    return subjectType;
  }
  public void setSubjectType(SubjectType subjectType) {
    this.subjectType = subjectType;
  }
  public int getCreadit() {
    return creadit;
  }
  public void setCreadit(int creadit) {
    this.creadit = creadit;
  }
  public int getSemister() {
    return semister;
  }
  public void setSemister(int semister) {
    this.semister = semister;
  }  
}
