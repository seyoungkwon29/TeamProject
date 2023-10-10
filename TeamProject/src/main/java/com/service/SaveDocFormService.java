package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SaveDocFormDAO;
import com.dto.AppDocFormDTO;
import com.dto.AppDocumentDTO;
import com.dto.AppPageInfoDTO;
import com.dto.AppSearchConditionDTO;
import com.dto.ApprovalDTO;

@Service("SaveDocFormService")
public class SaveDocFormService {
	
	@Autowired
	SaveDocFormDAO dao;

	public SaveDocFormService() {
		super();
	}
	
	//form 선택
	public AppDocFormDTO docFormName(String form_name) {
		AppDocFormDTO dto = dao.docFormName(form_name);
		return dto;
	}
	
	//모달: 전체 선택
	public List<ApprovalDTO> selectAllMemberInfo() {
		List<ApprovalDTO> list = dao.selectAllMemberInfo();
		return list;
	}

	//모달: 각종 조건 조회
	public List<ApprovalDTO> searchModalMemberInfo(AppSearchConditionDTO search) {
		List<ApprovalDTO> memlist = dao.searchModalMemberInfo(search);
		return memlist;
	}

	public int saveDocForm(AppDocumentDTO docDto) {
		int num = dao.saveDocForm(docDto);
		return num;
	}

	public int saveTempDocForm(AppDocumentDTO docDto) {
		int num = dao.saveTempDocForm(docDto);
		return num;
	}

	public List<AppDocumentDTO> callTempList(String doc_status) {
		List<AppDocumentDTO> tempList = dao.callTempList(doc_status);
		return tempList;
	}

	public AppDocumentDTO detailDocContent(int doc_no) {
		AppDocumentDTO contentDto = dao.detailDocContent(doc_no);
		return contentDto;
	}
	
	public AppDocumentDTO tempDetailDocContent(AppDocumentDTO doc) {
		AppDocumentDTO contentDto = dao.tempDetailDocContent(doc);
		return contentDto;
	}


	@Transactional
	public int SaveDocFormAndDelete(AppDocumentDTO docDto, int doc_no) {
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

	public int modifyDocStatus(ApprovalDTO app) {
		int num = dao.modifyDocStatus(app);
		return num;
	}

	public int getListCount(AppDocumentDTO doc) {
		int totalCount = dao.getListCount(doc);
		return totalCount;
	}

	public List<AppDocumentDTO> saveDocFormList(AppDocumentDTO doc, AppPageInfoDTO api) {
		List<AppDocumentDTO> docList = dao.saveDocFormList(doc, api);
		return docList;
	}

	public List<AppDocumentDTO> selectListAppDoc(AppDocumentDTO doc, AppPageInfoDTO api) {
		List<AppDocumentDTO> docList = dao.selectListAppDoc(doc, api);
		return docList;
	}

	public int getListCountApp(AppDocumentDTO doc) { //결재 문서함 문서 총 개수
		int totalCount = dao.getListCountApp(doc);
		return totalCount;
	}

	public int searchDraftCount(AppSearchConditionDTO search) { //문서함 검색 페이징
		int totalCount = dao.searchDraftCount(search);
		return totalCount;
	}

	public List<AppDocumentDTO> allSearchDraft(AppSearchConditionDTO search, AppPageInfoDTO api) {
		List<AppDocumentDTO> docList = dao.allSearchDraft(search, api);
		return docList;
	}

	public int searchAppCount(AppSearchConditionDTO search) { //기안 문서함 검색 페이징
		int totalCount = dao.searchAppCount(search);
		return totalCount;
	}

	public List<AppDocumentDTO> allSearchApp(AppSearchConditionDTO search, AppPageInfoDTO api) {
		List<AppDocumentDTO> docList = dao.allSearchApp(search, api);
		return docList;
	}
	
	
}
