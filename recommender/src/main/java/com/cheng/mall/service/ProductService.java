package com.cheng.mall.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Product;
import com.cheng.mall.dao.ProductRepository;

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

	/**
	 * 返回十件热门商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午11:27:11
	 * @return
	 *
	 */
	public List<Product> findHot() {
		int pageNo = 0;
		int pageSize = 10;
		// 根据pid排序，倒序
		Order order = new Order(Direction.DESC, "pid");
		Sort sort = new Sort(order);
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		// 通常使用 Specification 的匿名内部类
		Specification<Product> specification = new Specification<Product>() {
			/**
			 * @param *root:
			 *            代表查询的实体类.
			 * @param query:
			 *            可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 *            来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb:
			 *            CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到
			 *            Predicate 对象
			 * @return: *Predicate 类型, 代表一个查询条件.
			 */
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("is_hot");
				Predicate predicate = cb.equal(path, 1);
				return predicate;
			}
		};

		Page<Product> page = productRepository.findAll(specification, pageable);
		return page.getContent();
		// System.out.println("总记录数: " + page.getTotalElements());
		// System.out.println("当前第几页: " + (page.getNumber() + 1));
		// System.out.println("总页数: " + page.getTotalPages());
		// System.out.println("当前页面的 List: " + page.getContent());
		// System.out.println("当前页面的记录数: " + page.getNumberOfElements());

	}

	/**
	 * 返回十件最新上架商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月12日 下午11:27:11
	 * @return
	 *
	 */
	public List<Product> findNew() {
		int pageNo = 0;
		int pageSize = 10;
		// 根据pid排序，倒序
		Order order = new Order(Direction.DESC, "pid");
		Sort sort = new Sort(order);
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Product> page = productRepository.findAll(pageable);
		return page.getContent();

	}

	/**
	 * 根据一级分类查询商品，分页
	 * 
	 * @author linkaicheng
	 * @date 2017年4月13日 下午8:58:08
	 * @param cid
	 * @param pageNo
	 *            从0开始
	 * @param pageSize
	 * @return
	 *
	 */
	public List<Product> findProductsPageByCid(final Integer cid, Integer pageNo, Integer pageSize) {
		// 根据pid排序，倒序
		Order order = new Order(Direction.DESC, "pid");
		Sort sort = new Sort(order);
		// pageNo从0开始
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		// 通常使用 Specification 的匿名内部类
		Specification<Product> specification = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("categorySecond").get("category").get("cid");
				Predicate predicate = cb.equal(path, cid);
				return predicate;
			}
		};

		Page<Product> page = productRepository.findAll(specification, pageable);
		return page.getContent();

	}

	/**
	 * 根据二级分类分页查询商品
	 * 
	 * @author linkaicheng
	 * @date 2017年4月13日 下午8:58:08
	 * @param cid
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	public List<Product> findProductsPageByCsid(final Integer csid, Integer pageNo, Integer pageSize) {
		// 根据pid排序，倒序
		Order order = new Order(Direction.DESC, "pid");
		Sort sort = new Sort(order);
		// pageNo从0开始
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		// 通常使用 Specification 的匿名内部类
		Specification<Product> specification = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("categorySecond").get("csid");
				Predicate predicate = cb.equal(path, csid);
				return predicate;
			}
		};

		Page<Product> page = productRepository.findAll(specification, pageable);
		return page.getContent();

	}

	/**
	 * 查询一级分类下的商品总数
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午3:55:31
	 * @param cid
	 * @return
	 *
	 */
	public Integer findCountCid(Integer cid) {
		return productRepository.findCountCid(cid);
	}

	/**
	 * 查询二级分类下的商品总数
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午6:57:47
	 * @param csid
	 * @return
	 *
	 */
	public Integer findCountCsid(Integer csid) {
		return productRepository.findCountCsid(csid);
	}

}
