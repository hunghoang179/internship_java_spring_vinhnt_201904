package com.internship.demo.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;
import com.internship.demo.model.mapper.repository.UserRepository;
import com.internship.demo.utils.StringUtils;

@Service
public class UsersServiceImpl implements UsersDao {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Users> findAllUser() {
		List<Users> listUser = new UserRepository().findAllUser();
		return listUser;
	}

	@Override
	public UserModel findUserByUsername(String username) {
		UserModel userModel = userRepository.findUserByUsername(username);
		return userModel;
	}

	@Override
	public int insertUser(Users users) throws ParseException {
		List<Users> listUser = userRepository.findUserByUsernameOrMail(users.getUsername(), users.getMail());
		if (!listUser.isEmpty() || listUser.size() > 0) {
			return 0;
		}
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		users.setCreateUser(users.getUsername());
		users.setUpdateUser(users.getUsername());
		users.setCreateTime(StringUtils.getTimestampNow());
		users.setUpdateTime(StringUtils.getTimestampNow());
		userRepository.insertUser(users);
		return 1;
	}

	@Override
	public void updateUser(Users users) {
		// users.setPassword(passwordEncoder.encode(users.getPassword()));
		userRepository.updateUser(users);
	}

	@Override
	public Users findUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public void editUser(Users users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		userRepository.editUser(users);
	}

	@Override
	public void changePasswordUser(Users users) {
		userRepository.changePasswordUser(users);
	}

	@Override
	public List<Users> checkUpdateUser(Users users) {
		return userRepository.checkUpdateUser(users);
	}

}
