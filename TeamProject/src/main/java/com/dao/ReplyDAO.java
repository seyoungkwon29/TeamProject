package com.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.ReplyDTO;

@Repository
public class ReplyDAO {
	
	@Autowired
	SqlSessionTemplate template;
	
	public void insert(ReplyDTO reply) {
		template.insert("ReplyMapper.insert", reply);
	}

	public ReplyDTO getReplyByNum(Long replyNum) {
		return template.selectOne("ReplyMapper.getReplyByNum", replyNum);
	}

	public List<ReplyDTO> getReplyListByComNum(Long comNum) {
		return template.selectList("ReplyMapper.getReplyListByComNum");
	}

	public void update(ReplyDTO reply) {
		template.update("ReplyMapper.update", reply);
	}

	public void delete(Long replyNum) {
		template.delete("ReplyMapper.delete", replyNum);
	}
	
	public List<ReplyDTO> getReplyDetailsListByComNum(Long comNum) {
		return template.selectList("ReplyMapper.getReplyDetailsListByComNum", comNum);
	}
}
