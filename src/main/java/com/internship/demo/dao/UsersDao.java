package com.internship.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.internship.demo.domain.Users;

@Mapper
public interface UsersDao {
	
	Users findUser(String username);
	
}
