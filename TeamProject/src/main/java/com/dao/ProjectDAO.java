package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;
import com.dto.ProjectDTO;

@Repository
public class ProjectDAO {
	@Autowired
	SqlSessionTemplate session;
	
	public List<MemberDTO> selectMembers(int member_num) {
		List<MemberDTO> list = session.selectList("selectMembers",member_num);
		return list;
	}
	
	public List<MemberDTO> searchMembers(Map<String, Object> map) {
		List<MemberDTO> list = session.selectList("searchMembers", map);
		return list;
	}

	public MemberDTO createPM(int member_num) {
		MemberDTO dto = session.selectOne("createPM",member_num);
		return dto;
	}
	
	public List<ProjectDTO> getAllProject(int member_num) {
		List<ProjectDTO> list = session.selectList("getAllProject",member_num);
		return list;
	}

	public void createProject(ProjectDTO projectDTO) {
		int res = session.insert("createProject",projectDTO);
//		System.out.println("ProjectDAO createProject 결과  : "+res);
	}

	public void addProjectMember(Map<String,Integer> map) {
		int res = session.insert("addProjectMember", map);
	}

	public void updateProject(ProjectDTO projectDTO) {
		int res = session.update("updateProject",projectDTO);
		System.out.println("ProjectDAO createProject 결과  : "+res);
	}

	public List<Integer> selectProjectMembers(int project_num) {
		System.out.println("프로젝트 번호 : "+project_num);
		List<Integer> list = session.selectList("selectProjectMembers",project_num);
		System.out.println(list.toString());
		return list;
	}

	public void deleteProjectMembers(Map<String,Integer> parameters) {
		session.delete("deleteProjectMembers",parameters);
		
	}

	public void deleteProject(int project_num) {
		session.delete("deleteProject",project_num);
	}

	public List<MemberDTO> participatedMemberList(int project_num) {
		List<MemberDTO> memberList = session.selectList("participatedMemberList",project_num);
		return memberList;
	}

	public ProjectDTO selectProjectByProjectNum(int project_num) {
		ProjectDTO projectDTO =session.selectOne("selectProjectByProjectNum",project_num);
		return projectDTO;
	}
}
