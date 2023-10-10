package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("AppSearchConditionDTO")
public class AppSearchConditionDTO {

	int member_num;
	String searchCondition;
	String searchValue;
	String type;
	String doc_status;
	
	public AppSearchConditionDTO() {
		super();
	}

	public AppSearchConditionDTO(int member_num, String searchCondition, String searchValue, String type,
			String doc_status) {
		super();
		this.member_num = member_num;
		this.searchCondition = searchCondition;
		this.searchValue = searchValue;
		this.type = type;
		this.doc_status = doc_status;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDoc_status() {
		return doc_status;
	}

	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}

	@Override
	public String toString() {
		return "AppSearchConditionDTO [member_num=" + member_num + ", searchCondition=" + searchCondition
				+ ", searchValue=" + searchValue + ", type=" + type + ", doc_status=" + doc_status + "]";
	}


	
	
}
