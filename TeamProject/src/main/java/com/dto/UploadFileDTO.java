package com.dto;

public class UploadFileDTO {
	
	private String originalFilename;
	private String storeFilename;
	
	public UploadFileDTO() {}
	
	public UploadFileDTO(String originalFilename, String storeFilename) {

		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getStoreFilename() {
		return storeFilename;
	}

	public void setStoreFilename(String storeFilename) {
		this.storeFilename = storeFilename;
	}
	
}
