package com.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.PageRequestDTO;
import com.common.PageResponseDTO;
import com.dao.MemberDAO;
import com.dao.NoticeDAO;
import com.domain.Notice;
import com.dto.MemberDTO;
import com.dto.NoticeDTO;
import com.dto.UploadFileDTO;

@Service
public class NoticeService {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeService.class);
	@Autowired
	private NoticeDAO dao;

	@Autowired
	private MemberDAO memberDao;
	
	@Transactional(rollbackFor=Exception.class)
	public void createNotice(Long memberNum, Notice notice) {

		dao.insert(notice); 
		
		for (UploadFileDTO file : notice.getFiles()) {
			dao.insertFile(notice.getNoticeNum(), file);
		}
	}

	@Transactional(readOnly=true)
	public Notice getNoticeByNum(Long noticeNum) {
		
		Notice notice = dao.getNoticeByNum(noticeNum);
		
		List<UploadFileDTO> files = dao.getFilesByNoticeNum(noticeNum);
		for (UploadFileDTO file : files) {
			notice.addFile(file);
		}
		
		 return notice;
	}

	@Transactional(readOnly=true)
	public List<Notice> getNoticeList() {
		
		return dao.getNoticeList();
		
	}

	@Transactional(readOnly=true)
	public NoticeDTO getNoticeDTOByNum(Long noticeNum) {
		
		Notice notice = dao.getNoticeByNum(noticeNum);
		
		List<UploadFileDTO> files = dao.getFilesByNoticeNum(noticeNum);
		for (UploadFileDTO file : files) {
			notice.addFile(file);
		}
		
		MemberDTO member = memberDao.readMember(notice.getMemberNum().intValue());
		NoticeDTO noticeDTO = NoticeDTO.from(notice);
		noticeDTO.setMemberName(member.getMember_name());
		
		return noticeDTO;
	}

	@Transactional
	public void increaseViews(Long noticeNum) {
		dao.increaseViews(noticeNum);
	}

	@Transactional(readOnly=true)
	public PageResponseDTO<NoticeDTO> getNoticeDTOList(PageRequestDTO page) {
		int count= dao.countNotice();
		List<NoticeDTO> noticeDetailsList = dao.getNoticeDTOList(page);
		if (!noticeDetailsList.isEmpty()) {
			return new PageResponseDTO<NoticeDTO>(page, noticeDetailsList, count);
		}
		
		return new PageResponseDTO<NoticeDTO>(page, Collections.emptyList(), 0);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void updateNotice(Long noticeNum, Long memberNum, Notice updateParam, List<Long> deleteFileIds) {

			Notice notice = dao.getNoticeByNum(noticeNum);
	
			if (!memberNum.equals(notice.getMemberNum())) {
				return;
			}
	
			notice.setTitle(updateParam.getTitle());
			notice.setContent(updateParam.getContent());
	
			dao.update(notice);
			
			List<UploadFileDTO> newFiles = updateParam.getFiles();
			for (UploadFileDTO file : newFiles) {
				dao.insertFile(noticeNum, file);
			}
			
			for (Long id : deleteFileIds) {
				dao.deleteFile(id);
			}
	}

	@Transactional(rollbackFor=Exception.class)
	public void deleteNotice(Long noticeNum, Long memberNum) {
			
			Notice notice = dao.getNoticeByNum(noticeNum);
	
			if (!memberNum.equals(notice.getMemberNum())) {
				return;
			}
			dao.delete(noticeNum);	
	}
	
	@Transactional(readOnly=true)
	public PageResponseDTO<NoticeDTO> getNoticeDTOListByMemberName(PageRequestDTO page, String memberName) {

  		int count = dao.countNoticeByMemberName(memberName);
  		
  		List<NoticeDTO> noticeDTOList = dao.getNoticeDTOListByMemberName(page, memberName);
  		
  		return new PageResponseDTO<NoticeDTO>(page, noticeDTOList, count);

    }
    
	@Transactional(readOnly=true)
    public PageResponseDTO<NoticeDTO> getNoticeDTOListContentLike(PageRequestDTO page, String content) {

  		int count = dao.countNoticeContentLike(content);
  		
  		List<NoticeDTO> noticeDTOList = dao.getNoticeDTOListContentLike(page, content);
  		
  		return new PageResponseDTO<NoticeDTO>(page, noticeDTOList, count);
    }
	
	@Transactional(readOnly=true)
	public List<NoticeDTO> getNoticeDTOListTopN(int n) {
		PageResponseDTO<NoticeDTO> noticeDTOList = this.getNoticeDTOList(new PageRequestDTO(1,n));
		return noticeDTOList.getItems();
	}
	
}//class
