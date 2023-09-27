package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DocFormDAO;
import com.dto.DocFormDTO;

@Service("DocFormService")
public class DocFormService {

	@Autowired
	DocFormDAO dao;
	
	public DocFormService() {
		super();
	}

	public DocFormDTO docFormName(String form_name) {
		DocFormDTO dto = dao.docFormName(form_name);
		return dto;
	}


	
}

