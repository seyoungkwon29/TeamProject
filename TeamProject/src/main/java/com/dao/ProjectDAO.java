package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ProjectDTO;

@Repository
public class ProjectDAO {
	@Autowired
	SqlSessionTemplate session;

	public List<ProjectDTO> getAllProject(int member_num) {
		List<ProjectDTO> list = session.selectList("getAllProject", member_num);
		return list;
	}
}
