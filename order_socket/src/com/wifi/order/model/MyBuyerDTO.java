package com.wifi.order.model;

// 공구 구매자에 대한 평점정보 : 판매자의 내판매글 참여자보기 - 화면출력용 DTO
public class MyBuyerDTO {
	
	private int iidx;			// 판매글 번호 -FK
	private int buyer;		  	// 구매자 번호  -FK
	private String name;	  	// 구매자 이름 
	private int rvb_avg;		// 구매자 평점
	private int rvb_totalRow;	// 구매자 총 평점개수
	private int oidx;			// 구매자 주문번호 - FK
	private int ostate;			// 구매자 주문상태값 : 0 참여중, 1 거절, -1 null
	private int pstate;			// 구매자 결제상태값 : 0 미수령, 1 수령, -1 null
	private String qr;			// 구매자 결제QR : -1 null
	
	
	public MyBuyerDTO() {
	}


	public MyBuyerDTO(int iidx, int buyer, String name, int rvb_avg, int rvb_totalRow, int oidx, int ostate, int pstate,
			String qr) {
		this.iidx = iidx;
		this.buyer = buyer;
		this.name = name;
		this.rvb_avg = rvb_avg;
		this.rvb_totalRow = rvb_totalRow;
		this.oidx = oidx;
		this.ostate = ostate;
		this.pstate = pstate;
		this.qr = qr;
	}


	public int getIidx() {
		return iidx;
	}


	public void setIidx(int iidx) {
		this.iidx = iidx;
	}


	public int getBuyer() {
		return buyer;
	}


	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRvb_avg() {
		return rvb_avg;
	}


	public void setRvb_avg(int rvb_avg) {
		this.rvb_avg = rvb_avg;
	}


	public int getRvb_totalRow() {
		return rvb_totalRow;
	}


	public void setRvb_totalRow(int rvb_totalRow) {
		this.rvb_totalRow = rvb_totalRow;
	}


	public int getOidx() {
		return oidx;
	}


	public void setOidx(int oidx) {
		this.oidx = oidx;
	}


	public int getOstate() {
		return ostate;
	}


	public void setOstate(int ostate) {
		this.ostate = ostate;
	}


	public int getPstate() {
		return pstate;
	}


	public void setPstate(int pstate) {
		this.pstate = pstate;
	}


	public String getQr() {
		return qr;
	}


	public void setQr(String qr) {
		this.qr = qr;
	}


	@Override
	public String toString() {
		return "MyBuyerDTO [iidx=" + iidx + ", buyer=" + buyer + ", name=" + name + ", rvb_avg=" + rvb_avg
				+ ", rvb_totalRow=" + rvb_totalRow + ", oidx=" + oidx + ", ostate=" + ostate + ", pstate=" + pstate
				+ ", qr=" + qr + "]";
	}


}
