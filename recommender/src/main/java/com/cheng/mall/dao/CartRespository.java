package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.Cart;

public interface CartRespository extends JpaRepository<Cart, Integer> {
	@Query("select cart from Cart cart where cart.user.uid=:uid")
	Cart findCartByUid(@Param("uid") Integer uid);
}
