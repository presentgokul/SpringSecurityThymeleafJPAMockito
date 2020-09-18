package com.app.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailService implements UserDetailsService {

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if ("user".equals(userName)) {
			return new User("user", "user", new ArrayList());
		}
		return null;
	}

}
