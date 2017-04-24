package com.cheng.mall.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cheng.mall.bean.CartItem;
import com.cheng.mall.bean.Order;
import com.cheng.mall.bean.OrderItem;
import com.cheng.mall.bean.User;
import com.cheng.mall.service.CartItemService;
import com.cheng.mall.service.OrderItemService;
import com.cheng.mall.service.OrdersService;
import com.cheng.mall.util.Message;

/**
 * 订单相关操作
 * 
 * @author linkaicheng
 * @date 2017年4月22日 下午9:31:19
 *
 */
@Controller
public class OrderController {
	private Logger logger = Logger.getLogger(CartController.class);
	@Resource
	private CartItemService cartItemService;
	@Resource
	private OrderItemService orderItemService;
	@Resource
	private OrdersService ordersService;

	/**
	 * 提交订单，转到订单确认页面填写信息,交给订单controller
	 * 
	 * @author linkaicheng
	 * @date 2017年4月22日 下午3:26:28
	 * @param request
	 * @param cartItemIds
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/saveOrder" }, method = RequestMethod.POST)
	public Message saveOrder(HttpServletRequest request, Integer[] cartItemIds) {
		Message message = new Message();
		if (cartItemIds == null || cartItemIds.length == 0) {
			logger.info("=================cartItemIds is null============================");
			message.setInfo("failure");
			return message;
		}
		// 将要购买的item存到session中
		request.getSession().setAttribute("cartItemIds", cartItemIds);
		// 存数据库
		User user = (User) request.getSession().getAttribute("user");
		Order order = new Order();
		order.setOrdertime(new Date());
		order.setState(1);
		order.setUser(user);
		ordersService.createOrder(order);
		double total = 0.0;
		for (Integer id : cartItemIds) {
			CartItem cartItem = cartItemService.findCartItemById(id);
			if (cartItem != null) {
				total += cartItem.getSubtotal();
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(cartItem.getCount());
				orderItem.setOrder(order);
				orderItem.setProduct(cartItem.getProduct());
				orderItem.setSubtotal(cartItem.getSubtotal());
				orderItemService.createOrderItem(orderItem);
				// 删除购物车中的购物项
				cartItemService.deleteCartItemById(id);
			}
		}
		order.setTotal(total);
		ordersService.updateOrder(order);
		// 清除session中的cartItemids
		request.getSession().setAttribute("cartItemIds", null);
		request.getSession().setAttribute("orderId", order.getOid());
		message.setInfo("success");
		return message;
	}

	/**
	 * jump to order.html
	 * 
	 * @author linkaicheng
	 * @date 2017年4月22日 下午7:45:24
	 * @return
	 *
	 */
	@RequestMapping(value = { "/user/toOrder" }, method = RequestMethod.GET)
	public String toOrder() {
		logger.info("=================jump to order.html============================");
		return "/order";
	}

	/**
	 * 
	 * @author linkaicheng
	 * @date 2017年4月24日 上午12:11:39
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/getItemsToOrder" }, method = RequestMethod.GET)
	public Order findCartItems(HttpServletRequest request) {
		Integer orderId = (Integer) request.getSession().getAttribute("orderId");
		if (orderId != null) {
			return ordersService.getOrderByOid(orderId);
		}
		return null;
		// Set<CartItem> cartItems = new LinkedHashSet<>();
		// Cart cart = new Cart();
		// // 用从session中查处用户勾选的购物项id从数据库中查处购物项返回
		// Integer[] cartItemIds = (Integer[])
		// request.getSession().getAttribute("cartItemIds");
		// double total = 0.0;
		// for (Integer cartItemId : cartItemIds) {
		// CartItem cartItem = cartItemService.findCartItemById(cartItemId);
		// if (cartItem != null) {
		// total += cartItem.getSubtotal();
		// cartItems.add(cartItem);
		// }
		// }
		// cart.setCartItems(cartItems);
		// cart.setTotal(total);
		// return cart;
	}

}
