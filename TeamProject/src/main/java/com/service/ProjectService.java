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

	public List<ProjectDTO> getAllProject() {
		List<ProjectDTO> list = dao.getAllProject();
		ArrayList<Object> arr = new ArrayList<>();
		
		String title = list.get(0).getProject_title();
		String start = list.get(0).getStart_date();
		String end = list.get(0).getDue_date();
		
		
//		Object schedule = new Object() {
//			aa: title,
//			bb: start,
//			cc: end
//		};
		
		return list;
	}
	
}
