package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.PageRequestDTO;
import com.domain.Notice;
import com.dto.CommunityDTO;
import com.dto.NoticeDTO;

@Repository
public class NoticeDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public void insert(Notice notice) {
		template.insert("NoticeMapper.insert", notice);
	}

	public Notice getNoticeByNum(Long noticeNum) {
		return template.selectOne("NoticeMapper.getNoticeByNum", noticeNum);
	}

	public List<Notice> getNoticeList() {
		return template.selectList("NoticeMapper.getNoticeList");
	}

	public void update(Notice notice) {
		template.update("NoticeMapper.update", notice);
	}

	public void delete(Long noticeNum) {
		template.delete("NoticeMapper.delete", noticeNum);
	}

	public NoticeDTO getNoticeDTOByNum(Long noticeNum) {
		return template.selectOne("NoticeMapper.getNoticeDTOByNum", noticeNum);
	}

	public List<NoticeDTO> getNoticeDTOList(PageRequestDTO page) {
		return template.selectList("NoticeMapper.getNoticeDTOList", page);
	}

	public void increaseViews(Long noticeNum) {
		template.update("NoticeMapper.increaseViews", noticeNum);
	}
	
	public int countNotice() {
		return template.selectOne("NoticeMapper.countNotice");
	}
	
	public List<NoticeDTO> getNoticeDTOListByMemberName(PageRequestDTO page, String memberName) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("name", "%"+ memberName + "%");
		return template.selectList("NoticeMapper.getNoticeDTOListByMemberName", param);
	}
	
	public Integer countNoticeByMemberName(String memberName) {
		memberName = "%" + memberName + "%";
		return template.selectOne("NoticeMapper.countNoticeByMemberName", memberName);
	}
	
	public List<NoticeDTO> getNoticeDTOListContentLike(PageRequestDTO page, String content) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("content", "%"+ content + "%");
		return template.selectList("NoticeMapper.getNoticeDTOListContentLike", param);
	}
	
	public Integer countNoticeContentLike(String content) {
		content = "%" + content + "%";
		return template.selectOne("NoticeMapper.countNoticeContentLike", content);
	}


}
