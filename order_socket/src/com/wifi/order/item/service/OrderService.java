package com.wifi.order.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.OrderDao;
import com.wifi.order.model.Order;

@Service
public class OrderService {

	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public Order getOrderInfo(int oidx) {
		
		dao = template.getMapper(OrderDao.class);
		
		return dao.getOrderInfo(oidx); 
	}
	
}
