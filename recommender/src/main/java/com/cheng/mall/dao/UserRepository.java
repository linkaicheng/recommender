package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.username= :username and u.password= :password")
	User login(@Param("username") String username, @Param("password") String password);

	@Query("select u from User u where u.username=:username")
	User findUserByUsername(@Param("username") String username);

}
