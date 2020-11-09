package com.wifi.order.buyer.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.order.dao.OrderDao;

// 나의 공구구매현황[구매완료] - 글삭제
@Service
public class OrderDelService {

	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public int delOrder(int oidx, int pidx) {
		
		dao = template.getMapper(OrderDao.class);
		System.out.println("내 구매글 삭제 service");
		
		int result = 0;
		result = dao.delOrder(oidx);
		result += dao.delPurchase(pidx);
		
		return result;
		
	}

	
	
	
}
