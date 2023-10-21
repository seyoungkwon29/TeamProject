package com.controller.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.PageRequestDTO;
import com.common.PageResponseDTO;
import com.common.SearchCondition;
import com.common.SearchType;
import com.domain.Notice;
import com.service.NoticeService;
import com.dto.MemberDTO;
import com.dto.NoticeDTO;

@Controller
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@ModelAttribute("searchTypes")
	public List<SearchType> searchTypes() {
		List<SearchType> searchTypes = new ArrayList<>();
		searchTypes.add(new SearchType("writer","작성자"));
		searchTypes.add(new SearchType("content", "제목 + 내용"));
		return searchTypes;
	}
	
	@ModelAttribute("searchCondition")
	public SearchCondition searchCondition() {
		return new SearchCondition();
	}
	
	//공지사항 리스트
	@GetMapping("/notices")
	public String getNoticeList(
			@RequestParam(required=false, defaultValue = "1") int page,
			@RequestParam(required=false, defaultValue = "5") int size,
			@ModelAttribute("searchCondition") SearchCondition searchCondition,
			Model model) {
		
		//페이지네이션 정보 생성
		
		PageRequestDTO pageRequest = new PageRequestDTO(page, size);
		
		PageResponseDTO<NoticeDTO> pageResponse;
		if(searchCondition.getSearchType().equals("writer")) {
			pageResponse = noticeService.getNoticeDTOListByMemberName(pageRequest, searchCondition.getSearchKeyword());
		} else if (searchCondition.getSearchType().equals("content")) {
			pageResponse = noticeService.getNoticeDTOListContentLike(pageRequest, searchCondition.getSearchKeyword());
		}
		else {
			pageResponse = noticeService.getNoticeDTOList(pageRequest);
		}

		model.addAttribute("pageResponse", pageResponse);
		
		return "notice/notice-list";
	}
	
	//작성폼
	@GetMapping("/notices/new") 
	public String showNewNoticeForm(@ModelAttribute NoticeForm noticeForm, Model model) {

		model.addAttribute("noticeForm", noticeForm);
		
		return "notice/notice-new";
	}
	
	//작성하기
	@PostMapping("/notices/new")
	public String newNotice(
			@Valid @ModelAttribute NoticeForm noticeForm, BindingResult bindingResult,
			HttpSession session) {
			
		if(bindingResult.hasErrors()) {
			return "notice/notice-new";
		}
		
		MemberDTO member =(MemberDTO)session.getAttribute("login");
		Long memberNum = Long.valueOf(member.getMember_num());
		
		Notice notice = new Notice(memberNum, noticeForm.getTitle(), noticeForm.getContent());

		noticeService.createNotice(memberNum, notice);
		
		return "redirect:/notices/"+ notice.getNoticeNum();

	}
	//상세페이지
	@GetMapping("/notices/{noticeNum}")
	public String getNoticeDetails(@PathVariable("noticeNum") Long noticeNum, Model model) {
		
		NoticeDTO notice = noticeService.getNoticeDTOByNo(noticeNum);
		model.addAttribute("notice", notice);
		
		return "notice/notice-details";
	}
	
	//수정폼
	@GetMapping("/notices/{noticeNum}/edit")
	public String showUpdateNoticeForm(@ModelAttribute NoticeForm noticeForm, @PathVariable("noticeNum") Long noticeNum, Model model) {
		
		Notice notice = noticeService.getNoticeByNo(noticeNum);
		
		noticeForm.setNoticeNum(notice.getNoticeNum());
		noticeForm.setTitle(notice.getTitle());
		noticeForm.setContent(notice.getContent());
		model.addAttribute("noticeForm", noticeForm);
		
		return "notice/notice-edit";
	}
	//수정하기
	@PostMapping("/notices/{noticeNum}/edit")
	public String updateNotice(
			@Valid @ModelAttribute NoticeForm noticeForm, BindingResult bindingResult,
			@PathVariable("noticeNum") Long noticeNum,
			HttpSession session) {
		
		if (bindingResult.hasErrors()) {
			return "notice/notice-edit";
		}
		
		MemberDTO member = (MemberDTO)session.getAttribute("login");
		
		Long memberNum = Long.valueOf(member.getMember_num());
		
		NoticeDTO updateDTO = new NoticeDTO(memberNum, noticeForm.getTitle(), noticeForm.getContent());

		noticeService.updateNotice(noticeNum, memberNum, updateDTO);
		
		return "redirect:/notices/"+ noticeNum;
	}
	//삭제하기
	@PostMapping("/notices/{noticeNum}/delete") 
	public String deleteNotice(@PathVariable Long noticeNum, HttpSession session) {
		
		MemberDTO member = (MemberDTO)session.getAttribute("login");
				
		Long memberNum = Long.valueOf(member.getMember_num());
		
		noticeService.deleteNotice(noticeNum, memberNum);
		
		return "redirect:/notices";
	}
}
