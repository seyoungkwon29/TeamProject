package com.controller.projectmanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constant.LoginConstant;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ProjectService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProjectManagementController {
	@Autowired
	ProjectService service;
	
	//로그인 확인
	public MemberDTO loginCheck(String t_key) {
		MemberDTO memberDto = null;
		if(!StringUtils.hasText(t_key)
				|| null == LoginConstant.memberMap.get(t_key)
				|| !(LoginConstant.memberMap.get(t_key) instanceof MemberDTO)) {
			System.out.println("로그인 정보가 없습니다");
			throw new RuntimeException("emptyUserInfo");
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			memberDto = LoginConstant.memberMap.get(t_key);
		}
		return memberDto;
	}
	
	//프로젝트 번호 확인 (점차  세부적으로 추가해줘야함 ex.int형식으로만 데이터를 받도록)
	public void validatePorject_num(Map<String,Object> parameters) {
		Map<String,Object> map = (Map<String,Object>)parameters.get("projectDTO");
		if(parameters.get("project_num") != null) {
			return;
		} else if(map != null && map.get("project_num") != null) {
			return;
		}
		throw new RuntimeException("empty project_num");
	}
	
	
	//프로젝트 매니저(PM),로그인한 유저
	@GetMapping("/createPM")
	public MemberDTO createPM(@RequestParam Map<String, String> parameters){		
		String t_key = parameters.get("t_key");
		MemberDTO memberDTO = loginCheck(t_key);
		MemberDTO dto  = service.createPM(memberDTO.getMember_num());
		return dto;
	}	
	
	@GetMapping("/projectList")
    public List<ProjectDTO> projectList(@RequestParam Map<String, String> parameters) {
		List<ProjectDTO> list = null;
		String t_key = parameters.get("t_key");
		
		MemberDTO loginMember = loginCheck(t_key);
		list  = service.getAllProject(loginMember.getMember_num());
		System.out.println("프로젝트 리스트>>"+list);
		return list;
    }
	
	@GetMapping("/searchAllMember")
	public List<MemberDTO> searchAllMember(@RequestParam Map<String, String> parameters) {
		List<MemberDTO> list = null;
		String t_key = parameters.get("t_key");
		
		MemberDTO memberDTO = loginCheck(t_key);
		list  = service.selectMembers(memberDTO.getMember_num());
		return list;
    }
		
	//조건으로 사원 검색
	@PostMapping("/searchMember")
	public List<MemberDTO> searchMember(@RequestBody Map<String, String> parameters) {
		List<MemberDTO> list = null;
		String t_key = parameters.get("t_key");

		MemberDTO memberDTO = loginCheck(t_key);
		list = service.searchMembers(parameters, memberDTO);
		return list;
	}
	
	@PostMapping("/participatedMemberList")
	public List<MemberDTO> participatedMemberList(@RequestBody Map<String,Object> parameters) {
		loginCheck((String)parameters.get("t_key"));
		validatePorject_num(parameters);
		List<MemberDTO> memberList = service.participatedMemberList(parameters);
		return memberList;
	}
	
	
	@PostMapping("/createProject")
	public ProjectDTO createProject (@RequestBody Map<String,Object> parameters) { //JSON형태로 들어오는 데이터를 Map으로 접근
		loginCheck((String)parameters.get("t_key"));
		ProjectDTO projectDTO = service.createProject(parameters);
		return projectDTO;
	}
	
	@PostMapping("/updateProject")
	public ProjectDTO updateProject(@RequestBody Map<String,Object> parameters) {
		loginCheck((String)parameters.get("t_key"));
		validatePorject_num(parameters);
		ProjectDTO projectDTO = service.updateProject(parameters);
		return projectDTO;
	}
	
	@PostMapping("/deleteProject")
	public void deleteProject(@RequestBody Map<String,Object> parameters) {
		loginCheck((String)parameters.get("t_key"));
		validatePorject_num(parameters);
		service.deleteProject(parameters);
	}
	
	
	
}
