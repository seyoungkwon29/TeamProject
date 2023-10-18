package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.FileBoardDTO;
import com.dto.PageDTO;

@Repository("FileBoardDAO")
public class FileBoardDAO {

	@Autowired
	SqlSessionTemplate session;

	// fileContentList
	public List<FileBoardDTO> fileBoardList() {
		List<FileBoardDTO> list = session.selectList("fileBoardList");
		return list;
	}

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
			System.out.println("DTO>>>>"+pageDTO);
			
			int offset = (pageDTO.getPage() - 1) * pageDTO.getListSize();
			System.out.println("offset>>>>"+offset);
			
			pageDTO.setListCnt(resultList.size());//검색된 총 게시물 갯수
			System.out.println("DAO >>> total listCNT >>> "+pageDTO.getListCnt());
			
			RowBounds rowBounds = new RowBounds(offset, pageDTO.getListSize());
			System.out.println("listsize>>>>"+pageDTO.getListSize());
			System.out.println(rowBounds);
			
			List<FileBoardDTO> list = session.selectList(mapperKey, map, rowBounds);	//검색된 리스트 
		
			pageDTO.setFileBoardDTOList(list);
			pageDTO.setPage(Integer.parseInt(page));
		return list;
	}
	
	public FileBoardDTO fileBoardDetail(int file_board_no) {
		FileBoardDTO dto = session.selectOne("fileBoardDetail", file_board_no);
		return dto;
	}

	public int fileBoardInsert(FileBoardDTO dto) {
		int n = session.insert("fileBoardInsert", dto);
		return n;
	}

	public int fileBoardUpdate(FileBoardDTO dto) {
		System.out.println("fileboardDAO >>> "+ dto);
		int n = session.update("fileBoardUpdate", dto);
		return n;
	}

	public int fileBoardDelete(int file_board_no) {
		int n = session.delete("fileBoardDelete", file_board_no);
		return n;
	}

	public int boardViews(int file_board_no){
		int n = session.update("boardViews", file_board_no);
		System.out.println("조회수 호출");
		return n;
	}

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

}
