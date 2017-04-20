package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.mall.bean.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
