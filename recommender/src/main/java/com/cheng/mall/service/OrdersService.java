package com.cheng.mall.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Order;
import com.cheng.mall.dao.OrdersRepository;

@Service
public class OrdersService {
	@Resource
	private OrdersRepository ordersRepository;

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午4:45:24
	 * @return
	 *
	 */
	public List<Order> findAllOrder() {
		return ordersRepository.findAll();
	}

	/**
	 * 更新
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午7:25:41
	 * @param order
	 * @return
	 *
	 */
	public Order updateOrder(Order order) {
		if (order.getOid() != null) {
			return ordersRepository.save(order);
		}
		return null;
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午7:27:19
	 * @param oid
	 * @return
	 *
	 */
	public Order getOrderByOid(Integer oid) {
		return ordersRepository.findOne(oid);
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月26日 下午11:56:58
	 * @param order
	 * @return
	 *
	 */
	public Order createOrder(Order order) {
		return ordersRepository.save(order);
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月26日 下午11:57:01
	 * @param uid
	 * @return
	 *
	 */
	public List<Order> finOrdersByUid(Integer uid) {
		return ordersRepository.findOrdersByUid(uid);
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月26日 下午11:57:57
	 * @param oid
	 * @return
	 *
	 */
	public Order finOrdersByOid(Integer oid) {
		return ordersRepository.findOne(oid);
	}
}
