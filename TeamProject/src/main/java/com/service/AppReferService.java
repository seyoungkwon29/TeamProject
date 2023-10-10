package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AppReferDAO;
import com.dto.AppReferDTO;

@Service("AppReferService")
public class AppReferService {

	@Autowired
	AppReferDAO dao;

	public int registerRefMem(AppReferDTO refDto) {
		int num = dao.registerRefMem(refDto);
		return num;
	}

	public List<AppReferDTO> searchRefMem(int doc_no) {
		List<AppReferDTO> list = dao.searchRefMem(doc_no);
		return list;
	}

	
}
