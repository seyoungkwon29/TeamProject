package com.controller.chatting;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.ChatMemberDTO;
import com.dto.ChatRoomDTO;
import com.dto.MemberDTO;
import com.service.ChattingService;
import com.service.MemberService;

@Controller
public class ChattingController {
	@Autowired
	MemberService mService;
	@Autowired
	ChattingService chatService;
	
	@RequestMapping("/chatting")
	public String chatting(HttpSession session) {
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
		List<MemberDTO> memberList = chatService.selectMembersExceptMe(mDTO.getMember_num());
		session.setAttribute("memberList", memberList);
		return "chatting";
	}

	@RequestMapping(value = "/searchValue", method = RequestMethod.POST)
	@ResponseBody
	public List<MemberDTO> searchValue(@RequestBody Map<String, String> map, HttpSession session) {
		List<MemberDTO> selectedList = chatService.searchValue(map);
		return selectedList;
	}
	
	@ResponseBody
	@RequestMapping(value = "/createChatRoom", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public int createChatRoom(@RequestBody Map<String, Object> map, HttpSession session) {
	  MemberDTO member = (MemberDTO)session.getAttribute("login");
      String chatroom_title = (String)map.get("chatroom_title");
      List<String> stringList = (List<String>)map.get("chat_member");
      List<Integer> chatMembers = stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
      
      ChatRoomDTO chatRoom = new ChatRoomDTO();
   // 1:1(=0) / 1:N(=1)로 설정하는 채팅방 생성
      if (chatMembers.size()==1) {
    	  chatRoom.setChatroom_type(0);
	} else {
		chatRoom.setChatroom_type(1);
	}
      chatRoom.setChatroom_title(chatroom_title);
      System.out.println(chatRoom);
      int result = 0; //채팅방 생성 결과를 변수 선언
      result = chatService.createChatRoom(chatRoom);
      
      //채팅방 생성 성공시 채팅멤버 등록
      if (result>0) {//생성 성공
    	  int registor = chatService.registor(member.getMember_num());
    	  System.out.println("registor>>>"+registor);
    	  for (int i = 0; i < chatMembers.size(); i++) {
    		  System.out.println(chatMembers.get(i));
    		  registor = chatService.registor(chatMembers.get(i));
		}
    	  if(registor > 0) { // 채팅방 생성자/사용자 등록 성공 시
    		//채팅방 번호 0으로 설정 -> 가장 최근에 생성됐다는 의미
    		  	chatRoom.setChatroom_num(0);
				List<ChatMemberDTO> mList = chatService.selectAllMember(chatRoom); //// 채팅방 사용자 목록 조회
				System.out.println("mList>>>"+mList);
				
				
				String chatMemberArr[] = new String[mList.size()]; //채팅방 사용자 목록을 담을 배열 선언
				if(!mList.isEmpty()) {
					for(int j = 0; j < mList.size(); j++) {
						chatMemberArr[j] = mList.get(j).getDiv_name() + " " + mList.get(j).getMember_name() + " " + mList.get(j).getRank();
						System.out.println(chatMemberArr);
					}
				}
    	  }
	}//end registor
      return chatRoom.getChatroom_num();
	}
	
	
	//채팅방 상세
		@RequestMapping(value = "/openChatRoom")
		public ModelAndView chatView(ModelAndView mv, @RequestParam("chatroom_num") int chatroom_num) {
			mv.addObject("chatroom_num", chatroom_num);
			mv.setViewName("chat/chattingRoom");
			return mv;
		}
	
	
	
	
}
