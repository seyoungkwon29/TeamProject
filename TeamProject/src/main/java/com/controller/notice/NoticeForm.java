package com.controller.notice;

import javax.validation.constraints.NotBlank;

public class NoticeForm {
	
	private Long noticeNum;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	public NoticeForm() {
	}

	
	public Long getNoticeNum() {
		return noticeNum;
	}


	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
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
	
}
