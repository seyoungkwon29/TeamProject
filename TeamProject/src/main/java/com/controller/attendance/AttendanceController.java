package com.controller.attendance;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.AttendanceDTO;
import com.dto.MemberDTO;
import com.service.AttendanceService;

@Controller
public class AttendanceController {
	@Autowired
	AttendanceService service;
	
	@RequestMapping("attendancePage")
	public String attendancePage() {
		return "/attendancePage";
	}
	
	// 날짜 검색
	@RequestMapping(value="attendance/searchDate")
	public String searchDate(String date, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		
		String memNum = member.getMember_num() + date;
		System.out.println("memNum >>>" + memNum);
		
		// 해당 날짜의 근무 기록 리스트
		List<AttendanceDTO> attendanceList = service.viewAttList(memNum);
		System.out.println("근무 기록 리스트 >>>" + attendanceList);
		
		session.setAttribute("attendanceList", attendanceList);
		session.setAttribute("date", date);
		return "redirect:/attendancePage";
	}
	
	// 출근
	@RequestMapping(value="attendance/punchIn", method=RequestMethod.POST)
	public String punchIn(HttpSession session, AttendanceDTO attendance) {
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		int member_num = member.getMember_num(); // 사번
		// 사번 등록
		attendance.setMember_num(member_num);
		// 현재 시각
		LocalTime time = LocalTime.now();
		int hour = time.getHour();
		int minute = time.getMinute();
		
		// 출근 시각은 jsp에서 전송해서 attendance 매개변수로 받는다
		
		// 출근 상태
		if (hour >= 9 && minute > 0) attendance.setAtt_status("지각");
		else attendance.setAtt_status("출근");
		
		System.out.println("출근 시간 >>>" + attendance.getAtt_start());
		System.out.println("출근 상태 >>>" + attendance.getAtt_status());
		// 출근 인원 insert
		int att = service.punchIn(attendance);
		if (att == 1) System.out.println("출근 완료!");
		else System.out.println("출근 실패!");
		
		return "redirect:../loginCheck/attendance/attendanceList";
	}
	
	// 퇴근
	@RequestMapping(value="attendance/punchOut", method=RequestMethod.POST)
	public String punchOut(HttpSession session, AttendanceDTO attendance) {
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		int member_num = member.getMember_num(); // 사번
		// 사번 등록
		attendance.setMember_num(member_num);
		// 현재 시각
		LocalTime time = LocalTime.now();
		int hour = time.getHour();
		
		// 퇴근 시각은 jsp에서 전송해서 attendance 매개변수로 받는다
		
		// 퇴근 상태
		if (hour < 17) attendance.setAtt_status("조퇴");
		else attendance.setAtt_status("퇴근");
		
		System.out.println("퇴근 시간 >>>" + attendance.getAtt_fin());
		System.out.println("퇴근 상태 >>>" + attendance.getAtt_status());
		// 퇴근 인원 update	
		int att = service.punchOut(attendance);
		if (att == 1) System.out.println("퇴근 완료!");
		else System.out.println("퇴근 실패!");
		
		return "redirect:../loginCheck/attendance/attendanceList";
	}
	
	// 근무 기록 리스트
	@RequestMapping(value="loginCheck/attendance/attendanceList", method=RequestMethod.GET)
	public String attendaceList(HttpSession session){
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		
		// 현재 날짜
		Date today = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("YYYY-MM");
		String memNum = member.getMember_num() + simpleDate.format(today);
		System.out.println("memNum >>>" + memNum);
		
		// 근무 기록 리스트
		List<AttendanceDTO> attendanceList = service.viewAttList(memNum);
		System.out.println("근무 기록 리스트 >>>" + attendanceList);
		
		if(!attendanceList.isEmpty()) {
			session.setAttribute("attendanceList", attendanceList);
		}		
		return "redirect:../../attendancePage";
	}
}
