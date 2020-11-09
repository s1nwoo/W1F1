package com.wifi.order.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;

@Service
public class MyBuyerCntService {
	
	private ItemDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public int cntBuyer(int iidx) {
		
		dao = template.getMapper(ItemDao.class);
		System.out.println("내 판매글 - 현재 참여자수  service");
		
		return dao.cntBuyer(iidx);
	}

}
