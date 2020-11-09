package com.wifi.order.model;

import java.sql.Timestamp;

// 참여신청 정보
public class Order {

	private int oidx;				// 주문번호 -PK
	private int state;				// 주문상태 : 0 참여중(기본값), 1 거절 - 판매자의 선택에 따라 변경됨. 
	private int midx;				// 주문한 회원번호 -FK
	private int iidx;				// 게시물 번호 -FK
	private Timestamp orderdate; 	// 주문날짜

	
	public Order() {
	}

	public Order(int oidx, int state, int midx, int iidx, Timestamp orderdate) {
		this.oidx = oidx;
		this.state = state;
		this.midx = midx;
		this.iidx = iidx;
		this.orderdate = orderdate;
	}

	public int getOidx() {
		return oidx;
	}

	public void setOidx(int oidx) {
		this.oidx = oidx;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
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
		return "Order [oidx=" + oidx + ", state=" + state + ", orderdate=" + orderdate + ", midx=" + midx + ", iidx="
				+ iidx + "]";
	}
	
	
	
	
	
	
	
	
}
