package com.cheng.mall.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Category;
import com.cheng.mall.service.admin.CategoryService;

@RestController
public class CategoryController {
	@Resource
	private CategoryService categoryService;

	@RequestMapping("/getCategoryList")
	public List<Category> getCategoryList() {
		return categoryService.findAllCategory();
	}

	@RequestMapping(value = { "/addCategory" }, method = RequestMethod.POST)
	public List<Category> addCategory(Category category) {
		categoryService.saveCategory(category);
		return categoryService.findAllCategory();
	}

	@RequestMapping(value = { "/deleteCategory" }, method = RequestMethod.POST)
	public List<Category> deleteCategory(Category category) {
		categoryService.deleteCategory(category);
		return categoryService.findAllCategory();
	}

	@RequestMapping(value = { "/updateCategory" }, method = RequestMethod.POST)
	public List<Category> updateCategory(Category category) {
		System.out.println(category + "****************");
		categoryService.updateCategory(category);
		return categoryService.findAllCategory();
	}

}
