package com.wifi.order.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;
import com.wifi.order.model.Item_rvb;

@Service
public class MyBuyerReviewService {
	
	private ItemDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public int reviewBuyer(Item_rvb rvb) {
		
		dao = template.getMapper(ItemDao.class);
		System.out.println("구매자 평점등록  service");
		
		return dao.reviewBuyer(rvb);
	}
	
	
	
	
	

}
