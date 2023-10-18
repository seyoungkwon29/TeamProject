package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.FileBoardDAO;
import com.dto.CommunityDTO;
import com.dto.FileBoardDTO;
import com.dto.PageDTO;
import com.dto.UploadFileDTO;

@Service("FileBoardService")
public class FileBoardService {

	@Autowired
	FileBoardDAO dao;

	public List<FileBoardDTO> fileBoardList() {
		List<FileBoardDTO> list = dao.fileBoardList();
		return list;
	}

	public List<FileBoardDTO> fileBoardSearch(String page, String searchField, String searchVal) {
		List<FileBoardDTO> searchList = dao.searchList(page, searchField, searchVal);
		return searchList;
	}

	public FileBoardDTO fileBoardDetail(int file_board_no) {
		FileBoardDTO dto = dao.fileBoardDetail(file_board_no);
		return dto;
	}

	public int fileBoardInsert(int writerNumber, String title, String editor) {
		FileBoardDTO dto = new FileBoardDTO();
		dto.setMember_num(writerNumber);
		dto.setFile_board_title(title);
		dto.setFile_board_content(editor);
		int n = dao.fileBoardInsert(dto);
		return n;
	}

	public int fileBoardUpdate(int no, int writerNumber, String title, String editor) {
		FileBoardDTO dto = new FileBoardDTO();
		dto.setFile_board_no(no);
		dto.setMember_num(writerNumber);
		dto.setFile_board_title(title);
		dto.setFile_board_content(editor);

		int n = dao.fileBoardUpdate(dto);
		System.out.println("업데이트 >>> " + n);
		int num = dto.getFile_board_no();
		return num;
	}

	public int fileBoardDelete(int file_board_no) {
		int n = dao.fileBoardDelete(file_board_no);
		return n;
	}

	// 조회수
	@Transactional(readOnly = true)
	public int boardViews(int file_board_no) {
		int n = dao.boardViews(file_board_no);
		return n;
	}

	// 페이징
	public PageDTO ListCount(String page) {
		PageDTO pageDTO = dao.ListCount(page);
		return pageDTO;
	}
	
}
