package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApprovalFileDAO;
import com.dto.AppFileDTO;

@Service("ApprovalFileService")
public class ApprovalFileService {

	@Autowired
	ApprovalFileDAO dao;
	
	public AppFileDTO fileContent(int doc_no) {
		AppFileDTO fileList = dao.fileContent(doc_no);
		return fileList;
	}

	public int registerFile(AppFileDTO file) {
		int result = dao.registerFile(file);
		return result;
	}

	public int removeFile(int doc_no) {
		int num = dao.removeFile(doc_no);
		return num;
	}

	public AppFileDTO fileDownload(int file_no) {
		AppFileDTO fileDto = dao.fileDownload(file_no);
		return fileDto;
	}


	
}
