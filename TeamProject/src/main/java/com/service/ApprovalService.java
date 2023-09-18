package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApprovalDao;
import com.dto.ApprovalDTO;

@Service("ApprovalService")
public class ApprovalService {

	@Autowired
	ApprovalDao dao;
	
	public ApprovalService() {
		super();
	}
	
	//전체 멤버 데이터 => 결재자 || 참조자 선택시에 사용
	public List<ApprovalDTO> selectAllMemberInfo() {
		List<ApprovalDTO> list = dao.selectAllMemberInfo();
		return list;
	}

	public List<ApprovalDTO> searchByDivName(String div_name) {
		List<ApprovalDTO> list = dao.searchByDivName(div_name);
		return list;
	}

	public List<ApprovalDTO> searchByMemName(String member_name) {
		List<ApprovalDTO> list = dao.searchByMemName(member_name);
		return list;
	}
	
}

