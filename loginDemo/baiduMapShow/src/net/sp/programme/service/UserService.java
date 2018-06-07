package net.sp.programme.service;

import net.sp.programme.entity.User;

public interface UserService {

	public User login(User user);
	
	public User findUserById(String id);
	
}
