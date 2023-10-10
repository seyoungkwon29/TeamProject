package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AppReferDTO;

@Repository("AppReferDAO")
public class AppReferDAO {

	@Autowired
	SqlSessionTemplate session;


	public int registerRefMem(AppReferDTO refDto) {
		int num = session.insert("ApprovalMapper.registerRefMem", refDto);
		return num;
	}

	public List<AppReferDTO> searchRefMem(int doc_no) {
		List<AppReferDTO> list = session.selectList("ApprovalMapper.searchRefMem", doc_no);
		return list;
	}
}
