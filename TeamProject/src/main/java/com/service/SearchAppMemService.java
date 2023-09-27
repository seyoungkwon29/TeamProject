package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SearchAppMemDAO;
import com.dto.SearchMemberDTO;

@Service("SearchAppMemService")
public class SearchAppMemService {

	@Autowired
	SearchAppMemDAO dao;
	
	public SearchAppMemService() {
		super();
	}
	
	//전체 멤버 데이터 => 결재자 || 참조자 선택시에 사용
	public List<SearchMemberDTO> selectAllMemberInfo() {
		List<SearchMemberDTO> list = dao.selectAllMemberInfo();
		return list;
	}

	public List<SearchMemberDTO> searchByDivName(String div_name) {
		List<SearchMemberDTO> list = dao.searchByDivName(div_name);
		return list;
	}

	public List<SearchMemberDTO> searchByMemName(String member_name) {
		List<SearchMemberDTO> list = dao.searchByMemName(member_name);
		return list;
	}
	
}

