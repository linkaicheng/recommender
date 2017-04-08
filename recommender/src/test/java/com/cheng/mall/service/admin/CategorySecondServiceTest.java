package com.cheng.mall.service.admin;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cheng.mall.bean.Category;
import com.cheng.mall.bean.CategorySecond;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategorySecondServiceTest {
	@Autowired
	private CategorySecondService categorySecondService;
	@Autowired
	private CategoryService categoryService;

	@Test
	public void testFindAllCategorySecond() {
		fail("Not yet implemented");
	}

	// @Test
	public void testUpdateCategorySecond() {
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(4);
		categorySecond.setCsname("html");
		Category category = new Category();
		category.setCid(3);
		categorySecond.setCategory(category);
		categorySecondService.updateCategorySecond(categorySecond);
	}

	// @Test
	public void testSaveCategorySecond() {
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsname("aaa");
		Category category = new Category();
		category.setCid(3);
		category.setCname("办公室用书");
		categorySecond.setCategory(category);
		categorySecondService.saveCategorySecond(categorySecond);

	}

}
