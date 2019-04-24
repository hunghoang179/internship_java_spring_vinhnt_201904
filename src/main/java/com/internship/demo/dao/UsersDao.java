package com.internship.demo.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.internship.demo.domain.Users;
import com.internship.demo.model.UserModel;

public interface UsersDao {

	List<Users> findAllUser();

	UserModel findUserByUsername(String username);

	List<Users> checkUpdateUser(Users users);

	int insertUser(Users users) throws ParseException;

	void updateUser(Users users);

	Users findUserById(int id);

	void editUser(Users users);

	void changePasswordUser(Users users);

	Optional<Users> findUserByMail(String mail);

	Optional<Users> findUserByToken(String token);
}
