package com.internship.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.internship.demo.dao.UsersDao;
import com.internship.demo.model.UserModel;

@Controller
@RequestMapping(path = "/")
@SessionAttributes("user")
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	UsersDao usersDao;

	@GetMapping("/home")
	public String index(Model model, Principal principal) {
		UserModel users = usersDao.findUserByUsername(principal.getName());
		if (users == null) {
			return "redirect: /403";
		}
		model.addAttribute("user", users);
		return "home";
	}

	@GetMapping("/403")
	public String accessDenied() {
		log.info("Exception");
		return "403";
	}

	@GetMapping(path = { "/", "/login" })
	public String getLogin() {
		return "login";
	}
	
	@GetMapping(path = "/register")
	public String redirectRigisterPage() {
		return "register";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping("/cap-nhat-thong-tin")
	public String rediectUpdateUserPage(@SessionAttribute UserModel user, Model model) {
		model.addAttribute("users", user);
		return "/users/updateUser";
	}

	@PostMapping("/cap-nhat-thong-tin")
	public String updateUser(Model model) {
		return "redirect:/home";
	}
}
