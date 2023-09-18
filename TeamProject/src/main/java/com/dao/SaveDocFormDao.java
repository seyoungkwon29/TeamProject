package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.SaveDocFormDTO;

@Repository("SaveDocFormDao")
public class SaveDocFormDao {
	
	@Autowired
	SqlSessionTemplate session;

	public int saveDocForm(SaveDocFormDTO formDto) {
		int num = session.insert("ApprovalMapper.saveDocForm", formDto);
		return num;
	}

	public List<SaveDocFormDTO> saveDocList() {
		List<SaveDocFormDTO> docList = session.selectList("ApprovalMapper.saveDocList");
		return docList;
	}

}
