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

	/**
	 * 改
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 上午10:47:36
	 * @param categorySecond
	 * @return
	 *
	 */
	@RequestMapping(value = { "/updateCategorySecond" }, method = RequestMethod.POST)
	public List<CategorySecond> updateCategorySecond(Integer csid, String csname, Integer cid) {
		CategorySecond categorySecond = categorySecondService.findCategorySecondByCsid(csid);
		Category category = categoryService.findCategoryByCid(cid);
		if (categorySecond != null && category != null) {
			categorySecond.setCsname(csname);
			categorySecond.setCategory(category);
			categorySecondService.saveCategorySecond(categorySecond);
		}
		return categorySecondService.findAllCategorySecond();
	}

	/**
	 * 删
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 上午10:49:52
	 * @param categorySecond
	 * @return
	 *
	 */
	@RequestMapping(value = { "/deleteCategorySecond" }, method = RequestMethod.POST)
	public List<CategorySecond> deleteCategorySecond(Integer csid) {
		// System.out.println(csid);
		if (csid != null) {
			categorySecondService.deleteCategorySecond(csid);
		}
		return categorySecondService.findAllCategorySecond();
	}

}
