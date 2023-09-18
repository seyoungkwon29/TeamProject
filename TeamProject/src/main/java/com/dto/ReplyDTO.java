package com.dto;

import java.util.Date;

public class ReplyDTO {
	private Long replyNum;
	private Long memberNum;
	private String memberName;
	private Long comNum;
	private Long parentReplyNum;
	private String content;
	private Date createdAt;
	private Integer level;

	public ReplyDTO() {
	}
	
    public ReplyDTO(Long memberNum, Long comNum, Long parentReplyNum, String content) {
        this.memberNum = memberNum;
        this.comNum = comNum;
        this.parentReplyNum = parentReplyNum;
        this.content = content;
        this.createdAt = new Date();
    }

	public Long getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Long replyNum) {
		this.replyNum = replyNum;
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
	}

	public Long getParentReplyNum() {
		return parentReplyNum;
	}

	public void setParentReplyNum(Long parentReplyNum) {
		this.parentReplyNum = parentReplyNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "ReplyDTO [replyNum=" + replyNum + ", memberNum=" + memberNum + ", memberName=" + memberName
				+ ", comNum=" + comNum + ", parentReplyNum=" + parentReplyNum + ", content=" + content + ", createdAt="
				+ createdAt + ", level=" + level + "]";
	}

	
}
