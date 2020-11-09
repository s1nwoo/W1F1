package com.wifi.order.dao;

import java.util.List;

import com.wifi.order.model.Item_rvs;
import com.wifi.order.model.MyItemDTO;
import com.wifi.order.model.Order;

public interface OrderDao {
	
// 내 프로필 관련 --------------------------------------------	
	
	Order getOrderInfo(int oidx);

	// 내 판매자 평균평점 
	float getRvs(int midx);
	
	// 내 판매자 평균평점 
	float getRvb(int midx); 

	
	
// 공구신청 관련 --------------------------------------------	

	// 공구 참여신청
	int regOrder(int midx, int iidx);

	// 공구 참여신청시 체크1 - 참여자 중복확인 
	int checkOrderOverlap(int midx, int iidx);
	
	// 공구 참여신청시 체크2 - 잔여인원 확인
	int checkItemRemain(int iidx);
	
	
	
	
// 내 공구 구매현황 관련 ----------------------------------------	
	
	// 내 구매글 
	List<MyItemDTO> getMyOrder(int midx);

	
	// 나의 공구구매현황[참여중][다음기회에..] - 참여취소(글삭제)
	int cancleOrder(int oidx);

	
	// 나의 공구구매현황[구매자] - 판매자 평점등록
	int reviewSeller(Item_rvs rvs);

	
	// 나의 공구구매현황[구매완료] - 글숨김
	int hideOrder(int iidx);

	
	// 나의 공구구매현황[구매완료] - 글삭제 
	// 주문글 삭제
	int delOrder(int oidx);

	// 나의 공구구매현황[구매완료] - 글삭제
	// 결제글 삭제
	int delPurchase(int pidx);

	
	int getOidx(int midx, int iidx);

	
	
	

}
