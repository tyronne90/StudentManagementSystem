package com.sms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sms.entity.Student;
import com.sms.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
  private StudentService service;
  private static final int PAGESIZE = 10;
  private static final String SORTBY = "studentId";
  private static final String STATUS = "STATUS";
  private static final String MESSAGE = "MESSAGE";
  private static final String FAILED = "FAILED";
  private static final String SUCCESS = "SUCCESS";
  private static final String DENIED = "DENIED";
  private static final String SAVESUCCESS = "SAVED_SUCCESS";
  private static final String SAVEFAIL = "SAVED_FAILED";
  private static final String UPDATESUCCESS = "UPDATED_SUCCESS";
  private static final String UPDATEFAIL = "UPDATED_FAILED";
  private static final String ALREADYEXIST = "ID_ALREADY_EXIST";
  private static final String IDNOTFOUND = "ID_NOT_FOUND";
  private static final String DELETED = "DELETED";
  private static final String DUPLICATEENTRY = "DUPLICATE_ENTRIES";
  
  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }
  
  @GetMapping("/list")
  public List<Student> list() {
    return service.findAll();
  }
  
  @GetMapping("/students/{pageno}")
  public List<Student> listAll(@PathVariable("pageno") Integer pageNo) {
    return service.findAll(pageNo, PAGESIZE, SORTBY);
  }

  @GetMapping("/student/{courseid}")
  public Object findOne(@PathVariable("courseid") String studentId) {
    if (service.findOne(studentId) != null) {
      return service.findOne(studentId);
    } else {
      return "NO_RECORD_FOUND";
    }
  }

  @PostMapping("/student")
  public Map<String, Object> saveSubject(@RequestBody Student student) {
    Map<String, Object> response = new HashMap<>();
    try {
      Student obj = service.findOne(student.getStudentId());
      if(obj != null) {
        String id = obj.getStudentId();
        if (!student.getStudentId().equals(id)) {
          service.save(student);
          response.put(STATUS, SUCCESS);
          response.put(MESSAGE, SAVESUCCESS);
        } else {
          response.put(STATUS, SAVEFAIL);
          response.put(MESSAGE, ALREADYEXIST);
        }
      }
      else {
        service.save(student);
        response.put(STATUS, SUCCESS);
        response.put(MESSAGE, SAVESUCCESS);
      }
      return response;
    } catch (Exception ex) {
      response.put(STATUS, FAILED);
      response.put(MESSAGE, ex.getMessage());
      return response;
    }
  }

  @PostMapping("/students")
  public Map<String, Object> saveMany(@RequestBody List<Student> students) {
    Map<String, Object> response = new HashMap<>();
    try {
      List<Student> db = service.findAll();
      for (int i = 0; i < db.size(); i++) {
        for (int j = 0; j < students.size(); j++) {
          if (db.get(i).getStudentId().equals(students.get(j).getStudentId())) {
            students.remove(j);
          }
        }
      }
      if (students.isEmpty()) {
        response.put(MESSAGE, DUPLICATEENTRY);
        return response;
      } else {
        service.saveMany(students);
        response.put(STATUS, SUCCESS);
        response.put(MESSAGE, SAVESUCCESS);
        return response;
      }

    } catch (Exception ex) {
      response.put(STATUS, FAILED);
      response.put(MESSAGE, ex.getMessage());
      return response;
    }
  }

  @PutMapping("/student")
  public Map<String, Object> update(@RequestBody Student student) {
    Map<String, Object> response = new HashMap<>();
    try {
      Student obj = service.findOne(student.getStudentId());
      if (obj!=null){
        service.update(student);
        response.put(STATUS, SUCCESS);
        response.put(MESSAGE, UPDATESUCCESS);
      } else {
        response.put(STATUS, UPDATEFAIL);
        response.put(MESSAGE, IDNOTFOUND);
      }
      return response;
    } catch (Exception ex) {
      response.put(STATUS, FAILED);
      response.put(MESSAGE, ex.getMessage());
      return response;
    }
  }

  @DeleteMapping("/student/{studentid}")
  public Map<String, Object> deleteSubject(@PathVariable("studentid") String studentId){
    Map<String, Object> response = new HashMap<>();
    try {
      Student obj = service.findOne(studentId);
      Long id = obj.getId();
      
      if (id != null){
        service.delete(id);
        response.put(STATUS, SUCCESS);
        response.put(MESSAGE, DELETED);
      }else {
        response.put(STATUS, DENIED);
        response.put(MESSAGE, IDNOTFOUND);
      }
      return response;
      } catch (Exception ex) {
        response.put(STATUS, FAILED);
        response.put(MESSAGE, ex.getMessage());
        return response;
      }
  }
  
}
