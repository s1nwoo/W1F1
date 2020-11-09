package com.wifi.client.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wifi.client.dao.MemberDao;
import com.wifi.client.model.Member;

/* 회원여부 체크 */
@Service
public class MemberCheckService {

	private MemberDao dao;

	@Autowired
	private SqlSessionTemplate template;

	public int checkMember(HttpServletRequest req, String checkId) {

		dao = template.getMapper(MemberDao.class);

		// 세션을 가져온다.
		// request의 getSession() 메서드는 서버에 생성된 세션이 있다면 세션을 반환하고, 없다면 새 세션을 생성하여 반환한다. (인수
		// default가 true)
		// request의 getSession()메서드의 파라미터로 false를 전달하면, 이미 생성된 세션이 있을 때 그 세션을 반환하고, 없으면
		// null을 반환한다.
		HttpSession session = req.getSession();
		
		int check = 0;
		
		int result = dao.checkMember(checkId);

		if (result == 1) {
			
			Member member = dao.getMember(checkId);
			session.setAttribute("loginInfo", member);
			session.setAttribute("loginMidx", member.getMidx());
			session.setAttribute("loginName", member.getName());
			System.out.println("회원. 세션저장값 확인 loginInfo : "+session.getAttribute("loginInfo"));
			System.out.println("회원. 세션저장값 확인 loginMidx : "+session.getAttribute("loginMidx"));
			System.out.println("회원. 세션저장값 확인 loginName : "+session.getAttribute("loginName"));
	
			check = 1;

		} else {
			check = 0;
		}

		return check;
	}

}
