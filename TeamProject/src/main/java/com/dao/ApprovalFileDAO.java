package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AppFileDTO;

@Repository("ApprovalFileDAO")
public class ApprovalFileDAO {

	@Autowired
	SqlSessionTemplate session;

	public int saveDocFile(AppFileDTO fileDto) {
		int num = session.insert("ApprovalMapper.saveDocFile", fileDto);
		return num;
	}

	public AppFileDTO fileContent(int doc_no) {
		AppFileDTO fileList = session.selectOne("ApprovalMapper.fileContent", doc_no);
		return fileList;
	}

	public int draftfileCancel(int doc_no) {
		int num = session.delete("ApprovalMapper.draftfileCancel", doc_no);
		return num;
	}
	
}
