package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("AppReferMapDTO")
public class AppReferMapDTO {

	int ref_no;
	int doc_no;
	int member_num;
	String member_name;
	String div_name;
	String rank;
	String ref_status;
	
	
	public AppReferMapDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AppReferMapDTO(int ref_no, int doc_no, int member_num, String member_name, String div_name, String rank,
			String ref_status) {
		super();
		this.ref_no = ref_no;
		this.doc_no = doc_no;
		this.member_num = member_num;
		this.member_name = member_name;
		this.div_name = div_name;
		this.rank = rank;
		this.ref_status = ref_status;
	}


	public int getRef_no() {
		return ref_no;
	}


	public void setRef_no(int ref_no) {
		this.ref_no = ref_no;
	}


	public int getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
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


	public String getRef_status() {
		return ref_status;
	}


	public void setRef_status(String ref_status) {
		this.ref_status = ref_status;
	}


	@Override
	public String toString() {
		return "AppReferMapDTO [ref_no=" + ref_no + ", doc_no=" + doc_no + ", member_num=" + member_num
				+ ", member_name=" + member_name + ", div_name=" + div_name + ", rank=" + rank + ", ref_status="
				+ ref_status + "]";
	}
	
	


	
}
