package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ReplyDAO;
import com.dto.ReplyDTO;

@Service
public class ReplyService {

	@Autowired
    private ReplyDAO dao;
  
	@Transactional(rollbackFor=Exception.class)
    public void save(ReplyDTO reply) {
        dao.insert(reply);
    }
    
	@Transactional(readOnly=true)
    public List<ReplyDTO> getReplyDetailsListByComNum(Long comNum) {
		return dao.getReplyDetailsListByComNum(comNum);
    }
    
	@Transactional(rollbackFor=Exception.class)
	public void update(Long replyNum, Long memberNum, ReplyDTO updateDTO) {
		ReplyDTO reply = dao.getReplyByNum(replyNum);
			
		if (!memberNum.equals(reply.getMemberNum())) {
			return;
		}
		
		reply.setContent(updateDTO.getContent());
		dao.update(reply);
	}
    
	@Transactional(rollbackFor=Exception.class)
    public void delete(Long replyNum, Long memberNum) {
    	ReplyDTO reply = dao.getReplyByNum(replyNum);
    	
    	if (!memberNum.equals(reply.getMemberNum())) {
    		return;
    	}
    	
    	dao.delete(replyNum);
    }
}
