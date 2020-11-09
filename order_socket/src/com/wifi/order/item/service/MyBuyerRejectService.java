package com.wifi.order.item.service;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;

@Service
public class MyBuyerRejectService {
	
	private ItemDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	// 나의 공구판매현황[모집중] - 참여자 거절하기 (한명씩)
	public int rejectBuyer(int oidx) {
	
		dao = template.getMapper(ItemDao.class);
		System.out.println("참여자 거절 service");
		
		return dao.rejectBuyer(oidx);
	}

	
	// 나의 공구판매현황[모집중. 판매실패] - 참여자 자동거절처리  (배열로)
//	public int autoRejectBuyer(int iidx, Object[] buyer) {
//		
//		dao = template.getMapper(ItemDao.class);
//		System.out.println("참여자 자동거절 service");
//		
//		return dao.autoRejectBuyer(iidx, buyer);
//	}

	// 나의 공구판매현황[모집중. 판매실패] - 참여자 자동거절처리  (배열로)
	public int autoRejectBuyer(HashMap<String, Object> rejectArr) {
		
		dao = template.getMapper(ItemDao.class);
		System.out.println("참여자 자동거절 service");
		return dao.autoRejectBuyer(rejectArr);
		
	}

}
