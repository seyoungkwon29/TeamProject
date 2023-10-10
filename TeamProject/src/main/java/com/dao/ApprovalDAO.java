package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ApprovalDTO;

@Repository("ApprovalDAO")
public class ApprovalDAO {

	@Autowired
	SqlSessionTemplate session;

	public int registerAppMem(ApprovalDTO app) {
		int num = session.insert("ApprovalMapper.registerAppMem", app);
		return num;
	}

	public List<ApprovalDTO> searchAppMem(int doc_no) {
		List<ApprovalDTO> list = session.selectList("ApprovalMapper.searchAppMem", doc_no);
		return list;
	}

	public int draftAppMemCancel(int doc_no) {
		int num = session.delete("ApprovalMapper.draftAppMemCancel", doc_no);
		return num;
	}

	public int draftRefMemCancel(int doc_no) {
		int num = session.delete("ApprovalMapper.draftRefMemCancel", doc_no);
		return num;
	}

	public List<ApprovalDTO> selectAllWaitAppStatus(int doc_no) {
		List<ApprovalDTO> list = session.selectList("ApprovalMapper.selectAllWaitAppStatus", doc_no);
		return list;
	}

	public void modifyNextAppMemStatus(int app_no) {
		session.update("ApprovalMapper.modifyNextAppMemStatus", app_no);	
	}

	public int updateAppMemStatus(ApprovalDTO app) {
		int num = session.update("ApprovalMapper.updateAppMemStatus", app);
		return num;
	}

	public int updateDocStatus(ApprovalDTO app) {
		int num = session.update("ApprovalMapper.updateDocStatus", app);
		return num;
	}
}
