package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.SearchMemberDTO;

@Repository("SearchAppMemDAO")
public class SearchAppMemDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public List<SearchMemberDTO> selectAllMemberInfo() {
		List<SearchMemberDTO> list = session.selectList("ApprovalMapper.selectAllMemberInfo");
		return list;
	}


	public List<SearchMemberDTO> searchByDivName(String div_name) {
		List<SearchMemberDTO> list = session.selectList("ApprovalMapper.searchByDivName", div_name);
		return list;
	}

	public List<SearchMemberDTO> searchByMemName(String member_name) {
		List<SearchMemberDTO> list = session.selectList("ApprovalMapper.searchByMemName", member_name);
		return list;
	}


}
