package com.internship.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.internship.demo.domain.Users;
import com.internship.demo.model.mapper.repository.UserRepository;
import com.internship.demo.service.UsersServiceImpl;

@Controller
@RequestMapping(path = "/ajax")
public class AjaxController {

  /*
   * @Autowired UsersServiceImpl usersServiceImpl;
   * 
   * @GetMapping(path = "/kiem-tra-user")
   * 
   * @ResponseBody public String checkUpdateUser(@ModelAttribute Users user) { List<Users> listUser
   * = new UserRepository().checkUpdateUser(user.getUsername(), user.getMail(), user.getId()+""); if
   * (!listUser.isEmpty()) { return "false"; } usersServiceImpl.updateUser(user); return "ok"; }
   */

}
