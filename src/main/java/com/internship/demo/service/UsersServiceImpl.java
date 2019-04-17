package com.internship.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.demo.dao.UsersDao;
import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;
import com.internship.demo.repository.UserRepository;

@Service
public class UsersServiceImpl implements UsersDao {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Users> findAllUser() {
		List<Users> listUser = userRepository.findAllUser();
		return listUser;
	}

	@Override
	public UserModel findUserByUsername(String username) {
		UserModel userModel = userRepository.findUserByUsername(username);
		return userModel;
	}

	@Override
	public int insertUser(Users users) {
		Users user = userRepository.findUserByUsernameOrMail(users.getUsername(), users.getMail());
		if(user != null) {
			return 0;
		}
		userRepository.insertUser(users);
		return 1;
	}

}
