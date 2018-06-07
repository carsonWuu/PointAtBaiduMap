package org.cc.practice.dao;

import org.cc.practice.entity.User;

public interface UserDao {

	public User login(User user);
	
	public User findUserById(String id);
	
}
