package com.wifi.order.item.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.AlarmDao;
import com.wifi.order.dao.ItemDao;
import com.wifi.order.model.MyItemDTO;

// 내 판매글
@Service
public class MyItemService {

	private ItemDao dao;
	private AlarmDao alarmDao;

	@Autowired
	private SqlSessionTemplate template;

	public List<MyItemDTO> getMyItem(int midx) {

		dao = template.getMapper(ItemDao.class);
		alarmDao = template.getMapper(AlarmDao.class);

		System.out.println("내 판매글 리스트  service");

		List<MyItemDTO> list = dao.getMyItem(midx);

		for (int i = 0; i < list.size(); i++) {

			// 공구글 index
			int iidx = list.get(i).getIidx();

			// 판매상태. 수령일마감과 결제상태 확인 ㅡ> 상태라벨 set
			int receiveCheck = list.get(i).getReceive_check();
			int pstate = list.get(i).getPstate();
			int ostate = list.get(i).getOstate();

			// 마감 전. 결제 null
			if (receiveCheck >= 0 && pstate == -1) {
				
				int alarmState = alarmDao.getItemState(midx, iidx);
				int cntBuyer = dao.cntBuyer(iidx);
				int count_m = list.get(i).getCount_m();
				
				if(alarmState == 0 && cntBuyer >= count_m) {
					// alarm_s의 해당 게시글의 state를 1로 변경
					alarmDao.changeSellerAlarm(midx, iidx);
					list.get(i).setLabel("모집중1");
				} else if(alarmState == 2 && cntBuyer < count_m) {
					// alarm_s의 해당 게시글의 state를 0으로 변경
					alarmDao.cancleSellerAlarm(midx, iidx);
					list.get(i).setLabel("모집중");
				} else if(alarmState == 1) {
					list.get(i).setLabel("모집중1");
				} else {
					list.get(i).setLabel("모집중");
				}
				
				// 모집중이면 현재 참여자수 set
				list.get(i).setCntBuyer(cntBuyer);
				

				continue;

				// 마감 전. 결제 있음
			} else if (receiveCheck >= 0 && pstate != -1) {
				list.get(i).setLabel("모집완료");
				continue;

				// 마감됨. 결제 있음
			} else if (receiveCheck < 0 && pstate != -1) {
				
				int alarmState = alarmDao.getItemState(midx, iidx);
				
				if (alarmState == 1) {
					list.get(i).setLabel("판매완료1");
				} else {
					list.get(i).setLabel("판매완료");
				}
				continue;

				// 마감됨. 결제 null
			} else if (receiveCheck < 0 && pstate == -1) {

				if (ostate == 0) {
					int reject = dao.autoRejectBuyer1(iidx);
					System.out.println(iidx + "번 글 판매실패로  자동참여거절! ㅡ> 처리결과 :" + reject);
				}

				list.get(i).setLabel("판매실패");
				continue;

			}
			;
		}
		;

		return list;
	};

};
