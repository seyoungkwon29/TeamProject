package com.common;

public class SearchType {
	private String type;
	private String description;
	
	
	public SearchType() {
		super();
	}

	public SearchType(String type, String description) {
		super();
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
