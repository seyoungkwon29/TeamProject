package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.controller.mail.MailController;
import com.dao.MailDAO;
import com.dao.MemberDAO;
import com.dto.MailDTO;
import com.dto.MailRecDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;

import lombok.RequiredArgsConstructor;

@Service("MailService")
@RequiredArgsConstructor
public class MailService {
	
	private final MailDAO dao;
	private final MemberDAO Mdao;
	
	public MailService(MailDAO dao, MemberDAO Mdao) {
		this.dao = dao;
		this.Mdao = Mdao;
	}

//메일 보내기
	@Transactional
	public String sendMail(MailDTO mailDto, String addressListStr, MultipartFile attachmentFile) {
		
		dao.saveMail(mailDto);

		//보낸 메일 고유번호
		int recentMailNum = mailDto.getMail_num();
		//DB에 넘겨줄 MailRecDto
		MailRecDTO mailRecDto = new MailRecDTO();
		mailRecDto.setMail_num(recentMailNum);
		
		//Mail_Rec테이블 insert (MappingTable -- 받은 사람들 저장하기)
		String msg = "메일전송이 완료되었습니다!";
		int res = 0;
		int resCheck = 0;

		//내게쓰기인 경우
		if(addressListStr == null) {
			mailRecDto.setRec_num(mailDto.getMember_num());
			mailRecDto.setMail_receiver(mailDto.getMail_sender()); //보내는 사람이 받는 사람이기 떄문
			dao.saveReceiveTable(mailRecDto); //insert 작업
			return msg;
		}
		
		if(addressListStr != null) {
			List<String>addressList = Arrays.asList(addressListStr.split(" "));
			List<Integer> rec_numList = dao.findMemberNumByMailAddress(addressList);
			resCheck = rec_numList.size();
			
			for(int i=0; i<rec_numList.size(); i++) {
				mailRecDto.setRec_num(rec_numList.get(i));
				mailRecDto.setMail_receiver(addressList.get(i));
				res += dao.saveReceiveTable(mailRecDto); //insert 작업
			}
			return msg;
		}
		
		if(res != resCheck) {
			msg = "다시 시도해주세요";
			return "redirect:writeMail";
		}
		
		//첨부파일 저장
		if(!attachmentFile.isEmpty()) {
			saveAttachmentFile(mailDto, attachmentFile);
		}
		return msg;
	}
	
//첨부파일  저장
	private void saveAttachmentFile(MailDTO mailDto, MultipartFile attachmentFile) {
		String realPath = "C:/mail_upload";
		String mail_fileName = attachmentFile.getOriginalFilename(); //사용자 지정 파일 이름
		UUID uuid = UUID.randomUUID(); //파일 이름 중복 방지를 위한 식별자 
		String mail_fileReName = uuid+"_"+mail_fileName; //식별자 이름 + 사용자 지정파일 이름으로 파일이름 중복 방지
		mailDto.setMail_fileName(mail_fileName);
		mailDto.setMail_filePath(realPath);
		mailDto.setMail_fileReName(mail_fileReName);
		//파일 I.O처리
		File saveFile = new File(realPath,mail_fileReName);
		try {
			attachmentFile.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO에러");
		}
	}

	
//주소록(나를 제외한 유저 리스트)
	@Transactional(readOnly = true)
	public List<MemberDTO> selectAllMemberListExceptMe(MemberDTO loginDto) {
		int user_num = loginDto.getMember_num();
		List<MemberDTO> list = dao.selectAllMemberListExceptMe(user_num);
		return list;
	}
	
//받은 메일함 조회
	@Transactional(readOnly = true)
	public Map<String, Object> receiveMailList(String page, MemberDTO loginDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		int member_num = loginDto.getMember_num();
		if(page == null) { page = "1"; }
		//페이징 처리를 위한 객체
		PageDTO pageDTO =  dao.receiveMailList(member_num, page);
		//페이징 처리
		setPaging(pageDTO, page);
		
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
		
		map.put("pageDTO", pageDTO);
		map.put("mailRecDTOList", mailRecDTOList);
		map.put("memberDTOList", memberDTOList);
		return map;
	}	
	//페이징처리를 위한 변수 저장
		private void setPaging(PageDTO pageDTO,String page) {
			pageDTO.setRange((pageDTO.getPage()-1)/pageDTO.getRangeSize() + 1);
			pageDTO.pageInfo(Integer.parseInt(page), pageDTO.getRange(), pageDTO.getListCnt());
		}
	
//보낸 메일함 조회
	@Transactional(readOnly = true)
	public Map<String, Object> sentMailList(String page, MemberDTO login) {
		Map<String, Object> map = new HashMap<String, Object>();
		int member_num = login.getMember_num();
		if(page == null) { page = "1"; }
		//페이징 처리를 위한 객체
		PageDTO pageDTO = dao.sentMailList(member_num, page);
		//페이징 처리
		setPaging(pageDTO, page);
		
		//받는사람 : 홍길동 외 n명 처리
		List<Integer> firstRecMemberNum = new ArrayList<>(); //홍길동 
		List<MemberDTO> firstRecMember = new ArrayList<>();
		List<Integer> recMemberCount = new ArrayList<>(); //외 n명 

		for(int i=0; i<pageDTO.getMailDTOList().size(); i++) {
			int mail_num = pageDTO.getMailDTOList().get(i).getMail_num();
			
			List<MailRecDTO> list = dao.selectMailRecDTOByMailNum(mail_num);
			recMemberCount.add(list.size()-1);
			firstRecMemberNum.add(list.get(0).getRec_num());
		}
		for(int i=0; i<firstRecMemberNum.size(); i++) {
			firstRecMember.add(Mdao.myPage(firstRecMemberNum.get(i)));
		}
		map.put("pageDTO", pageDTO);
		map.put("firstRecMember", firstRecMember);
		map.put("recMemberCount", recMemberCount);
		return map;
	}
	
//내게 쓴 메일함 조회
	@Transactional(readOnly = true)
	public Map<String, Object> selfMailList(MemberDTO login, String page ) {
		Map<String, Object> map = new HashMap<String, Object>();
		int member_num = login.getMember_num();
		if(page == null) { page = "1"; }
		//페이징 처리를 위한 객체
		PageDTO pageDTO = dao.selfMailList(member_num, page);
		//페이징 처리
		setPaging(pageDTO, page);
		
		//메일 확인여부 확인을 위한 MailRecDTO 리스트
		List<MailRecDTO> mailRecDTOList = new ArrayList<>();
		MailRecDTO mailRecDTO = new MailRecDTO();
		mailRecDTO.setRec_num(member_num);
		for(MailDTO m : pageDTO.getMailDTOList()) {
			mailRecDTO.setMail_num(m.getMail_num());
			mailRecDTOList.add(dao.selectMailRecDTOByMailNumAndMemberNum(mailRecDTO));
		}
		map.put("mailRecDTOList", mailRecDTOList);
		map.put("pageDTO", pageDTO);
		return map;

	}
	
	@Transactional(readOnly = true)
	public List<MailRecDTO> selectMailRecDTOByMailNum(int mail_num) {
		List<MailRecDTO> list = dao.selectMailRecDTOByMailNum(mail_num);
		return list;
	}

//메일 상세보기
	@Transactional(readOnly = true)
	public Map<String,Object>viewMail(int mail_num, MemberDTO memberDTO) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		//넘어온 메일번호로 MailDTO 찾기(발신자 및 메일정보를 뽑기 위함)
		MailDTO mailDTO = selectMailDTOByMailNum(mail_num);
		map.put("mailDTO", mailDTO);
		//넘어온 메일번호로 해당메일 수신자들 사번 찾기(수신자 리스트를 뽑기 위함)
		List<MailRecDTO> mailRecList = dao.selectMailRecDTOByMailNum(mail_num);
		
		//수신자들 사번리스트(mailRecList)으로 받은사람들 MemberDTO로 전환
		List<MemberDTO> RecMemberList = new ArrayList<>(); //
		for(MailRecDTO m : mailRecList) {
			RecMemberList.add(Mdao.myPage(m.getRec_num()));
		}
		map.put("RecMemberList",RecMemberList);
		MemberDTO mailSender = Mdao.myPage(mailDTO.getMember_num()); //보낸사람 사번으로 멤버찾기
		map.put("mailSender", mailSender);
		
		//해당 메일 읽음 여부 수정하기
		MailRecDTO tempMailRecDTO = new MailRecDTO();
		tempMailRecDTO.setRec_num(memberDTO.getMember_num());
		tempMailRecDTO.setMail_num(mail_num);
		MailRecDTO mailRecDTO = selectMailRecDTOByMailNumAndMemberNum(tempMailRecDTO);
		if(mailRecDTO != null && mailRecDTO.getRec_status().equals("N")) { //null인 경우는 보낸 메일 볼 때에 해당됨
			checkMail(mailRecDTO);
		}
		return map;
	}
	
	@Transactional(readOnly = true)
	public MailDTO selectMailDTOByMailNum(int mail_num) {
		MailDTO mailDTO = dao.selectMailDTOByMailNum(mail_num);
		return mailDTO;
	}
	
	@Transactional
	public void checkMail(MailRecDTO mailRecDTO) {
		dao.checkMail(mailRecDTO);
	}

	@Transactional(readOnly = true)
	public MailRecDTO selectMailRecDTOByMailNumAndMemberNum(MailRecDTO mailRecDto) {
		MailRecDTO mailRecDTO = dao.selectMailRecDTOByMailNumAndMemberNum(mailRecDto);
		return mailRecDTO;
	}
	
	@Transactional
	public void deleteRecMail(MemberDTO login, String[] list) {
		int member_num = login.getMember_num();
		
		MailRecDTO mailRecDTO = new MailRecDTO();
		mailRecDTO.setRec_num(member_num);
		for(int i=0; i<list.length; i++) {
			int mail_num = Integer.parseInt(list[i]);
			mailRecDTO.setMail_num(mail_num);			
			dao.deleteRecMail(mailRecDTO);
		}	
	}
	

	@Transactional(readOnly = true)
	public List<MailDTO> homeReceiveMailList(int member_num) {
		List<MailDTO> receiveList = dao.homeReceiveMailList(member_num);
		return receiveList;

	}

	@Transactional(readOnly = true)
	public List<MailDTO> countMailNotReading(MemberDTO login) {
		List<MailDTO> list = dao.countMailNotReading(login.getMember_num());
		return list;
	}
	
}
