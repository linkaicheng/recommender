package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.mall.bean.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Integer> {

}
