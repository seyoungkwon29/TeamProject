package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("AppReferDTO")
public class AppReferDTO {

	int ref_no;
	int doc_no;
	int member_num;
	String ref_status;
	
	
	public AppReferDTO() {
		super();
	}


	public AppReferDTO(int ref_no, int doc_no, int member_num, String ref_status) {
		super();
		this.ref_no = ref_no;
		this.doc_no = doc_no;
		this.member_num = member_num;
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


	public String getRef_status() {
		return ref_status;
	}


	public void setRef_status(String ref_status) {
		this.ref_status = ref_status;
	}


	@Override
	public String toString() {
		return "AppReferDTO [ref_no=" + ref_no + ", doc_no=" + doc_no + ", member_num=" + member_num + ", ref_status="
				+ ref_status + "]";
	}



	
}
