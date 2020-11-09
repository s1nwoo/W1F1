package com.wifi.order.buyer.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.order.dao.OrderDao;

// 프로필영역
@Service
public class MyProfileService {
	
	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	
	//public HashMap<String, String> getProfile(int midx) {
	public float[] getProfile(int midx) {

		dao = template.getMapper(OrderDao.class);
		
		//HashMap<String, String> map = new HashMap<String, String>();
		float rvs = dao.getRvs(midx);
		float rvb = dao.getRvb(midx);
		//map.put("rvs", rvs);
		//map.put("rvb", rvb);
		
		float[] arr = {rvs, rvb};
		System.out.println("프로필 내 평점 배열확인 . seller : "+arr[0]+",buyer : "+arr[1]);
		
		return arr;
	}

}
