package com.wifi.client.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.wifi.client.model.PayReadyResponse;
import com.wifi.client.model.PaymentApprovalResponse;

@Service
public class KakaoPayService {
	
	// 카카오 API 어드민 키
			private static final String K_ADMIN_KEY = "a8c2dcb166ddbd292dc5160e4f0ae3c1";
			
			// 카카오 API Host 주소
			private static final String K_HOST = "https://kapi.kakao.com";
			
			// 단건 결제 테스트 가맹코드
			private static final String K_CID = "TC0ONETIME";  
			
			// 결제 성공 시 redirect Url
			private static final String K_APPROVAL_URL = "http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/client/successPay";
			//private static final String K_APPROVAL_URL = "http://localhost:8081/successPay";
			
			// 결제 취소 시 redirect Url	
			private static final String K_CANCEL_URL = "http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/client/regItemForm";
			//private static final String K_CANCEL_URL = "http://localhost:8081/regItemForm";
			
			// 결제 실패 시 redirect Url
			private static final String K_FAIL_URL = "http://ec2-54-180-89-108.ap-northeast-2.compute.amazonaws.com:8080/client/regItemForm";
			//private static final String K_FAIL_URL = "http://localhost:8080/server/payfail";
			
			private PayReadyResponse kakaoPayReady;
			private PaymentApprovalResponse kakaoPayApproval;
			
			
			public String requestPay () {
				
				RestTemplate restTemplate = new RestTemplate();
				
				// header정보를 생성할 수 있는 내장메서드
				HttpHeaders header = new HttpHeaders();
				
				System.out.println("결제 요청 들어옴! =============");
				
				// 서버에 요청할 Header 생성 : 로그인API와 다르게 POST 방식으로 통신하므로 Header와 Body에 정보를 담아줘야한다.
				header.add("Authorization", "KakaoAK " +K_ADMIN_KEY);
				header.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
				header.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+ ";charset=UTF-8");
				
				// 서버에 요청할 Body
				// Request body로 데이터 전달하는 POST 방식에서 형변환 없이 데이터를 전달할 수 있는 Spring 내장 메서드
				// HashMap과의 차이점 : 같은 key값을 가진 파라미터 값이 여러개일 경우를 대비함
				// https://jojoldu.tistory.com/478
				MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
				params.add("cid", K_CID);
				params.add("partner_order_id", "1004");
				params.add("partner_user_id", "1Ps");
				params.add("item_name", "추천 공구 등록");
				params.add("quantity", "1");
				params.add("total_amount", "5000");
				params.add("tax_free_amount", "500");
				params.add("approval_url", K_APPROVAL_URL);
				params.add("cancel_url", K_CANCEL_URL);
				params.add("fail_url", K_FAIL_URL);
				
				HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, header);
				
				System.out.println("PayReady Request body 생성 확인 : " + body);
				
				try {
					kakaoPayReady = restTemplate.postForObject(new URI(K_HOST + "/v1/payment/ready"), body, PayReadyResponse.class);
					
					
					System.out.println("결제요청 준비------------");
					System.out.println("kakaoPayReady : " + kakaoPayReady);
					
					System.out.println("Next_redirect_pc_url : " + kakaoPayReady.getNext_redirect_pc_url());
					
					return kakaoPayReady.getNext_redirect_pc_url();
					
				} catch(RestClientException e) {
					e.printStackTrace();
				}catch (URISyntaxException e) {
					e.printStackTrace();
				}
				
				return "/pay";
			}
			
			public PaymentApprovalResponse PaymentInfo (String pg_token) {
				
				RestTemplate restTemplate = new RestTemplate();
				
				// header정보를 생성할 수 있는 내장메서드
				HttpHeaders header = new HttpHeaders();
				
				System.out.println("결제 승인! =============");
				
				// 서버로 요청할 Header 생성 : 로그인API와 다르게 POST 방식으로 통신하므로 Header와 Body에 정보를 담아줘야한다.
				header.add("Authorization", "KakaoAK " +K_ADMIN_KEY);
				header.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
				header.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+ ";charset=UTF-8");
				
				// 서버로 요청할 Body
				MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
				params.add("cid", K_CID);
				params.add("tid", kakaoPayReady.getTid());
				params.add("partner_order_id", "1004");
				params.add("partner_user_id", "1Ps");
				params.add("item_name", "추천 공구 등록");
				params.add("pg_token", pg_token);
				params.add("total_amount", "5000");
				
				
				HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, header);
				
				System.out.println("Pay Approve Response body 생성 확인 : " + body);
				
				try {
					kakaoPayApproval = restTemplate.postForObject(new URI(K_HOST+"/v1/payment/approve"), 
							body, 
							PaymentApprovalResponse.class);
					
					System.out.println("kakaoPayApproval : " +kakaoPayApproval);
					
					
					
				} catch (RestClientException e) {
					e.printStackTrace();
				} catch(URISyntaxException e) {
					e.printStackTrace();
				}
				
				return kakaoPayApproval;
			}
	
	
}
