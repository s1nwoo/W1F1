package com.wifi.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wifi.order.model.Item;
import com.wifi.order.model.ItemDTO;
import com.wifi.order.model.Item_rvb;
import com.wifi.order.model.MyBuyerDTO;
import com.wifi.order.model.MyItemDTO;

public interface ItemDao {

//	공구 리스트 관련----------------------------------

	// 추천 공구 리스트 : 최신순 정렬 (기본정렬)
	List<ItemDTO> getRecomItemlist();

	// 0917 추가 - totalCount
	// 일반 공구 리스트 : 최신순 정렬 (기본정렬)
	List<ItemDTO> getItemlist(Map<String, Object> search);

	int totalCount(Map<String, Object> search);

	// 공구 상세보기
	ItemDTO viewItem(int iidx);

//	공구 등록. 수정. 삭제 관련---------------------------

	// 공구 등록
	int regItem(Item item);

	// 공구 등록 후 iidx 반환
	// int regItemIidx(Map map);
	int regItemIidx(int midx);

	// 조회수 +1
	int viewCountUp(int iidx);

	// 공구 삭제
	int delItem(int iidx);

//	내 공구 판매현황 관련------------------------------

	// 나의 공구판매현황[모집중] - 현재 참여자수 (서버에서 처리할떄)
	int cntBuyer(int iidx);

	// 내 판매글
	List<MyItemDTO> getMyItem(int midx);

	// 내 판매글 참여자리스트
	List<MyBuyerDTO> getMyItemBuyer(int iidx);

//	내 공구 판매현황 관련------------------------------

	// 나의 공구판매현황[모집중] - 참여자 '구매자로 선정'하기
	// int selectBuyer(int iidx, Object[] buyer);
	int selectBuyer(HashMap<String, Object> buyerArr);

	// 나의 공구판매현황[모집중] - 참여자 '거절'하기
	int rejectBuyer(int oidx);

	// 나의 공구판매현황[모집중] - 참여자 '자동거절'처리
	// int autoRejectBuyer(int iidx, Object[] buyer);
	int autoRejectBuyer(HashMap<String, Object> rejectArr);

	// 나의 공구판매현황[판매실패] - 참여자 '자동거절'처리 (서버에서 처리할떄)
	int autoRejectBuyer1(int iidx);

	// 나의 공구판매현황[판매완료] - 구매자 평점등록
	int reviewBuyer(Item_rvb rvb);

	// 나의 공구판매현황[판매완료, 판매실패] - 내 판매글 숨김
	int hideMyItem(int iidx);

	// QR보기
	String getQR(int midx, int iidx);

	// QR발급..

}
