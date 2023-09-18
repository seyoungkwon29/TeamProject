package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.DocFormDTO;

@Repository("DocFormDao")
public class DocFormDao {
	
	@Autowired
	SqlSessionTemplate session;

	public DocFormDTO docFormName(String form_name) {
		DocFormDTO dto = session.selectOne("ApprovalMapper.docFormName", form_name);
		return dto;
	}

}
