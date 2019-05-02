package com.internship.demo.controller;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.internship.demo.dao.BookDao;
import com.internship.demo.dao.CategoryDao;
import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Book;
import com.internship.demo.domain.Category;
import com.internship.demo.domain.Users;
import com.internship.demo.model.PasswordDto;
import com.internship.demo.model.UserModel;
import com.internship.demo.model.mapper.repository.UserRepository;
import com.internship.demo.utils.StringUtils;

@Controller
@RequestMapping(path = "/")
@SessionAttributes("user")
public class HomeController {

  private static final Logger log = LoggerFactory.getLogger(HomeController.class);

  @Autowired
  UsersDao usersDao;

  @Autowired
  BookDao bookDao;

  @Autowired
  CategoryDao categoryDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/home")
  public String index(Model model, Principal principal) {
    UserModel users = usersDao.findUserByUsername(principal.getName());
    if (users == null) {
      return "redirect: /403";
    }
    model.addAttribute("user", users);
    List<Book> listBook = bookDao.getListBook();
    List<Category> listCategory = categoryDao.getListCategory();
    model.addAttribute("listBook", listBook);
    model.addAttribute("listCategory", listCategory);
    return "home";
  }

  @GetMapping("/403")
  public String accessDenied() {
    log.info("Exception");
    return "403";
  }

  @GetMapping(path = {"/", "/login"})
  public String getLogin() {
    return "login";
  }

  @GetMapping(path = "/register")
  public String redirectRigisterPage(Model model) {
    model.addAttribute("userRegister", new Users());
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
  public String rediectUpdateUserPage(Model model) {
    return "/users/updateUser";
  }

  @PostMapping("/cap-nhat-thong-tin")
  public String updateUser(@SessionAttribute UserModel user, @ModelAttribute @Valid Users users,
      BindingResult result, Model model) {
    
    if (result.hasErrors()) {
      return "/users/updateUser";
    }
    
    users.setId(user.getId());
    List<Users> listUser = new UserRepository().checkUpdateUser(users);
    if (!listUser.isEmpty()) {
      model.addAttribute("errorMail", "Mail đã tồn tại");
      return "/users/updateUser";
    }
   
    usersDao.updateUser(users);
    return "redirect:/home";
  }

  @GetMapping("/thay-doi-mat-khau")
  public String rediectUpdatePasswordPage() {
    return "/users/changePassword";
  }

  @PostMapping("/thay-doi-mat-khau")
  public String updatePassword(@SessionAttribute UserModel user,
      @ModelAttribute PasswordDto passwordDto, Model model) throws ParseException {
    Users users = usersDao.findUserById(user.getId());
    if (!passwordEncoder.matches(passwordDto.getPassword(), users.getPassword())) {
      model.addAttribute("error", "Mật khẩu hiện tại không đúng");
      return "/users/changePassword";
    }
    if (!passwordEncoder.matches(passwordDto.getPasswordNew(), users.getPassword())) {
      users.setPassword(passwordEncoder.encode(passwordDto.getPasswordNew()));
      users.setUpdateUser(users.getUsername());
      users.setUpdateTime(StringUtils.getTimestampNow());
      usersDao.changePasswordUser(users);
    }
    return "redirect:/home";
  }
}
