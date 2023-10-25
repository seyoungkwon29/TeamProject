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

	public MemberDTO createPM(int member_num) {
		MemberDTO dto = dao.createPM(member_num);
		return dto;
	}
	
	public List<ProjectDTO> getAllProject(int member_num) {
		List<ProjectDTO> list = dao.getAllProject(member_num);
		return list;
	}
	
	public List<MemberDTO> selectMembers(int member_num) {
		List<MemberDTO> list = dao.selectMembers(member_num);
		return list;
	}
	
	public List<MemberDTO> searchMembers(Map<String,String> parameters, MemberDTO memberDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCondition", parameters.get("searchCondition"));
		map.put("searchValue", parameters.get("searchValue"));
		map.put("member_num",memberDTO.getMember_num());
		
		List<MemberDTO> list = dao.searchMembers(map);
		return list;
	}
	
	public ProjectDTO createProject(Map<String, Object> parameters) {
		Map<String,Object> tempMap = (Map<String,Object>)parameters.get("projectDTO");
		
		//JSON안에 있는 JSON데이터를 한번에 ProjectDTO로 변환 시켜준다.(아니면 하나씩 파싱해서 DTO를 생성했어야함)
		ObjectMapper objectMapper = new ObjectMapper();
		ProjectDTO projectDTO = objectMapper.convertValue(tempMap, ProjectDTO.class);
		dao.createProject(projectDTO);
		
		int project_num = projectDTO.getProject_num();
		//System.out.println("방금추가된 프로젝트의 번호: " + project_num);

		//프로젝트 멤버 테이블 추가
		List<Integer> memberList = (List<Integer>)(parameters.get("members"));
		String tKey = (String)parameters.get("t_key"); 
		int member_num = LoginConstant.memberMap.get(tKey).getMember_num();
		memberList.add(member_num);//프로젝트 멤버 리스트에 자신 추가
		
		addProjectMember(project_num, memberList);
		return projectDTO;
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

	public ProjectDTO updateProject(Map<String, Object> parameters) {
		//프로젝트 테이블 업데이트
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object>tempMap = (Map<String,Object>)parameters.get("projectDTO");
		ProjectDTO projectDTO = objectMapper.convertValue(tempMap, ProjectDTO.class);
		int project_num = projectDTO.getProject_num();
		
		dao.updateProject(projectDTO);
		
		//프로젝트 멤버 테이블 업데이트	
		List<Integer> memberList = (List<Integer>)(parameters.get("members"));
		String t_key = (String)parameters.get("t_key"); 
		int member_num = LoginConstant.memberMap.get(t_key).getMember_num();
		memberList.add(member_num);//프로젝트 멤버 리스트에 자신 추가
		
		List<Integer> originList = dao.selectProjectMembers(project_num);
		List<Integer> toAddList = new ArrayList<Integer>(memberList);
		List<Integer> toRemoveList = new ArrayList<Integer>(originList);
		
		toRemoveList.removeAll(memberList);
		for(int toRemoveNum : toRemoveList) {
			Map<String, Integer> map = new HashMap<>();
			map.put("project_num", project_num);
			map.put("member_num", toRemoveNum);
			dao.deleteProjectMembers(map);
		}
		toAddList.removeAll(originList);
		
		addProjectMember(project_num, toAddList);
		return projectDTO;
		
	}//end updateProject

	public void deleteProject(Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num");
		dao.deleteProject(project_num);
		
	}

	public List<MemberDTO> participatedMemberList(Map<String,Object> parameters) {
		int project_num = (int)parameters.get("project_num");
		List<MemberDTO> memberList = dao.participatedMemberList(project_num);
		
		//프로젝트 매니저 멤버 제거
		ProjectDTO projectDTO = dao.selectProjectByProjectNum(project_num);
		int PM_member_num = projectDTO.getMember_num();
		
		memberList.removeIf(member -> member.getMember_num() == PM_member_num); //리스트에서 프로젝트 매니저 요소 삭제
		
		return memberList;
	}
	
	
	
}
