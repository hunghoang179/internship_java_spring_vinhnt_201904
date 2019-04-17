package com.internship.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;

@Mapper
public interface UserRepository {
	List<Users> findAllUser();
	UserModel findUserByUsername(String username);
	int insertUser(Users users);
	Users findUserByUsernameOrMail(String username, String mail);
}
