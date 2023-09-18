package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DocFormDao;
import com.dto.DocFormDTO;

@Service("DocFormService")
public class DocFormService {

	@Autowired
	DocFormDao dao;
	
	public DocFormService() {
		super();
	}

	public DocFormDTO docFormName(String form_name) {
		DocFormDTO dto = dao.docFormName(form_name);
		return dto;
	}


	
}

