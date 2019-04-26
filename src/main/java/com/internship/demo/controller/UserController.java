package com.internship.demo.controller;

import java.text.ParseException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;

@Controller
@RequestMapping(path = "/user")
public class UserController {

  @Autowired
  UsersDao usersDao;

  @PostMapping(path = "/register")
  public String insertUsers(@ModelAttribute("userRegister") @Valid Users users,
      BindingResult result, @RequestParam String rePassword, Model model) throws ParseException {

    if (result.hasErrors()) {
      return "register";
    }

    if (!users.getPassword().equals(rePassword)) {
      model.addAttribute("errorPassword", "Mật khẩu xác nhận không đúng");
      return "register";
    }

    int isValidUser = usersDao.insertUser(users);
    if (isValidUser == 0) { 
      model.addAttribute("errorMessage", "Tài khoản hoặc mail đã tồn tại");
      return "register";
    }
    return "login";
  }
}
