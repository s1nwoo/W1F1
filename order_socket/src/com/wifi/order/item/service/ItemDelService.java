package com.wifi.order.item.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;

@Service
public class ItemDelService {
	
	private ItemDao dao; 
	
	@Autowired
	private SqlSessionTemplate template;
	
	
	public int delItem(int iidx) {
		
		dao = template.getMapper(ItemDao.class);
		
		return dao.delItem(iidx);
	};

};
