package com.service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dao.FileBoardDAO;
import com.dto.AttachVO;
import com.dto.ExcelDTO;
import com.dto.FileBoardDTO;
import com.dto.PageDTO;

@Service("FileBoardService")
public class FileBoardService {

	@Autowired
	FileBoardDAO dao;
	
	//엑셀다운
	public List<ExcelDTO> fileBoardTitleListExcel() {
		List<ExcelDTO> rtnList = new ArrayList<ExcelDTO>();
		try { // fileList 조회
			List<FileBoardDTO> list = dao.fileBoardList();
			ExcelDTO eDTO = null;
			for (FileBoardDTO fileDTO : list) {
				eDTO = new ExcelDTO();
				eDTO.setExcelNo(fileDTO.getFile_board_no());
				eDTO.setExcelId(fileDTO.getMember_num());
				eDTO.setExcelTitle(fileDTO.getFile_board_title());
				rtnList.add(eDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return rtnList;
	};
	
	// 목록조회
	public List<FileBoardDTO> fileBoardList() {
		List<FileBoardDTO> list = dao.fileBoardList();
		return list;
	}
	
	// 검색
	public List<FileBoardDTO> fileBoardSearch(String page, String searchField, String searchVal) {
		List<FileBoardDTO> searchList = dao.searchList(page, searchField, searchVal);
		return searchList;
	}
	
	// 상세페이지
	public FileBoardDTO fileBoardDetail(int file_board_no) {
		FileBoardDTO dto = dao.fileBoardDetail(file_board_no);
		System.out.println("fileBoard file_board_no >>>> "+file_board_no);
		List<AttachVO> vo = dao.fileBoardDetailAttachVO(file_board_no);
		dto.setAttaches(vo);
		return dto;
	}
	
	// 게시물 등록하기
	public int fileBoardInsert(int writerNumber, String title, String editor, List<MultipartFile> postAttachFiles) throws IOException {
		System.out.println("=============== fileBoard service ===============");
		FileBoardDTO fileBoardDTO = new FileBoardDTO();
		fileBoardDTO.setMember_num(writerNumber);
		fileBoardDTO.setFile_board_title(title);
		fileBoardDTO.setFile_board_content(editor);
		int n = dao.fileBoardInsert(fileBoardDTO);
		System.out.println("fileBoard no >>>> "+n);
		System.out.println("fileBoard dto >>>> "+ fileBoardDTO.toString());
		String fileName = "";
		String filePath = "";
		String originalFilename = "";
		byte[] fileBytes;
		long fileSize;
		List<AttachVO> attaches = new ArrayList<AttachVO>();
		AttachVO attachVO = new AttachVO();
		try {
			if (postAttachFiles != null) {
				// Files를 서버컴퓨터에 업로드 하는 코드
				System.out.println("파일 들어간 사이즈 > "+postAttachFiles.size());
				int postSize = postAttachFiles.size();
				for(MultipartFile file : postAttachFiles) {
			
						fileName = UUID.randomUUID().toString(); // 파일이름은 랜덤해야됨. 사용자가올리는 다른 파일이름이 같을 수 있음.
						filePath = "C:\\fileBoard\\" + File.separatorChar + "free";
						originalFilename = file.getOriginalFilename();
						fileBytes = file.getBytes();
						fileSize = file.getSize();
						
						attachVO.setAtch_parent_no(n);	//부모글번호
						attachVO.setAtch_original_name(file.getOriginalFilename());
						attachVO.setAtch_file_size(file.getSize());
						attachVO.setAtch_content_type(file.getContentType());
						attachVO.setAtch_file_name(fileName);
						attachVO.setAtch_path(filePath);
						attachVO.setAtch_fancy_size(fancySize(file.getSize()));
						FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filePath, fileName));
						
						int b = dao.fileBoardAttachFileInsert(attachVO);
						System.out.println("fileBoardAttachFileInsert >>>>" +b);
				}
				
			} 
		} catch (Exception e) {
			e.getMessage();
			e.getStackTrace();
		}
	
		return n;
	}
	
	// 게시물 수정하기
	public int fileBoardUpdate(int no, int writerNumber, String title, String editor) {
		FileBoardDTO dto = new FileBoardDTO();
		dto.setFile_board_no(no);
		dto.setMember_num(writerNumber);
		dto.setFile_board_title(title);
		dto.setFile_board_content(editor);

		int n = dao.fileBoardUpdate(dto);
		System.out.println("fileBoard update >>>> " + n);
		int num = dto.getFile_board_no();
		return num;
	}
	// 게시물 삭제하기
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
	
	//파일사이즈 계산
	private DecimalFormat df = new DecimalFormat("#,###.0");
	private String fancySize(long size) {
		if (size < 1024) { // 1k 미만
			return size + " Bytes";
		} else if (size < (1024 * 1024)) { // 1M 미만
			return df.format(size / 1024.0) + " KB";
		} else if (size < (1024 * 1024 * 1024)) { // 1G 미만
			return df.format(size / (1024.0 * 1024.0)) + " MB";
		} else {
			return df.format(size / (1024.0 * 1024.0 * 1024.0)) + " GB";
		}
	}
	
	//첨부파일 다운로드 1 조회
	public AttachVO getAttach(int atch_no) {
		AttachVO vo = dao.getAttach(atch_no);
		return vo;
	}
	
	//첨부파일 다운 횟수 증가
	public int increaseDownHit(int atchNo) {
		int n = dao.increaseDownHit(atchNo);
		return n;
		
	};
}
