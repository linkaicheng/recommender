package com.cheng.mall.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.mall.dao.CartItemRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartItemServiceTest {
	@Autowired
	private CartItemRepository cartItemRepository;

	@Test
	public void testCreateCartItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCartItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCartItemById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCartItemById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteCartItemByCartId() {
		cartItemRepository.deleteCartItemByCartId(2);
	}

}
