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
import com.sms.entity.Department;
import com.sms.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
  private DepartmentService service;
  private static final int PAGESIZE = 10;
  private static final String SORTBY = "depId";
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
  public DepartmentController(DepartmentService service) {
    this.service = service;
  }
  
  
  @GetMapping("/list")
  public List<Department> list() {
    return service.findAll();
  }
  
  @GetMapping("/departments/{pageno}")
  public List<Department> listAll(@PathVariable("pageno") Integer pageNo) {
    return service.findAll(pageNo, PAGESIZE, SORTBY);
  }

  @GetMapping("/department/{depid}")
  public Object findOne(@PathVariable("depid") String depId) {
    if (service.findOne(depId) != null) {
      return service.findOne(depId);
    } else {
      return "NO_RECORD_FOUND";
    }
  }

  @PostMapping("/department")
  public Map<String, Object> saveSubject(@RequestBody Department department) {
    Map<String, Object> response = new HashMap<>();
    try {
      Department obj = service.findOne(department.getDepId());
      if(obj != null) {
        String id = obj.getDepId();
        if (!department.getDepId().equals(id)) {
          service.save(department);
          response.put(STATUS, SUCCESS);
          response.put(MESSAGE, SAVESUCCESS);
        } else {
          response.put(STATUS, SAVEFAIL);
          response.put(MESSAGE, ALREADYEXIST);
        }
      }
      else {
        service.save(department);
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

  @PostMapping("/departments")
  public Map<String, Object> saveMany(@RequestBody List<Department> departments) {
    Map<String, Object> response = new HashMap<>();
    try {
      List<Department> db = service.findAll();
      for (int i = 0; i < db.size(); i++) {
        for (int j = 0; j < departments.size(); j++) {
          if (db.get(i).getDepId().equals(departments.get(j).getDepId())) {
            departments.remove(j);
          }
        }
      }
      if (departments.isEmpty()) {
        response.put(MESSAGE, DUPLICATEENTRY);
        return response;
      } else {
        service.saveMany(departments);
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

  @PutMapping("/department")
  public Map<String, Object> update(@RequestBody Department department) {
    Map<String, Object> response = new HashMap<>();
    try {
      Department obj = service.findOne(department.getDepId());
      if (obj!=null){
        service.update(department);
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

  @DeleteMapping("/department/{depid}")
  public Map<String, Object> deleteSubject(@PathVariable("depid") String depId){
    Map<String, Object> response = new HashMap<>();
    try {
      Department obj = service.findOne(depId);
      if (obj != null){
        service.delete(depId);
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
