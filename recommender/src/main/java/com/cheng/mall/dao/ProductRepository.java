package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.Product;

/**
 * 商品表Product的Repository
 * 
 * @author linkaicheng
 * @date 2017年4月16日 下午3:58:38
 *
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Product p where p.pid=:pid")
	void deleteProductByPid(@Param("pid") Integer pid);

	/**
	 * 查询一级分类下的商品总数
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午3:58:15
	 * @param cid
	 * @return
	 *
	 */
	@Query(value = "select count(*) from Product p where p.categorySecond.category.cid = ?")
	Integer findCountCid(@Param("cid") Integer cid);

	/**
	 * 查询二级分类下的商品总数
	 * 
	 * @author linkaicheng
	 * @date 2017年4月16日 下午6:41:45
	 * @param csid
	 * @return
	 *
	 */
	@Query(value = "select count(*) from Product p where p.categorySecond.csid = ?")
	Integer findCountCsid(@Param("csid") Integer csid);
}
