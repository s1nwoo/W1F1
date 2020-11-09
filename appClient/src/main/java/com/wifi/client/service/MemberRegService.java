package com.wifi.client.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wifi.client.dao.MemberDao;
import com.wifi.client.model.Member;

/* 회원가입 */
@Service
public class MemberRegService {

	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate template;

	public int regMember(HttpServletRequest req, Member memberReq) {
		
		dao = template.getMapper(MemberDao.class);
		
		// 세션을 가져온다.
		// request의 getSession() 메서드는 서버에 생성된 세션이 있다면 세션을 반환하고, 없다면 새 세션을 생성하여 반환한다. (인수 default가 true)
		// request의 getSession()메서드의 파라미터로 false를 전달하면, 이미 생성된 세션이 있을 때 그 세션을 반환하고, 없으면 null을 반환한다.
        HttpSession session = req.getSession();
        
		int result = 0;

		System.out.println("regMember service");
		
		// 회원가입 
		result = dao.regMember(memberReq);
		System.out.println("회원가입 성공여부 확인 : "+result);
		
		String id = memberReq.getId();
		
		// 회원가입 성공 시 ㅡ> 객체 가져와서 세션에 저장
		if(result == 1) {
			
			Member obj = dao.getMember(id);
			Member member = new Member(
					obj.getMidx(), obj.getId(), obj.getName(), obj.getAddr(),obj.getPhone(), obj.getType());
			System.out.println("member :"+member);
			
			session.setAttribute("loginInfo", member);
			session.setAttribute("loginMidx", member.getMidx());
			System.out.println("회원가입 성공시 - 세션 확인 loginInfo : "+session.getAttribute("loginInfo"));
			System.out.println("회원가입 성공시 - 세션 확인 midx : "+session.getAttribute("loginMidx"));
			
		} else {
			session.removeAttribute("access_Token");
		}
		
		return result;
	}
	

	
}
