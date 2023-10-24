package com.webSocket;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.dto.MemberDTO;
import com.service.NotificationService;

@Component
public class NotificationHandler extends TextWebSocketHandler {
	@Autowired
	NotificationService service;
	
	HashMap<String, WebSocketSession> members = new HashMap<>(); //로그인 된 멤버들	
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String msg = "프로젝트가 추가되었습니다.";
		String[] projectMembers = message.getPayload().split(",");
		//알림 발송
		for(String member_num : projectMembers) {
			service.saveNotification(Integer.parseInt(member_num), msg);
			if(isConnected(member_num)) {
				WebSocketSession receiverWebSocketSession = members.get(member_num);
				try {
					receiverWebSocketSession.sendMessage(new TextMessage(msg));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println(member_num+"번 유저는 오프라인 입니다.");
			}	
		}//end for문
		
	}//end handleTextMessage()
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		super.afterConnectionEstablished(session);
		String member_num = getMember_num(session);	
		
		System.out.println(member_num+"님이 실시간 알림 서버에 접속하셨습니다!");
		members.put(member_num,session);
	
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		String member_num = getMember_num(session);
		members.remove(member_num);
		System.out.println(member_num+"님의 연결이 해제되었습니다");
		super.afterConnectionClosed(session, status);
		
	}
	
	//HttpSession에 저장된 login정보를 가져오기 위한 메서드
	public String getMember_num(WebSocketSession session) {
		Map<String,Object> httpSession = session.getAttributes(); //servlet-context파일에서 선언한 핸드세이커로 인해 httpSession과 websocketSession이 연결됨
		
		MemberDTO member = (MemberDTO)httpSession.get("login");
		String member_num = String.valueOf(member.getMember_num());
		
		return member_num;
	}
	
	 //사용자가 연결되어 있지 않으면 알림을 보내지 않기 위한 메서드
	public boolean isConnected(String member_num) {
		if(members.containsKey(member_num)) {
			return true;
		}
		return false;
	}
}