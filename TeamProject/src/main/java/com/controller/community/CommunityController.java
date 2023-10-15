package com.controller.community;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import com.common.FileStore;
import com.common.PageRequestDTO;
import com.common.PageResponseDTO;
import com.common.SearchCondition;
import com.common.SearchType;
import com.dto.CommunityDTO;
import com.dto.MemberDTO;
import com.dto.ReplyDTO;
import com.dto.UploadFileDTO;
import com.service.CommunityService;
import com.service.ReplyService;

@Controller
public class CommunityController {
	
	private final static Logger log = LoggerFactory.getLogger(CommunityController.class);
	
	@Autowired
	private CommunityService communityService;
	
	@Autowired
	private FileStore fileStore;
	
	@Autowired
	private ReplyService replyService;
	
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
	
	//자유게시판 리스트
	@GetMapping("/communities")
	public String getCommunityList(
		@RequestParam(name="page", required=false, defaultValue="1") int page,
		@RequestParam(name="size", required=false, defaultValue="5") int size,
		@ModelAttribute("searchCondition") SearchCondition searchCondition,
		Model model) {
		PageRequestDTO pageRequest = new PageRequestDTO(page, size);
		PageResponseDTO<CommunityDTO> pageResponse;
		if(searchCondition.getSearchType().equals("writer")) {
			pageResponse = communityService.getCommunityDetailsListByMemberName(pageRequest, searchCondition.getSearchKeyword());
		} else if (searchCondition.getSearchType().equals("content")) {
			pageResponse = communityService.getCommunityDetailsListContentLike(pageRequest, searchCondition.getSearchKeyword());
		}
		else {
			pageResponse = communityService.getCommunityDetailsList(pageRequest);
		}
		
		model.addAttribute("pageResponse", pageResponse);
		
		return "community/community-list";
	}
	
	//새로작성폼 보여주기
	@GetMapping("/communities/new")
	public String showNewCommunityForm(@ModelAttribute CommunityForm communityForm) {
		return "community/community-new";
	}
	
	//새로작성
	@PostMapping("/communities/new")
	public String newCommunity(@Valid @ModelAttribute CommunityForm communityForm, BindingResult bindingResult,
		HttpSession session, 
		RedirectAttributes redirectAttributes) throws IOException {
		
		if (bindingResult.hasErrors()) {
			return "community/community-new";
		}
		
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		Long memberNum = Long.valueOf(member.getMember_num());
		CommunityDTO community = new CommunityDTO(memberNum, communityForm.getTitle(), communityForm.getContent());
		
		List<UploadFileDTO> files = fileStore.storeFiles(communityForm.getFiles());
		List<UploadFileDTO> images = fileStore.storeFiles(communityForm.getImages());
		
		community.setFiles(files);
		community.setImages(images);
		
		communityService.save(community);
		List<UploadFileDTO> savedFiles = community.getFiles();
		List<UploadFileDTO> savedImages = community.getImages();
		
		for (UploadFileDTO file : savedFiles) {
			String savedPath = fileStore.getFullPath(file.getStoreFilename());
			log.debug("File: [{}][{}]", file.getOriginalFilename(),savedPath);
		}
		for (UploadFileDTO image : savedImages) {
			String savedPath = fileStore.getFullPath(image.getStoreFilename());
			log.debug("Image: [{}][{}]", image.getOriginalFilename(),savedPath);
		}
		
		redirectAttributes.addAttribute("comNum",community.getComNum());
		return "redirect:/communities/{comNum}";
	}
	
	//상세페이지
	@GetMapping("/communities/{comNum}") 
	public String getCommunityDetails(@PathVariable Long comNum, Model model) throws UnsupportedEncodingException {

		communityService.increaseViews(comNum);
		
		CommunityDTO communityDetails = communityService.getCommunityDetailsByNum(comNum);
		List<ReplyDTO> replyDetailsList = replyService.getReplyDetailsListByComNum(comNum);
		
		List<UploadFileDTO> files = communityDetails.getFiles();
		
		for (UploadFileDTO file : files) {
			String originalFilename = file.getOriginalFilename();
			String encodedOriginalFilename = UriUtils.encode(originalFilename, StandardCharsets.UTF_8.toString());
			file.setEncodedOriginalFilename(encodedOriginalFilename);
		}
		
		model.addAttribute("communityDetails", communityDetails);
		model.addAttribute("replyDetailsList", replyDetailsList);

		return "community/community-details";
	}
	
	//수정폼 보여주기
	@GetMapping("/communities/{comNum}/edit")
	public String showUpdatCommunityForm(@ModelAttribute("communityForm") UpdateCommunityForm communityForm, @PathVariable Long comNum, HttpSession session, Model model) {
		// TODO 게시글 작성자만 수정폼을 볼 수 있도록 변경 
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		
		CommunityDTO community = communityService.getCommunityByNum(comNum);
		
		communityForm.setComNum(comNum);
		communityForm.setTitle(community.getTitle());
		communityForm.setContent(community.getContent());
		communityForm.setAttachFiles(community.getFiles());
		model.addAttribute("communityForm", communityForm);
		return "community/community-edit";
	}
	
	//수정하기
	@PostMapping("/communities/{comNum}/edit")
	public String updateCommunity(
		@PathVariable Long comNum,
		@ModelAttribute("communityForm") UpdateCommunityForm communityForm, BindingResult bindingResult,
		HttpSession session) throws IOException {
		
		if (bindingResult.hasErrors()) {
			return "community/community-edit";
		}
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		Long memberNum = Long.valueOf(member.getMember_num());

		CommunityDTO updateDTO = new CommunityDTO();
		updateDTO.setTitle(communityForm.getTitle());
		updateDTO.setContent(communityForm.getContent());
		List<MultipartFile> newFiles = communityForm.getFiles();
		log.debug("newFiles={}", newFiles.size());
		if (!newFiles.isEmpty()) {
			List<UploadFileDTO> storeFiles = fileStore.storeFiles(newFiles);
			log.debug("storeFiles={}", storeFiles.size());
			for (UploadFileDTO file : storeFiles) {
				updateDTO.addFile(file);
			}
		}
		log.debug("updateDTO.files={}", updateDTO.getFiles());
		communityService.update(comNum, memberNum, updateDTO);
		
		return "redirect:/communities/{comNum}";
	}
	
	//삭제하기
	@PostMapping("/communities/{comNum}/delete") 
	public String deleteCommunity(@PathVariable Long comNum, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("login");
		Long memNum = Long.valueOf(member.getMember_num());
		
		communityService.delete(comNum, memNum);
		
		return "redirect:" + "/communities/";
	}
}
