package com.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.PageRequestDTO;
import com.common.PageResponseDTO;
import com.dao.CommunityDAO;
import com.dao.ReplyDAO;
import com.dto.CommunityDTO;
import com.dto.ReplyDTO;
import com.dto.UploadFileDTO;

@Service
public class CommunityService {

	@Autowired
    private CommunityDAO communityDao;
	
	@Autowired
    private ReplyDAO replyDao;

    public void save(CommunityDTO community) {
		communityDao.insert(community);
		
		Long comNum = community.getComNum();
		List<UploadFileDTO> files = community.getFiles();
		for (UploadFileDTO file : files) {
			communityDao.insertFile(comNum, file);
		}
		
		List<UploadFileDTO> images = community.getImages();
		for (UploadFileDTO image : images) {
			communityDao.insertImage(comNum, image);
		}
    }

    public CommunityDTO getCommunityByNum(Long comNum) {
         CommunityDTO community = communityDao.getCommunityByNum(comNum);
         
         List<UploadFileDTO> files = communityDao.getFilesByComNum(comNum);
         community.setFiles(files);
         
         List<UploadFileDTO> images = communityDao.getImagesByComNum(comNum);
         community.setImages(images);
         
         return community;
    }

    public List<CommunityDTO> getCommunityList() {
		return communityDao.getCommunityList();
    }

    public void update(Long comNum, Long memberNum, CommunityDTO updateParam) {
        CommunityDTO community = communityDao.getCommunityByNum(comNum);

        if (!community.getMemberNum().equals(memberNum)) {
            return;
        }
        
        community.setTitle(updateParam.getTitle());
        community.setContent(updateParam.getContent());
        
        communityDao.update(community);
    }

    public void delete(Long comNum, Long memberNum) {
        CommunityDTO community = communityDao.getCommunityByNum(comNum);

        if (!community.getMemberNum().equals(memberNum)) {
            return;
        }
        
        List<ReplyDTO> replyList = replyDao.getReplyListByComNum(comNum);
        for (ReplyDTO reply : replyList) {
            replyDao.delete(reply.getReplyNum());
        }
        communityDao.delete(comNum);
    }
    
    public void increaseViews(Long comNum) {
		communityDao.increaseViews(comNum);
    }

    public CommunityDTO getCommunityDetailsByNum(Long comNum) {
		CommunityDTO community = communityDao.getCommunityDetailsByNum(comNum);
		
		List<UploadFileDTO> files = communityDao.getFilesByComNum(comNum);
		
		List<UploadFileDTO> images = communityDao.getImagesByComNum(comNum);
		
		community.setFiles(files);
		
		community.setImages(images);
		
		return community;
		
    }

    
    public PageResponseDTO<CommunityDTO> getCommunityDetailsList(PageRequestDTO page) {
		int count = communityDao.count();
		List<CommunityDTO> communityDetailsList = communityDao.getCommunityDetailsList(page);
		if (!communityDetailsList.isEmpty()) {
			return new PageResponseDTO<CommunityDTO>(page, communityDetailsList, count);
		}
	
		return new PageResponseDTO<CommunityDTO>(page, Collections.emptyList(), 0);
    }
    
    public PageResponseDTO<CommunityDTO> getCommunityDetailsListByMemberName(PageRequestDTO page, String memberName) {

  		int count = communityDao.countByMemberName(memberName);
  		
  		List<CommunityDTO> communityDetailsList = communityDao.getCommunityDetailsListByMemberName(page, memberName);
  		
  		return new PageResponseDTO<CommunityDTO>(page, communityDetailsList, count);

    }
    
    public PageResponseDTO<CommunityDTO> getCommunityDetailsListContentLike(PageRequestDTO page, String content) {

  		int count = communityDao.countContentLike(content);
  		
  		List<CommunityDTO> communityDetailsList = communityDao.getCommunityDetailsListContentLike(page, content);
  		
  		return new PageResponseDTO<CommunityDTO>(page, communityDetailsList, count);

    }
}
