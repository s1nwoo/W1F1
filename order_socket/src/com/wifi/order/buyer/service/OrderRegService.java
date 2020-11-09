package com.wifi.order.buyer.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.alarm.service.AlarmService;
import com.wifi.order.dao.OrderDao;

//공구 참여신청
@Service
public class OrderRegService {

	private OrderDao dao;
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private SqlSessionTemplate template;
	
	public int regOrder(int midx, int iidx) {
		
		dao = template.getMapper(OrderDao.class);
		
		System.out.println("공구 참여신청 service");
		
		int overlap = 0;
		int remain = 0;
		int result = 0;
		
		// 공구 참여신청 전 - 참여자 중복확인
		overlap = dao.checkOrderOverlap(midx, iidx);
		
		switch(overlap) {
		
			// 중복x ㅡ> 잔여인원 확인
			case 0: 
				remain = dao.checkItemRemain(iidx);
				
				// 잔여인원o ㅡ> 참여신청 진행
				if(remain > 0) {
					result = dao.regOrder(midx, iidx);
					break;
				
				// 잔여인원 x 
				} else {
					result = -2;
					break;
				}
			
			// 중복o
			case 1:
				result= -1;  
				break;
		};
		
		// 가람 수정 0921: 참여신청 시 alarm_b 테이블에 행추가
		alarmService.insertAlarm(midx, iidx, "buyer");
		
		return result;
	};



};
