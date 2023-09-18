package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ApprovalDTO")
public class ApprovalDTO {

	private int member_num;
	private String member_name;
	private String div_name;
	private String rank;
	
	
	public ApprovalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ApprovalDTO(int member_num, String member_name, String div_name, String rank) {
		super();
		this.member_num = member_num;
		this.member_name = member_name;
		this.div_name = div_name;
		this.rank = rank;
	}


	public int getMember_num() {
		return member_num;
	}


	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public String getDiv_name() {
		return div_name;
	}


	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	@Override
	public String toString() {
		return "ApprovalnfoDTO [member_num=" + member_num + ", member_name=" + member_name + ", div_name=" + div_name
				+ ", rank=" + rank + "]";
	}
	
	
	
	
	
}
