package com.internship.demo.controller;

import java.util.Optional;
import java.util.UUID;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.internship.demo.dao.EmailDao;
import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;

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

    if (optional.isPresent()) {
      Users user = optional.get();
      user.setToken(UUID.randomUUID().toString());
      usersDao.updateUser(user);

      String appUrl =
          request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

      // Email message
      SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
      passwordResetEmail.setFrom("adminsystem@gmail.com");
      passwordResetEmail.setTo(user.getMail());
      passwordResetEmail.setSubject("Password Reset Request");
      passwordResetEmail.setText("Quên mật khẩu, click vào linh để đặt lại mật khẩu của bạn:\n"
          + appUrl + "/forgot/reset?token=" + user.getToken());

      emailDao.sendEmail(passwordResetEmail);
    }

    model.addAttribute("successMessage", "Link đặt lại mật khẩu đã được gửi tới email " + mail);

    return "forgot-password";

  }

  @GetMapping("/reset")
  public String displayResetPasswordPage(Model model, @RequestParam("token") String token) {

    Optional<Users> user = usersDao.findUserByToken(token);

    if (user.isPresent()) {
      model.addAttribute("token", token);
    } else {
      model.addAttribute("errorMessage", "Lỗi! Link reset mật khẩu không đúng.");
      return "403";
    }

    return "reset-password";
  }

  @PostMapping("/reset")
  public String setNewPassword(Model model, @RequestParam String token,
      @RequestParam String passwordNew, @RequestParam("rePassword") String rePassword,
      RedirectAttributes redir) {

    Optional<Users> user = usersDao.findUserByToken(token);

    if (!passwordNew.equals(rePassword)) {
      model.addAttribute("errorMessage", "Mật khẩu xác nhận không đúng");
      return "reset-password";
    }

    if (passwordNew == null || rePassword == null) {
      model.addAttribute("errorMessage", "Mật khẩu không được để trống");
      return "reset-password";
    } else {
      if (user.isPresent()) {

        Users resetUser = user.get();

        resetUser.setPassword(passwordEncoder.encode(passwordNew));

        resetUser.setToken(null);

        usersDao.changePasswordUser(resetUser);

        usersDao.updateUser(resetUser);

        return "redirect:/login";

      } else {
        model.addAttribute("errorMessage", "Xảy ra lỗi");
      }
    }
    return "reset-password";
  }

}
