package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ExtendedChatContentDTO")
public class ExtendedChatContentDTO extends ChatContentDTO{
	private String div_name;
	private String member_name;
	private String rank;
	
	public String getDiv_name() {
		return div_name;
	}
	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}

	
}
