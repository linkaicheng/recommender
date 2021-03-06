package com.cheng.mall.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cheng.mall.bean.Order;
import com.cheng.mall.bean.OrderItem;
import com.cheng.mall.dto.AdminOrderDto;
import com.cheng.mall.dto.PageDto;
import com.cheng.mall.service.OrderItemService;
import com.cheng.mall.service.OrdersService;
import com.cheng.mall.util.Message;

@RestController
@RequestMapping("/admin")
public class AdminOrderController {
	@Resource
	private OrdersService ordersService;
	@Resource
	private OrderItemService orderItemService;

	/**
	 * 修改订单状态
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午7:32:54
	 * @param order
	 * @return
	 *
	 */
	@RequestMapping(value = { "/updateOrder" }, method = RequestMethod.POST)
	public Message updateOrderState(String oid, Integer state) {
		// 修改操作，允许改订单状态
		if (oid != null && state != null) {
			Order updateOrder = ordersService.getOrderByOid(oid);
			if (updateOrder != null) {
				updateOrder.setState(state);
				ordersService.updateOrder(updateOrder);
			}
		}
		// // 返回订单列表
		// List<Order> orders = ordersService.findAllOrder();
		// List<AdminOrderDto> orderDtos = new ArrayList<>();
		// for (Order order2 : orders) {
		// AdminOrderDto orderDto = new AdminOrderDto();
		// orderDto.setOrder(order2);
		// List<OrderItem> orderItems =
		// orderItemService.findOrderItemByOid(order2.getOid());
		// orderDto.setOrderItems(orderItems);
		// orderDtos.add(orderDto);
		// }
		// return orderDtos;
		Message message = new Message();
		message.setInfo("success");
		return message;
	}

	/**
	 * 返回订单列表
	 * 
	 * @author linkaicheng
	 * @date 2017年4月10日 下午7:33:18
	 * @return
	 *
	 */
	@RequestMapping(value = { "/getOrderList" }, method = RequestMethod.GET)
	public List<AdminOrderDto> getOrderList() {
		List<Order> orders = ordersService.findAllOrder();
		List<AdminOrderDto> orderDtos = new ArrayList<>();
		for (Order order : orders) {
			AdminOrderDto orderDto = new AdminOrderDto();
			orderDto.setOrder(order);
			List<OrderItem> orderItems = orderItemService.findOrderItemByOid(order.getOid());
			orderDto.setOrderItems(orderItems);
			orderDtos.add(orderDto);
		}
		return orderDtos;
	}

	// ==============分页
	@RequestMapping(value = { "/getOrderListPage" }, method = RequestMethod.GET)
	public Map<String, Object> getOrderListPage(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		PageDto<Order> pageDto = new PageDto<>();
		List<Order> orders = ordersService.findOrdersPage(pageNo - 1, pageSize);
		Integer count = ordersService.findCount();
		pageDto.setList(orders);
		pageDto.setTotalCount(count);
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("totalCount", pageDto.getTotalCount());

		List<AdminOrderDto> orderDtos = new ArrayList<>();
		for (Order order : pageDto.getList()) {
			AdminOrderDto orderDto = new AdminOrderDto();
			orderDto.setOrder(order);
			List<OrderItem> orderItems = orderItemService.findOrderItemByOid(order.getOid());
			orderDto.setOrderItems(orderItems);
			orderDtos.add(orderDto);
		}
		pageMap.put("list", orderDtos);
		return pageMap;
	}

}
