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

	
	public int pwUpdate(MemberDTO dto) {
		System.out.println("서비스에서 받아온changepw"+dto);
		int updatePwd = 0;
			  updatePwd = dao.updatePwd(dto);
			  System.out.println("updatePwd 서비스에서"+updatePwd);
		  return updatePwd;
		}

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


	public int updatemember(int member_num, String phone, String mail) {
		System.out.println("서비스에서 받아온 phone : "+phone);
		System.out.println("서비스에서 받아온 mail : "+mail);
		int updatemember = 0;
		updatemember = dao.updatemember(member_num, phone, mail);
		System.out.println("updatePwd 서비스에서"+updatemember);
		return updatemember;		
	}

	

	
	
//	public void updateImg(MemberDTO mdto) {
//		dao.updateImg(mdto);	
//	}

//	public void updateImg(String memberImg, String member_num) {
//		Map<String, String> map = new HashMap<>();//두가지를 넘겨야해서 키갑 밸류값으로넘김
//		map.put("photo", memberImg);//컬럼명 = 키값 = mapper의 이름과 같아야함. 
//		map.put("member_num", member_num);
//		System.out.println("servrice에 있는 map내용 ="+map);
//		dao.updateImg(map);//
//		이렇게 하면 DAO에서는 Map을 사용하여 photo와 member_num를 추출하여 업데이트 작업을 수행할 수 있음
//		}

	
//	public int updateImg(String memberImg, String memberId) {
//		System.out.println("서비스에서 받아온photochange"+memberImg+memberId);
//		int updateImg = 0;
//				updateImg = dao.updateImg(memberImg,memberId);
//			  System.out.println("updatePwd 서비스에서"+updateImg);
//		  return updateImg;
//		}





}
