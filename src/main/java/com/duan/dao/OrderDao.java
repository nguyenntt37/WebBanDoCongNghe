package com.duan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.duan.entity.Order;


public interface OrderDao extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o Where o.account.username=?1")
	List<Order> findByUsername(String username);

}
