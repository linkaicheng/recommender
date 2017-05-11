package com.cheng.mall.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.mall.bean.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	public void testSave() {
		User user = new User();
		user.setAddr("深圳");
		user.setAge(18);
		user.setEmail("1063002901@qq.com");
		user.setName("cheng");
		user.setPassword("2852575");
		user.setPhone("18813973791");
		user.setSex("男");
		user.setUsername("zhangfei");
		userService.save(user);

		userService.deleteUser(user);
	}

	@Test
	public void testLogin() {
		User user = userService.findUserByUid(2);
		assertEquals(user, userService.login(user.getUsername(), user.getPassword()));
	}

	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setAddr("深圳");
		user.setAge(18);
		user.setEmail("1063002901@qq.com");
		user.setName("cheng");
		user.setPassword("2852575");
		user.setPhone("18813973791");
		user.setSex("男");
		user.setUsername("zhangfei");
		userService.save(user);

		userService.deleteUser(user);
	}

	@Test
	public void testFindUserByUsername() {
		User user = userService.findUserByUid(2);
		assertEquals(user, userService.findUserByUsername(user.getUsername()));
	}

	@Test
	public void testFindUserByUid() {
		User user = userService.findUserByUid(2);

	}

}
