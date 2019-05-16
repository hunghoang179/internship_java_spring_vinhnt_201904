package com.internship.demo.controller.rest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.internship.demo.model.UserModel;

@RestController
@RequestMapping(path = "/api")
@SessionAttributes("user")
public class UserRestController {

  @GetMapping(path = "/session/user")
  public ResponseEntity<UserModel> getSessionUser(HttpServletRequest request) {
    UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
    if (sessionUser == null) {
      return new ResponseEntity<UserModel>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<UserModel>(sessionUser, HttpStatus.OK);
  }

}
