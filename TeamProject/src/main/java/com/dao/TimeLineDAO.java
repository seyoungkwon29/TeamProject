package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.MemberDTO;
import com.dto.ProjectDTO;
import com.dto.ProjectManagementDTO;

@Repository("TimeLineDAO")
public class TimeLineDAO {

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

	public int createProject(ProjectDTO projectDTO) {
		int num = session.insert("createProject",projectDTO);
		return num;
	}

	public void addProjectMember(int project_num, int member_num) {
		 Map<String, Integer> params = new HashMap<>();
		 params.put("project_num", project_num);
		 params.put("member_num", member_num);
		 session.insert("addProjectMember", params);
	}

	public List<ProjectDTO> projectList(int member_num) {
		List<ProjectDTO> list = session.selectList("projectList",member_num);
		return list;
	}
	
	public List<MemberDTO> participatedMemberList(int project_num) {
	        List<MemberDTO> memberList = session.selectList("participatedMemberList",project_num);
	        return memberList;
	}
	
	public void updateProject(ProjectDTO projectDTO) {
		int res = session.update("updateProject",projectDTO);
		System.out.println("ProjectDAO createProject 결과  : "+res);
	}
	
	public void deleteProject(int project_num) {
		session.delete("deleteProject",project_num);
	}
	
	public void deleteProjectMembers(int project_num) {
		session.delete("deleteProjectMembers",project_num);
		
	}
	
	public void addProjectMember(Map<String,Integer> map) {
		int res = session.insert("addProjectMember", map);
	}
	
	public List<Integer> selectProjectMembers(int project_num) {
		System.out.println("프로젝트 번호 : "+project_num);
		List<Integer> list = session.selectList("selectProjectMembers",project_num);
		System.out.println(list.toString());
		return list;
	}


}
