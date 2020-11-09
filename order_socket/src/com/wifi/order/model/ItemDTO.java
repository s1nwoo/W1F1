package com.wifi.order.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

// 공구글 정보 DTO
public class ItemDTO {

	private int iidx; // 게시물 번호 -PK
	private String title; // 게시글 제목
	private int price; // 가격
	private int count_m; // 모집 인원
	private int count_w; // 대기 정원
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp regdate; // 게시글 등록일시(현재시간)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp receive; // 물품수령일시
	private int receive_check; // 오늘기준 마감 확인
	private String addr; // 판매처
	private String location_y; // 좌표y
	private String location_x; // 좌표x
	private String content; // 게시글 본문
	private String photo; // 첨부사진
	private int istate; // 게시글 현황
	private int view_count; // 조회수
	private int category; // 판매물품 카테고리 : 0 과일, 1 육류, 2 해산물, 3 채소, 4 생필품, 5 음료, 6 전체
	private int midx; // 작성자(회원번호) - FK
	private String name; // 작성자 닉네임
	private float rvs_avg; // 작성자 평균평점 -Item_rvs table join
	private int rvs_totalRow; // 작성자 총평점개수 -Item_rvs table join

	public ItemDTO() {
	}

	public ItemDTO(int iidx, String title, int price, int count_m, int count_w, Timestamp regdate, Timestamp receive,
			int receive_check, String addr, String location_y, String location_x, String content, String photo,
			int istate, int view_count, int category, int midx, String name, float rvs_avg, int rvs_totalRow) {
		this.iidx = iidx;
		this.title = title;
		this.price = price;
		this.count_m = count_m;
		this.count_w = count_w;
		this.regdate = regdate;
		this.receive = receive;
		this.receive_check = receive_check;
		this.addr = addr;
		this.location_y = location_y;
		this.location_x = location_x;
		this.content = content;
		this.photo = photo;
		this.istate = istate;
		this.view_count = view_count;
		this.category = category;
		this.midx = midx;
		this.name = name;
		this.rvs_avg = rvs_avg;
		this.rvs_totalRow = rvs_totalRow;
	}

	public int getIidx() {
		return iidx;
	}

	public void setIidx(int iidx) {
		this.iidx = iidx;
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

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public Timestamp getReceive() {
		return receive;
	}

	public void setReceive(Timestamp receive) {
		this.receive = receive;
	}

	public int getReceive_check() {
		return receive_check;
	}

	public void setReceive_check(int receive_check) {
		this.receive_check = receive_check;
	}

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

	public int getIstate() {
		return istate;
	}

	public void setIstate(int istate) {
		this.istate = istate;
	}

	public int getView_count() {
		return view_count;
	}

	public void setView_count(int view_count) {
		this.view_count = view_count;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRvs_avg() {
		return rvs_avg;
	}

	public void setRvs_avg(float rvs_avg) {
		this.rvs_avg = rvs_avg;
	}

	public int getRvs_totalRow() {
		return rvs_totalRow;
	}

	public void setRvs_totalRow(int rvs_totalRow) {
		this.rvs_totalRow = rvs_totalRow;
	}

	@Override
	public String toString() {
		return "ItemDTO [iidx=" + iidx + ", title=" + title + ", price=" + price + ", count_m=" + count_m + ", count_w="
				+ count_w + ", regdate=" + regdate + ", receive=" + receive + ", receive_check=" + receive_check
				+ ", addr=" + addr + ", location_y=" + location_y + ", location_x=" + location_x + ", content="
				+ content + ", photo=" + photo + ", istate=" + istate + ", view_count=" + view_count + ", category="
				+ category + ", midx=" + midx + ", name=" + name + ", rvs_avg=" + rvs_avg + ", rvs_totalRow="
				+ rvs_totalRow + "]";
	}

}
