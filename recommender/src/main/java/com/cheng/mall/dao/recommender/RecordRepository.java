package com.cheng.mall.dao.recommender;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.Record;

public interface RecordRepository extends JpaRepository<Record, Integer> {
	@Query(value = "select record from Record record where record.product.pid=:pid")
	List<Record> findRecordByPid(@Param("pid") Integer pid);
}
