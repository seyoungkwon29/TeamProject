package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("AppFileDTO")
public class AppFileDTO {

	int file_no;
	int doc_no;
	String file_name;
	String file_rename;
	String file_path;
	
	public AppFileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AppFileDTO(int file_no, int doc_no, String file_name, String file_rename, String file_path) {
		super();
		this.file_no = file_no;
		this.doc_no = doc_no;
		this.file_name = file_name;
		this.file_rename = file_rename;
		this.file_path = file_path;
	}



	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public int getDoc_no() {
		return doc_no;
	}

	public void setDoc_no(int doc_no) {
		this.doc_no = doc_no;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_rename() {
		return file_rename;
	}

	public void setFile_rename(String file_rename) {
		this.file_rename = file_rename;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


	@Override
	public String toString() {
		return "ApprovalFileDTO [file_no=" + file_no + ", doc_no=" + doc_no + ", file_name=" + file_name
				+ ", file_rename=" + file_rename + ", file_path=" + file_path + "]";
	}
	
	
	
}
