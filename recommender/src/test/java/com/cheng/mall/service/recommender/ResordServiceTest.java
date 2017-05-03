package com.cheng.mall.service.recommender;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.mall.bean.Record;
import com.cheng.mall.service.ProductService;
import com.cheng.mall.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResordServiceTest {

	@Resource
	private ProductService productService;
	@Resource
	private UserService userService;

	@Autowired
	private RecordService recordService;

	// @Test
	public void testCreateRecord() {
		Record record = new Record();
		record.setProduct(productService.findProductByPid(3));
		record.setUser(userService.findUserByUsername("cheng"));
		record.setPurchaseDate(new Date());
		recordService.createRecord(record);

	}

	// @Test
	public void testFindAllRecord() {
		System.out.println(recordService.findAllRecord());
	}

}
