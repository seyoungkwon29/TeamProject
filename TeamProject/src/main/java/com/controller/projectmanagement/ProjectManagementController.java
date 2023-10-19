package com.controller.projectmanagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.service.ProjectService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProjectManagementController {
	@Autowired
	ProjectService service;
	
	//프로젝트 매니저(PM),로그인한 유저
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
	
	@GetMapping("/projectList")
    public List<ProjectDTO> projectList(@RequestParam Map<String, String> parameters) {
		List<ProjectDTO> list = null;
		String tKey = parameters.get("t_key");
		System.out.println("컨트롤러 실행!");
		if(!StringUtils.hasText(tKey) 
				|| null == LoginConstant.memberMap.get(tKey) 
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			System.out.println("로그인 정보 없음");
		}else {
			MemberDTO loginMember = LoginConstant.memberMap.get(tKey);
			list  = service.getAllProject(loginMember.getMember_num());
			System.out.println("projectList>>>>>>>>"+list);
		}
		return list;
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
	@PostMapping("/participatedMemberList")
	public List<MemberDTO> participatedMemberList(@RequestBody Map<String,Object> parameters) {
		List<MemberDTO> memberList = service.participatedMemberList(parameters);
		return memberList;
	}
	
	@PostMapping("/createProject")
	public ProjectDTO createProject (@RequestBody Map<String,Object> parameters) { //JSON형태로 들어오는 데이터를 Map으로 접근
		ProjectDTO projectDTO = service.createProject(parameters);
		return projectDTO;
	}
	
	@PostMapping("/updateProject")
	public ProjectDTO updateProject(@RequestBody Map<String,Object> parameters) {
		ProjectDTO projectDTO = service.updateProject(parameters);
		return projectDTO;
	}
	
	@PostMapping("/deleteProject")
	public void deleteProject(@RequestBody Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num");
		System.out.println(project_num);
		service.deleteProject(project_num);
		System.out.println(project_num);
	}
	
	
	
}
