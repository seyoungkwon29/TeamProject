package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.OrganizationDTO;

@Repository("OrganizationDAO")
public class OrganizationDAO {

	@Autowired
	SqlSessionTemplate session;

	// 전체 조직도
	public List<OrganizationDTO> viewOrganization() {
		List<OrganizationDTO> list = session.selectList("viewOrganization");
		return list;
	}
	
	
}
