package com.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(rollbackFor=Exception.class)
    public void save(CommunityDTO community) {
		communityDao.insert(community);
		
		Long comNum = community.getComNum();
		List<UploadFileDTO> files = community.getFiles();
		for (UploadFileDTO file : files) {
			file.setComNum(comNum);
			communityDao.insertFile(file);
		}
    }

	@Transactional(readOnly=true)
    public CommunityDTO getCommunityByNum(Long comNum) {
         
		CommunityDTO community = communityDao.getCommunityByNum(comNum);
         
         List<UploadFileDTO> files = communityDao.getFilesByComNum(comNum);
         for (UploadFileDTO file : files) {
        	 community.addFile(file);
         }
         
         return community;
    }

	@Transactional(readOnly=true)
    public List<CommunityDTO> getCommunityList() {
		return communityDao.getCommunityList();
    }

	@Transactional(rollbackFor=Exception.class)
    public void update(Long comNum, Long memberNum, CommunityDTO updateParam, List<Long> deleteFileIds) {
        
		CommunityDTO community = communityDao.getCommunityByNum(comNum);

        if (!community.getMemberNum().equals(memberNum)) {
            return;
        }
        
        community.setTitle(updateParam.getTitle());
        community.setContent(updateParam.getContent());
        
        communityDao.update(community);
        
		List<UploadFileDTO> files = updateParam.getFiles();
        for (UploadFileDTO file : files) {
        	if (file.isNew()) {
				file.setComNum(comNum);
				communityDao.insertFile(file);
        	}
		}
		
        for (Long fileId : deleteFileIds) {
        	communityDao.deleteFile(fileId);	
        }
    }

	@Transactional(rollbackFor=Exception.class)
    public void delete(Long comNum, Long memberNum) {
        CommunityDTO community = communityDao.getCommunityByNum(comNum);

        if (!community.getMemberNum().equals(memberNum)) {
            return;
        }
        
        List<ReplyDTO> replyList = replyDao.getReplyListByComNum(comNum);
        for (ReplyDTO reply : replyList) {
            replyDao.delete(reply.getReplyNum());
        }
        
        List<UploadFileDTO> files = community.getFiles();
		for (UploadFileDTO file : files) {
			communityDao.deleteFile(file.getId());
		}
        
        communityDao.delete(comNum);
    }
    
	
    public void increaseViews(Long comNum) {
		communityDao.increaseViews(comNum);
    }

    @Transactional(readOnly=true)
    public PageResponseDTO<CommunityDTO> getCommunityDTOList(PageRequestDTO page) {
		int count = communityDao.count();
		List<CommunityDTO> communityDTOList = communityDao.getCommunityDTOList(page);
		if (!communityDTOList.isEmpty()) {
			return new PageResponseDTO<CommunityDTO>(page, communityDTOList, count);
		}
	
		return new PageResponseDTO<CommunityDTO>(page, Collections.emptyList(), 0);
    }
    
    @Transactional(readOnly=true)
    public PageResponseDTO<CommunityDTO> getCommunityDTOListByMemberName(PageRequestDTO page, String memberName) {

  		int count = communityDao.countByMemberName(memberName);
  		
  		List<CommunityDTO> communityDTOList = communityDao.getCommunityDTOListByMemberName(page, memberName);
  		
  		return new PageResponseDTO<CommunityDTO>(page, communityDTOList, count);

    }
    
    @Transactional(readOnly=true)
    public PageResponseDTO<CommunityDTO> getCommunityDTOListContentLike(PageRequestDTO page, String content) {

  		int count = communityDao.countContentLike(content);
  		
  		List<CommunityDTO> communityDTOList = communityDao.getCommunityDTOListContentLike(page, content);
  		
  		return new PageResponseDTO<CommunityDTO>(page, communityDTOList, count);

    }
    
    public List<CommunityDTO> getCommunityDTOListTopN(int n) {
    	PageResponseDTO<CommunityDTO> communityList = this.getCommunityDTOList(new PageRequestDTO(1,n));
    	return communityList.getItems();
    }
}
