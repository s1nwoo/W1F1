package com.wifi.order.socket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.wifi.order.alarm.service.AlarmService;
import com.wifi.order.dao.OrderDao;
import com.wifi.order.item.service.MyBuyerSelectService;
import com.wifi.order.model.Messenger;

public class EchoHandler extends TextWebSocketHandler {
	
	private OrderDao dao;
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private MyBuyerSelectService selectService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	private Map<WebSocketSession, Integer> sessionCheck = new HashMap<WebSocketSession, Integer>();
	private Map<Integer, WebSocketSession> sessionMap = new HashMap<Integer, WebSocketSession>();
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		Gson gson = new Gson();
	      
	      // 알림을 보내는 접속자 midx, 알림을 받는 midx, 액션이 일어나는 게시글 iidx,  
	      Messenger messenger = gson.fromJson(message.getPayload(), Messenger.class);
	      
	      String label = messenger.getLabel();
	      int senderMidx = messenger.getSenderMidx();   // 액션을 발생시킨 사람
	      List<Integer> receiverList = messenger.getReceiverList();   // 알림을 받아야 할 사람
	      int receiverMidx = messenger.getReceiverMidx();
	      int iidx = messenger.getIidx();   // 게시글
	      
	      // 알림 받는 사람 -1 : 액션을 발생시키는 사람의 초기 접속 -> session에 추가
	      if(receiverMidx == -1){
	         sessionCheck.put(session, senderMidx);   // 접속 중인지 확인을 위한 HashMap
	         sessionMap.put(senderMidx, session);
	         System.out.println("초기 접속 연결 성공 "+senderMidx);
	         System.out.println("초기 접속 session값 "+session);
	      }
	      else {
	         
	    	  
	         for(int i=0; i<receiverList.size(); i++) {
	            logger.info("{}로 부터 {}를 전달 받았습니다.", senderMidx, message.getPayload());
	            
	            // 받는 사람의 session값
	            WebSocketSession ws = sessionMap.get(receiverList.get(i));
	            
	            
	            // 전달 메시지
	            Messenger result = new Messenger(senderMidx, receiverList.get(i), iidx, label);
	            TextMessage sendMsg = new TextMessage(gson.toJson(result));
	            
	            // 상대방에게 메시지 전달
	            // 상대방이 접속되어 있으면 전달 : 받는 사람의 session값을 키로 찾았을 경우 null 이면 미접속 상태
	            if(sessionCheck.get(ws) != null) {
	               ws.sendMessage(sendMsg);
	               
	            }
	            // alarm state를 1로 변경
	            alarmService.changeBuyerAlarm(receiverList.get(i), iidx);
	            
	            
	         }
	      }
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionCheck.remove(session);	// 연결 끊기 : 사용자의 session과 midx가 삭제됨
	}
}
