package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProjectDAO;
import com.dto.ProjectDTO;

@Service
public class ProjectService {
	@Autowired
	ProjectDAO dao;

	public List<ProjectDTO> getAllProject(int member_num) {
		List<ProjectDTO> list = dao.getAllProject(member_num);
		return list;
	}
	
}
