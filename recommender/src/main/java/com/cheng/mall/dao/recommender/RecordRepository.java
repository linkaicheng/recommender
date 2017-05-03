package com.cheng.mall.dao.recommender;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheng.mall.bean.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {

}
