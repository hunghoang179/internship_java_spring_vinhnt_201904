package com.internship.demo.model.mapper.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.internship.demo.domain.Role;
import com.internship.demo.model.mapper.RoleModelMapper;
import com.internship.demo.mybaties.activemodel.Repository;

public class RoleRepository extends Repository<RoleModelMapper> {

	@Override
	protected RoleModelMapper repositoryMapper(SqlSession session) {
		return session.getMapper(RoleModelMapper.class);
	}
	
	public List<Role> getListRole() {
		return execute(mapper -> {
			return mapper.getListRole();
		});
	}

}
