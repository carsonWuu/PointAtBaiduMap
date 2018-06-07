package org.cc.practice.service;

import org.cc.practice.entity.User;

public interface UserService {

	public User login(User user);
	
	public User findUserById(String id);
	
}
