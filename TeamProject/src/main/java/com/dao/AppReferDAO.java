package com.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AppReferDTO;
import com.dto.AppReferMapDTO;
import com.dto.ApprovalDTO;
import com.dto.ApprovalMapDTO;

@Repository("AppReferDAO")
public class AppReferDAO {

	@Autowired
	SqlSessionTemplate session;


	public int registerRefMem(AppReferDTO refDto) {
		int num = session.insert("ApprovalMapper.registerRefMem", refDto);
		return num;
	}

	public List<AppReferMapDTO> searchRefMem(int doc_no) {
		List<AppReferMapDTO> list = session.selectList("ApprovalMapper.searchRefMem", doc_no);
		return list;
	}
}
