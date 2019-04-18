package com.internship.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;
import com.internship.demo.utils.StringUtils;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UsersDao usersDao;


	@PostMapping(path = "/register")
	public String insertUsers(@ModelAttribute Users users, Model model) {
		if (!StringUtils.validateEmail(users.getMail())) {
			model.addAttribute("errorMail", "Mail không đúng định dạng");
			return "register";
		}
		
		if (!StringUtils.isNumeric(users.getPhone())) {
			model.addAttribute("errorPhone", "Điện thoại không đúng định dạng");
			return "register";
		}
		
		int result = usersDao.insertUser(users);
		if (result == 0) {
			model.addAttribute("errorMessage", "tài khoản hoặc mail đã tồn tại");
			return "register";
		}
		return "login";
	}
}
