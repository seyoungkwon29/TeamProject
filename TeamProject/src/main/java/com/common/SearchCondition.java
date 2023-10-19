package com.common;

public class SearchCondition {
	
	private String searchType;
	private String searchKeyword;
	
	public SearchCondition() {
		this.searchType = "";
		this.searchKeyword = "";
	}
	
	public SearchCondition(String searchType, String searchKeyword) {
		super();
		this.searchType = searchType;
		this.searchKeyword = searchKeyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
}
