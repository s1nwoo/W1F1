package com.wifi.order.item.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.order.dao.ItemDao;
import com.wifi.order.model.Item;
import com.wifi.order.model.ItemDTO;
import com.wifi.order.model.ItemDTOView;

@Service
public class ItemlistService {

	private ItemDao dao;

	@Autowired
	private SqlSessionTemplate template;

	// 추천 공구 리스트 : 최신순 정렬
	public List<ItemDTO> getRecomItemlist() {

		dao = template.getMapper(ItemDao.class);
		System.out.println("추천 공구 리스트 최신순정렬  service");

		return dao.getRecomItemlist();

	};

	// 0917 윤원 수정 : 공구 검색을 위한 Map 추가
	// 일반 공구 리스트 : 최신순 정렬
	public ItemDTOView getItemlist(HttpServletRequest request) {

		dao = template.getMapper(ItemDao.class);
		System.out.println("일반 공구 리스트 최신순정렬  service");

		Map<String, Object> search = new HashMap<String, Object>();

		// 검색 데이터 : searchType, keyword
		String searchType = request.getParameter("searchType");
		String istate = request.getParameter("istate");
		String keyword = request.getParameter("keyword");
		String count = request.getParameter("count");

		System.out.println("count : " + count);
		System.out.println("keyword : " + keyword);
		System.out.println("istate : " + istate);

		// 값이 공백이라면 null로 전달해주기
		if (searchType != null && !searchType.isEmpty()) {
			search.put("searchType", searchType);

		}

		if (keyword != null && !keyword.isEmpty()) {
			if (searchType == "category") {
				int categoryNum = Integer.parseInt(keyword);
				if (categoryNum == 7) {
					categoryNum = 0;
					search.put("keyword", categoryNum);
					System.out.println("7일 때 categoryNum : " + categoryNum);
				} else {
					search.put("keyword", categoryNum);
					System.out.println("7이 아닐 때 categoryNum : " + categoryNum);
				}
			} else {
				search.put("keyword", keyword);
			}
		}

		if (istate != null && !istate.isEmpty()) {
			search.put("istate", istate);
		}

		ItemDTOView itemDTOView = null;

		// 전체 게시물 개수 구하기
		int itemTotalCnt = dao.totalCount(search);

		// 한 페이지 당 노출할 게시물의 개수
		int ITEM_CNT_PER_PAGE = 10;
		if (count != null) {
			try {
				ITEM_CNT_PER_PAGE = Integer.parseInt(count);
				System.out.println("ITEM_CNT_PER_PAGE : " + ITEM_CNT_PER_PAGE);
			} catch (NumberFormatException e) {
				System.out.println("숫자 타입의 문자열이 전달되지 않아 예외 발생");
			}
		}

		// 현재 페이지 번호
		int currentPageNumber = 1;
		String page = request.getParameter("page");
		System.out.println("▶page : " + page);
		System.out.println("▶searchType : " + searchType);
		System.out.println("▶istate : " + istate);
		System.out.println("▶keyword : " + keyword);

		if (page != null) {
			try {
				currentPageNumber = Integer.parseInt(page);
			} catch (NumberFormatException e) {
				System.out.println("숫자 타입의 문자열이 전달되지 않아 예외 발생");
			}
		}

		// 게시물의 첫번째 행의 index
		int startRow = 0;

		// 한 페이지 당 노출할 리스트
		List<ItemDTO> itemList = null;

		if (itemTotalCnt > 0) {
			startRow = (currentPageNumber - 1) * ITEM_CNT_PER_PAGE;

			System.out.println("startRow ▶" + startRow);
			search.put("startRow", startRow);
			search.put("count", ITEM_CNT_PER_PAGE);

			itemList = dao.getItemlist(search);
			System.out.println("itemList ▶" + itemList);

		} else {
			currentPageNumber = 0;
			itemList = Collections.emptyList();
		}

		itemDTOView = new ItemDTOView(itemTotalCnt, currentPageNumber, itemList, ITEM_CNT_PER_PAGE, startRow);

		System.out.println("▶ listView : " + itemDTOView);

		return itemDTOView;
	}

};
