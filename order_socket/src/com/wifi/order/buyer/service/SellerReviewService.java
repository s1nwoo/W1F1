package com.wifi.order.buyer.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.order.dao.OrderDao;
import com.wifi.order.model.Item_rvs;

//나의 공구구매현황[구매자] - 판매자 평점등록
@Service
public class SellerReviewService {
	
	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public int reviewSeller(Item_rvs rvs) {

		dao = template.getMapper(OrderDao.class);
		System.out.println("판매자 평점등록 service");
		
		return dao.reviewSeller(rvs);
	}

}
