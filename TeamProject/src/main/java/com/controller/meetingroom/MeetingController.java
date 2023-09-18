package com.controller.meetingroom;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.MeetingRoomDTO;
import com.dto.MemberDTO;
import com.service.MeetingRoomService;
import com.service.MemberService;

@Controller
public class MeetingController {
	@Autowired
	MemberService service;
	@Autowired
	MeetingRoomService serviceM;
	
	@RequestMapping("/loginCheck/meetingRoomReserve")
	public String meetingroom(MeetingRoomDTO dto, HttpSession session) {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		dto.setMember_num(mDTO.getMember_num());
		
		int num = serviceM.reserve(dto);
		if (num==1) {
			session.setAttribute("mesg", "예약 완료");
		}else {
			session.setAttribute("mesg", "예약 실패");
		}
		return "redirect:../meetingRoom";
	}

	@RequestMapping("/meetingRoomCheck")
	public String meetingRoomCheck(HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		List<MeetingRoomDTO> list =  serviceM.select(dto.getMember_num());
		session.setAttribute("list", list);
		return "meetingRoomCheck";
	}

	@RequestMapping(value = "/cancelReserve", method = RequestMethod.POST)
	public String cancelReserve(String meeting_date, String meeting_time, String meeting_num, HttpSession session) {
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		int member_num = dto.getMember_num();
		MeetingRoomDTO dtoR = new MeetingRoomDTO(member_num, meeting_num, meeting_date, meeting_time, "1");
		
		int num = serviceM.delete(dtoR);
		String page = null;
		if(num == 1) {
			page = "meetingRoomCheck";
		}
		return page;
	}
}
