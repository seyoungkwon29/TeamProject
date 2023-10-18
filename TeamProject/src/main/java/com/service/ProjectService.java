package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.constant.LoginConstant;
import com.dao.ProjectDAO;
import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProjectService {
	@Autowired
	ProjectDAO dao;

	public List<ProjectDTO> getAllProject(int member_num) {
		List<ProjectDTO> list = dao.getAllProject(member_num);
		return list;
	}

	public void createProject(Map<String, Object> parameters) {
		Map<String,Object> tempMap = (Map<String,Object>)parameters.get("projectDTO");
		
		//JSON안에 있는 JSON데이터를 한번에 ProjectDTO로 변환 시켜준다.(아니면 하나씩 파싱해서 DTO를 생성했어야함)
		ObjectMapper objectMapper = new ObjectMapper();
		ProjectDTO projectDTO = objectMapper.convertValue(tempMap, ProjectDTO.class);
		System.out.println("projectDTO : " + projectDTO);
		
		dao.createProject(projectDTO);
		
		int project_num = projectDTO.getProject_num();
		//System.out.println("방금추가된 프로젝트의 번호: " + project_num);

		//프로젝트 멤버 테이블 추가
		List<Integer> memberList = (List<Integer>)(parameters.get("members"));
		String tKey = (String)parameters.get("tKey"); 
		int member_num = LoginConstant.memberMap.get(tKey).getMember_num();
		memberList.add(member_num);//프로젝트 멤버 리스트에 자신 추가
		
		addProjectMember(project_num, memberList);
	}

	public void addProjectMember(int project_num, List<Integer> memberList) {
		for(int i=0; i<memberList.size(); i++) {
			Map<String, Integer> map = new HashMap<>();
			map.put("project_num", project_num);
			map.put("member_num", memberList.get(i));
			dao.addProjectMember(map);
		}
		System.out.println("service멤버추가 완료");
		
	}

	public void updateProject(Map<String, Object> parameters) {
		//프로젝트 테이블 업데이트
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object>tempMap = (Map<String,Object>)parameters.get("projectDTO");
		ProjectDTO projectDTO = objectMapper.convertValue(tempMap, ProjectDTO.class);
		int project_num = projectDTO.getProject_num();
		
		//dao.updateProject(projectDTO);
		
		//프로젝트 멤버 테이블 업데이트
		List<Integer> memberList = (List<Integer>)(parameters.get("members"));
		String tKey = (String)parameters.get("tKey"); 
		int member_num = LoginConstant.memberMap.get(tKey).getMember_num();
		memberList.add(member_num);//프로젝트 멤버 리스트에 자신 추가
		
		dao.deleteProjectMembers(project_num);//기존데이터 해당 프로젝트의 멤버들 초기화
		addProjectMember(project_num, memberList);
		
		
//		//시간복잡도가 n^2이 되는데,,,어떡하지,,,
//		//기존 프로젝트 멤버에 없는 멤버들 추가
//		List<Integer> originList = dao.selectProjectMembers(project_num);
//		for(int i=0; i<list.size(); i++) {
//			for(int j=0; j<originList.size(); j++) {
//				if(list.get(i)==originList.get(j)) {
//					break;
//				} else {
//					Map<String, Integer> map = new HashMap<>();
//					map.put("project_num", projectDTO.getProject_num());
//					map.put("member_num", list.get(i));
//					dao.addProjectMember(map);
//				}
//			}
//		}
//		//삭제될 멤버가 필요한 리스트
//		originList = dao.selectProjectMembers(projectDTO.getProject_num());
//		for(int i=0; i<originList.size(); i++) {
//			for(int j=0; j<list.size(); j++) {
//				if(originList.get(i)==list.get(j)) {
//					break;
//				} else {
//					dao.deleteProjectMember(originList.get(i));
//				}
//			}
//		}	
	}//end updateProject

	public void deleteProject(int project_num) {
		dao.deleteProject(project_num);
		
	}

	public List<MemberDTO> participatedMemberList(Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num");
		List<MemberDTO> memberList = dao.participatedMemberList(project_num);
		return memberList;
	}
	
	
	
}
