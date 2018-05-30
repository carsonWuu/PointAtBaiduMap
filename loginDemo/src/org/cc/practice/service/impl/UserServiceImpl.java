package org.cc.practice.service.impl;

import org.cc.practice.dao.UserDao;
import org.cc.practice.dao.impl.UserDaoImpl;
import org.cc.practice.entity.User;
import org.cc.practice.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public UserServiceImpl() {
		userDao=new UserDaoImpl();
	}
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

}
