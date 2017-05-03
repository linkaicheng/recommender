package com.cheng.mall.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.User;
import com.cheng.mall.dao.UserRepository;

@Service
public class UserService {
	@Resource
	private UserRepository userRepository;

	// 保存数据.
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}

	// 用户登录的方法
	public User login(String username, String password) {
		return userRepository.login(username, password);
	}

	// 删除user
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	public User findUserByUid(Integer uid) {
		return userRepository.findOne(uid);
	}
}
