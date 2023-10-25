package com.webSocket;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dto.ChatContentDTO;
import com.service.ChattingService;

import lombok.experimental.SuperBuilder;


@Component
public class ChattingHandler extends TextWebSocketHandler {
	
	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	@Autowired
	ChattingService service;
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		//메시지 발송
		String msg = message.getPayload();
		JSONObject obj = JsonToObjectParser(msg);
		System.out.println("obj>>>>>"+obj);
		
		// JSON 객체에서 필요한 값들을 추출하여 DTO 객체 생성
	    ChatContentDTO chatContent = new ChatContentDTO();
	    chatContent.setChatroom_num(Integer.parseInt(obj.get("chatroom_num").toString()));
	    chatContent.setChat_content(obj.get("chat_content").toString());
	    chatContent.setMember_num(Integer.parseInt(obj.get("member_num").toString()));
	    chatContent.setChat_type(Integer.parseInt(obj.get("chat_type").toString()));

	    // DB에 메시지 저장
	    int result = service.registerChatContent(chatContent);
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(obj.toJSONString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		System.out.println("session.getId>>"+session.getId());
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		JSONObject obj = new JSONObject();
		obj.put("chat_type", "getId");
		obj.put("sessionId", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		System.out.println("소켓 종료");
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
	}
	
	private static JSONObject JsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject)parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
}