package com.cheng.mall.service.recommender;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.mall.bean.Product;
import com.cheng.mall.bean.RecommenderItem;
import com.cheng.mall.service.ProductService;
import com.cheng.mall.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RecommenderItemServiceTest {
	@Resource
	private ProductService productService;
	@Resource
	private UserService userService;
	@Autowired
	private RecommenderItemService recommenderItemService;

	// @Test
	public void testCreateRecommenderItem() {
		RecommenderItem recommenderItem = new RecommenderItem();
		Product product = productService.findProductByPid(3);
		recommenderItem.setProduct(product);
		recommenderItem.setRecommenderDate(new Date());
		recommenderItem.setUser(userService.findUserByUsername("cheng"));
		recommenderItemService.createRecommenderItem(recommenderItem);

	}

	// @Test
	public void testFindAll() {
		System.out.println(recommenderItemService.findAllRecommenderItems());
	}

	// @Test
	public void testFindItemByUid() {
		for (RecommenderItem item : recommenderItemService.findRecommenderItemByUid(5)) {

			System.out.println(item.getRecommenderItemId());
		}
	}

}
