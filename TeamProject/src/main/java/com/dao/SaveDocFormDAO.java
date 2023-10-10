package com.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AppDocFormDTO;
import com.dto.AppDocumentDTO;
import com.dto.AppPageInfoDTO;
import com.dto.AppSearchConditionDTO;
import com.dto.ApprovalDTO;

@Repository("SaveDocFormDAO")
public class SaveDocFormDAO {
	
	@Autowired
	SqlSessionTemplate session;
	
	public List<ApprovalDTO> selectAllMemberInfo() {
		List<ApprovalDTO> list = session.selectList("ApprovalMapper.selectAllMemberInfo");
		return list;
	}

	public List<ApprovalDTO> searchModalMemberInfo(AppSearchConditionDTO search) {
		List<ApprovalDTO> memlist = session.selectList("ApprovalMapper.searchModalMemberInfo", search);
		return memlist;
	}
	
	public AppDocFormDTO docFormName(String form_name) {
		AppDocFormDTO dto = session.selectOne("ApprovalMapper.docFormName", form_name);
		return dto;
	}

	public int saveDocForm(AppDocumentDTO docDto) {
		int num = session.insert("ApprovalMapper.saveDocForm", docDto);
		return num;
	}

	public int saveTempDocForm(AppDocumentDTO docDto) {
		int num = session.insert("ApprovalMapper.saveTempDocForm", docDto);
		return num;
	}

	public List<AppDocumentDTO> callTempList(String doc_status) {
		List<AppDocumentDTO> tempList = session.selectList("ApprovalMapper.callTempList", doc_status);
		return tempList;
	}

	public AppDocumentDTO detailDocContent(int doc_no) {
		AppDocumentDTO contentDto = session.selectOne("ApprovalMapper.detailDocContent", doc_no);
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

	public int modifyDocStatus(ApprovalDTO app) {
		int num = session.update("ApprovalMapper.modifyDocStatus", app);
		return num;
	}

	public int getListCount(AppDocumentDTO doc) {
		int totalCount = session.selectOne("ApprovalMapper.getListCount", doc);
		return totalCount;
	}

	public List<AppDocumentDTO> saveDocFormList(AppDocumentDTO doc, AppPageInfoDTO api) {
		int docLimit = api.getDocLimit(); //한 페이지당 표시할 문서의 개수
		int currentPage = api.getCurrentPage(); //현재 페이지 번호
		int offset = (currentPage - 1) * docLimit; //가져올 문서 목록의 시작 인덱스: 현재 페이지 번호와 한 페이지당 표시할 문서의 개수
		RowBounds rowBounds = new RowBounds(offset, docLimit); //시작 인덱스와 한 페이지당 표시할 문서의 개수를 지정
		
		List<AppDocumentDTO> docList = session.selectList("ApprovalMapper.saveDocFormList", doc, rowBounds);
		return docList;		
	}


	public int getListCountApp(AppDocumentDTO doc) {
		int totalCount = session.selectOne("ApprovalMapper.getListCountApp", doc);
		return totalCount;
	}
	
	public List<AppDocumentDTO> selectListAppDoc(AppDocumentDTO doc, AppPageInfoDTO api) {
		int docLimit = api.getDocLimit(); //한 페이지당 표시할 문서의 개수
		int currentPage = api.getCurrentPage(); //현재 페이지 번호
		int offset = (currentPage - 1) * docLimit; //가져올 문서 목록의 시작 인덱스: 현재 페이지 번호와 한 페이지당 표시할 문서의 개수
		RowBounds rowBounds = new RowBounds(offset, docLimit); //시작 인덱스와 한 페이지당 표시할 문서의 개수를 지정
		
		List<AppDocumentDTO> docList = session.selectList("ApprovalMapper.selectListAppDoc", doc, rowBounds);
		return docList;
	}

	
	public int searchDraftCount(AppSearchConditionDTO search) { //기안, 임시 문서함:  검색 페이징
		int totalCount = session.selectOne("ApprovalMapper.searchDraftCount", search);
		return totalCount;
	}

	public List<AppDocumentDTO> allSearchDraft(AppSearchConditionDTO search, AppPageInfoDTO api) {
		int docLimit = api.getDocLimit(); //한 페이지당 표시할 문서의 개수
		int currentPage = api.getCurrentPage(); //현재 페이지 번호
		int offset = (currentPage - 1) * docLimit; //가져올 문서 목록의 시작 인덱스: 현재 페이지 번호와 한 페이지당 표시할 문서의 개수
		RowBounds rowBounds = new RowBounds(offset, docLimit); //시작 인덱스와 한 페이지당 표시할 문서의 개수를 지정
		
		List<AppDocumentDTO> docList = session.selectList("ApprovalMapper.allSearchDraft", search, rowBounds);
		return docList;
	}

	public int searchAppCount(AppSearchConditionDTO search) { //결재 문서함:  검색 페이징
		int totalCount = session.selectOne("ApprovalMapper.searchAppCount", search);
		return totalCount;
	}

	public List<AppDocumentDTO> allSearchApp(AppSearchConditionDTO search, AppPageInfoDTO api) {
		int docLimit = api.getDocLimit(); //한 페이지당 표시할 문서의 개수
		int currentPage = api.getCurrentPage(); //현재 페이지 번호
		int offset = (currentPage - 1) * docLimit; //가져올 문서 목록의 시작 인덱스: 현재 페이지 번호와 한 페이지당 표시할 문서의 개수
		RowBounds rowBounds = new RowBounds(offset, docLimit); //시작 인덱스와 한 페이지당 표시할 문서의 개수를 지정
		
		List<AppDocumentDTO> docList = session.selectList("ApprovalMapper.allSearchApp", search, rowBounds);
		return docList;
	}



}
