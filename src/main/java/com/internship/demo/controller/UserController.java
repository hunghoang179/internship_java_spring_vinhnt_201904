package com.internship.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.internship.demo.model.UserModel;
import com.internship.demo.service.UsersService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	
	 private static final Logger log = LoggerFactory.getLogger(UserController.class);
	 public static final String MESSAGE_ERROR = "Tài khoản mật khẩu không đúng";
	
	@Autowired
	UsersService usersService;
	
	/*
	 * @GetMapping(path = "/login") public String redirectUsersPage(Model model) {
	 * return "login"; }
	 * 
	 * @PostMapping(path = "/login") public String authenticateLogin(@RequestParam
	 * String username, @RequestParam String password, Model model) { boolean
	 * isValid = false; isValid = usersService.authenticateUser(username, password);
	 * if (isValid) { return "redirect:/home"; }
	 * model.addAttribute("errorLogin",MESSAGE_ERROR); return "login"; }
	 * 
	 * @PostMapping(path = "/register") public String createUser(@ModelAttribute
	 * UserModel userModel) { return ""; }
	 */
	
	
}
