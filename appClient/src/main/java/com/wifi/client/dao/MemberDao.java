package com.wifi.client.dao;

import com.wifi.client.model.Member;

public interface MemberDao {

	/* 회원여부 확인 후 객체 가져오기: 카카오로그인한 이메일 ㅡ> db에 있는지 확인 */
	int checkMember(String checkId);

	/* 회원정보 가져오기 */
	Member getMember(String checkId);
	
	/* 회원가입 */
	int regMember(Member member);

	
}
