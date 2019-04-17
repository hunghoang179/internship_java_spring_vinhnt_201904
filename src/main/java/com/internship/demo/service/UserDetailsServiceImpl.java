
package com.internship.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.internship.demo.model.UserModel;
import com.internship.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = userRepository.findUserByUsername(username);
		if (userModel == null) {
			throw new UsernameNotFoundException("User not found");
		}
		userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		String rolesName = userModel.getRoleName();
		grantedAuthorities.add(new SimpleGrantedAuthority(rolesName));

		return new org.springframework.security.core.userdetails.User(userModel.getUsername(), userModel.getPassword(),
				grantedAuthorities);
	}

}
