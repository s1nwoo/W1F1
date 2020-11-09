package com.wifi.order.model;

import java.util.List;

public class ItemDTOView {

	
	private int itemTotalCount;
	private int currentPageNumber;
	private List<ItemDTO> itemList;
	private int pageTotalCount;
	private int itemCountPerPage;
	private int startRow;
	
	
	public ItemDTOView(int itemTotalCount, int currentPageNumber, List<ItemDTO> itemList, int itemCountPerpage,
			int startRow) {
		this.itemTotalCount = itemTotalCount;
		this.currentPageNumber = currentPageNumber;
		this.itemList = itemList;
		this.itemCountPerPage = itemCountPerpage;
		this.startRow = startRow;
		calcPageTotalCount();
	}
	
	public void calcPageTotalCount() {

		if (itemTotalCount == 0) {
			pageTotalCount = 0;
		} else {
			pageTotalCount = itemTotalCount / itemCountPerPage;
			System.out.println("아이템 토탈 % 아이템퍼페이지의 나머지 : " + itemTotalCount % itemCountPerPage);
			if (itemTotalCount % itemCountPerPage > 0) {
				pageTotalCount++;
			}
			System.out.println("페이지토탈카운트 : " + pageTotalCount);
		}

	}
	
	
	// only getter
	public int getItemTotalCount() {
		return itemTotalCount;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public List<ItemDTO> getItemList() {
		return itemList;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public int getItemCountPerPage() {
		return itemCountPerPage;
	}

	public int getStartRow() {
		return startRow;
	}

	
	
	@Override
	public String toString() {
		return "ItemListView [itemTotalCount=" + itemTotalCount + ", currentPageNumber=" + currentPageNumber
				+ ", itemList=" + itemList + ", pageTotalCount=" + pageTotalCount + ", itemCountPerPage="
				+ itemCountPerPage + ", startRow=" + startRow + "]";
	}
	
}
