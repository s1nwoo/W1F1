package com.wifi.client.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wifi.client.model.Member;
import com.wifi.client.service.KakaoLoginService;
import com.wifi.client.service.MemberCheckService;
import com.wifi.client.service.MemberRegService;


@Controller
@RequestMapping("/")
public class MemberController {
		
	/* 카카오로그인 . 로그아웃 */
    @Autowired
    private KakaoLoginService kakao;
	
	/* 회원여부 체크 */
	@Autowired
	MemberCheckService checkMemberService;
	
	/* 회원가입 */
	@Autowired
	MemberRegService regService;
	
// 카카오 로그인. 로그아웃 관련 ------------------------------------------------------------------------------------
	
	@GetMapping
	//@RequestMapping("client")
	public String index(HttpSession session) {
		
		String loginUrl = kakao.getLoginUrl(session);
		String logoutUrl = kakao.getLogoutUrl(session);
		
		session.setAttribute("login_url", loginUrl);
		session.setAttribute("logout_url", logoutUrl);
		
		return "login";
	}
	
	/* 로그인 - 로그인하고 코드 받기 ㅡ> 코드로 엑세스토큰 받기 ㅡ> 액세스토큰으로 사용자정보받기 ㅡ> 사용자정보 세션에 저장 ㅡ> 로그인 완료처리 */
    @RequestMapping("/login")
	public String login(@RequestParam("code") String code, HttpSession session, HttpServletRequest req, Model model) {
    	
    	int check = 0;
    	String view ="";
    	
	    String access_Token = kakao.getAccessToken(code);
	    System.out.println("controller access_token : " + access_Token);
	    
	    HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
	    System.out.println("login Controller loginInfo: " + userInfo);
	    
	    // 클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	    if (userInfo.get("id") != null) {
	    	
	    	String checkId = (String) userInfo.get("id");
	    	// checkMemberService.checkMember(req, checkId);
	        session.setAttribute("userInfo", userInfo);
	     // 0921 김승연 추가 : profile
	        session.setAttribute("profile_img", (String)userInfo.get("profile_image"));
	        
	        //view = memberCheck(req, checkId);
	        check = memberCheck(req, checkId);
	    }
	    
	    if(check == 0) {
	    	model.addAttribute("name", (String) userInfo.get("name"));
			model.addAttribute("email", (String) userInfo.get("id"));
			model.addAttribute("new_member", true);
			// 0921 김승연 추가 : profile
	        session.setAttribute("profile_img", (String)userInfo.get("profile_image"));
			session.setAttribute("access_Token", access_Token);
			
			view = "login";
			
		} else {
			session.setAttribute("access_Token", access_Token);
			System.out.println("session access_Token : " + session.getAttribute("access_Token"));
			
			view = "main";
		}
	    
	    return view;
	    
	};
    
    
	/* 로그인 할 때 세션에 넣어두었던 토큰을 꺼내서 로그아웃 메소드를 실행 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
	    kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	    session.removeAttribute("loginName");
	    session.removeAttribute("loginMidx");
	    session.removeAttribute("loginInfo");
	 // 0921 김승연 추가
	    session.removeAttribute("profile_img");
	    session.removeAttribute("access_Token");
	    
	    // 가람 수정 0917
	    //return "index";
	    return "redirect:/";
	};
	
	
	
	
	
// 회원확인. 회원가입 관련 --------------------------------------------------------------------------------------------------------	
	
	/* 회원여부 체크 : 로그인 시 메소드로 호출*/ 
	private int memberCheck(HttpServletRequest req, String checkId) {
		
		System.out.println("MemberCheck controller");
		System.out.println("checkId : "+checkId);

		// 회원여부 체크 ㅡ> 회원이면 1, 회원이 아니면 0으로
		return checkMemberService.checkMember(req, checkId);
	};
	
	
	/* 회원가입 */
	@RequestMapping(value="/members", method = RequestMethod.POST)
	@ResponseBody
	private int regMember(HttpServletRequest req, Member memberReq) {
		
		System.out.println("regMember controller");
		System.out.println("매개변수 확인 memberReq : "+memberReq.toString());
		
		//return "redirect:/";
		return regService.regMember(req, memberReq);
	};	
	
}

	

