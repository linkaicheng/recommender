package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query("select c from Category c where c.cname=:cname")
	Category findCategoryByCname(@Param("cname") String cname);

	@Query("select c from Category c where c.cid = :cid")
	Category findCategoryByCid(@Param("cid") Integer cid);

	@Modifying(clearAutomatically = true)
	@Query(value = "delete from Category c where c.cid=:cid")
	void deleteCategoryByCid(@Param("cid") Integer cid);

}
