package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ApprovalDTO;

@Repository("ApprovalDao")
public class ApprovalDao {
	
	@Autowired
	SqlSessionTemplate session;

	public List<ApprovalDTO> selectAllMemberInfo() {
		List<ApprovalDTO> list = session.selectList("ApprovalMapper.selectAllMemberInfo");
		return list;
	}


	public List<ApprovalDTO> searchByDivName(String div_name) {
		List<ApprovalDTO> list = session.selectList("ApprovalMapper.searchByDivName", div_name);
		return list;
	}

	public List<ApprovalDTO> searchByMemName(String member_name) {
		List<ApprovalDTO> list = session.selectList("ApprovalMapper.searchByMemName", member_name);
		return list;
	}


}
