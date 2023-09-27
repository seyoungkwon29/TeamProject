package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ApprovalDTO")
public class ApprovalDTO {

	int app_no;
	int doc_no;
	int member_num;
	int app_level;
	String app_date;
	String app_status;
	String rej_reason;
	

	public ApprovalDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApprovalDTO(int app_no, int doc_no, int member_num, int app_level, String app_date, String app_status,
			String rej_reason) {
		super();
		this.app_no = app_no;
		this.doc_no = doc_no;
		this.member_num = member_num;
		this.app_level = app_level;
		this.app_date = app_date;
		this.app_status = app_status;
		this.rej_reason = rej_reason;
	}

	public int getApp_no() {
		return app_no;
	}

	public void setApp_no(int app_no) {
		this.app_no = app_no;
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

	public int getApp_level() {
		return app_level;
	}

	public void setApp_level(int app_level) {
		this.app_level = app_level;
	}

	public String getApp_date() {
		return app_date;
	}

	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}

	public String getApp_status() {
		return app_status;
	}

	public void setApp_status(String app_status) {
		this.app_status = app_status;
	}

	public String getRej_reason() {
		return rej_reason;
	}

	public void setRej_reason(String rej_reason) {
		this.rej_reason = rej_reason;
	}

	@Override
	public String toString() {
		return "ApprovalDTO [app_no=" + app_no + ", doc_no=" + doc_no + ", member_num=" + member_num + ", app_level="
				+ app_level + ", app_date=" + app_date + ", app_status=" + app_status + ", rej_reason=" + rej_reason
				+ "]";
	}
	
	
}
