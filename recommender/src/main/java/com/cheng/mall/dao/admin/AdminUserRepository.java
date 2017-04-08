package com.cheng.mall.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {
	@Query("select u from AdminUser u where u.username= :username and u.password= :password and u.authority=1")
	AdminUser login(@Param("username") String username, @Param("password") String password);
}
