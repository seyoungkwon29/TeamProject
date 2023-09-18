package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("SaveDocFormDTO")
public class SaveDocFormDTO {
	
	int doc_no;
    int member_num;
    String form_name;
    String doc_title;
    String doc_content;
    String doc_status;
    String doc_date;
    String leave_type;
    String leave_start;
    String leave_end;
    String leave_time;
    int leave_date;
    int leave_left;
    int leave_apply;
    
    
	public SaveDocFormDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SaveDocFormDTO(int doc_no, int member_num, String form_name, String doc_title, String doc_content,
			String doc_status, String doc_date, String leave_type, String leave_start, String leave_end,
			String leave_time, int leave_date, int leave_left, int leave_apply) {
		super();
		this.doc_no = doc_no;
		this.member_num = member_num;
		this.form_name = form_name;
		this.doc_title = doc_title;
		this.doc_content = doc_content;
		this.doc_status = doc_status;
		this.doc_date = doc_date;
		this.leave_type = leave_type;
		this.leave_start = leave_start;
		this.leave_end = leave_end;
		this.leave_time = leave_time;
		this.leave_date = leave_date;
		this.leave_left = leave_left;
		this.leave_apply = leave_apply;
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


	public String getForm_name() {
		return form_name;
	}


	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}


	public String getDoc_title() {
		return doc_title;
	}


	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}


	public String getDoc_content() {
		return doc_content;
	}


	public void setDoc_content(String doc_content) {
		this.doc_content = doc_content;
	}


	public String getDoc_status() {
		return doc_status;
	}


	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}


	public String getDoc_date() {
		return doc_date;
	}


	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}


	public String getLeave_type() {
		return leave_type;
	}


	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}


	public String getLeave_start() {
		return leave_start;
	}


	public void setLeave_start(String leave_start) {
		this.leave_start = leave_start;
	}


	public String getLeave_end() {
		return leave_end;
	}


	public void setLeave_end(String leave_end) {
		this.leave_end = leave_end;
	}


	public String getLeave_time() {
		return leave_time;
	}


	public void setLeave_time(String leave_time) {
		this.leave_time = leave_time;
	}


	public int getLeave_date() {
		return leave_date;
	}


	public void setLeave_date(int leave_date) {
		this.leave_date = leave_date;
	}


	public int getLeave_left() {
		return leave_left;
	}


	public void setLeave_left(int leave_left) {
		this.leave_left = leave_left;
	}


	public int getLeave_apply() {
		return leave_apply;
	}


	public void setLeave_apply(int leave_apply) {
		this.leave_apply = leave_apply;
	}


	@Override
	public String toString() {
		return "SaveDocFormDTO [doc_no=" + doc_no + ", member_num=" + member_num + ", form_name=" + form_name
				+ ", doc_title=" + doc_title + ", doc_content=" + doc_content + ", doc_status=" + doc_status
				+ ", doc_date=" + doc_date + ", leave_type=" + leave_type + ", leave_start=" + leave_start
				+ ", leave_end=" + leave_end + ", leave_time=" + leave_time + ", leave_date=" + leave_date
				+ ", leave_left=" + leave_left + ", leave_apply=" + leave_apply + "]";
	}


	

}
