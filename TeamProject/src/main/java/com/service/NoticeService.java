package com.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.PageRequestDTO;
import com.common.PageResponseDTO;
import com.dao.NoticeDAO;
import com.dto.NoticeDTO;

@Service
public class NoticeService {
	@Autowired
	private NoticeDAO dao;

	public void createNotice(Long memberNum, NoticeDTO notice) {
		dao.insert(notice); 
	}

	public NoticeDTO getNoticeByNo(Long noticeNum) {
		return dao.getNoticeByNo(noticeNum);
	}

	public List<NoticeDTO> getNoticeList() {
		return dao.getNoticeList(); 
	}

	public NoticeDTO getNoticeDetailsByNo(Long noticeNum) {
		dao.increaseViews( noticeNum);
		NoticeDTO notice = dao.getNoticeDetailsByNo(noticeNum);
		return notice;
	}

	public PageResponseDTO<NoticeDTO> getNoticeDetailsList(PageRequestDTO page) {
		int count= dao.countNotice();
		List<NoticeDTO> noticeDetailsList = dao.getNoticeDetailsList(page);
		if (!noticeDetailsList.isEmpty()) {
			return new PageResponseDTO<NoticeDTO>(page, noticeDetailsList, count);
		}
		
		return new PageResponseDTO<NoticeDTO>(page, Collections.emptyList(), 0);
	}
	
	public void updateNotice(Long noticeNum, Long memberNum, NoticeDTO updateDTO) {

			NoticeDTO notice = dao.getNoticeByNo(noticeNum);
	
			if (!memberNum.equals(notice.getMemberNum())) {
				return;
			}
	
			notice.setTitle(updateDTO.getTitle());
			notice.setContent(updateDTO.getContent());
	
			dao.update(notice);
	}

	public void deleteNotice(Long noticeNum, Long memberNum) {
			
			NoticeDTO notice = dao.getNoticeByNo(noticeNum);
	
			if (!memberNum.equals(notice.getMemberNum())) {
				return;
			}
			dao.delete(noticeNum);	
	}
	
	public PageResponseDTO<NoticeDTO> getNoticeDetailsListByMemberName(PageRequestDTO page, String memberName) {

  		int count = dao.countNoticeByMemberName(memberName);
  		
  		List<NoticeDTO> noticeDetailsList = dao.getNoticeDetailsListByMemberName(page, memberName);
  		
  		return new PageResponseDTO<NoticeDTO>(page, noticeDetailsList, count);

    }
    
    public PageResponseDTO<NoticeDTO> getNoticeDetailsListContentLike(PageRequestDTO page, String content) {

  		int count = dao.countNoticeContentLike(content);
  		
  		List<NoticeDTO> noticeDetailsList = dao.getNoticeDetailsListContentLike(page, content);
  		
  		return new PageResponseDTO<NoticeDTO>(page, noticeDetailsList, count);

    }
}
