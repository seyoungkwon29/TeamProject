package com.controller.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dto.MailDTO;
import com.dto.MailRecDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;
import com.service.MailService;
import com.service.MemberService;

import lombok.RequiredArgsConstructor;


@Controller("MailController")
@RequiredArgsConstructor
public class MailController {
	private final MailService service; //생성자 주입 활용
	private final MemberService mService;
	
	public MailController(MailService service, MemberService mService) {
		this.service = service;
		this.mService = mService;
	}
	
	private MemberDTO getUserInfoBySession(HttpSession session) {
		MemberDTO loginDto = (MemberDTO)session.getAttribute("login");
		return loginDto;
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(HttpSession session, HttpServletRequest request,
							@RequestParam("mail_file") MultipartFile multipartFile) {
		//사용자 정보
		MemberDTO loginDto = getUserInfoBySession(session);

		//메일 받는 사람들
		String addressListStr = request.getParameter("mail_receiver");
		
		List<String> addressList = new ArrayList<String>();
		if(addressListStr != null) {
			addressList = Arrays.asList(addressListStr.split(" "));
		}
		
		//데이터 파싱
		String mail_title = request.getParameter("mail_title");
		String mail_content = request.getParameter("mail_content");
		String mail_sender = loginDto.getMail();
		int member_num = loginDto.getMember_num();	
		
		//Service에 넘겨줄 MailDTO
		MailDTO mailDto = new MailDTO();
		mailDto.setMail_title(mail_title);
		mailDto.setMail_content(mail_content);
		mailDto.setMail_sender(mail_sender);
		mailDto.setMember_num(member_num);

		String msg = service.sendMail(mailDto, addressList, multipartFile);
		session.setAttribute("msg", msg);	
		return "redirect:writeMail";
	}
	
	
//메일 주소록
	@RequestMapping("/mailAddressBook")
	public String mailAddressBook(HttpSession session, HttpServletRequest request) {
		MemberDTO loginDto = getUserInfoBySession(session);
		List<MemberDTO> list = service.selectAllMemberListExceptMe(loginDto);
		request.setAttribute("memberList", list);
		return "mail_addressBook";
	}
	
//받은 메일함 조회
	@RequestMapping("mailReceiveList")
	public String MailReceiveList(HttpServletRequest request, HttpSession session) {
		MemberDTO loginDto = getUserInfoBySession(session);
		String page = request.getParameter("page");
		Map<String, Object> map = service.receiveMailList(page, loginDto);
		
		PageDTO pageDTO = (PageDTO)map.get("pageDTO");
		List<MailRecDTO> mailRecDTOList = (List<MailRecDTO>)map.get("mailRecDTOList");
		List<MemberDTO> memberDTOList = (List<MemberDTO>)map.get("memberDTOList");
		
		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("mailRecDTOList", mailRecDTOList);
		session.setAttribute("memberDTOList", memberDTOList);
		session.setAttribute("recMailList", pageDTO.getMailDTOList());

		return "mail_receiveList";
	}
	

//보낸 메일함 조회
	@RequestMapping("mailSentList")
	public String mailSentList(HttpServletRequest request, HttpSession session) {
		MemberDTO login = getUserInfoBySession(session);
		String page = request.getParameter("page");
		Map<String, Object> map = service.sentMailList(page, login);
		
		PageDTO pageDTO = (PageDTO)map.get("pageDTO");
		List<MemberDTO> firstRecMember = (List<MemberDTO>)map.get("firstRecMember");
		List<Integer> recMemberCount = (List<Integer>)map.get("recMemberCount");
		
		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("sentMailList", pageDTO.getMailDTOList());
		session.setAttribute("firstRecMember", firstRecMember);
		session.setAttribute("recMemberCount", recMemberCount);
		
		return "mail_sentList";
	}
	
	
//내게 쓴 메일함 조회
	@RequestMapping("mailSelfList")
	public String mailSelfList(HttpServletRequest request, HttpSession session) {
		MemberDTO loginDto = getUserInfoBySession(session);
		String page = request.getParameter("page");
		Map<String, Object> map = service.selfMailList(loginDto, page);
		
		List<MailRecDTO> mailRecDTOList = (List<MailRecDTO>)map.get("mailRecDTOList");
		PageDTO pageDTO = (PageDTO)map.get("pageDTO");
		session.setAttribute("mailRecDTOList", mailRecDTOList);
		session.setAttribute("selfMailList", pageDTO.getMailDTOList());
		session.setAttribute("pageDTO", pageDTO);
		
		return "mail_selfList";
		
	}
	
	
//메일 상세보기
	@RequestMapping("/viewMail")
	public String viewMail(@RequestParam int mail_num, HttpSession session) {
		MemberDTO loginDto = getUserInfoBySession(session);
		
		Map<String,Object> map = service.viewMail(mail_num, loginDto);
		MailDTO mailDTO = (MailDTO)map.get("mailDTO");
		List<MemberDTO> RecMemberList = (List<MemberDTO>)map.get("RecMemberList");
		MemberDTO mailSender = (MemberDTO)map.get("mailSender");
		
		session.setAttribute("mailDTO", mailDTO);
		session.setAttribute("RecMemberList", RecMemberList);
		session.setAttribute("mailSender", mailSender);
		
		return "mail_viewMail";
	}
	
	
//파일 다운로드
	@GetMapping("mailFileDownload")
	public ModelAndView mailFileDownload(@RequestParam int mail_num, ModelAndView mav){
		MailDTO mailDto = service.selectMailDTOByMailNum(mail_num);
		String fileName = mailDto.getMail_fileName();
		String fileReName = mailDto.getMail_fileReName();
		String filePath = mailDto.getMail_filePath();
		
		mav.addObject("fileName", fileName);
		mav.addObject("fileReName", fileReName);
		mav.addObject("filePath", filePath);
		mav.setViewName("fileDownView");
		return mav;
	}

//메일 삭제하기
	@ResponseBody
	@RequestMapping(value = "deleteMail", method = RequestMethod.POST)
	public String deleteMail(@RequestParam String[] list, HttpSession session){
		MemberDTO loginDto = getUserInfoBySession(session);
		int member_num = loginDto.getMember_num();
		service.deleteRecMail(loginDto, list);
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping("countMailNotReading")
	public List<MailDTO> countMailNotReading(HttpSession session) {
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		List<MailDTO> list = service.countMailNotReading(login);
		System.out.println("처리 완료");
		return  list;
	}
	
}