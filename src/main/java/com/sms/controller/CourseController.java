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
import com.sms.entity.Course;
import com.sms.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
  private CourseService service;
  private static final int PAGESIZE = 10;
  private static final String SORTBY = "courseId";
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
  public CourseController(CourseService service) {
    this.service = service;
  }
  
  @GetMapping("/list")
  public List<Course> list() {
    return service.findAll();
  }
  
  @GetMapping("/courses/{pageno}")
  public List<Course> listAll(@PathVariable("pageno") Integer pageNo) {
    return service.findAll(pageNo, PAGESIZE, SORTBY);
  }

  @GetMapping("/course/{courseid}")
  public Object findOne(@PathVariable("courseid") String courseId) {
    if (service.findOne(courseId) != null) {
      return service.findOne(courseId);
    } else {
      return "NO_RECORD_FOUND";
    }
  }

  @PostMapping("/course")
  public Map<String, Object> saveSubject(@RequestBody Course course) {
    Map<String, Object> response = new HashMap<>();
    try {
      Course obj = service.findOne(course.getCourseId());
      if(obj != null) {
        String id = obj.getCourseId();
        if (!course.getCourseId().equals(id)) {
          service.save(course);
          response.put(STATUS, SUCCESS);
          response.put(MESSAGE, SAVESUCCESS);
        } else {
          response.put(STATUS, SAVEFAIL);
          response.put(MESSAGE, ALREADYEXIST);
        }
      }
      else {
        service.save(course);
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

  @PostMapping("/courses")
  public Map<String, Object> saveMany(@RequestBody List<Course> courses) {
    Map<String, Object> response = new HashMap<>();
    try {
      List<Course> db = service.findAll();
      for (int i = 0; i < db.size(); i++) {
        for (int j = 0; j < courses.size(); j++) {
          if (db.get(i).getCourseId().equals(courses.get(j).getCourseId())) {
            courses.remove(j);
          }
        }
      }
      if (courses.isEmpty()) {
        response.put(MESSAGE, DUPLICATEENTRY);
        return response;
      } else {
        service.saveMany(courses);
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

  @PutMapping("/course")
  public Map<String, Object> update(@RequestBody Course course) {
    Map<String, Object> response = new HashMap<>();
    try {
      Course obj = service.findOne(course.getCourseId());
      if (obj!=null){
        service.update(course);
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

  @DeleteMapping("/course/{courseid}")
  public Map<String, Object> deleteSubject(@PathVariable("courseid") String courseId){
    Map<String, Object> response = new HashMap<>();
    try {
      Course obj = service.findOne(courseId);
      if (obj != null){
        service.delete(courseId);
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
