package com.cheng.mall.dao.recommender;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.RecommenderItem;

public interface RecommenderItemRepository extends JpaRepository<RecommenderItem, Integer> {
	@Query("select recommenderItem from RecommenderItem recommenderItem where recommenderItem.user.uid=:uid")
	List<RecommenderItem> findRecommenderItemByUid(@Param("uid") Integer uid);
}
