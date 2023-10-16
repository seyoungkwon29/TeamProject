package com.controller.community;

public class UpdateCommunityResponse extends Response {

	private String status;
	private String url;
	
	public UpdateCommunityResponse(String status, String url) {
		this.status = status;
		this.url = url;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
