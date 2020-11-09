package com.wifi.order.buyer.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.order.dao.OrderDao;

//나의 공구구매현황[구매완료] - 글숨김
@Service
public class OrderHideService {
	
	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	
	public int hideOrder(int oidx) {
		
		dao = template.getMapper(OrderDao.class);
		System.out.println("내 구매글 숨김 service");
		
		return dao.hideOrder(oidx);
	}

}
