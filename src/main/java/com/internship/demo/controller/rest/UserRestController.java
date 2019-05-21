package com.internship.demo.controller.rest;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;
import com.internship.demo.model.PasswordDto;
import com.internship.demo.model.UserModel;
import com.internship.demo.model.mapper.repository.UserRepository;
import com.internship.demo.utils.StringUtils;

@RestController
@RequestMapping(path = "/api")
@SessionAttributes("user")
public class UserRestController {

  @Autowired
  UsersDao usersDao;

  @Autowired
  PasswordEncoder passwordEncoder;

  @GetMapping(path = "/session/user")
  public ResponseEntity<UserModel> getSessionUser(HttpServletRequest request) {
    UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
    if (sessionUser == null) {
      return new ResponseEntity<UserModel>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<UserModel>(sessionUser, HttpStatus.OK);
  }

  @GetMapping(path = "/user")
  public ResponseEntity<List<Users>> getAllUser() {
    List<Users> listUser = usersDao.findAllUser();
    if (listUser == null) {
      return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Users>>(listUser, HttpStatus.OK);
  }

  @GetMapping(path = "/user/{id}")
  public ResponseEntity<Users> findUserById(@PathVariable int id) {
    Users userById = usersDao.findUserById(id);
    if (userById == null) {
      return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Users>(userById, HttpStatus.OK);
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Users> updateBook(@PathVariable("id") int id, @RequestBody Users user)
      throws ParseException {
    Users users = usersDao.findUserById(id);
    users.setPassword(user.getPassword());
    users.setAddress(user.getAddress());
    users.setPhone(user.getPhone());
    users.setStatus(user.getStatus());
    users.setRole(user.getRole());
    users.setUpdateTime(StringUtils.getTimestampNow());
    users.setUpdateUser(user.getUsername());
    usersDao.editUser(users);

    return new ResponseEntity<Users>(users, HttpStatus.OK);
  }

  @PostMapping("/change/password")
  public <T> ResponseEntity<T> updatePassword(HttpServletRequest request,
      @RequestBody PasswordDto passwordDto, Model model) throws ParseException {
    UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
    Users users = usersDao.findUserById(sessionUser.getId());
    if (!passwordEncoder.matches(passwordDto.getPassword(), users.getPassword())) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    if (!passwordDto.getPasswordNew().equals(passwordDto.getRePassword())) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    if (!passwordEncoder.matches(passwordDto.getPasswordNew(), users.getPassword())) {
      users.setPassword(passwordEncoder.encode(passwordDto.getPasswordNew()));
      users.setUpdateUser(users.getUsername());
      users.setUpdateTime(StringUtils.getTimestampNow());
      usersDao.changePasswordUser(users);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/change/infor")
  public ResponseEntity<Void> updateUser(HttpServletRequest request, @RequestBody Users users,
      BindingResult result, Model model) {

    UserModel sessionUser = (UserModel) request.getSession().getAttribute("user");
    users.setId(sessionUser.getId());
    List<Users> listUser = new UserRepository().checkUpdateUser(users);
    if (!listUser.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    usersDao.updateUser(users);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
