package com.internship.demo.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;
import com.internship.demo.service.RoleServiceImpl;
import com.internship.demo.service.UsersServiceImpl;
import com.internship.demo.utils.StringUtils;

@Controller
@RequestMapping(path = "admin")
@SessionAttributes("user")
public class AdminController {

	@Autowired
	UsersServiceImpl usersServiceImpl;

	@Autowired
	RoleServiceImpl roleService;

	@GetMapping(path = "/nguoi-dung")
	public String redirectListUser(Model model) {
		List<Users> listUser = usersServiceImpl.findAllUser();
		model.addAttribute("listUser", listUser);
		return "/admin/list-user";
	}

	@GetMapping(path = "/thay-doi-thong-tin/{id}")
	public String redirectEditUserPage(@SessionAttribute UserModel user, @PathVariable int id, Model model) {
		Users userById = usersServiceImpl.findUserById(id);

		if (user.getRole() == 2) {
			model.addAttribute("listRole", roleService.getListRole());
		}

		model.addAttribute("userById", userById);
		return "/admin/edit-user";
	}
	
	@PostMapping(path = "/thay-doi-thong-tin")
	public String redirectEditUserPage(@SessionAttribute UserModel user,@ModelAttribute Users users, Model model) throws ParseException {
		users.setUpdateTime(StringUtils.getTimestampNow());
		users.setUpdateUser(user.getUsername());
		usersServiceImpl.editUser(users);
		return "redirect:/admin/nguoi-dung";
	}

}
