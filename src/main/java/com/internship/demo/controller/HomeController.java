package com.internship.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/home")
    public String index() {
		log.info("OK home");
        return "home";
    }

    @GetMapping("/admin") 
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
    	log.info("Exception");
        return "403";
    }

    @GetMapping("/login") 
    public String getLogin() {
        return "login";
    }
}
