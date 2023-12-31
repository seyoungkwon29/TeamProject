package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.PageRequestDTO;
import com.dto.CommunityDTO;
import com.dto.CommunityUploadFileDTO;
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
	
	public CommunityDTO getCommunityDTOByNum(Long comNum) {
		return template.selectOne("CommunityMapper.getCommunityDTOByNum", comNum);
	}
	
	public List<CommunityDTO> getCommunityDTOList(PageRequestDTO page) {
		return template.selectList("CommunityMapper.getCommunityDTOList", page);
	}
	
	public Integer count() {
		return template.selectOne("CommunityMapper.count");
	}
	
	public List<CommunityDTO> getCommunityDTOListByMemberName(PageRequestDTO page, String memberName) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("name", "%"+ memberName + "%");
		return template.selectList("CommunityMapper.getCommunityDTOListByMemberName", param);
	}
	
	public Integer countByMemberName(String memberName) {
		memberName = "%" + memberName + "%";
		return template.selectOne("CommunityMapper.countByMemberName", memberName);
	}
	
	public List<CommunityDTO> getCommunityDTOListContentLike(PageRequestDTO page, String content) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("content", "%"+ content + "%");
		return template.selectList("CommunityMapper.getCommunityDTOListContentLike", param);
	}
	
	public Integer countContentLike(String content) {
		content = "%" + content + "%";
		return template.selectOne("CommunityMapper.countContentLike", content);
	}

	public void insertFile(Long comNum, UploadFileDTO file) {
		CommunityUploadFileDTO jdbcFile = new CommunityUploadFileDTO(comNum, file.getOriginalFilename(), file.getStoreFilename());
		template.insert("CommunityMapper.insertFile", jdbcFile);
		file.setId(jdbcFile.getId());
	}

	public void deleteFile(Long fileId) {
		template.delete("CommunityMapper.deleteFile", fileId);
	}
	
	public List<UploadFileDTO> getFilesByComNum(Long comNum) {
		return template.selectList("CommunityMapper.getFilesByComNum", comNum);
	}
	
}
