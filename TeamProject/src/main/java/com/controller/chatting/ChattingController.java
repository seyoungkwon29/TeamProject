package com.controller.chatting;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dto.ChatContentDTO;
import com.dto.ChatMemberDTO;
import com.dto.ChatRoomDTO;
import com.dto.ExtendedChatContentDTO;
import com.dto.MemberDTO;
import com.google.gson.Gson;
import com.service.ChattingService;
import com.service.MemberService;

@Controller
public class ChattingController {
	@Autowired
	MemberService mService;
	@Autowired
	ChattingService chatService;
	
	@RequestMapping("/chatting")
	public String chatting(HttpSession session, Model m) {
		MemberDTO mDTO = (MemberDTO)session.getAttribute("login");
		int member_num = mDTO.getMember_num();
		//채팅 리스트
		List<ChatRoomDTO> chatList = chatService.chatList(member_num);
		List<ChatContentDTO> latestChatList = chatService.latestChatList(member_num);
		// 오전/오후 문자열을 지역화하기 위한 DateFormatSymbols 설정
		for (ChatContentDTO chatContentDTO : latestChatList) {
			String time = convertDateToLocale(chatContentDTO.getChat_date());
		    chatContentDTO.setChat_date(time);
		}
		m.addAttribute("chatList",chatList);
		m.addAttribute("latestChatList",latestChatList);
		
		//초대할 멤버 리스트
		List<MemberDTO> memberList = chatService.selectMembersExceptMe(member_num);
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
	public String createChatRoom(@RequestBody Map<String, Object> map, HttpSession session) {
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
      int result = 0; //채팅방 생성 결과를 변수 선언
      result = chatService.createChatRoom(chatRoom);
      
      //채팅방 생성 성공시 채팅멤버 등록
      if (result>0) {//생성 성공
    	  int registor = chatService.registor(member.getMember_num());
    	  for (int i = 0; i < chatMembers.size(); i++) {
    		  registor = chatService.registor(chatMembers.get(i));
		}
    	  if(registor > 0) { // 채팅방 생성자/사용자 등록 성공 시
    		//채팅방 번호 0으로 설정 -> 가장 최근에 생성됐다는 의미
    		  	chatRoom.setChatroom_num(0);
    		  	List<ChatMemberDTO> mList = chatService.selectAllMember(chatRoom); //// 채팅방 사용자 목록 조회
				
				
				String chatMemberArr[] = new String[mList.size()]; //채팅방 사용자 목록을 담을 배열 선언
				if(!mList.isEmpty()) {
					for(int j = 0; j < mList.size(); j++) {
						chatMemberArr[j] = mList.get(j).getDiv_name() + " " + mList.get(j).getMember_name() + " " + mList.get(j).getRank();
					}
				}
				//생성후 채팅방에 공지할 상세내용
				ChatContentDTO chatContent = new ChatContentDTO();
				chatContent.setChat_type(1);//채팅방 생성멤버 공지(1)로 등록
				chatContent.setChatroom_num(0); //채팅방번호 0으로 설정
				
				
				//채팅멤버 공지
				int n = chatMemberArr.length - 1; //채팅멤버 목록에서 생성자 제외
				String inviteMemberArr[] = new String[n]; //초대받은 멤버들
				System.arraycopy(chatMemberArr, 1, inviteMemberArr, 0, n); //초대한 사람 제외한 초대받은 멤버들 배열에 복사
				String inviteMember = ""; //초대한 사람
				
				for (int i = 0; i < inviteMemberArr.length; i++) {
					if (i < inviteMemberArr.length - 1) { //초대받은 마지막 멤버 전의 멤버들일 경우
						inviteMember += "<strong>" + inviteMemberArr[i] + "</strong>님, ";
					}else if(i == inviteMemberArr.length - 1) { //초대받은 마지막 멤버일 경우
						inviteMember += "<strong>" + inviteMemberArr[i] + "</strong>님을 초대했습니다. ";
					}
				}
				chatContent.setChat_content("<strong>" + chatMemberArr[0] + "</strong>님이<br> "+inviteMember); //초대한 사람 공지로 등록
				chatService.registerChatContent(chatContent); //채팅 등록(채팅참여 멤버들 공지로 등록)
				int chatroom_num = chatService.selectOnechatRoomNum(); // 최근 생성한 채팅방번호 불러오기
				return new Gson().toJson(chatroom_num);
    	  }
	}//end registor
      return null;
	}
	
	
	//채팅방 열기
		@RequestMapping(value = "/moveChatting")
		public ModelAndView moveChatting(@RequestParam("chatroom_num") int chatroom_num, HttpSession session) {
			ModelAndView mv = new ModelAndView();
			int countMember = chatService.countMember(chatroom_num);
			MemberDTO memberDTO = (MemberDTO)session.getAttribute("login");
			ChatRoomDTO chatroom = chatService.searchRoom(chatroom_num);
			List<ChatMemberDTO> cList = chatService.selectAllMember(chatroom);
			ChatContentDTO createdRoomContent = chatService.createdRoomContent(chatroom_num);
			List<ExtendedChatContentDTO> myContentsList = chatService.myContentsList(chatroom_num);
			//날짜 변환
			String createdDate = createdRoomContent.getChat_date();
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = inputFormat.parse(createdDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			SimpleDateFormat outputFormat = new SimpleDateFormat("- yyyy년 M월 d일 E요일 -");
			createdDate = outputFormat.format(date);
			
			String createdMember = createdRoomContent.getChat_content();
			
			for (ChatContentDTO chatContentDTO : myContentsList) {
				String time = convertDateToLocale(chatContentDTO.getChat_date());
			    chatContentDTO.setChat_date(time);
			}
			
			System.out.println(myContentsList);
			mv.addObject("countMember", countMember);
			mv.addObject("member", memberDTO);
			mv.addObject("chatroom", chatroom);
			mv.addObject("cList", cList);
			mv.addObject("createdDate", createdDate);
			mv.addObject("createdMember", createdMember);
			mv.addObject("contentsList", myContentsList);
			mv.setViewName("chatting/chattingRoom");
			return mv;
		}
		
		 private String convertDateToLocale(String inputDate) {
		        DateFormatSymbols symbols = new DateFormatSymbols();
		        symbols.setAmPmStrings(new String[]{"오전", "오후"});
		        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date date = null;
		        try {
		            date = inputFormat.parse(inputDate);
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm", symbols);
		        return outputFormat.format(date);
		    }
		
		//채팅방 제목 변경
		 @PostMapping(value = "updateTitle", produces="text/plain;charset=utf-8")
		 @ResponseBody
		 public String updateTitle(@RequestBody Map<String, Object> map) {
			 int chatroom_num = Integer.parseInt((String)map.get("chatroom_num"));
			 map.put("chatroom_num", chatroom_num);
			 
			 int num = chatService.updateTitle(map);
			 if (num>0) {
				 return (String)map.get("chatroom_title"); 
			}
			 return null;
		 }
		 
		//초대 멤버 리스트 불러오기
		@PostMapping(value="/inviteMemberList")
		@ResponseBody
		public List<ChatMemberDTO> inviteMemberList(@RequestBody Map<String, Integer> map, HttpSession session) {
		List<ChatMemberDTO> list = chatService.inviteMemberList(map);
		return list;
		}

		//초대하기
		@PostMapping(value="/inviteMember", produces = "text/html;charset=utf-8")
		@ResponseBody
		public String inviteMember(@RequestBody Map<String, Object> map, HttpSession session) {
			MemberDTO member = (MemberDTO)session.getAttribute("login");
			int chatroom_num = Integer.parseInt((String)map.get("chatroom_num"));
			List<String> stringList = (List<String>)map.get("invite_member");
		    List<Integer> inviteMembers = stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
		    String inviteMsg = "<strong>" + member.getDiv_name() +" "+member.getMember_name()+" "+member.getRank()
			+ "</strong>님이, ";
		    
		    for (int i = 0; i < inviteMembers.size(); i++) {
		    	Map<String, Integer> paramMap = new HashMap<>();
		        paramMap.put("chatroom_num", chatroom_num);
		        paramMap.put("member_num", inviteMembers.get(i));
		    	System.out.println("paramMap>>>"+paramMap);
		    	System.out.println("inviteMembers.get(i)>>>"+inviteMembers.get(i));
		    	
		        //방에 초대된적이 있는 멤버면 status=0으로 바꿔주기
		        ChatMemberDTO invitedMember = chatService.memberExceptStatus(paramMap);
		        System.out.println(invitedMember);
		        if(invitedMember != null) {
		        	paramMap.put("member_status", 0);
 		        	chatService.updateMemberStatus(paramMap);
		        	inviteMsg += "<strong>" + invitedMember.getDiv_name() +" "+invitedMember.getMember_name()+" "+invitedMember.getRank()
					+ "</strong>님, ";
		        }else {
		        	chatService.inviteMember(paramMap);
		        	ChatMemberDTO dto = chatService.selectMember(paramMap.get("member_num"));
		        	inviteMsg += "<strong>" + dto.getDiv_name() +" "+dto.getMember_name()+" "+dto.getRank()
					+ "</strong>님, ";
		        }
		        
		        
				}
		    inviteMsg = inviteMsg.substring(0, inviteMsg.length() - 2);
		    inviteMsg += "을 초대했습니다. ";
		    return inviteMsg;
			}
		
		//나가기
		@PostMapping(value="/exitChatroom", produces = "text/html;charset=utf-8")
		@ResponseBody
		public String exitChatroom(@RequestBody Map<String, Integer> map, HttpSession session, Model m) {
			map.put("member_status", 1);
			int num = chatService.updateMemberStatus(map);
		    int chatroom_num = map.get("chatroom_num");
		    String exitMember = null;
		    if (num>0) {
		    	MemberDTO member = (MemberDTO)session.getAttribute("login");
				
				exitMember = "<strong>" + member.getDiv_name() +" "+member.getMember_name()+" "+member.getRank()
				+ "</strong>님이 채팅방을 나갔습니다.";
				
				//참여인원수 0이면 채팅방 삭제
				int count = chatService.countMember(chatroom_num);
				if(count<1) {
					chatService.deleteChatRoom(chatroom_num);
				}
			}
		    return exitMember;
	    }
		
		
		
		
}
