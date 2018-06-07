package net.sp.programme.dao;

import net.sp.programme.entity.User;

public interface UserDao {

	public User login(User user);
	
	public User findUserById(String id);
	
}
