package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.AppDocumentDTO;
import com.dto.ApprovalDTO;
import com.dto.MemberDTO;
import com.dto.NoticeDTO;
import com.service.NoticeService;
import com.service.SaveDocFormService;

@Controller
public class HomeController {
	
//	@Autowired
//	NoticeService service;//공지사항 서비스
//	
//	@Autowired
//	SaveDocFormService appService;//전자결재 서비스
//	
//	@RequestMapping(value ="/homeAppList")
//	public String homeAppList( HttpSession session, AppDocumentDTO doc, ApprovalDTO app) {	
//
//		int member_num = (int)((MemberDTO)session.getAttribute("login")).getMember_num(); 			
//		doc.setDoc_status("대기");
//		doc.setMember_num(member_num);
//		System.out.println("========= doc 확인 : " + doc);
//		
//		List<AppDocumentDTO> appDocList = appService.selectHomeAppList(doc);
//		System.out.println("============ 홈화면 결재 리스트 : " + appDocList);
//		session.setAttribute("appDocList", appDocList); //기안한 문서
//		return "homePage";
//	}
//
//	
//	
//	//공지사항 불러오기s
//	@GetMapping("/homeAppNoti") // 
//	public String homecon(HttpSession session) {
//		MemberDTO dto = (MemberDTO) session.getAttribute("login");
//		int membernum = dto.getMember_num();
//		List<NoticeDTO> list =  service.getAllNotices(membernum);
//		System.out.println("list>>"+list.toString());
//		session.setAttribute("HomeNoticelists",list);
//		return "homePage";
//	
//	}
//	

	
	
}//controller
