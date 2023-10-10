package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApprovalDAO;
import com.dto.ApprovalDTO;

@Service("ApprovalService")
public class ApprovalService {

	@Autowired
	ApprovalDAO dao;

	public int registerAppMem(ApprovalDTO app) {
		int num = dao.registerAppMem(app);
		return num ;
	}

	public List<ApprovalDTO> searchAppMem(int doc_no) {
		List<ApprovalDTO> list = dao.searchAppMem(doc_no);
		return list;
	}

	public int draftAppMemCancel(int doc_no) {
		int num = dao.draftAppMemCancel(doc_no);
		return num ;
	}

	public int draftRefMemCancel(int doc_no) {
		int num = dao.draftRefMemCancel(doc_no);
		return num ;
	}

	public List<ApprovalDTO> selectAllWaitAppStatus(int doc_no) {
		List<ApprovalDTO> list = dao.selectAllWaitAppStatus(doc_no);
		return list;
	}

	public void modifyNextAppMemStatus(int app_no) {
		 dao.modifyNextAppMemStatus(app_no);	
	}

	public int updateAppMemStatus(ApprovalDTO app) {
		int num = dao.updateAppMemStatus(app);
		return num ;
	}

	public int updateDocStatus(ApprovalDTO app) {
		int num = dao.updateDocStatus(app);
		return num ;
	}
	
}