package com.cheng.mall.service.recommender;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Record;
import com.cheng.mall.dao.recommender.RecordRepository;

@Service
public class RecordService {
	@Resource
	private RecordRepository recordRepository;

	/**
	 * 增加一条购买记录
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 上午1:49:09
	 * @param record
	 *
	 */
	public void createRecord(Record record) {
		recordRepository.save(record);
	}

	/**
	 * 返回所有购买记录
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 上午1:49:35
	 * @return
	 *
	 */
	public List<Record> findAllRecord() {
		return recordRepository.findAll();
	}

	public Record findRecordByPid(Integer pid) {
		// TODO Auto-generated method stub
		return recordRepository.findRecordByPid(pid);
	}

}
