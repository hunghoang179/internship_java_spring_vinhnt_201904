package com.internship.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.internship.demo.dao.EmailDao;
import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;
import com.internship.demo.utils.StringUtils;

@Controller
@RequestMapping("/forgot")
public class PasswordController {

	@Autowired
	UsersDao usersDao;

	@Autowired
	EmailDao emailDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public String displayForgotPasswordPage() {
		return "forgot-password";
	}

	@PostMapping
	public String processForgotPasswordForm(Model model, @RequestParam("mail") String mail,
			HttpServletRequest request) {

		Optional<Users> optional = usersDao.findUserByMail(mail);

		if (!optional.isPresent()) {
			model.addAttribute("errorMessage", "Không tìm thấy địa chỉ email");
		} else {

			String randomPassword = StringUtils.generateRandomPassword(10);
			Users user = optional.get();

			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("adminsystem@gmail.com");
			passwordResetEmail.setTo(user.getMail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("Mật khẩu mới: " + randomPassword);

			emailDao.sendEmail(passwordResetEmail);

			user.setPassword(passwordEncoder.encode(randomPassword));
			usersDao.changePasswordUser(user);

			// Add success message to view
			model.addAttribute("successMessage", "A password reset link has been sent to " + user.getMail());
		}

		return "forgot-password";

	}

}
