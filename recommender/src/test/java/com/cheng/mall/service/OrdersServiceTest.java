package com.cheng.mall.service;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersServiceTest {
	@Autowired
	private OrdersService ordersService;

	@Test
	public void testFindAllOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrderByOid() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOrder() {
		fail("Not yet implemented");
	}

	// @Test
	public void testFinOrdersByUid() {
		System.out.println(ordersService.finOrdersByUid(1) + "============================");
	}

}
