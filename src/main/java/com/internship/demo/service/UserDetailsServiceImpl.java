/*
 * package com.internship.demo.service;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.internship.demo.dao.UsersMapper;
 * 
 * @Service public class UserDetailsServiceImpl implements UserDetailsService{
 * 
 * @Autowired private UsersMapper usersMapper;
 * 
 * @Transactional public UserDetails loadUserByUsername(String username, String
 * password) throws UsernameNotFoundException { Users user =
 * usersMapper.findUser(username,password); if (user == null) { throw new
 * UsernameNotFoundException("User not found"); }
 * 
 * return new org.springframework.security.core.userdetails.User(
 * user.getEmail(), user.getPassword(), grantedAuthorities); }
 * 
 * }
 */