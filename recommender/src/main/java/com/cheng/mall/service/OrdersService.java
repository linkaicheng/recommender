package com.cheng.mall.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cheng.mall.bean.Order;
import com.cheng.mall.dao.OrderItemRepository;
import com.cheng.mall.dao.OrdersRepository;

@Service
public class OrdersService {
	@Resource
	private OrdersRepository ordersRepository;
	@Resource
	private OrderItemRepository orderItemRepository;

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
	public Order getOrderByOid(String oid) {
		return ordersRepository.findOrderByOid(oid);
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
	public Order finOrdersByOid(String oid) {
		return ordersRepository.findOrderByOid(oid);
	}

	/**
	 * 分页查询订单
	 * 
	 * @author linkaicheng
	 * @date 2017年5月7日 下午9:43:16
	 * @param uid
	 * @param i
	 * @param pageSize
	 * @return
	 *
	 */
	public List<Order> finOrdersPageByUid(final Integer uid, Integer pageNo, Integer pageSize) {
		// 根据pid排序，倒序
		org.springframework.data.domain.Sort.Order orderSort = new org.springframework.data.domain.Sort.Order(
				Direction.DESC, "ordertime");
		Sort sort = new Sort(orderSort);
		// pageNo从0开始
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		// 通常使用 Specification 的匿名内部类
		Specification<Order> specification = new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("user").get("uid");
				Predicate predicate = cb.equal(path, uid);
				return predicate;
			}
		};

		Page<Order> page = ordersRepository.findAll(specification, pageable);
		return page.getContent();
	}

	/**
	 * 查某个用户的订单总数
	 * 
	 * @author linkaicheng
	 * @date 2017年5月7日 下午9:57:08
	 * @param uid
	 * @return
	 *
	 */
	public Integer findCountUid(Integer uid) {
		return ordersRepository.findCountUid(uid);
	}

	/**
	 * 分页查询所有订单
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 下午3:08:06
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *
	 */
	public List<Order> findOrdersPage(Integer pageNo, Integer pageSize) {
		// 根据pid排序，倒序
		org.springframework.data.domain.Sort.Order orderSort = new org.springframework.data.domain.Sort.Order(
				Direction.DESC, "oid");
		Sort sort = new Sort(orderSort);
		// pageNo从0开始
		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Order> page = ordersRepository.findAll(pageable);
		return page.getContent();
	}

	public Integer findCount() {
		return ordersRepository.findAll().size();
	}

	/**
	 * 删除订单
	 * 
	 * @author linkaicheng
	 * @date 2017年5月11日 下午11:10:07
	 * @param order
	 *
	 */
	@Transactional
	public void deleteOrder(Order order) {
		ordersRepository.deleteOrderByOid(order.getOid());
	}
}
