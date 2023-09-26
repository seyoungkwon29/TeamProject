package com.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AppDocumentDTO;
import com.dto.AppDocumentMapDTO;
import com.dto.ApprovalMapDTO;

@Repository("SaveDocFormDAO")
public class SaveDocFormDAO {
	
	@Autowired
	SqlSessionTemplate session;

	public int saveDocForm(AppDocumentMapDTO docDto) {
		int num = session.insert("ApprovalMapper.saveDocForm", docDto);
		return num;
	}

	public List<AppDocumentDTO> saveDocFormList(Map<String, Object> map) {
		List<AppDocumentDTO> docList = session.selectList("ApprovalMapper.saveDocFormList", map);
		return docList;
	}

	public int saveTempDocForm(AppDocumentDTO docDto) {
		int num = session.insert("ApprovalMapper.saveTempDocForm", docDto);
		return num;
	}

	public List<AppDocumentDTO> callTempList(String doc_status) {
		List<AppDocumentDTO> tempList = session.selectList("ApprovalMapper.callTempList", doc_status);
		return tempList;
	}

	public AppDocumentMapDTO detailDocContent(int doc_no) {
		AppDocumentMapDTO contentDto = session.selectOne("ApprovalMapper.detailDocContent", doc_no);
		return contentDto;
	}
	
	public AppDocumentDTO tempDetailDocContent(AppDocumentDTO doc) {
		AppDocumentDTO contentDto = session.selectOne("ApprovalMapper.tempDetailDocContent", doc);
		return contentDto;
	}

	public int tempDocDelete(int doc_no) {
		System.out.println("dao : " + doc_no);
		int num = session.delete("ApprovalMapper.tempDocDelete", doc_no);
		return num;
	}


	public List<AppDocumentMapDTO> selectListAppDoc(int member_num) {
		List<AppDocumentMapDTO> docList = session.selectList("ApprovalMapper.selectListAppDoc", member_num);
		return docList;
	}

	public int modifyDocStatus(ApprovalMapDTO app) {
		int num = session.update("ApprovalMapper.modifyDocStatus", app);
		return num;
	}





}
