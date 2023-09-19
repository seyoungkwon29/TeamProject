package com.controller.community;

import javax.validation.constraints.NotBlank;

public class ReplyForm {
	
	@NotBlank
	private String content;

	public ReplyForm() {
	}

	public ReplyForm(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
