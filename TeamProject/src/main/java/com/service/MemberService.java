package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.dto.MemberDTO;

@Service("MemberService")
public class MemberService {
	@Autowired
	MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	// 로그인
	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = new MemberDTO();
		dto = dao.login(map);
		return dto;
	}

	// 마이페이지
	public MemberDTO myPage(int member_num) {
		MemberDTO dto = new MemberDTO();
		dto = dao.myPage(member_num);
		return dto;
	}
	
	//전체 멤버 리스트
		public List<MemberDTO> selectAllMemberList() {
			List<MemberDTO> list = dao.selectAllMemberList();
			return list;
			}
	
		
	//비밀번호 변경
	public int pwUpdate(MemberDTO dto) {
		System.out.println("서비스에서 받아온changepw"+dto);
		int updatePwd = 0;
			  updatePwd = dao.updatePwd(dto);
			  System.out.println("updatePwd 서비스에서"+updatePwd);
		  return updatePwd;
		}
	
	//프로필사진변경
	public void setphoto(int member_num, String photo) {
//		경로전체를 넘기기 
		Map<String, Object> map = new HashMap<>();//두가지를 넘겨야해서 키갑 밸류값으로넘김
		map.put("member_num", member_num);
		map.put("photo", photo);//컬럼명 = 키값 = mapper의 이름과 같아야함. 
		System.out.println("servrice에 있는 map내용 ="+map);
		dao.setphoto(map);//
//		int photoupdate = dao.setphoto(map);//
//		이렇게 하면 DAO에서는 Map을 사용하여 photo와 member_num를 추출하여 업데이트 작업을 수행할 수 있음
//		return photoupdate;
	}	
	
//회원정보업데이트
	public int updatemember(int member_num, String phone, String mail) {
		System.out.println("서비스에서 받아온 phone : "+phone);
		System.out.println("서비스에서 받아온 mail : "+mail);
		int updatemember = 0;
		updatemember = dao.updatemember(member_num, phone, mail);
		System.out.println("updatePwd 서비스에서"+updatemember);
		return updatemember;		
	}

//	
//	//비밀번호 찾기 이메일발송
//	public void sendEmail(MemberDTO mdto, String div) throws Exception {
//		// Mail Server 설정
//		String charSet = "utf-8";
//		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
//		String hostSMTPid = "서버 이메일 주소(보내는 사람 이메일 주소)";
//		String hostSMTPpwd = "서버 이메일 비번(보내는 사람 이메일 비번)";
//
//		// 보내는 사람 EMail, 제목, 내용
//		String fromEmail = "보내는 사람 이메일주소(받는 사람 이메일에 표시됨)";
//		String fromName = "EveryWhere";
//		String subject = "";
//		String msg = "";
//
//		if(div.equals("findpw")) {
//			subject = "EveryWhere 임시 비밀번호 입니다.";
//			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
//			msg += "<h3 style='color: blue;'>";
//			msg += mdto.getMember_name() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
//			msg += "<p>임시 비밀번호 : ";
//			msg += mdto.getPassword() + "</p></div>";
//		}
//
//		// 받는 사람 E-Mail 주소
//		String mail = mdto.getMail();
//		try {
//			HtmlEmail email = new HtmlEmail();
//			email.setDebug(true);
//			email.setCharset(charSet);
//			email.setSSL(true);
//			email.setHostName(hostSMTP);
//			email.setSmtpPort(587); //네이버 이용시 587
//
//			email.setAuthentication(hostSMTPid, hostSMTPpwd);
//			email.setTLS(true);
//			email.addTo(mail, charSet);
//			email.setFrom(fromEmail, fromName, charSet);
//			email.setSubject(subject);
//			email.setHtmlMsg(msg);
//			email.send();
//		} catch (Exception e) {
//			System.out.println("메일발송 실패 : " + e);
//		}
//	}
//
//	//비밀번호찾기
//	public void findPw(HttpSession session, MemberDTO mdto) throws Exception {
//		MemberDTO ck = dao.myPage(mdto.getMember_num());
//		
//		// 가입된 아이디가 없으면
//		if(dao.idCheck(mdto.getMember_num()) == 0) {
//			session.getAttribute("등록되지 않은 아이디입니다.");
//		}
//		// 가입된 이메일이 아니면
//		else if(!mdto.getMail().equals(ck.getMail())) {
//			session.getAttribute("등록되지 않은 이메일입니다.");
//		}else {
//			// 임시 비밀번호 생성
//			String pw = "";
//			for (int i = 0; i < 12; i++) {
//				pw += (char) ((Math.random() * 26) + 97);
//			}
//			mdto.setPassword(pw);
//			
//			// 비밀번호 변경
//			dao.updatePwd(mdto);
//			
//			// 비밀번호 변경 메일 발송
//			sendEmail(mdto, "findpw");
//
//			session.getAttribute("이메일로 임시 비밀번호를 발송하였습니다.");
//		}
//	}

	
}//service end
