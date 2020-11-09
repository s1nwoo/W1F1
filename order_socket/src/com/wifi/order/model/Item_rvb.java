package com.wifi.order.model;

// 공구 구매자에 대한 평점정보 : VO
public class Item_rvb {
	
	private int irbidx;		// 공구구매자 평점번호 -PK
	private int score_b;	// 구매자 평점
	private int midx;		// 회원번호(평가자=판매자) -FK
	private int iidx;		// 게시물 번호 -FK
	
	
	public Item_rvb() {
	}


	public Item_rvb(int irbidx, int score_b, int midx, int iidx) {		
		this.irbidx = irbidx;
		this.score_b = score_b;
		this.midx = midx;
		this.iidx = iidx;
	}
	
	public int getIrbidx() {
		return irbidx;
	}


	public void setIrbidx(int irbidx) {
		this.irbidx = irbidx;
	}


	public int getScore_b() {
		return score_b;
	}


	public void setScore_b(int score_b) {
		this.score_b = score_b;
	}


	public int getMidx() {
		return midx;
	}


	public void setMidx(int midx) {
		this.midx = midx;
	}


	public int getIidx() {
		return iidx;
	}


	public void setIidx(int iidx) {
		this.iidx = iidx;
	}


	@Override
	public String toString() {
		return "Item_rvb [irbidx=" + irbidx + ", score_b=" + score_b + ", midx=" + midx + ", iidx=" + iidx + "]";
	}
	
	
	
	
	
	

}
