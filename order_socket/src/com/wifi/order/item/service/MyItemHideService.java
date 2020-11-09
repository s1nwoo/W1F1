package com.wifi.order.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;


@Service
public class MyItemHideService {

	private ItemDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int hideMyItem(int iidx) {
		
		dao = template.getMapper(ItemDao.class);
		System.out.println("내 판매글 숨김 service");
		
		return dao.hideMyItem(iidx);
	}

}
