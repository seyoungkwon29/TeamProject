package com.controller.fileboard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.PageDTO;
import com.dto.FileBoardDTO;
import com.google.gson.JsonObject;
import com.service.FileBoardService;

@Controller
public class FileBoardController {
	//인터셉트는 나중에
	@Autowired
	FileBoardService service;
	
	// 목록조회
		@RequestMapping("/fileBoardList")
		public ModelAndView fileBoardList(HttpServletRequest request, HttpSession session) {
			String page = (String) session.getAttribute("page");

			List<FileBoardDTO> list = service.fileBoardList();
			ModelAndView mv = new ModelAndView();
			mv.addObject("fileBoardList", list);
			mv.setViewName("/fileBoardList");
			System.out.println(">>list ... " + list);

			// paging
			if (page == null) {
				page = "1";
			}

			// 페이징 처리를 위해서 service에서 게시글이 총 몇인지 만들기
			PageDTO pageDTO = service.ListCount(page);
			System.out.println("page 유무 >>> "+pageDTO);
			session.setAttribute("pagefileBoardList", pageDTO.getFileBoardDTOList());
			
			// 페이징 처리 객체 계산
			int range = (pageDTO.getPage() - 1) / pageDTO.getRangeSize() + 1;
			pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());

			System.out.println("시작 페이지 : " + pageDTO.getStartPage());
			System.out.println("pageDTO : " + pageDTO.toString());
			System.out.println("pagefileBoardList : " + pageDTO.getFileBoardDTOList());
			System.out.println("getFileBoardDTOList개수 : " + pageDTO.getFileBoardDTOList().size());
			System.out.println("전체 리스트 개수 : " + pageDTO.getListCnt());
			System.out.println("전체 페이지 개수 : " + pageDTO.getPageCnt());
			System.out.println("현재 Range : " + pageDTO.getRange());

			session.setAttribute("pageDTO", pageDTO);
			session.setAttribute("getFileBoardDTOList", pageDTO.getFileBoardDTOList());
			return mv;
		}
	
	//상세페이지정보
	@RequestMapping("/fileBoardDetail")
	public ModelAndView fileBoardDetail(@RequestParam("file_board_no")int file_board_no) {
		FileBoardDTO dto = service.fileBoardDetail(file_board_no);
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileBoardDetail", dto);
		mv.setViewName("/fileboard/fileBoardDetail");
		System.out.println(">>detail dto ..." + dto);
		return mv;
	}

	//새게시물 쓰기
	@RequestMapping("/fileBoardPost")
	public ModelAndView fileBoardPost() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fileboard/fileBoardPost");
		return mv;
	}
	
	//등록하기 - 리퀘스트파라미터 null로 넣어주는법 게시글등록
	@RequestMapping("/fileBoardInsert")
	public ModelAndView fileBoardInsert(@RequestParam("writerNumber") int writerNumber
			,@RequestParam("title") String title, @RequestParam("content") String content) {	
		int n = service.fileBoardInsert(writerNumber,title,content);
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertMesg",n);
		mv.setViewName("redirect:/fileBoardList");
		System.out.println(">>insert dto ..."+n);
		return mv;
	}
	
	//수정하기 - 사원번호 넣기 수정불가, 아니면 자동 등록 
	@RequestMapping("/fileBoardUpdate")
	public ModelAndView fileBoardUpdate(@RequestParam("no") int no,@RequestParam("writerNumber") int writerNumber
	,@RequestParam("title") String title, @RequestParam("content") String content){
		
		int n = service.fileBoardUpdate(no,writerNumber,title,content);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("file_board_no",n);
		mv.setViewName("redirect:/fileBoardList");
		return mv;
	}
	
	//삭제하기	
	@RequestMapping("/fileBoardDelete")
	public ModelAndView fileBoardDelete(@RequestParam("no")int file_board_no) {
		int n = service.fileBoardDelete(file_board_no);
		System.out.println(">>> delete n ..."+n);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/fileBoardList");	
		return mv;
	}
	
	//첨부파일 업로드
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, 
			HttpServletRequest request )  {
		JsonObject jsonObject = new JsonObject();
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = "C:\\summernote_image\\"; // 외부경로로 저장을 희망할때.
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	//경로 + 파일이름 저장
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernote/resources/fileupload/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();	//이거뭔데
		return a;
	}
	
	//첨부파일 조회
	
	
	//첨부파일 다운
	
	
	//첨부파일 수정

	
}
