package com.cheng.mall.service.recommender;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.RecommenderItem;
import com.cheng.mall.dao.recommender.RecommenderItemRepository;

@Service
public class RecommenderItemService {
	@Resource
	private RecommenderItemRepository recommenderItemRepository;

	/**
	 * 插入推荐
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 下午2:25:46
	 * @param recommenderItem
	 *
	 */
	public void createRecommenderItem(RecommenderItem recommenderItem) {
		recommenderItemRepository.save(recommenderItem);

	}

	/**
	 * 返回所有推荐
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 下午2:27:38
	 * @return
	 *
	 */
	public List<RecommenderItem> findAllRecommenderItems() {
		return recommenderItemRepository.findAll();

	}

	public void deleteAllItem() {
		recommenderItemRepository.deleteAll();
	}
}
