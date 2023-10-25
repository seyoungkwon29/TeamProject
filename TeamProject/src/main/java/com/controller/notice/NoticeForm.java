package com.controller.notice;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class NoticeForm {
	
	private Long noticeNum;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;

	private List<MultipartFile> attachFiles;
	
	
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


	public List<MultipartFile> getAttachFiles() {
		return attachFiles;
	}
	
	public void setAttachFiles(List<MultipartFile> attachFiles) {
		this.attachFiles = attachFiles;
	}


	
}
