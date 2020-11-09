package com.wifi.order.model;

// 회원정보
public class Member {
	
	private int midx;		// 회원번호 -PK
	private String id;		// 이메일
	private String name;	// 닉네임
	private String addr;	// 주소
	private String phone;	// 전화번호
	private int type;		// 회원구분 : 0 회원(기본값), 1 탈퇴, 2 강퇴, 3 관리자
	
	
	public Member() {
	}


	public Member(int midx, String id, String name, String addr, String phone, int type) {
		this.midx = midx;
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.type = type;
	}


	public int getMidx() {
		return midx;
	}


	public void setMidx(int midx) {
		this.midx = midx;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Member [midx=" + midx + ", id=" + id + ", name=" + name + ", addr=" + addr + ", phone=" + phone
				+ ", type=" + type + "]";
	}
	
	
	
	
	
	
}
