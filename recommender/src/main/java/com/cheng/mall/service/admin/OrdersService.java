package com.cheng.mall.service.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Order;
import com.cheng.mall.dao.admin.OrdersRepository;

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
	public Order save(Order order) {
		return ordersRepository.save(order);
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
}
