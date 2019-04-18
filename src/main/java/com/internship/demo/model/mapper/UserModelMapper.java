package com.internship.demo.model.mapper;

import java.util.List;

import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;

public interface UserModelMapper {
	
	List<Users> findAllUser();
	UserModel findUserByUsername(String username);
	int insertUser(Users users);
	List<Users> findUserByUsernameOrMail(String username, String mail);
	List<Users> checkUpdateUser(String username, String mail, String id);
	void updateUser(Users users);
	Users findUserById(int id);
	void editUser(Users users);
}
