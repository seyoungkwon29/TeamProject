package com.dto;

public class UploadImageResponseDTO {
	
	private String responseCode;
	private String originalFilename;
	private String url;
	
	public UploadImageResponseDTO() {
	}

	public UploadImageResponseDTO(String responseCode, String originalFilename, String url) {
		this.responseCode = responseCode;
		this.originalFilename = originalFilename;
		this.url = url;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
		
}
