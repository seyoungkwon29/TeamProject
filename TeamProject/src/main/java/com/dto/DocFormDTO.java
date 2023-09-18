package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("DocFormDTO")
public class DocFormDTO {
	
	private int form_no;
	private String form_name;
	private String form_content;
	
	
	public DocFormDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocFormDTO(int form_no, String form_name, String form_content) {
		super();
		this.form_no = form_no;
		this.form_name = form_name;
		this.form_content = form_content;
	}

	public int getForm_no() {
		return form_no;
	}

	public void setForm_no(int form_no) {
		this.form_no = form_no;
	}

	public String getForm_name() {
		return form_name;
	}

	public void setForm_name(String form_name) {
		this.form_name = form_name;
	}

	public String getForm_content() {
		return form_content;
	}

	public void setForm_content(String form_content) {
		this.form_content = form_content;
	}

	@Override
	public String toString() {
		return "DocFormDTO [form_no=" + form_no + ", form_name=" + form_name + ", form_content=" + form_content + "]";
	}
	
	
	
}
