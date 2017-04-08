package com.cheng.mall.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Category;
import com.cheng.mall.bean.CategorySecond;
import com.cheng.mall.service.admin.CategorySecondService;
import com.cheng.mall.service.admin.CategoryService;

@RestController
public class CategorySecondController {
	@Resource
	private CategorySecondService categorySecondService;
	@Resource
	private CategoryService categoryService;

	/**
	 * 返回二级分类列表
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午11:50:27
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getCategorySecondList" }, method = RequestMethod.GET)
	public List<CategorySecond> getCategorySecondList() {
		return categorySecondService.findAllCategorySecond();
	}

	/**
	 * 添加一个二级分类
	 * 
	 * @author linkaicheng
	 * @date 2017年4月8日 下午11:51:05
	 * @return
	 *
	 */
	@RequestMapping(value = { "/addCategorySecond" }, method = RequestMethod.POST)
	public List<CategorySecond> addCategorySecond(String csname, Integer cid) {
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsname(csname);
		Category category = categoryService.findCategoryByCid(cid);
		if (category != null) {
			categorySecond.setCategory(category);
			categorySecondService.saveCategorySecond(categorySecond);
		}
		return categorySecondService.findAllCategorySecond();
	}

	@RequestMapping(value = { "/updateCategorySecond" }, method = RequestMethod.POST)
	public List<CategorySecond> updateCategorySecond(CategorySecond categorySecond) {
		if (categorySecond != null) {
			categorySecondService.saveCategorySecond(categorySecond);
		}
		return categorySecondService.findAllCategorySecond();
	}

}
