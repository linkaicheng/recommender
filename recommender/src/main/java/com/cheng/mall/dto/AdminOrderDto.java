package com.cheng.mall.dto;

import java.io.Serializable;
import java.util.List;

import com.cheng.mall.bean.Order;
import com.cheng.mall.bean.OrderItem;

/**
 * 返回给前台订单管理页面所需信息
 * 
 * @author linkaicheng
 * @date 2017年4月16日 上午1:08:28
 *
 */
public class AdminOrderDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;
	private List<OrderItem> orderItems;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "AdminOrderDto [order=" + order + ", orderItems=" + orderItems + "]";
	}

}
