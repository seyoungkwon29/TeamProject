package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MailDAO;
import com.dto.MailDTO;
import com.dto.MailRecDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;

@Service("MailService")
public class MailService {
	@Autowired
	MailDAO dao;
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

	public PageDTO receiveMailList(int member_num, String page) {
		PageDTO pageDTO = dao.receiveMailList(member_num, page);
		return pageDTO;
	}

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


//	public List<MailDTO> selectMailRecDTOByMailNum(Map<String, Integer> map) {
//		List<MailDTO>
//		return null;
//	}
	
}
