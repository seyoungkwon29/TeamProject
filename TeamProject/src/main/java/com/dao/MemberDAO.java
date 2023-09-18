package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.dto.MemberDTO;

@Repository("MemberDAO")
public class MemberDAO {
	@Autowired
	SqlSessionTemplate session;

	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = session.selectOne("login", map);
		return dto;	
	}

	public MemberDTO myPage(int member_num) {
		MemberDTO dto = session.selectOne("myPage", member_num);
		return dto;
	}

//	public int pwCheck(String passwd) {
//		int count = session.selectOne("MemberMapper.pwCheck", passwd);
//		return count;
//	}

	public int updatePwd(MemberDTO changepw) {
		  int updatePwd = session.update("MemberMapper.updatePwd", changepw);
		  System.out.println(updatePwd);
		return updatePwd;
	}


}
