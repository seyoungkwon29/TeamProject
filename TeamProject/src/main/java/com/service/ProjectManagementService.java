package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProjectManagementDAO;
import com.dto.ProjectManagementDTO;

@Service("ProjectManagementService")
public class ProjectManagementService {

	@Autowired
	ProjectManagementDAO pmDAO;
	
	public List<ProjectManagementDTO> viewList(int member_num) {
		List<ProjectManagementDTO> list = pmDAO.viewList(member_num);
		return list;
	}

}
