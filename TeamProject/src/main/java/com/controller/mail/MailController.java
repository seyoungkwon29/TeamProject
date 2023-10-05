package com.controller.mail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

@Controller
public class MailController {
	@Autowired
	MailService service;
	@Autowired
	MemberService mService;
	@RequestMapping(value = "/sendMailProcess", method = RequestMethod.POST)
	public String sendMail(HttpSession session,
			HttpServletRequest request,
			@RequestParam("mail_file") MultipartFile multipartFile) {
		
		ServletContext application = session.getServletContext();
		String realPath = "C:/mail_upload";
		
		//사용자 정보
		MemberDTO loginDto = (MemberDTO)session.getAttribute("login");
		
		//메일 받는 사람들
		String addressListStr = request.getParameter("mail_receiver");
		String addressList[] = addressListStr.split(" ");
		List<Integer> rec_numList = service.findMemberNumByEmail(addressList);

		
		//데이터 파싱
		String mail_title = request.getParameter("mail_title");
		String mail_content = request.getParameter("mail_content");
		String mail_sender = loginDto.getMail();
		int member_num = loginDto.getMember_num();	
		
		String mail_fileName = multipartFile.getOriginalFilename(); //사용자 지정 파일 이름
		UUID uuid = UUID.randomUUID(); //파일 이름 중복 방지를 위한 식별자 
		String mail_fileReName = uuid+"_"+mail_fileName; //식별자 이름 + 사용자 지정파일 이름으로 파일이름 중복 방지
		
		//파일 I.O처리
		File saveFile = new File(realPath,mail_fileReName);
		try {
			multipartFile.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO에러");
		}
		
		//DB에 넘겨줄 MailDTO
		MailDTO mailDto = new MailDTO();
		mailDto.setMail_title(mail_title);
		mailDto.setMail_content(mail_content);
		mailDto.setMail_sender(mail_sender);
		mailDto.setMember_num(member_num);
		mailDto.setMail_fileName(mail_fileName);
		mailDto.setMail_filePath(realPath);
		mailDto.setMail_fileReName(mail_fileReName);
		
		System.out.println(mailDto);
		
		//최근 파일 고유번호
		int recentEmailNum = service.mailSendProcess1(mailDto);
		
		
		//DB에 넘겨줄 MailRecDto
		MailRecDTO mailRecDto = new MailRecDTO();
		mailRecDto.setMail_num(recentEmailNum);
		
		//Mail_Rec테이블 insert
		int res = 0;
		for(int i=0; i<rec_numList.size(); i++) {
			mailRecDto.setRec_num(rec_numList.get(i));
			mailRecDto.setMail_receiver(addressList[i]);
			res += service.insertReceiveTable(mailRecDto); //insert 작업
		}

		String msg = "메일전송이 완료되었습니다!";
		if(res != rec_numList.size()) {
			msg = "다시 시도해주세요";
			return "redirect:writeMail";
		}
		
		session.setAttribute("msg", msg);
		
		return "redirect:writeMail";
	}
	
//메일 주소록
	@RequestMapping("/mailAddressBook")
	public String mailAddressBook(HttpSession session, HttpServletRequest request) {
		MemberDTO loginDto = (MemberDTO)session.getAttribute("login");
		if(loginDto == null) {
			return "redirect:login";
		}
		int user_num = loginDto.getMember_num();
		
		List<MemberDTO> list = service.selectAllMemberListExceptMe(user_num);
		request.setAttribute("memberList", list);
		return "mail_addressBook";
	}
	
	
	
	
//메일 내게쓰기
	@RequestMapping(value = "/sendSelfMailProcess", method = RequestMethod.POST)
	public String sendSelfMailProcess(HttpSession session,
			HttpServletRequest request,
			@RequestParam("mail_file") MultipartFile multipartFile) {
		
		ServletContext application = session.getServletContext();
		String realPath = "C:/mail_upload";
		
		//사용자 정보
		MemberDTO loginDto = (MemberDTO)session.getAttribute("login");

		
		//데이터 파싱
		String mail_title = request.getParameter("mail_title");
		String mail_content = request.getParameter("mail_content");
		String mail_sender = loginDto.getMail();
		int member_num = loginDto.getMember_num();		
		String mail_fileName = multipartFile.getOriginalFilename(); //사용자가 지정한 파일이름
		UUID uuid = UUID.randomUUID(); //파일 이름 중복 방지용 식별자
		String mail_fileReName = uuid+"_"+mail_fileName; //식별자+사용자 지정 파일이름으로 파일이름 중복방지
		
		//파일 IO 과정
		File saveFile = new File(realPath,mail_fileReName);
		try {
			multipartFile.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO에러");
		}
		
		//DB에 넘겨줄 MailDTO
		MailDTO mailDto = new MailDTO();
		mailDto.setMail_title(mail_title);
		mailDto.setMail_content(mail_content);
		mailDto.setMail_sender(mail_sender);
		mailDto.setMember_num(member_num);
		mailDto.setMail_fileName(mail_fileName);
		mailDto.setMail_filePath(realPath);
		mailDto.setMail_fileReName(mail_fileReName);
		
		System.out.println(mailDto);
		
		int recentEmailNum = service.mailSendProcess1(mailDto);
		
		
		//DB에 넘겨줄 MailRecDTO
		MailRecDTO mailRecDto = new MailRecDTO();
		mailRecDto.setMail_num(recentEmailNum);
		
		//Mail_Rec테이블 insert
		int res = 0;
		mailRecDto.setRec_num(loginDto.getMember_num());
		mailRecDto.setMail_receiver(loginDto.getMail());
		res += service.insertReceiveTable(mailRecDto); //insert

		String msg = "메일전송 완료";
		
		session.setAttribute("msg", msg);
		
		return "redirect:writeMail";
	}
	
//받은 메일함 조회
	@RequestMapping("mailReceiveList")
	public String MailReceiveList(HttpServletRequest request, HttpSession session) {
		MemberDTO loginDto = (MemberDTO)session.getAttribute("login");
		if(loginDto==null) {
			return "redirect:/";
		}
		
		//페이징 처리 객체
		String page = request.getParameter("page");
		
		if(page == null) {
			page = "1";
		}
		
		int member_num = loginDto.getMember_num();
		//페이징 처리를 위한 객체
		PageDTO pageDTO =  service.receiveMailList(member_num, page);
		
		
		session.setAttribute("recMailList", pageDTO.getMailDTOList());
		
		
		//메일 수신여부 확인을 위한 MailRecDTO 리스트, //보낸사람 정보
		List<MemberDTO> memberDTOList = new ArrayList<>();
		List<MailRecDTO> mailRecDTOList = new ArrayList<>();
		MailRecDTO mailRecDTO = new MailRecDTO();
		mailRecDTO.setRec_num(member_num);
		for(MailDTO m : pageDTO.getMailDTOList()) {
			mailRecDTO.setMail_num(m.getMail_num());
			mailRecDTOList.add(service.selectMailRecDTOByMailNumAndMemberNum(mailRecDTO));
			memberDTOList.add(mService.myPage(m.getMember_num()));
		}
		//페이징 처리 객체 계산
		int range = (pageDTO.getPage()-1)/pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());
		
		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("mailRecDTOList", mailRecDTOList);
		session.setAttribute("memberDTOList", memberDTOList);

		return "mail_receiveList";
	}

//보낸 메일함 조회
	@RequestMapping("mailSendList")
	public String mailSendList(HttpServletRequest request, HttpSession session) {
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		if(login==null) {
			return "redirect:/";
		}
		
		//페이징 처리 객체
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		int member_num = login.getMember_num();
		PageDTO pageDTO = service.sentMailList(member_num, page);
		//페이징 처리 객체 계산
		int range = (pageDTO.getPage()-1)/pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());
		
		
		//받는사람 : 홍길동 외 n명 처리
		List<Integer> firstRecMemberNum = new ArrayList<>(); //홍길동 
		List<MemberDTO> firstRecMember = new ArrayList<>();
		List<Integer> recMemberCount = new ArrayList<>(); //외 n명 

		for(int i=0; i<pageDTO.getMailDTOList().size(); i++) {
			int mail_num = pageDTO.getMailDTOList().get(i).getMail_num();
			
			List<MailRecDTO> list = service.selectMailRecDTOByMailNum(mail_num);
			recMemberCount.add(list.size()-1);
			firstRecMemberNum.add(list.get(0).getRec_num());
		}
		for(int i=0; i<firstRecMemberNum.size(); i++) {
			firstRecMember.add(mService.myPage(firstRecMemberNum.get(i)));
		}
		
		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("sentMailList", pageDTO.getMailDTOList());
		session.setAttribute("firstRecMember", firstRecMember);
		session.setAttribute("recMemberCount", recMemberCount);
		
		
		
		
		return "mail_sendList";
	}
	
	
//내게 쓴 메일함 조회
	@RequestMapping("mailSelfList")
	public String mailSelfList(HttpServletRequest request, HttpSession session) {
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		if(login==null) {
			return "redirect:/";
		}
		
		int member_num = login.getMember_num();
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		PageDTO pageDTO = service.selfMailList(member_num, page);
		//페이징 처리 객체 계산
		int range = (pageDTO.getPage()-1)/pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());
		
	
		
		//메일 확인여부 확인을 위한 MailRecDTO 리스트
		List<MailRecDTO> mailRecDTOList = new ArrayList<>();
		MailRecDTO mailRecDTO = new MailRecDTO();
		mailRecDTO.setRec_num(member_num);
		for(MailDTO m : pageDTO.getMailDTOList()) {
			mailRecDTO.setMail_num(m.getMail_num());
			mailRecDTOList.add(service.selectMailRecDTOByMailNumAndMemberNum(mailRecDTO));
		}
		session.setAttribute("mailRecDTOList", mailRecDTOList);
		session.setAttribute("selfMailList", pageDTO.getMailDTOList());
		session.setAttribute("pageDTO", pageDTO);

		return "mail_selfList";
		
	}
	
	
//메일 상세보기
	@RequestMapping("/viewMail")
	public String viewMail(@RequestParam int mail_num, HttpSession session) {
		//넘어온 메일번호로 MailDTO 찾기(발신자 및 메일정보를 뽑기 위함)
		MailDTO mailDTO = service.selectMailDTOByMailNum(mail_num);
		//넘어온 메일번호로 MailRecDTO 찾기(수신자 리스트를 뽑기 위함)
		List<MailRecDTO> MailReclist = service.selectMailRecDTOByMailNum(mail_num);
		
		
		//MailRecDTO를 받은사람들 사번으로 받은사람들 MemberDTO로 전환
		List<MemberDTO> RecMemberList = new ArrayList<>(); //
		for(MailRecDTO m : MailReclist) {
			RecMemberList.add(mService.myPage(m.getRec_num()));
		}
		MemberDTO mailSender = mService.myPage(mailDTO.getMember_num()); //보낸사람 사번으로 멤버찾기
		
		//받은 메일 읽음 여부 수정하기
		MemberDTO memberDTO =(MemberDTO)session.getAttribute("login");
		MailRecDTO tempMailRecDTO = new MailRecDTO();
		tempMailRecDTO.setRec_num(memberDTO.getMember_num());
		tempMailRecDTO.setMail_num(mail_num);
		
		MailRecDTO mailRecDTO = service.selectMailRecDTOByMailNumAndMemberNum(tempMailRecDTO);
		if(mailRecDTO == null || mailRecDTO.getRec_status().equals("Y")) { //null인 경우는 보낸 메일 볼 때에 해당됨
	
		} else {
			service.checkMail(mailRecDTO);	
		}
	
		session.setAttribute("mailDTO", mailDTO);
		session.setAttribute("RecMemberList", RecMemberList);
		session.setAttribute("mailSender", mailSender);
		
		return "mail_viewMail";
	}
	
	
//파일 다운로드
	@GetMapping("mailFileDownload")
	public ModelAndView mailFileDownload(
			@RequestParam int mail_num,
			ModelAndView mav){
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
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		int member_num = login.getMember_num();
		
		MailRecDTO mailRecDTO = new MailRecDTO();
		mailRecDTO.setRec_num(member_num);
		for(int i=0; i<list.length; i++) {
			int mail_num = Integer.parseInt(list[i]);
			mailRecDTO.setMail_num(mail_num);			
			service.deleteRecMail(mailRecDTO);
		}	
		return null;

	}
	
	
	
}