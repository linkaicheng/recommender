package com.cheng.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	@Query("select oi from OrderItem oi where oi.order.oid=:oid")
	List<OrderItem> findOrderItemByOid(@Param("oid") Integer oid);
}
