package com.internship.demo.dao;


import java.util.List;

import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;

public interface UsersDao {
	
	List<Users> findAllUser();
	UserModel findUserByUsername(String username);
	int insertUser(Users users);
}
