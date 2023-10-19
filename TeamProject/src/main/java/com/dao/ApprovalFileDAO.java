package com.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AppFileDTO;

@Repository("ApprovalFileDAO")
public class ApprovalFileDAO {

	@Autowired
	SqlSessionTemplate session;

	public AppFileDTO fileContent(int doc_no) {
		AppFileDTO fileList = session.selectOne("ApprovalMapper.fileContent", doc_no);
		return fileList;
	}
	
	public int registerFile(AppFileDTO file) {
		int result = session.insert("ApprovalMapper.registerFile", file);
		return result;
	}

	public int removeFile(int doc_no) {
		int num = session.delete("ApprovalMapper.removeFile", doc_no);
		return num;
	}
	
}
