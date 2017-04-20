package com.cheng.mall.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.CartItem;
import com.cheng.mall.dao.CartItemRepository;
import com.cheng.mall.dao.CartRespository;

@Service
public class CartItemService {
	@Resource
	private CartRespository cartRespository;
	@Resource
	private CartItemRepository cartItemRepository;

	public CartItem createCartItem(CartItem cartItem) {
		CartItem cartItem2 = cartItemRepository.save(cartItem);
		cartItemRepository.flush();
		return cartItem2;
	}

}
