package com.cheng.mall.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.OrderItem;
import com.cheng.mall.dao.OrderItemRepository;

@Service
public class OrderItemService {
	@Resource
	private OrderItemRepository orderItemRepository;

	public List<OrderItem> findOrderItemByOid(String oid) {
		return orderItemRepository.findOrderItemByOid(oid);
	}

	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	/**
	 * 删除订单项
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 下午11:09:47
	 * @param orderItem
	 *
	 */
	public void deleteOrderItem(OrderItem orderItem) {
		orderItemRepository.delete(orderItem);

	}

}
