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
import com.sms.entity.Faculty;
import com.sms.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
  
  private FacultyService service;
  private static final int PAGESIZE = 10;
  private static final String SORTBY = "facultyId";
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
  public FacultyController(FacultyService service) {
    this.service = service;
  }
  
  
  @GetMapping("/list")
  public List<Faculty> list() {
    return service.findAll();
  }
  
  @GetMapping("/faculties/{pageno}")
  public List<Faculty> listAll(@PathVariable("pageno") Integer pageNo) {
    return service.findAll(pageNo, PAGESIZE, SORTBY);
  }

  @GetMapping("/faculty/{facultyid}")
  public Object findOne(@PathVariable("facultyid") String facultyId) {
    if (service.findOne(facultyId) != null) {
      return service.findOne(facultyId);
    } else {
      return "NO_RECORD_FOUND";
    }
  }

  @PostMapping("/faculty")
  public Map<String, Object> saveSubject(@RequestBody Faculty faculty) {
    Map<String, Object> response = new HashMap<>();
    try {
      Faculty facObject = service.findOne(faculty.getFacultyId());
      if(facObject != null) {
        String id = facObject.getFacultyId();
        if (!faculty.getFacultyId().equals(id)) {
          service.save(faculty);
          response.put(STATUS, SUCCESS);
          response.put(MESSAGE, SAVESUCCESS);
        } else {
          response.put(STATUS, SAVEFAIL);
          response.put(MESSAGE, ALREADYEXIST);
        }
      }
      else {
        service.save(faculty);
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

  @PostMapping("/faculties")
  public Map<String, Object> saveMany(@RequestBody List<Faculty> faculties) {
    Map<String, Object> response = new HashMap<>();
    try {
      List<Faculty> db = service.findAll();
      for (int i = 0; i < db.size(); i++) {
        for (int j = 0; j < faculties.size(); j++) {
          if (db.get(i).getFacultyId().equals(faculties.get(j).getFacultyId())) {
            faculties.remove(j);
          }
        }
      }
      if (faculties.isEmpty()) {
        response.put(MESSAGE, DUPLICATEENTRY);
        return response;
      } else {
        service.saveMany(faculties);
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

  @PutMapping("/faculty")
  public Map<String, Object> update(@RequestBody Faculty faculty) {
    Map<String, Object> response = new HashMap<>();
    try {
      Faculty obj = service.findOne(faculty.getFacultyId());
      if (obj!=null){
        service.update(faculty);
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

  @DeleteMapping("/faculty/{facultyid}")
  public Map<String, Object> deleteSubject(@PathVariable("facultyid") String facultyId){
    Map<String, Object> response = new HashMap<>();
    try {
      Faculty obj = service.findOne(facultyId);
      if (obj != null){
        service.delete(facultyId);
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
