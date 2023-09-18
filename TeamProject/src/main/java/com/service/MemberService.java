package com.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
	
	
	//마이페이지 비밀번호 변경전 동일여부확인
//	public int pwCheck(String passwd) {
//		int count = dao.pwCheck(passwd);
//		return count;
//	}

	public int pwUpdate(MemberDTO changepw) {
		System.out.println("서비스에서 받아온changepw"+changepw);
		int updatePwd = 0;
		MemberDAO dao = new MemberDAO();
		updatePwd = dao.updatePwd(changepw);
		System.out.println("updatePwd 서비스에서"+updatePwd);
		return updatePwd;
	}
}
