package com.domain;

import java.util.Date;

public class Notice {
	private Long noticeNum;
	private Long memberNum;
	private String title;
	private String content;
	private Integer views;
	private Date createdAt;

	public Notice() {}
	
	public Notice(Long memberNum, String title, String content) {
		this.memberNum = memberNum;
		this.title = title;
		this.content = content;
		this.views = Integer.valueOf(0);
		this.createdAt = new Date();
	}

	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Notice [noticeNum=" + noticeNum + ", memberNum=" + memberNum + ", title=" + title + ", content="
				+ content + ", views=" + views + ", createdAt=" + createdAt + "]";
	}

	
}
