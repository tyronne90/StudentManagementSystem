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
import com.sms.entity.Subject;
import com.sms.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
  private SubjectService service;

  private static final int PAGESIZE = 10;
  private static final String SORTBY = "subjectId";
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
  public SubjectController(SubjectService service) {
    this.service = service;
  }

  @GetMapping("/list")
  public List<Subject> list() {
    return service.findAll();
  }

  @GetMapping("/subjects/{pageno}")
  public List<Subject> listAll(@PathVariable("pageno") Integer pageNo) {
    return service.findAll(pageNo, PAGESIZE, SORTBY);
  }

  @GetMapping("/subject/{subjectid}")
  public Object findOne(@PathVariable("subjectid") String subjectId) {
    if (service.findOne(subjectId) != null) {
      return service.findOne(subjectId);
    } else {
      return "NO_RECORD_FOUND";
    }
  }

  @PostMapping("/subject")
  public Map<String, Object> save(@RequestBody Subject subject) {
    Map<String, Object> response = new HashMap<>();
    try {
      Subject obj = service.findOne(subject.getSubjectId());
      if(obj != null) {
        String id = obj.getSubjectId();
        if (!subject.getSubjectId().equals(id)) {
          service.save(subject);
          response.put(STATUS, SUCCESS);
          response.put(MESSAGE, SAVESUCCESS);
        } else {
          response.put(STATUS, SAVEFAIL);
          response.put(MESSAGE, ALREADYEXIST);
        }
      }
      else {
        service.save(subject);
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

  @PostMapping("/subjects")
  public Map<String, Object> saveMany(@RequestBody List<Subject> subjects) {
    Map<String, Object> response = new HashMap<>();
    try {
      List<Subject> db = service.findAll();
      for (int i = 0; i < db.size(); i++) {
        for (int j = 0; j < subjects.size(); j++) {
          if (db.get(i).getSubjectId().equals(subjects.get(j).getSubjectId())) {
            subjects.remove(j);
          }
        }
      }
      if (subjects.isEmpty()) {
        response.put(MESSAGE, DUPLICATEENTRY);
        return response;
      } else {
        service.saveMany(subjects);
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


  @PutMapping("/subject")
  public Map<String, Object> update(@RequestBody Subject subject) {
    Map<String, Object> response = new HashMap<>();
    try {
      Subject subObject = service.findOne(subject.getSubjectId());
      if (subObject != null) {
        service.save(subject);
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

  @DeleteMapping("/subject/{subjectid}")
  public Map<String, Object> delete(@PathVariable("subjectid") String subjectId) {
    Map<String, Object> response = new HashMap<>();
    try {
      Subject subObject = service.findOne(subjectId);
      if (subObject != null) {
        service.delete(subjectId);
        response.put(STATUS, SUCCESS);
        response.put(MESSAGE, DELETED);
      } else {
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
