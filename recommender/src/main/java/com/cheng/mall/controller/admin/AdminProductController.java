package com.cheng.mall.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Product;
import com.cheng.mall.service.admin.ProductService;

@RestController
public class AdminProductController {
	@Resource
	private ProductService productService;

	@RequestMapping(value = { "/getProductList" }, method = RequestMethod.GET)
	public List<Product> getProductList() {
		return productService.findAllProduct();
	}
}
