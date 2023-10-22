package com.dto;

public class CommunityUploadFileDTO extends UploadFileDTO {
	private Long comNum;
	
	public CommunityUploadFileDTO(Long comNum, String originalFilename, String storeFilename) {
		this.comNum = comNum;
		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
	}
	
	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
	}
}
