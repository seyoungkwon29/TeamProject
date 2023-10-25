package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.common.PageRequestDTO;
import com.common.PageResponseDTO;
import com.dto.AppDocumentDTO;
import com.dto.CommunityDTO;
import com.dto.MailDTO;
import com.dto.MemberDTO;
import com.dto.NoticeDTO;
import com.service.CommunityService;
import com.service.MailService;
import com.service.NoticeService;
import com.service.SaveDocFormService;

@Controller
public class HomeController {
	
	private final static Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	SaveDocFormService approvalService; // 전자결재 서비스
	
	@Autowired
	NoticeService noticeService; // 공지사항 서비스
	
	@Autowired
	CommunityService communityService; // 자유게시판 서비스
	
	@Autowired
	MailService mailService; // 메일 서비스

	@GetMapping("/")
    public String home(HttpSession session, Model model, HttpServletRequest request) {

        MemberDTO member = (MemberDTO) session.getAttribute("login");
        
        if (member == null) {
        	return "redirect:/login";
        }
        
		// 결재 대기 중인 문서 목록
        List<AppDocumentDTO> appDocList = getApprovalList(member.getMember_num());
        session.setAttribute("appDocList", appDocList);
        
		// 공지사항
		List<NoticeDTO> noticeList = noticeService.getNoticeDTOListTopN(3);
		model.addAttribute("noticeList", noticeList);
        
		// 자유게시판
		List<CommunityDTO> communityList = communityService.getCommunityDTOListTopN(3);
		model.addAttribute("communityList", communityList);
		
		// 메일 목록
		
		List<MailDTO> mailList = mailService.homeReceiveMailList(member.getMember_num());
		mailService.receiveMailList(request, session);
		
        return "homePage";
    }
	
	
	private List<AppDocumentDTO> getApprovalList(int member_num) {	
	
		AppDocumentDTO searchCondition = new AppDocumentDTO();
		searchCondition.setDoc_status("대기");
		searchCondition.setMember_num(member_num);
		
		log.debug("검색 조건 확인 : doc={}", searchCondition);
		
		List<AppDocumentDTO> appDocList = approvalService.selectHomeAppList(searchCondition);
		log.debug("결재 리스트 : appDocList={}", appDocList);
		System.out.println("============ 홈화면 결재 리스트 : " + appDocList);
		
		return appDocList;
	}

	
}