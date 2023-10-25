package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.PageRequestDTO;
import com.dto.CommunityDTO;
import com.dto.NoticeDTO;

@Repository
public class NoticeDAO {

	@Autowired
	SqlSessionTemplate template;
	
	public void insert(NoticeDTO notice) {
		template.insert("NoticeMapper.insert", notice);
	}

	public NoticeDTO getNoticeByNo(Long noticeNum) {
		return template.selectOne("NoticeMapper.getNoticeByNum", noticeNum);
	}

	public List<NoticeDTO> getNoticeList() {
		return template.selectList("NoticeMapper.getNoticeList");
	}

	public void update(NoticeDTO notice) {
		template.update("NoticeMapper.update", notice);
	}

	public void delete(Long noticeNum) {
		template.delete("NoticeMapper.delete", noticeNum);
	}

	public NoticeDTO getNoticeDetailsByNo(Long noticeNum) {
		return template.selectOne("NoticeMapper.getNoticeDetailsByNum", noticeNum);
	}

	public List<NoticeDTO> getNoticeDetailsList(PageRequestDTO page) {
		return template.selectList("NoticeMapper.getNoticeDetailsList", page);
	}

	public void increaseViews(Long noticeNum) {
		template.update("NoticeMapper.increaseViews", noticeNum);
	}
	
	public int countNotice() {
		return template.selectOne("NoticeMapper.countNotice");
	}
	
	public List<NoticeDTO> getNoticeDetailsListByMemberName(PageRequestDTO page, String memberName) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("name", "%"+ memberName + "%");
		return template.selectList("NoticeMapper.getNoticeDetailsListByMemberName", param);
	}
	
	public Integer countNoticeByMemberName(String memberName) {
		memberName = "%" + memberName + "%";
		return template.selectOne("NoticeMapper.countNoticeByMemberName", memberName);
	}
	
	public List<NoticeDTO> getNoticeDetailsListContentLike(PageRequestDTO page, String content) {
		Map<String, Object> param = new HashMap<>();
		param.put("page", page.getPage());
		param.put("size", page.getSize());
		param.put("content", "%"+ content + "%");
		return template.selectList("NoticeMapper.getNoticeDetailsListContentLike", param);
	}
	
	public Integer countNoticeContentLike(String content) {
		content = "%" + content + "%";
		return template.selectOne("NoticeMapper.countNoticeContentLike", content);
	}



	public List<NoticeDTO> getAllNotices(int member_num) {
		System.out.println("member_num >>>>>>>>" + member_num);
		return template.selectList("NoticeMapper.getNoticeList", member_num);
	}

}
