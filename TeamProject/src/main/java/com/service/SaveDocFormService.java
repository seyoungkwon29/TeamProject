package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SaveDocFormDao;
import com.dto.SaveDocFormDTO;

@Service("SaveDocFormService")
public class SaveDocFormService {
	
	@Autowired
	SaveDocFormDao dao;

	public SaveDocFormService() {
		super();
	}

	public int saveDocForm(SaveDocFormDTO formDto) {
		int num = dao.saveDocForm(formDto);
		return num;
	}

	public List<SaveDocFormDTO> saveDocList() {
		List<SaveDocFormDTO> docList = dao.saveDocList();
		return docList;
	}

	
	

	
}
