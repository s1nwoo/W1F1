package com.wifi.order.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;

@Service
public class QRService {
	
	private ItemDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public String getQR(int midx, int iidx) {
		
		dao = template.getMapper(ItemDao.class);
		System.out.println("QR보기  service");
		
		return dao.getQR(midx,iidx);
	}

}
