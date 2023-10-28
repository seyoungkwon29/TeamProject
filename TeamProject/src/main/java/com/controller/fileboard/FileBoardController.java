package com.controller.fileboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.ExcelDownload;
import com.dto.AttachVO;
import com.dto.FileBoardDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;
import com.google.gson.JsonObject;
import com.service.FileBoardService;

@Controller
public class FileBoardController {
	
	@Autowired
	FileBoardService service;
	//엑셀다운로드
	@RequestMapping("/excel/download")
    public void fileBoardTitleListExcel(HttpServletResponse response , HttpServletRequest request) {
		System.out.println("===============fileBoardTitleListExcel===================");
		String localNow = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
		System.out.println(localNow);
		ExcelDownload.createXlsx(service.fileBoardTitleListExcel(), "FileBoard_"+localNow+".xlsx", response);
		System.out.println("==============fileBoardTitleListExcel end==============");
	}
	
	// 목록조회
	@RequestMapping("fileBoardList")
	public ModelAndView fileBoardList(@RequestParam(required = false) String page, HttpServletRequest request,
			HttpSession session) {

		if (page == null || page == "") {
			page = "1";
		}
		// 페이징 처리 중 총 게시물 조회
		PageDTO pageDTO = service.ListCount(page);

		// 페이징 처리 객체 계산
		int range = (pageDTO.getPage() - 1) / pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());

		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("getFileBoardDTOList", pageDTO.getFileBoardDTOList());
		session.setAttribute("pagefileBoardList", pageDTO.getFileBoardDTOList());

		ModelAndView mv = new ModelAndView();
		mv.setViewName("fileBoardList");
		return mv;
	}

	// 검색
	@RequestMapping("searchFileBoard")
	public ModelAndView fileBoardSearch(@RequestParam(required = false) String page,
			@RequestParam(defaultValue = "searchField") String searchField,
			@RequestParam(defaultValue = "searchVal") String searchVal, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("searchField", searchField); // 검색항목
		map.put("searchVal", searchVal); // 검색문장
		if (page == null || page == "") {
			page = "1";
		}

		// 서치결과 리스트
		List<FileBoardDTO> searchList = service.fileBoardSearch(page, searchField, searchVal);

		// 페이징 처리 중 총 게시물 조회
		PageDTO pageDTO = new PageDTO();
		pageDTO.setFileBoardDTOList(searchList);
		// 페이징 처리 객체 계산
		int range = (pageDTO.getPage() - 1) / pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());

		int endPage = range * pageDTO.getRangeSize();
		pageDTO.setEndPage(endPage);

		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("getFileBoardDTOList", pageDTO.getFileBoardDTOList());
		session.setAttribute("pagefileBoardList", pageDTO.getFileBoardDTOList());

		ModelAndView mv = new ModelAndView();
		mv.addObject("map", map);
		mv.setViewName("searchFileBoard");

		return mv;
	}

	// 상세페이지
	@RequestMapping("fileBoardDetail")
	public ModelAndView fileBoardDetail(@RequestParam("file_board_no") int file_board_no, 
			HttpServletRequest request,
			HttpServletResponse response) {
		FileBoardDTO dto = service.fileBoardDetail(file_board_no);
		service.boardViews(file_board_no);
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileBoardDetail", dto);
		mv.setViewName("fileboard/fileBoardDetail");
		return mv;
	}

	// 새 게시물 쓰기 (첨부파일 등록)
	@RequestMapping("fileBoardPost")
	public ModelAndView fileBoardPost(HttpSession session) {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		System.out.println("fileBoardPost >>>> "+ mDTO);
		FileBoardDTO fDTO = new FileBoardDTO();
		fDTO.setMember_num(mDTO.getMember_num());
		ModelAndView mv = new ModelAndView();
		mv.addObject("fDTO", fDTO);
		mv.setViewName("fileboard/fileBoardPost");
		return mv;
	}

	// 게시물 등록하기
	@RequestMapping("fileBoardInsert")
	public ModelAndView fileBoardInsert(HttpSession session, @RequestParam("writerNumber") int writerNumber,
			@RequestParam("title") String title, @RequestParam("editor") String editor,
			@RequestParam(value = "postAttachFiles", required = false) List<MultipartFile> postAttachFiles)
			throws IOException {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		
		System.out.println("postAttachFiles : " + postAttachFiles.size());
		System.out.println("postAttachFiles : " + postAttachFiles.toString());
		
		ModelAndView mv = new ModelAndView();
		int n = service.fileBoardInsert(writerNumber, title, editor, postAttachFiles);
		mv.setViewName("redirect:/fileBoardList");
		
		System.out.println("fileBoardInsert >>>> " + n);
		return mv;
	}

	// 게시물 수정하기
	@RequestMapping("fileBoardUpdate")
	public ModelAndView fileBoardUpdate(@RequestParam("no") int no, @RequestParam("writerNumber") int writerNumber,
			@RequestParam("title") String title, @RequestParam("editor") String editor) {
		System.out.println("fileBoard no > " + no);
		System.out.println("fileBoard writerNumber > " + writerNumber);
		System.out.println("fileBoard title > " + title);
		System.out.println("fileBoard editor > " + editor);

		int n = service.fileBoardUpdate(no, writerNumber, title, editor);

		ModelAndView mv = new ModelAndView();
		mv.addObject("file_board_no", n);
		mv.setViewName("redirect:/fileBoardList");
		return mv;
	}

	// 게시물 삭제하기
	@RequestMapping("fileBoardDelete")
	public ModelAndView fileBoardDelete(@RequestParam("no") int file_board_no) {
		int n = service.fileBoardDelete(file_board_no);
		System.out.println("fileBoard del >>>> " + n);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:fileBoardList");
		return mv;
	}

	// 썸머노트 이미지 업로드
	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		System.out.println("fileBoard multiparFile >>>> " + multipartFile);

		JsonObject jsonObject = new JsonObject();
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = "C:\\fileBoard\\summernoteImg\\"; // 외부경로로 저장을 희망할때.
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
		String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명

		System.out.println("fileroot + sav>>>>>> " + fileRoot + savedFileName);
		File targetFile = new File(fileRoot + savedFileName); // 경로 + 파일이름 저장

		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
			jsonObject.addProperty("url", "/summernoteImg/" + savedFileName);
			jsonObject.addProperty("responseCode", "success");

		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String result = jsonObject.toString();
		System.out.println("fileBoard return >>>>  " + result);
		return result;
	}
	
	// 첨부파일 다운로드
	@RequestMapping("/attach/download/{atch_no:[0-9]{1,16}}")
	public void process(@PathVariable(name = "atch_no") int atch_no,
			HttpServletResponse response) throws Exception {
		System.out.println("===============download=================");
		try {
			// 서비스를 통해 첨부파일 가져오기
			AttachVO vo = service.getAttach(atch_no);
			System.out.println("atach_no >>> "+ atch_no);
			// 파일명에 한글이 있는경우 처리
			String originalName = vo.getAtch_original_name();
			System.out.println("originalName >>> "+ originalName );
			String filePath = vo.getAtch_path()+"\\";	//경로
			System.out.println("filePath >>> "+ filePath );
			String fileName = vo.getAtch_file_name();	//실제 저장된 랜덤 파일명
			System.out.println("fileName >>> "+ fileName);
			
			File downloadFile = new File(filePath + fileName);	//파일경로 + 실제파일명
			int fileSize = (int)downloadFile.length();
			
			System.out.println("downloadFile>>> "+ downloadFile);
			
			if (fileSize>0) {
				String encodedFilename = "attachment; fileName*=" +"UTF-8"+ "''"+ URLEncoder.encode(fileName,"UTF-8");
				System.out.println("fileBoard_encodedFilename >>>>"+encodedFilename);
				
				response.setContentType("application/download");				//ContentTYpe
		        response.setContentLength(fileSize);							//contentLength
		        response.setHeader("Content-Disposition", encodedFilename);		//header
		        
				OutputStream os = response.getOutputStream();
				FileInputStream fis = new FileInputStream(downloadFile);
		        FileCopyUtils.copy(fis, os);
		        fis.close();
		        os.close();
				
			} else {
				throw new FileNotFoundException("파일이 없습니다..");
			}
			service.increaseDownHit(atch_no);	//다운로드 횟수
		}catch (IOException e) {
			response.reset();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
		}
	}
	
}
