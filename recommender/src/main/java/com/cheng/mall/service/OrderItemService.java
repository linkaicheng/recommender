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

	public List<OrderItem> findOrderItemByOid(Integer oid) {
		return orderItemRepository.findOrderItemByOid(oid);
	}

}
