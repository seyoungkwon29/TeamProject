package com.controller.community;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dto.UploadFileDTO;

public class UpdateCommunityForm {
	
	private Long comNum;
	private String memberName;
	private String title;
	private String content;

	private List<UploadFileDTO> attachFiles;
	
	private List<MultipartFile> files;
	
	private List<Long> deleteFileIds;
	
	
	public UpdateCommunityForm() {
	}

	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public List<UploadFileDTO> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<UploadFileDTO> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<Long> getDeleteFileIds() {
		return deleteFileIds;
	}

	public void setDeleteFileIds(List<Long> deleteFileIds) {
		this.deleteFileIds = deleteFileIds;
	}
}
