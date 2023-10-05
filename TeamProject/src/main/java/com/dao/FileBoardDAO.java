package com.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.PageDTO;
import com.dto.FileBoardDTO;

@Repository("FileBoardDAO")
public class FileBoardDAO {
	
	@Autowired
	SqlSessionTemplate session;
		//fileContentList
	public List<FileBoardDTO> fileBoardList() {
		List<FileBoardDTO> list = session.selectList("fileBoardList");
		return list;
	}

	public FileBoardDTO fileBoardDetail(int file_board_no) {
		FileBoardDTO dto = session.selectOne("fileBoardDetail",file_board_no);
		return dto;
	}

	public int fileBoardInsert(FileBoardDTO dto) {
		int n = session.insert("fileBoardInsert",dto);
		return n;
	}

	public int fileBoardUpdate(FileBoardDTO dto) {
		int n = session.update("fileBoardUpdate",dto);
		return n;
	}

	public int fileBoardDelete(int file_board_no) {
		int n = session.delete("fileBoardDelete",file_board_no);
		return n;
	}
	
	public int boardViews(int file_board_no) throws Exception {
		int n = session.update("boardViews",file_board_no);
		return n;
	}

	public PageDTO ListCount(String page) {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(Integer.parseInt(page));
		int offset = (pageDTO.getPage()-1)*pageDTO.getListSize();
		
		List<FileBoardDTO> tempList = session.selectList("listCount");
		List<FileBoardDTO> list = session.selectList("listCount", new RowBounds(offset, pageDTO.getListSize()));
		
		System.out.println("offset 왜 오프셋이 또 영이야 >>> "+offset);
		System.out.println("fileBoard dao tempList >>>"+tempList.size());
		System.out.println("fileBoard dao list >>>> " + list.size());
		pageDTO.setFileBoardDTOList(list);
		pageDTO.setListCnt(tempList.size());
		return pageDTO;
	}
}
