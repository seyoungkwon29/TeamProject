package com.controller.community;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommunityForm {

	private Long comNum;
	
	@NotBlank
	@Size(min = 5, max = 14)
	private String title;
	
	@NotBlank
	private String content;
	
	public CommunityForm() {
	}

	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
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
