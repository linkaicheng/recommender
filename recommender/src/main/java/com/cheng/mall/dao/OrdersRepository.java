package com.cheng.mall.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cheng.mall.bean.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
	@Query("select o from Order o where o.user.uid=:uid")
	List<Order> findOrdersByUid(@Param("uid") Integer uid);

	@Query("select o from Order o where o.oid=:oid")
	Order findOrderByOid(@Param("oid") String oid);

	@Query(value = "select count(*) from Order o where o.user.uid= ?")
	Integer findCountUid(@Param("uid") Integer uid);

}
