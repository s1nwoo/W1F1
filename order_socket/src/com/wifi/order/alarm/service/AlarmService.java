package com.wifi.order.alarm.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.AlarmDao;


@Service
public class AlarmService {
	
	private AlarmDao dao;
	
	@Autowired
	private SqlSessionTemplate template;


	public void cancleAlarm(int midx, int iidx, String type) {
		dao = template.getMapper(AlarmDao.class);
		
		if(type.equals("seller")) {
			dao.cancleSellerConfirmAlarm(midx, iidx);
		} else {
			dao.cancleBuyerAlarm(midx, iidx);
		}
	}
	
	public void insertAlarm(int midx, int iidx, String type) {
		dao = template.getMapper(AlarmDao.class);
		
		if(type.equals("seller")) {
			dao.insertSellerAlarm(midx, iidx);
		} else {
			dao.insertBuyerAlarm(midx, iidx);
		}
	}

	public void deleteAlarm(int midx, int iidx, String type) {
		dao = template.getMapper(AlarmDao.class);
		
		if(type.equals("seller")) {
			dao.deleteSellerAlarm(iidx);
		} else {
			dao.deleteBuyerAlarm(midx, iidx);
		}
		
	}

	public void changeBuyerAlarm(int midx, int iidx) {
		dao = template.getMapper(AlarmDao.class);
		
		dao.changeBuyerAlarm(midx, iidx);
		
	}

}
