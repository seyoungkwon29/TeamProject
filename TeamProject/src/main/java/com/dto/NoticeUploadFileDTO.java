package com.dto;

public class NoticeUploadFileDTO extends UploadFileDTO {
	private Long noticeNum;
	
	public NoticeUploadFileDTO(Long noticeNum, String originalFilename, String storeFilename) {
		this.noticeNum = noticeNum;
		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
	}
	
	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
	}
}
