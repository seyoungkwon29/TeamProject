package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ProjectManagementDTO;

@Repository("ProjectManagementDAO")
public class ProjectManagementDAO {

	@Autowired
	SqlSessionTemplate session;
	
	public List<ProjectManagementDTO> viewList(int member_num) {
		List<ProjectManagementDTO> list = session.selectList("viewList", member_num);
		return list;
	}
}
