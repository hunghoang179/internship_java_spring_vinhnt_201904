package com.internship.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.internship.demo.domain.Users;
import com.internship.demo.service.UsersService;

@Controller
@RequestMapping(path = "/")
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UsersService usersService;
	
	@GetMapping(path = "/register")
	public String redirectHomePage() {
		Users users = usersService.findUser("vinh");
		log.info(users.getPassword());
		return "home";
	}
}
