package com.wifi.order.model;

import java.sql.Timestamp;

// 결제정보
public class Purchase {
	
	private int pidx;				// 결제번호 -Pk
	private String qr;				// QR코드
	private int state;				// 결제상태 : 0 미수령(기본값), 1 수령
	private Timestamp paydate;		// 결제완료일
	private int oidx;				// 주문번호 -FK
	
	
	public Purchase() {
	}
	
	
	public Purchase(int pidx, String qr, int state, Timestamp paydate, int oidx) {
		this.pidx = pidx;
		this.qr = qr;
		this.state = state;
		this.paydate = paydate;
		this.oidx = oidx;
	}


	public int getPidx() {
		return pidx;
	}


	public void setPidx(int pidx) {
		this.pidx = pidx;
	}


	public String getQr() {
		return qr;
	}


	public void setQr(String qr) {
		this.qr = qr;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}


	public Timestamp getPaydate() {
		return paydate;
	}


	public void setPaydate(Timestamp paydate) {
		this.paydate = paydate;
	}


	public int getOidx() {
		return oidx;
	}


	public void setOidx(int oidx) {
		this.oidx = oidx;
	}


	@Override
	public String toString() {
		return "Purchase [pidx=" + pidx + ", qr=" + qr + ", state=" + state + ", paydate=" + paydate + ", oidx=" + oidx
				+ "]";
	}
	
	
	
	
	
	
}
