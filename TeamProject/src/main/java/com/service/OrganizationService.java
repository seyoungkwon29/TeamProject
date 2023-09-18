package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.OrganizationDAO;
import com.dto.OrganizationDTO;

@Service("OrganizationService")
public class OrganizationService {

	@Autowired
	OrganizationDAO dao;

	// 전체 조직도
	public List<OrganizationDTO> viewOrganization() {
		List<OrganizationDTO> list = dao.viewOrganization();
		return list;
	}
	
}
