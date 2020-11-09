package com.wifi.client.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoLoginService {

	// 카카오 REST API 키
	//20200923 private final static String CLIENT_ID = "f5e0701c39876968f090e686afa45c4a";
	private final static String CLIENT_ID = "9c67456b95fffe65ed319d1deae094af";

	/* 도메인 경로 변경시 수정이 용이하게 변수로 선언한다 */
	// 로그인 리다이렉트 uri
	//private final static String REDIRECT_URI_LOGIN = "http://localhost:8081/login";
	private final static String REDIRECT_URI_LOGIN = "http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/client/login";

	// 로그아웃 리다이렉트 uri
	//private final static String REDIRECT_URI_LOGOUT = "http://localhost:8081/logout";
	private final static String REDIRECT_URI_LOGOUT = "http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/client/logout";
	
	
	/* 로그인 성공 후 받은 코드 ㅡ> 다시 요청 ㅡ> access_Token 얻기 */
	public String getAccessToken(String authorize_code) {

		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + CLIENT_ID);
			sb.append("&redirect_uri=" + REDIRECT_URI_LOGIN);
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			System.out.println("sb.toString() : " + sb.toString());

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	/* access_Token으로 사용자 정보 얻기 */
	public HashMap<String, Object> getUserInfo(String access_Token) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body result : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			// 0921 김승연 추가 : profile
	        String profile_img = "";
	        // properties.getAsJsonObject().get("profile_image").getAsString();
	        System.out.println("프사 null 확인 : " + properties.has("profile_image"));
			
	        if(properties.has("profile_image")) { 
			  
				profile_img = properties.getAsJsonObject().get("profile_image").getAsString();
			  	System.out.println(profile_img); 
			  	
			} else { 
				// 프로필 사진이 없을 시 출력할 기본사진 경로
			  	profile_img = "img/default-profile.png";	
		  		System.out.println("프사없음"); 
			  	
		  	}
			
			
			
			userInfo.put("id", email);
			userInfo.put("name", nickname);
			userInfo.put("access_Token", access_Token);
			
			// 0921 김승연 추가 : profile
	        userInfo.put("profile_image", profile_img);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return userInfo;
	}

	/* 로그아웃 */
	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLoginUrl(HttpSession session) {

		String loginUrl = "https://kauth.kakao.com/oauth/authorize?" + "client_id=" + CLIENT_ID + "&redirect_uri="
				+ REDIRECT_URI_LOGIN + "&response_type=code";

		return loginUrl;
	}

	public String getLogoutUrl(HttpSession session) {

		String logoutUrl = "https://kauth.kakao.com/oauth/logout?" + "client_id=" + CLIENT_ID + "&logout_redirect_uri="
				+ REDIRECT_URI_LOGOUT;

		return logoutUrl;
	}
}