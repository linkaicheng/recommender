package com.cheng.mall.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.cheng.mall.util.PaymentUtil;

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
	 * 生成订单，转到订单确认页面填写信息
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
		order.setOid(UUID.randomUUID().toString());
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
	 * 订单支付页面，根据session中的orderId(购物车页面保存的或我的订单页面用户点击的)返回用户在购物车页面勾选的购物项
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
		String orderId = (String) request.getSession().getAttribute("orderId");
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

	/**
	 * 转到我的订单页面
	 * 
	 * @author linkaicheng
	 * @date 2017年4月25日 下午10:33:11
	 * @return
	 *
	 */
	@RequestMapping(value = { "/user/toMyOrders" }, method = RequestMethod.GET)
	public String toMyOrders() {
		return "/orderList";
	}

	/**
	 * 我的订单页面，返回该用户的所有订单
	 * 
	 * @author linkaicheng
	 * @date 2017年4月25日 下午9:49:42
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/findMyOrders" }, method = RequestMethod.GET)
	public List<Order> findMyOrders(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			return ordersService.finOrdersByUid(user.getUid());
		}

		return null;
	}

	/**
	 * 用户收货后在系统上点击签收，更改订单状态为订单已完成
	 * 
	 * @author linkaicheng
	 * @date 2017年4月26日 下午11:50:06
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/singnIn" }, method = RequestMethod.GET)
	public List<Order> signIn(HttpServletRequest request, String oid) {
		Order order = ordersService.finOrdersByOid(oid);
		if (order != null) {
			order.setState(4);
			ordersService.updateOrder(order);
		}
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			return ordersService.finOrdersByUid(user.getUid());
		}

		return null;
	}

	/**
	 * 用户在我的订单页面点击付款，跳转到订单支付页面
	 * 
	 * @author linkaicheng
	 * @date 2017年4月27日 上午12:29:56
	 * @param request
	 * @param oid
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/toPayForOid" }, method = RequestMethod.GET)
	public Message toPayOrderByOid(HttpServletRequest request, String oid) {
		Message message = new Message();
		request.getSession().setAttribute("orderId", oid);
		message.setInfo("success");
		return message;
	}

	@ResponseBody
	@RequestMapping(value = { "/user/payOrder/{oid}/{pdFrpId}" }, method = RequestMethod.POST)
	public Message payOrder(@PathVariable("oid") String oid, @PathVariable("pdFrpId") String pdFrpId, User user) {
		System.out.println(oid + "================" + pdFrpId + "=============" + user);
		// 修改订单信息
		Order order = ordersService.finOrdersByOid(oid);
		order.setAddr(user.getAddr());
		order.setName(user.getName());
		order.setPhone(user.getPhone());
		ordersService.updateOrder(order);
		logger.info("===========" + "order:" + order + "已修改====================");
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8081/user/orderCallBack"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = pdFrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);

		Message message = new Message();
		message.setInfo(sb.toString());
		return message;
	}

	// 付款成功后跳转回来的路径:
	@RequestMapping(value = { "/user/orderCallBack" }, method = RequestMethod.GET)
	public String orderCallBack(HttpServletRequest request, String r6_Order, String r3_Amt) {
		// 修改订单的状态:
		Order currOrder = ordersService.finOrdersByOid(r6_Order);
		// 修改订单状态为2:已经付款:
		currOrder.setState(2);
		ordersService.updateOrder(currOrder);
		logger.info("==================" + "订单：" + currOrder + "已付款==================");
		Message message = new Message();
		message.setInfo("支付成功!订单编号为: " + r6_Order + " 付款金额为: " + r3_Amt);
		logger.info("pay success .." + "=============" + message + "=================");
		request.getSession().setAttribute("message", message);
		return "/message";
	}

	/**
	 * 返回支付结果
	 * 
	 * @author linkaicheng
	 * @date 2017年4月29日 上午11:42:04
	 * @param request
	 * @return
	 *
	 */
	@ResponseBody
	@RequestMapping(value = { "/user/getPayResult" }, method = RequestMethod.GET)
	public Message findPayResult(HttpServletRequest request) {
		Message message = (Message) request.getSession().getAttribute("message");
		return message;
	}

}
