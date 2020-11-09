package com.wifi.comment.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

// 댓글 정보
public class Comment {
	
	private int cidx;			// 댓글 번호 -PK
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp regdate;	// 등록일 (현재시간)
	private String content;		// 댓글내용
	private int state;			// 댓글상태 : 0 댓글게시중(기본값), 1 삭제처리
	private int midx;			// 회원 번호 -FK
	private int iidx;			// 게시물 번호 -FK
	
	
	public Comment() {
	}
	
	
	public Comment(int cidx, Timestamp regdate, String content, int state, int midx, int iidx) {
		this.cidx = cidx;
		this.regdate = regdate;
		this.content = content;
		this.state = state;
		this.midx = midx;
		this.iidx = iidx;
	}
	
	
	public Comment(String content, int midx, int iidx) {
		this.content = content;
		this.midx = midx;
		this.iidx = iidx;
	}
	
	
	public int getCidx() {
		return cidx;
	}
	
	public void setCidx(int cidx) {
		this.cidx = cidx;
	}
	
	public Timestamp getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
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
		return "Comment [cidx=" + cidx + ", regdate=" + regdate + ", content=" + content + ", state=" + state
				+ ", midx=" + midx + ", iidx=" + iidx + "]";
	}
	
	
	
	
	
}
