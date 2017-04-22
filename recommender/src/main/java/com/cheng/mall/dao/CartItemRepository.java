package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from CartItem ci where ci.cart.cartId=:cartId")
	void deleteCartItemByCartId(@Param("cartId") Integer cartId);
}
