package com.wifi.order.item.service;

import java.util.HashMap;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.order.dao.ItemDao;


//나의 공구판매현황[모집중] - 참여자 구매자로 선정하기
@Service
public class MyBuyerSelectService {
	
	private ItemDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

//	public int selectBuyer(int iidx,  Object[] buyer) {
//		
//		dao = template.getMapper(ItemDao.class);
//		System.out.println("참여자 구매자로 선정 service");
//		
//		return dao.selectBuyer(iidx, buyer);
//	}

	public int selectBuyer(HashMap<String, Object> buyerArr) {
		
		dao = template.getMapper(ItemDao.class);
		System.out.println("참여자 구매자로 선정 service");
		
		return dao.selectBuyer(buyerArr);
		
	}

}
