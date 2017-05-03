package com.cheng.mall.dao.recommender;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.mall.bean.RecommenderItem;

public interface RecommenderItemRepository extends JpaRepository<RecommenderItem, Integer> {

}
