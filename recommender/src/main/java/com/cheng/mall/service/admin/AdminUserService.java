package com.cheng.mall.service.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.AdminUser;
import com.cheng.mall.dao.admin.AdminUserRepository;

/**
 * 
 * @author linkaicheng
 * @date 2017年4月8日 下午12:37:05
 *
 *
 */
@Service
public class AdminUserService {
	@Resource
	private AdminUserRepository adminUserRepository;

	// 管理员登录的方法
	public AdminUser login(String username, String password) {
		return adminUserRepository.login(username, password);
	}

}
