package com.cheng.mall.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Product p where p.pid=:pid")
	void deleteProductByPid(@Param("pid") Integer pid);

}
