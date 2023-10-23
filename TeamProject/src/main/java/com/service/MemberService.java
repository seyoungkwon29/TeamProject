package com.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
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

//====================================================================	
	//비밀번호 찾기 이메일발송
	public void sendEmail(MemberDTO vo, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "gmqtn1000";//아이디
		String hostSMTPpwd = "";//비밀번호입력필★

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "gmqtn1000@naver.com";
		String fromName = "Everywhere";
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += vo.getMember_name() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			System.out.println("받는사람 이름 : "+vo.getMember_name());
			msg += "<p>임시 비밀번호 : ";
			msg += vo.getPassword() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = vo.getMail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); //네이버 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, vo.getMember_name());
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	//비밀번호찾기
	public void findPw(HttpServletResponse response, MemberDTO vo) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		MemberDTO ck = dao.readMember(vo.getMember_num());
		System.out.println("v0 : "+vo);
		System.out.println("서비스의ck에 들어있는거 : "+ ck);
		PrintWriter out = response.getWriter();
		System.out.println("서비스의 out에들어있는것"+out);
		// 가입된 아이디가 없으면
		if(dao.pwidCheck(vo.getMember_num()) == null) {
			System.out.println("등록되지 않은 아이디입니다");
			out.print("등록되지 않은 아이디입니다.");
			out.close();
		}
		// 가입된 이메일이 아니면
		else if(!vo.getMail().equals(ck.getMail())) {
			out.print("등록되지 않은 이메일입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String memberName = ck.getMember_name();
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			vo.setPassword(pw);
			vo.setMember_name(memberName);
			System.out.println("패스워드 :"+pw);
			
			System.out.println("사원이름 : "+ memberName);
			// 비밀번호 변경
			dao.updatePw(vo);
			// 비밀번호 변경 메일 발송
			sendEmail(vo, "findpw");

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			System.out.println("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}
	}
	
}//service end
