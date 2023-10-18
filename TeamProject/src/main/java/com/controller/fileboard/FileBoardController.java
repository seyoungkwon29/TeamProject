package com.controller.fileboard;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dto.FileBoardDTO;
import com.dto.MemberDTO;
import com.dto.PageDTO;
import com.google.gson.JsonObject;
import com.service.FileBoardService;

@Controller
public class FileBoardController {
	// 인터셉트 목록조회 , 새글쓰기 적용 
	@Autowired
	FileBoardService service;
	
	// 목록조회
	@RequestMapping("fileBoardList")
	public ModelAndView fileBoardList(
			@RequestParam(required=false)String page,
			HttpServletRequest request, HttpSession session) {
		
		if (page == null || page == "") {
			page = "1";
		}
		// 페이징 처리를 위해서 service에서 게시글이 총 몇인지 만들기
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
	
	//검색
	@RequestMapping("searchFileBoard")
	public ModelAndView fileBoardSearch(
			@RequestParam(required=false)String page,
			@RequestParam(defaultValue="searchField") String searchField,
			@RequestParam(defaultValue="searchVal") String searchVal,
			HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page",page);
		map.put("searchField",searchField); //검색항목
		map.put("searchVal",searchVal);			//검색문장

		System.out.println("111111111  "+searchField.toString());
		System.out.println("222222222  "+searchVal.toString());
		
		if (page == null || page == "") {
			page = "1";
		}
	
		//서치결과 리스트
		List<FileBoardDTO> searchList = service.fileBoardSearch(page,searchField,searchVal);
		System.out.println("44444444444"+searchList);
		
		// 페이징 처리를 위해서 service에서 게시글이 총 몇인지 만들기
		PageDTO pageDTO = new PageDTO();
		pageDTO.setFileBoardDTOList(searchList);	
		// 페이징 처리 객체 계산
		int range = (pageDTO.getPage() - 1) / pageDTO.getRangeSize() + 1;
		pageDTO.pageInfo(Integer.parseInt(page), range, pageDTO.getListCnt());
		
		int endPage = range * pageDTO.getRangeSize();
		pageDTO.setEndPage(endPage);
		System.out.println("controller dto end page >>>"+ pageDTO.getEndPage());
		
		System.out.println("66666666666666");
		System.out.println("pageDTO > "+pageDTO);
		
		session.setAttribute("pageDTO", pageDTO);
		session.setAttribute("getFileBoardDTOList", pageDTO.getFileBoardDTOList());
		session.setAttribute("pagefileBoardList", pageDTO.getFileBoardDTOList());
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("map",map);
		mv.setViewName("searchFileBoard");
		
		return mv;
	}
	
	// 상세페이지정보 - 본인글
	@RequestMapping("fileBoardDetail")
	public ModelAndView fileBoardDetail(
			@RequestParam("file_board_no") int file_board_no,
			 HttpServletRequest request, HttpServletResponse response) {
		FileBoardDTO dto = service.fileBoardDetail(file_board_no);
		
		//조회수 else 인터셉터해서 쿠키 가져와야한다, 아닐경우 외부인 visit_cookies로 쿠키 생성
		Cookie newCookie = new Cookie("visit_cookie", request.getParameter("file_board_no"));
        newCookie.setMaxAge(60 * 60 * 2);
        response.addCookie(newCookie);
        service.boardViews(file_board_no);
        System.out.println("조회수 호출 CNT");
		
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("fileBoardDetail", dto);
		mv.setViewName("fileboard/fileBoardDetail");
		return mv;
	}
	
	// 상세페이지정보 - readOnly
	
	// 새게시물 쓰기
	@RequestMapping("loginCheck/fileBoardPost")
	public ModelAndView fileBoardPost(HttpSession session) {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		FileBoardDTO fDTO = new FileBoardDTO();
		fDTO.setMember_num(mDTO.getMember_num());
		ModelAndView mv = new ModelAndView();
		mv.addObject("fDTO", fDTO);
		mv.setViewName("fileboard/fileBoardPost");
		return mv;
	}

	// 등록하기 - 리퀘스트파라미터 null로 넣어주는법 게시글등록
	@RequestMapping("loginCheck/fileBoardInsert")
	public ModelAndView fileBoardInsert(HttpSession session,
			@RequestParam("writerNumber") int writerNumber,
			@RequestParam("title") String title, 
			@RequestParam("editor") String editor) {
		MemberDTO mDTO = (MemberDTO) session.getAttribute("login");
		int n = service.fileBoardInsert(writerNumber, title, editor);
		ModelAndView mv = new ModelAndView();
		mv.addObject("insertMesg", n);
		mv.setViewName("redirect:/fileBoardList");
		System.out.println(">>insert dto ..." + n);
		return mv;
	}

	// 수정하기 - 사원번호 넣기 수정불가, 아니면 자동 등록
	@RequestMapping("fileBoardUpdate")
	public ModelAndView fileBoardUpdate(@RequestParam("no") int no, @RequestParam("writerNumber") int writerNumber,
			@RequestParam("title") String title, @RequestParam("editor") String editor) {
		System.out.println("no > "+no);
		System.out.println("writerNumber > "+writerNumber);
		System.out.println("title > "+title);
		System.out.println("editor > "+editor);
		
		int n = service.fileBoardUpdate(no, writerNumber, title, editor);

		ModelAndView mv = new ModelAndView();
		mv.addObject("file_board_no", n);
		mv.setViewName("redirect:/fileBoardList");
		/* return mv; */
		return mv;
	}

	// 삭제하기
	@RequestMapping("fileBoardDelete")
	public ModelAndView fileBoardDelete(@RequestParam("no") int file_board_no) {
		int n = service.fileBoardDelete(file_board_no);
		System.out.println(">>> delete n ..." + n);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:fileBoardList");
		return mv;
	}
	
	// 첨부파일 업로드
		@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
		@ResponseBody
		public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
				HttpServletRequest request) {
			System.out.println("11111111111111"+multipartFile);
			

			JsonObject jsonObject = new JsonObject();
			String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
			String fileRoot = "C:\\summernoteImg\\"; // 외부경로로 저장을 희망할때.
			String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 파일 확장자
			String savedFileName = UUID.randomUUID() + extension; // 저장될 파일 명
			
			
			System.out.println("fileroot + sav>>>>>> "+fileRoot + savedFileName);
			File targetFile = new File(fileRoot + savedFileName); // 경로 + 파일이름 저장

			try {
				InputStream fileStream = multipartFile.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile); // 파일 저장
				jsonObject.addProperty("url", "/summernoteImg/" + savedFileName); // contextroot +
																									// resources + 저장할 내부
																									// 폴더명
				jsonObject.addProperty("responseCode", "success");
				

			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile); // 저장된 파일 삭제
				jsonObject.addProperty("responseCode", "error");
				e.printStackTrace();
			}
			String a = jsonObject.toString(); 
			System.out.println(">>>>>>>>>>>>>>a   "+a);
			return a;
		}

}
