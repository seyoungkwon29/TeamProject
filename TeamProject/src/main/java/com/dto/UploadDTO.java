package com.dto;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadDTO {
//	String theText;  //uploadForm의 name과 일치 시킴 
	CommonsMultipartFile theFile;
	int member_num;//db의 사원 이름
	String photo;//db의 사원 사진
//	public String getTheText() {
//		return theText;
//	}
//	public void setTheText(String theText) {
//		this.theText = theText;
//	}
	public CommonsMultipartFile getTheFile() {
		return theFile;
	}
	public void setTheFile(CommonsMultipartFile theFile) {
		this.theFile = theFile;
	}
	public UploadDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadDTO(int member_num, CommonsMultipartFile theFile) {
		super();
		this.member_num = member_num;
		this.theFile = theFile;
	}
	@Override
	public String toString() {
		return "UploadDTO [member_num=" + member_num + ", theFile=" + theFile + "]";
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}
