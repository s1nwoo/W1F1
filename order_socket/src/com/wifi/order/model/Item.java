package com.wifi.order.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

// 공구글 정보 : VO
public class Item {

	private int iidx; // 게시물 번호 -PK
	private String title; // 게시글 제목
	private int price; // 가격
	private int count_m; // 모집 인원
	private int count_w; // 대기 정원
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp regdate; // 게시글 등록일시(현재시간)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp receive; // 물품수령일시
	private String addr; // 판매처
	private String location_y; // 좌표y
	private String location_x; // 좌표x
	private String content; // 게시글 본문
	private String photo; // 첨부사진
	private int state; // 게시글 현황
	private int view_count; // 조회수
	private int category; // 판매물품 카테고리 : 0 전체, 1과일/채소, 2 육류/해산물, 3 생필품/기타
	private int midx; // 작성자(회원번호) - FK

	public Item() {
	}

	public Item(int iidx, String title, int price, int count_m, int count_w, Timestamp regdate, Timestamp receive,
			String addr, String location_y, String location_x, String content, String photo, int state, int view_count,
			int category, int midx) {
		super();
		this.iidx = iidx;
		this.title = title;
		this.price = price;
		this.count_m = count_m;
		this.count_w = count_w;
		this.regdate = regdate;
		this.receive = receive;
		this.addr = addr;
		this.location_y = location_y;
		this.location_x = location_x;
		this.content = content;
		this.photo = photo;
		this.state = state;
		this.view_count = view_count;
		this.category = category;
		this.midx = midx;
	}

	// ItemRequest ㅡ> Item
	// ItemRegRequest에서 객체로 변환하기 위한 생성자(iidx, regdate없음)
	// iidx=0, regdate=null, photo=null
	public Item(String title, int price, int count_m, int count_w, Timestamp receive, String addr, String location_y,
			String location_x, String content, int state, int view_count, int category, int midx) {
		this(0, title, price, count_m, count_w, null, receive, addr, location_y, location_x, content, null, state,
				view_count, category, midx);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getIidx() {
		return iidx;
	}

	public void setIidx(int iidx) {
		this.iidx = iidx;
	}

	public int getCount_m() {
		return count_m;
	}

	public void setCount_m(int count_m) {
		this.count_m = count_m;
	}

	public int getCount_w() {
		return count_w;
	}

	public void setCount_w(int count_w) {
		this.count_w = count_w;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public Timestamp getReceive() {

		return receive;
	}

	// @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	public void setReceive(Timestamp receive) {

		this.receive = receive;
	}

//	// java.sql.Date → java.util.Date 변환	
//	//@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//	@JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss", timezone = "Asia/Seoul")
//	public java.util.time getToDate(){		//${member.toDate}
//		return new java.util.Ti(receive.getTime());	// getTime() : 기준 날짜이후 밀리세컨단위 long타입으로 리턴
//	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLocation_y() {
		return location_y;
	}

	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}

	public String getLocation_x() {
		return location_x;
	}

	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getMidx() {
		return midx;
	}

	public void setMidx(int midx) {
		this.midx = midx;
	}

	// java.sql.Date -> java.util.Date

	public java.util.Date getToDate() {// ${member.toDate}
		return new java.util.Date(regdate.getTime());
	}

	@Override
	public String toString() {
		return "Item [iidx=" + iidx + ", title=" + title + ", price=" + price + ", count_m=" + count_m + ", count_w="
				+ count_w + ", regdate=" + regdate + ", receive=" + receive + ", addr=" + addr + ", location_y="
				+ location_y + ", location_x=" + location_x + ", content=" + content + ", photo=" + photo + ", state="
				+ state + ", view_count=" + view_count + ", category=" + category + ", midx=" + midx + "]";
	}

}
