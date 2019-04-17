package com.internship.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internship.demo.dao.UsersDao;
import com.internship.demo.model.UserModel;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	public static final String MESSAGE_ERROR = "Tài khoản mật khẩu không đúng";
	@Autowired
	UsersDao usersDao;
	

	@GetMapping(path = "/login")
	public String redirectUsersPage(Model model) {
	
		UserModel userModel = usersDao.findUserByUsername("vinh");
		log.info(userModel.getRoleName());
		return "login";
	}
}
