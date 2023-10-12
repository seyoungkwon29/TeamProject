package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApprovalFileDAO;
import com.dto.AppFileDTO;

@Service("ApprovalFileService")
public class ApprovalFileService {

	@Autowired
	ApprovalFileDAO dao;

	public int saveDocFile(AppFileDTO fileDto) {
		int num = dao.saveDocFile(fileDto);
		return num;
	}

	public AppFileDTO fileContent(int doc_no) {
		AppFileDTO fileList = dao.fileContent(doc_no);
		return fileList;
	}

	public int draftfileCancel(int doc_no) {
		int num = dao.draftfileCancel(doc_no);
		return num;
	}

	public int registerFile(AppFileDTO file) {
		int result = dao.insertFile(file);
		return result;
	}

//	public int saveFileInfo(MultipartFile[] files) {
//		List<Map<String, Object>> fileList = fileUtils.parseFileInfo(null, files);
//		
//	    for(int i=0; i < fileList.size(); i++) {
//	    	dao.insertFile(fileList.get(i));
//	    }
//	}


	
}
