package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.PageRequestDTO;
import com.dto.CommunityDTO;
import com.dto.UploadFileDTO;

@Repository
public class CommunityDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public void insert(CommunityDTO community) {
		template.insert("CommunityMapper.insert", community);
	}

	public CommunityDTO getCommunityByNum(Long comNum) {
		return template.selectOne("CommunityMapper.getCommunityByNum", comNum);
	}

	public List<CommunityDTO> getCommunityByMemberNum(Long memberNum) {	
		return template.selectOne("CommunityMapper.getCommunityByMemberNum", memberNum);
	}

	public List<CommunityDTO> getCommunityList() {
		return template.selectList("CommunityMapper.getCommunityList");
	}

	public void update(CommunityDTO communtiy) {
		template.update("CommunityMapper.update", communtiy);
	}

	public void delete(Long comNum) {
		template.delete("CommunityMapper.delete", comNum);
	}

	public void increaseViews(Long comNum) {
		template.update("CommunityMapper.increaseViews", comNum);		
	}
	
	public CommunityDTO getCommunityDetailsByNum(Long comNum) {
		return template.selectOne("CommunityMapper.getCommunityDetailsByNum", comNum);
	}
	
	public List<CommunityDTO> getCommunityDetailsList(PageRequestDTO page) {
		return template.selectList("CommunityMapper.getCommunityDetailsList", page);
	}
	
	public Integer count() {
		return template.selectOne("CommunityMapper.count");
	}
	
	public List<CommunityDTO> getCommunityDetailsListByMemberName(PageRequestDTO page, String memberName) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("name", "%"+ memberName + "%");
		return template.selectList("CommunityMapper.getCommunityDetailsListByMemberName", param);
	}
	
	public Integer countByMemberName(String memberName) {
		memberName = "%" + memberName + "%";
		return template.selectOne("CommunityMapper.countByMemberName", memberName);
	}
	
	public List<CommunityDTO> getCommunityDetailsListContentLike(PageRequestDTO page, String content) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("content", "%"+ content + "%");
		return template.selectList("CommunityMapper.getCommunityDetailsListContentLike", param);
	}
	
	public Integer countContentLike(String content) {
		content = "%" + content + "%";
		return template.selectOne("CommunityMapper.countContentLike", content);
	}

	public void insertFile(UploadFileDTO file) {
		
		template.insert("CommunityMapper.insertFile", file);
	}

	public void deleteFile(Long fileId) {
		template.delete("CommunityMapper.deleteFile", fileId);
	}
	
	public void insertImage(UploadFileDTO image) {
		template.insert("CommunityMapper.insertImage", image);
	}
	
	public List<UploadFileDTO> getFilesByComNum(Long comNum) {
		return template.selectList("CommunityMapper.getFilesByComNum", comNum);
	}
	
	public List<UploadFileDTO> getImagesByComNum(Long comNum) {
		return template.selectList("CommunityMapper.getImagesByComNum", comNum);
	}
}
