package com.internship.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.internship.demo.dao.RoleDao;
import com.internship.demo.domain.Role;
import com.internship.demo.model.mapper.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleDao {

  @Override
  public List<Role> getListRole() {
    return new RoleRepository().getListRole();
  }

}
