package com.wifi.order.buyer.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.OrderDao;

@Service
public class OrderCancleService {

	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	
	public int cancleOrder(int oidx) {
		
		dao = template.getMapper(OrderDao.class);
		System.out.println("참여신청 취소 service");
		
		return dao.cancleOrder(oidx);
	}

}
