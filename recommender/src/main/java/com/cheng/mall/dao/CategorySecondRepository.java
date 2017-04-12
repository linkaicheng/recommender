package com.cheng.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.CategorySecond;

public interface CategorySecondRepository extends JpaRepository<CategorySecond, Integer> {
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from CategorySecond cs where cs.csid=:csid")
	void deleteCategorySecondByCsid(@Param("csid") Integer csid);
}
