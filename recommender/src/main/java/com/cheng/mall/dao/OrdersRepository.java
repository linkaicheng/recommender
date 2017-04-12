package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.mall.bean.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {

}
