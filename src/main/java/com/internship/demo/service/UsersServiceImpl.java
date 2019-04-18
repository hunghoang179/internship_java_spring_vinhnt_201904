package com.internship.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;
import com.internship.demo.model.mapper.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersDao {

	@Autowired
	UserRepository userRepository;

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
	public int insertUser(Users users) {
		List<Users> listUser = userRepository.findUserByUsernameOrMail(users.getUsername(), users.getMail());
		if (!listUser.isEmpty() || listUser.size() > 0) {
			return 0;
		}
		userRepository.insertUser(users);
		return 1;
	}

	@Override
	public void updateUser(Users users) {
		userRepository.updateUser(users);
	}

	@Override
	public Users findUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public void editUser(Users users) {
		userRepository.editUser(users);
	}

}
