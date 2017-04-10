package com.cheng.mall.service.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

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

	/**
	 * 增
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:26:29
	 * @param product
	 * @return
	 *
	 */
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	/**
	 * 删
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:39:37
	 * @param pid
	 * @return
	 *
	 */
	@Transactional
	public Product deleteProduct(Integer pid) {
		Product product = productRepository.findOne(pid);
		if (product != null) {
			productRepository.deleteProductByPid(pid);
		}
		return null;
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:41:28
	 * @param product
	 * @return
	 *
	 */
	@Transactional
	public Product updateProduct(Product product) {
		if (product.getPid() != null && product.getCategorySecond() != null) {
			return productRepository.save(product);
		}
		return null;
	}

	/**
	 * 查
	 * 
	 * @author linkaicheng
	 * @date 2017年4月9日 下午5:42:36
	 * @param pid
	 * @return
	 *
	 */
	public Product findProductByPid(Integer pid) {
		return productRepository.findOne(pid);
	}

}
