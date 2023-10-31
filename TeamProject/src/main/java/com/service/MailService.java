package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MailDAO;
import com.dao.MemberDAO;
import com.dto.MailDTO;
import com.dto.MailRecDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;

@Service("MailService")
public class MailService {
	@Autowired
	MailDAO dao;
	
	@Autowired
	MemberDAO Mdao;
	
	public MailService() {
		super();
	}

	public String connectTest() {
		System.out.println(dao);
		
		return "dao : " + dao;
	}

	public int mailSendProcess1(MailDTO mailDto) {
		int recentEmailNum = dao.mailSendProcess1(mailDto);
		return recentEmailNum;
	}

	public MemberDTO selectByEmail(String email) {
		MemberDTO receiver = dao.selectByEmail(email);
		return receiver;
	}
	
	public List<MemberDTO> selectAllMemberListExceptMe(int user_num) {
		List<MemberDTO> list = dao.selectAllMemberListExceptMe(user_num);
		return list;
	}

	public List<Integer> findMemberNumByEmail(String[] addressList) {
		List<Integer> rec_numList = dao.findMemberNumByEmail(addressList);
		return rec_numList;
	}

	public int insertReceiveTable(MailRecDTO mailRecDTO) {
		int res = dao.insertReceiveTable(mailRecDTO);
		return res;
	}

//	public PageDTO receiveMailList(int member_num, String page) {
//		PageDTO pageDTO = dao.receiveMailList(member_num, page);
//		return pageDTO;
//	}

	public PageDTO sentMailList(int member_num, String page) {
		PageDTO pageDTO = dao.sentMailList(member_num, page);
		return pageDTO;
	}
	
///////////////////////////
	public PageDTO selfMailList(int member_num, String page) {
	PageDTO pageDTO = dao.selfMailList(member_num, page);
	return pageDTO;

	}
	
///////////////////////////
	public List<MailRecDTO> selectMailRecDTOByMailNum(int mail_num) {
		List<MailRecDTO> list = dao.selectMailRecDTOByMailNum(mail_num);
		return list;
	}


	public MailDTO selectMailDTOByMailNum(int mail_num) {
		MailDTO mailDTO = dao.selectMailDTOByMailNum(mail_num);
		return mailDTO;
	}

	public void checkMail(MailRecDTO mailRecDTO) {
		dao.checkMail(mailRecDTO);
	}

	public MailRecDTO selectMailRecDTOByMailNumAndMemberNum(MailRecDTO mailRecDto) {
		MailRecDTO mailRecDTO = dao.selectMailRecDTOByMailNumAndMemberNum(mailRecDto);
		return mailRecDTO;
	}

	public void deleteRecMail(MailRecDTO mailRecDTO) {
		dao.deleteRecMail(mailRecDTO);
		
	}

	public void receiveMailList(HttpServletRequest request, HttpSession session) {
		MemberDTO loginDto = (MemberDTO)session.getAttribute("login");
		//페이징 처리 객체
		String page = request.getParameter("page");
		
		if(page == null) {
			page = "1";
		}
		
		int member_num = loginDto.getMember_num();
		//페이징 처리를 위한 객체
		PageDTO pageDTO =  dao.receiveMailList(member_num, page);
		
		
		session.setAttribute("recMailList", pageDTO.getMailDTOList());
		
		
		//메일 수신여부 확인을 위한 MailRecDTO 리스트, //보낸사람 정보
		List<MemberDTO> memberDTOList = new ArrayList<>();
		List<MailRecDTO> mailRecDTOList = new ArrayList<>();
		MailRecDTO mailRecDTO = new MailRecDTO();
		mailRecDTO.setRec_num(member_num);
		for(MailDTO m : pageDTO.getMailDTOList()) {
			mailRecDTO.setMail_num(m.getMail_num());
			mailRecDTOList.add(dao.selectMailRecDTOByMailNumAndMemberNum(mailRecDTO));
			memberDTOList.add(Mdao.myPage(m.getMember_num()));
		}
		//페이징 처리 객체 계산
		int range = (pageDTO.getPage()-1)/pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());
		
		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("mailRecDTOList", mailRecDTOList);
		session.setAttribute("memberDTOList", memberDTOList);
		
	}
	
	public List<MailDTO> homeReceiveMailList(int member_num) {
		List<MailDTO> receiveList = dao.homeReceiveMailList(member_num);
		return receiveList;

	}

	public List<MailDTO> countMailNotReading(HttpSession session) {
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		List<MailDTO> list = dao.countMailNotReading(login.getMember_num());
		return list;
	}

	
}
