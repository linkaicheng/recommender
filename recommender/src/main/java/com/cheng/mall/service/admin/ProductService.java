package com.cheng.mall.service.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Product;
import com.cheng.mall.dao.admin.ProductRepository;

@Service
public class ProductService {
	@Resource
	private ProductRepository productRepository;

	/**
	 * 查询所有
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午4:57:49
	 * @return
	 *
	 */
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

}
