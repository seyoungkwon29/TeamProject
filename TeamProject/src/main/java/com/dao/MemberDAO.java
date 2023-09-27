package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;

@Repository("MemberDAO")
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate session;

	//로그인
	public MemberDTO login(HashMap<String, String> map) {
		MemberDTO dto = session.selectOne("login", map);
		return dto;	
	}

	//마이페이지
	public MemberDTO myPage(int member_num) {
		MemberDTO dto = session.selectOne("myPage", member_num);
		return dto;
	}


	//주소록에 뿌려줄 모든 멤버 리스트
	public List<MemberDTO> selectAllMemberList() {
		List<MemberDTO> list = session.selectList("selectAllMemberList");
		return list;
	}

	//비밀번호변경
	public int updatePwd(MemberDTO dto) {
		  int updatePwd = session.update("MemberMapper.updatePwd", dto); //누나 화이팅이다요~~
		  System.out.println(updatePwd);
		return updatePwd;
	}
	
	//사진변경
	public int setphoto(Map<String, Object> map) {
		System.out.println("DAO의 map에 담겨 있는것 : "+map);
		int n = session.update("MemberMapper.photoUpdate", map);
		System.out.println("업뎃완료 : "+n);//업데이트 성공 갯수
		return n;
	}

	//회원정보수정
	public int updatemember(int member_num, String phone, String mail) {
		MemberDTO mdto = new MemberDTO();
        mdto.setMember_num(member_num);
        mdto.setPhone(phone);
        mdto.setMail(mail);
        int n = session.update("MemberMapper.memberUpdate", mdto);
		return n;
	}

	//idcheck
	public int idCheck(int member_num) {
		  int n = session.selectOne("MemberMapper.memberUpdate", member_num);
		return n;
	}
	


}//dao end
