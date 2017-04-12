package com.cheng.mall.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Category;
import com.cheng.mall.bean.Product;
import com.cheng.mall.service.CategoryService;
import com.cheng.mall.service.ProductService;

@RestController
public class ProductController {
	@Resource
	private CategoryService categoryService;
	@Resource
	private ProductService productService;

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午10:27:57
	 * @return
	 *
	 */
	@RequestMapping("/getCategoryList")
	public List<Category> getCategoryList() {
		return categoryService.findAllCategory();
	}

	/**
	 * 返回十件热门商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午11:49:52
	 * @return
	 *
	 */
	@RequestMapping("/getProductHot")
	public List<Product> getProductHot() {
		return productService.findHot();
	}

	/**
	 * 返回十件最新商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午11:50:47
	 * @return
	 *
	 */
	@RequestMapping("/getProductNew")
	public List<Product> getProductNew() {
		return productService.findNew();
	}
}
