package com.cheng.mall.service.recommender;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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

	/**
	 * 删除所有记录
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 下午4:35:51
	 *
	 */
	public void deleteAllItem() {
		recommenderItemRepository.deleteAll();
	}

	/**
	 * 根据用户id查推荐
	 * 
	 * @author linkaicheng
	 * @date 2017年5月3日 下午4:37:07
	 * @param uid
	 * @return
	 *
	 */
	public List<RecommenderItem> findRecommenderItemByUid(Integer uid) {
		return recommenderItemRepository.findRecommenderItemByUid(uid);
	}

	/**
	 * 分页查询推荐结果
	 * 
	 * @author linkaicheng
	 * @date 2017年5月10日 下午10:19:28
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	public List<RecommenderItem> findRecommenderItemsPage(Integer pageNo, Integer pageSize) {
		// 根据pid排序，倒序
		Order orderSort = new Order(Direction.DESC, "recommenderItemId");
		Sort sort = new Sort(orderSort);
		// pageNo从0开始
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<RecommenderItem> page = recommenderItemRepository.findAll(pageable);
		return page.getContent();
	}

	/**
	 * 推荐条目的数目
	 * 
	 * @author linkaicheng
	 * @date 2017年5月10日 下午10:27:20
	 * @return
	 *
	 */
	public Integer findCount() {
		return recommenderItemRepository.findAll().size();
	}
}
