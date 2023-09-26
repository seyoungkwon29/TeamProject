package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SaveDocFormDAO;
import com.dto.AppDocumentDTO;
import com.dto.AppDocumentMapDTO;
import com.dto.ApprovalMapDTO;

@Service("SaveDocFormService")
public class SaveDocFormService {
	
	@Autowired
	SaveDocFormDAO dao;

	public SaveDocFormService() {
		super();
	}

	public int saveDocForm(AppDocumentMapDTO docDto) {
		int num = dao.saveDocForm(docDto);
		return num;
	}

	public List<AppDocumentDTO> saveDocFormList(Map<String, Object> map) {
		List<AppDocumentDTO> docList = dao.saveDocFormList(map);
		return docList;
	}

	public int saveTempDocForm(AppDocumentDTO docDto) {
		int num = dao.saveTempDocForm(docDto);
		return num;
	}

	public List<AppDocumentDTO> callTempList(String doc_status) {
		List<AppDocumentDTO> tempList = dao.callTempList(doc_status);
		return tempList;
	}

	public AppDocumentMapDTO detailDocContent(int doc_no) {
		AppDocumentMapDTO contentDto = dao.detailDocContent(doc_no);
		return contentDto;
	}
	
	public AppDocumentDTO tempDetailDocContent(AppDocumentDTO doc) {
		AppDocumentDTO contentDto = dao.tempDetailDocContent(doc);
		return contentDto;
	}


	@Transactional
	public int SaveDocFormAndDelete(AppDocumentMapDTO docDto, int doc_no) {
		int num = dao.saveDocForm(docDto); 
		if (num > 0) {
			num = dao.tempDocDelete(doc_no);
		}
		return num ;
	}

	public int draftDocCancel(int doc_no) {
		int num = dao.tempDocDelete(doc_no);
		return num;
	}

	public List<AppDocumentMapDTO> selectListAppDoc(int member_num) {
		List<AppDocumentMapDTO> docList = dao.selectListAppDoc(member_num);
		return docList;
	}

	public int modifyDocStatus(ApprovalMapDTO app) {
		int num = dao.modifyDocStatus(app);
		return num;
	}




	
	

	
}
