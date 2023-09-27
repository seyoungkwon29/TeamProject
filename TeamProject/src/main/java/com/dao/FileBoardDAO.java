package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.FileBoardDTO;
import com.dto.MeetingRoomDTO;
import com.dto.MemberDTO;
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
}
