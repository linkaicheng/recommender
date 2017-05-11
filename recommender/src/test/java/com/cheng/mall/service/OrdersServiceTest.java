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

	// @Test
	public void testGetOrderByOid() {
		System.out.println(ordersService.finOrdersByOid("843a8e99-4d8b-4019-80d7-af9c701194d1"));
	}

	@Test
	public void testCreateOrder() {
		fail("Not yet implemented");
	}

	// @Test
	public void testFinOrdersByUid() {
		System.out.println(ordersService.finOrdersByUid(1) + "============================");
	}

	@Test
	public void testFindOrdersPage() {
		System.out.println(ordersService.finOrdersPageByUid(1, 1, 2));
	}

}
