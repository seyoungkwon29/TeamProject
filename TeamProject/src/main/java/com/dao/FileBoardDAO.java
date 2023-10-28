package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AttachVO;
import com.dto.FileBoardDTO;
import com.dto.PageDTO;

@Repository("FileBoardDAO")
public class FileBoardDAO {

	@Autowired
	SqlSessionTemplate session;
	
	// 목록조회
	public List<FileBoardDTO> fileBoardList() {
		List<FileBoardDTO> list = session.selectList("fileBoardList");
		return list;
	}
	
	//검색
	public List<FileBoardDTO> searchList(String page,String searchField, String searchVal) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("searchField",searchField);
		map.put("searchVal",searchVal);
		
		System.out.println("dao >>> map >>> "+map.toString());
		String mapperKey = "";
			if(searchField.equals("title")) {
				mapperKey = "searchListTitle";
			}
			if(searchField.equals("userId")) {
				mapperKey = "searchListUserId";
			}
			
			List<FileBoardDTO> resultList = session.selectList(mapperKey, map);	//검색된 총 리스트
			
			
			PageDTO pageDTO = new PageDTO();
			pageDTO.setPage(Integer.parseInt(page));
			System.out.println("fileBoard DTO >>>>"+pageDTO);
			
			int offset = (pageDTO.getPage() - 1) * pageDTO.getListSize();
			System.out.println("fileBoard offset >>>>"+offset);
			
			pageDTO.setListCnt(resultList.size());//검색된 총 게시물 갯수
			System.out.println("fileBoard DAO >>> total listCNT >>> "+pageDTO.getListCnt());
			
			RowBounds rowBounds = new RowBounds(offset, pageDTO.getListSize());
			System.out.println("fileBoard listsize >>>>"+pageDTO.getListSize());
			System.out.println("fileBoard rowBounds >>>> "+ rowBounds);
			
			List<FileBoardDTO> list = session.selectList(mapperKey, map, rowBounds);	//검색된 리스트 
		
			pageDTO.setFileBoardDTOList(list);
			pageDTO.setPage(Integer.parseInt(page));
		return list;
	}
	
	// 상세페이지
	public FileBoardDTO fileBoardDetail(int file_board_no) {
		FileBoardDTO dto = session.selectOne("fileBoardDetail", file_board_no);
		return dto;
	}
	
	// 게시물 등록하기
	public int fileBoardInsert(FileBoardDTO dto) {
		int n = session.insert("fileBoardInsert", dto);
		System.out.println("fileBoard Insert 확인 >>>> "+ n);
		return dto.getFile_board_no();
	} 
	
	// 게시물 수정하기
	public int fileBoardUpdate(FileBoardDTO dto) {
		System.out.println("fileboardDAO >>> "+ dto);
		int n = session.update("fileBoardUpdate", dto);
		return n;
	}
	
	// 게시물 삭제하기
	public int fileBoardDelete(int file_board_no) {
		int n = session.delete("fileBoardDelete", file_board_no);
		return n;
	}
	
	// 조회수
	public int boardViews(int file_board_no){
		int n = session.update("boardViews", file_board_no);
		System.out.println("조회수");
		return n;
	}
	
	// 페이징
	public PageDTO ListCount(String page) {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(Integer.parseInt(page));
		int offset = (pageDTO.getPage() - 1) * pageDTO.getListSize();

		RowBounds rowBounds = new RowBounds(offset, pageDTO.getListSize());
		List<FileBoardDTO> total = session.selectList("fileBoardList");
		List<FileBoardDTO> list = session.selectList("listCount", null, rowBounds);
		pageDTO.setListCnt(total.size());
		pageDTO.setFileBoardDTOList(list);
		System.out.println("DAO >>> total list size >>> "+total.size());
		return pageDTO;
	}
	// 게시물 첨부파일 등록
	public Integer fileBoardAttachFileInsert(AttachVO attachVO) {
		List<AttachVO> list = new ArrayList<AttachVO>();
		list.add(attachVO);
		FileBoardDTO fileBoardDTO = new FileBoardDTO();
		fileBoardDTO.setAttaches(list);
		Integer b = null;
		try {
		System.out.println("attachVO >>> "+attachVO.toString());
		b = session.insert("fileBoardAttachFileInsert",attachVO);
		System.out.println("insert완료");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	// 첨부파일 리스트 조회
	public List<AttachVO> fileBoardDetailAttachVO(int file_board_no) {
		List<AttachVO> vo = session.selectList("fileBoardDetailAttachVO", file_board_no);
		System.out.println(">>>>>"+vo.toString());
		return vo;
	}
	// 첨부파일 다운로드 1 조회
	public AttachVO getAttach(int atch_no) {
		AttachVO vo = session.selectOne("getAttach",atch_no); 
		return vo;
	}
	// 다운로드 횟수 증가
	public int increaseDownHit(int atch_no) {
		int n = session.update("increaseDownHit",atch_no);
		System.out.println("다운로드 횟수 증가");
		return n;
	}
	
}
