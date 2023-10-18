package com.controller.projectmanagement;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/project")
    public List<ProjectDTO> project(@RequestParam Map<String, String> parameters) {
		List<ProjectDTO> list = null;
		
		String tKey = parameters.get("t_key");
		if(!StringUtils.hasText(tKey) 
				|| null == LoginConstant.memberMap.get(tKey) 
				|| !(LoginConstant.memberMap.get(tKey) instanceof MemberDTO)) {
			// 팅겨내기 - 로그인 정보 없다.
		}else {
			MemberDTO loginMember = LoginConstant.memberMap.get(tKey);
			int member_num = loginMember.getMember_num();
			System.out.println(member_num);
			list  = service.getAllProject(member_num);
		}
		System.out.println(list);
		
		return list;
    }
}
