package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.ApprovalDAO;
import com.dto.ApprovalDTO;
import com.dto.ApprovalMapDTO;

@Service("ApprovalService")
public class ApprovalService {

	@Autowired
	ApprovalDAO dao;

	public int registerAppMem(ApprovalMapDTO app) {
		int num = dao.registerAppMem(app);
		return num ;
	}

	public List<ApprovalMapDTO> searchAppMem(int doc_no) {
		List<ApprovalMapDTO> list = dao.searchAppMem(doc_no);
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

	public List<ApprovalMapDTO> selectAllWaitAppStatus(int doc_no) {
		List<ApprovalMapDTO> list = dao.selectAllWaitAppStatus(doc_no);
		return list;
	}

	public void modifyNextAppMemStatus(int app_no) {
		 dao.modifyNextAppMemStatus(app_no);	
	}

	public int updateAppMemStatus(ApprovalMapDTO app) {
		int num = dao.updateAppMemStatus(app);
		return num ;
	}

	public int updateDocStatus(ApprovalMapDTO app) {
		int num = dao.updateDocStatus(app);
		return num ;
	}
	
}