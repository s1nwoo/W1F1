package com.wifi.order.dao;

public interface AlarmDao {
	
	// 판매현황쪽 알람 끄기 : 상태값 0으로 변경
	int cancleSellerAlarm(int midx, int iidx);
	
	// 구매현황쪽 알람 끄기 : 상태값 0으로 변경
	int cancleBuyerAlarm(int midx, int iidx);
	
	// 판매현황쪽 알람 행생성
	void insertSellerAlarm(int midx, int iidx);
	
	// 구매현황쪽 알람 행생성
	void insertBuyerAlarm(int midx, int iidx);

	// 판매현황쪽 알람 행삭제
	void deleteSellerAlarm(int iidx);
	
	// 구매현황쪽 알람 행생성
	void deleteBuyerAlarm(int midx, int iidx);
	
	// 판매현황 쪽 알림 상태값 가져오기
	int getItemState(int midx, int iidx);
	
	// 구매현황 쪽 알림 상태값 가져오기
	int getOrderState(int midx, int iidx);
	
	// 참여자 선정시 해당 참여테이블 상태값 1로 바꾸기
	void changeBuyerAlarm(int midx, int iidx);
	
	// 판매자 테이블 상태값 1로 바꾸기
	void changeSellerAlarm(int midx, int iidx);

	void cancleSellerConfirmAlarm(int midx, int iidx);
}
