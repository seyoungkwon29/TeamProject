package com.controller.projectmanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.constant.LoginConstant;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.dto.ProjectManagementDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ChattingService;
import com.service.ProjectManagementService;
import com.service.ProjectService;
import com.service.TimeLineService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TimeLineRestController {
	@Autowired
	TimeLineService service;
	
	//프로젝트 매니저(PM)
	@GetMapping("/createPM")
	public MemberDTO createPM(@RequestParam Map<String, String> parameters){
		MemberDTO dto = null;
		String tKey = parameters.get("t_key");
		if(!StringUtils.hasText(tKey)
				|| null == LoginConstant.memberMap.get(tKey)
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			MemberDTO memberDto = LoginConstant.memberMap.get(tKey);
			dto  = service.createPM(memberDto.getMember_num());
		}
		return dto;
	}
	
	
	@GetMapping("/searchAllMember")
	public List<MemberDTO> searchAllMember(@RequestParam Map<String, String> parameters) {
		List<MemberDTO> list = null;
		
		String tKey = parameters.get("t_key");
		if(!StringUtils.hasText(tKey)
				|| null == LoginConstant.memberMap.get(tKey)
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			MemberDTO memberDto = LoginConstant.memberMap.get(tKey);
			list  = service.selectMembers(memberDto.getMember_num());
		}
		return list;
    }

	//조건으로 사원 검색
	@PostMapping("/searchMember")
	public List<MemberDTO> searchMember(@RequestBody Map<String, String> parameters) {
		List<MemberDTO> list = null;
		String tKey = parameters.get("t_key");
		if(!StringUtils.hasText(tKey)
				|| null == LoginConstant.memberMap.get(tKey)
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			MemberDTO memberDto = LoginConstant.memberMap.get(tKey);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("searchCondition", parameters.get("searchCondition"));
			map.put("searchValue", parameters.get("searchValue"));
			map.put("member_num",memberDto.getMember_num());
			list = service.searchMembers(map);
		}
		return list;
	}
	//프로젝트 생성
	@PostMapping("/createProject")
	public ProjectDTO createProject(@RequestBody Map<String, Object> parameters) {
		ProjectDTO projectDTO = null;
		String tKey = (String)parameters.get("t_key");
		
		if(!StringUtils.hasText(tKey)
				|| null == LoginConstant.memberMap.get(tKey)
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> projectData = (Map<String, Object>) parameters.get("project");
			projectDTO = objectMapper.convertValue(projectData, ProjectDTO.class);
			    // projectData에서 member_num 리스트 가져오기
			    List<Integer> memberNums = (List<Integer>) parameters.get("members");
			    if (projectDTO != null) {
			    	int createNum = service.createProject(projectDTO);
					int project_num = projectDTO.getProject_num();
					if (createNum>0) {
						for (int member_num : memberNums) {
							service.addProjectMember(project_num,member_num);
						}
					}
			    }
		}
		return projectDTO;
	}
	
	@GetMapping("/projectList")
	public List<ProjectDTO> projectList(@RequestParam Map<String, String> parameters) {
		List<ProjectDTO> list = null;
		String tKey = parameters.get("t_key");
		if(!StringUtils.hasText(tKey)
				|| null == LoginConstant.memberMap.get(tKey)
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			MemberDTO memberDto = LoginConstant.memberMap.get(tKey);
			list  = service.projectList(memberDto.getMember_num());
			System.out.println(list);
		}
		return list;
    }
	
	 @PostMapping("/participatedMemberList")
	    public List<MemberDTO> participatedMemberList(@RequestBody Map<String,Object> parameters) {
	     System.out.println("participatedMemberList>>>>>>"+parameters);   
		 List<MemberDTO> memberList = service.participatedMemberList(parameters);
	        return memberList;
	    }
	 
	 @PostMapping("/updateProject")
		public void updateProject(@RequestBody Map<String,Object> parameters) {
		System.out.println(parameters);	
		 service.updateProject(parameters);
		}
		
	@PostMapping("/deleteProject")
		public void deleteProject(@RequestBody Map<String,Object> parameters) {
			int project_num = (int)parameters.get("project_num");
			System.out.println(project_num);
			service.deleteProject(project_num);
			System.out.println(project_num);
		}
	
}
