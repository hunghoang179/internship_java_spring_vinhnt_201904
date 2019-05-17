package com.internship.demo.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.internship.demo.dao.RoleDao;
import com.internship.demo.domain.Role;

@RestController
@RequestMapping(path = "/api")
public class RoleRestController {
  @Autowired
  RoleDao roleDao;

  @GetMapping(path = "/role")
  public ResponseEntity<List<Role>> getAllRole() {
    List<Role> listRole = roleDao.getListRole();
    if (listRole == null) {
      return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Role>>(listRole, HttpStatus.OK);
  }
}
