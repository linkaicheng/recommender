package com.cheng.mall.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.mall.bean.Cart;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {
	@Autowired
	private CartService cartService;

	// @Test
	public void testFindCartByUid() {
		System.out.println(cartService.findCartByUid(1));
	}

	// @Test
	public void testCreateCart() {
		Cart cart = cartService.findCartByUid(1);
		cart.setTotal(20.00);
		cartService.createCart(cart);
	}
}
