package com.dto;

public class UploadImageResponseDTO {
	
	private String responseCode;
	private String url;
	
	public UploadImageResponseDTO() {
	}

	public UploadImageResponseDTO(String responseCode, String url) {
		this.responseCode = responseCode;
		this.url = url;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
		
}
