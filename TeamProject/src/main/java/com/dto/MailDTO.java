package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("MailDTO")
public class MailDTO {

	private int mail_num;  			//메일 고유번호
	private String mail_title; 		//메일 제목
	private String mail_content; 	//메일 내용
	private String mail_sender; 	//메일 발신자
	private int member_num; 		//메일 발신자 사번
	private String mail_from_date; 	//메일 발신 시간
	private String mail_to_date;	//메일 수신 시간
	private String mail_fileNum;	//메일 첨부파일 고유번호
	private String mail_fileName;	//메일 첨부파일 이름
	private String mail_filePath;	//메일 첨부파일 저장경로
	private String mail_fileReName;	//메일 첨부파일 시스템 자동저장 이름
	
	public MailDTO() {
		super();
		
	}

	public MailDTO(int mail_num, String mail_title, String mail_content, String mail_sender, int member_num,
			String mail_from_date, String mail_to_date, String mail_fileNum, String mail_fileName, String mail_filePath,
			String mail_fileReName) {
		super();
		this.mail_num = mail_num;
		this.mail_title = mail_title;
		this.mail_content = mail_content;
		this.mail_sender = mail_sender;
		this.member_num = member_num;
		this.mail_from_date = mail_from_date;
		this.mail_to_date = mail_to_date;
		this.mail_fileNum = mail_fileNum;
		this.mail_fileName = mail_fileName;
		this.mail_filePath = mail_filePath;
		this.mail_fileReName = mail_fileReName;
	}

	public int getMail_num() {
		return mail_num;
	}

	public void setMail_num(int mail_num) {
		this.mail_num = mail_num;
	}

	public String getMail_title() {
		return mail_title;
	}

	public void setMail_title(String mail_title) {
		this.mail_title = mail_title;
	}

	public String getMail_content() {
		return mail_content;
	}

	public void setMail_content(String mail_content) {
		this.mail_content = mail_content;
	}

	public String getMail_sender() {
		return mail_sender;
	}

	public void setMail_sender(String mail_sender) {
		this.mail_sender = mail_sender;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getMail_from_date() {
		return mail_from_date;
	}

	public void setMail_from_date(String mail_from_date) {
		this.mail_from_date = mail_from_date;
	}

	public String getMail_to_date() {
		return mail_to_date;
	}

	public void setMail_to_date(String mail_to_date) {
		this.mail_to_date = mail_to_date;
	}

	public String getMail_fileNum() {
		return mail_fileNum;
	}

	public void setMail_fileNum(String mail_fileNum) {
		this.mail_fileNum = mail_fileNum;
	}

	public String getMail_fileName() {
		return mail_fileName;
	}

	public void setMail_fileName(String mail_fileName) {
		this.mail_fileName = mail_fileName;
	}

	public String getMail_filePath() {
		return mail_filePath;
	}

	public void setMail_filePath(String mail_filePath) {
		this.mail_filePath = mail_filePath;
	}

	public String getMail_fileReName() {
		return mail_fileReName;
	}

	public void setMail_fileReName(String mail_fileReName) {
		this.mail_fileReName = mail_fileReName;
	}

	@Override
	public String toString() {
		return "MailDTO [mail_num=" + mail_num + ", mail_title=" + mail_title + ", mail_content=" + mail_content
				+ ", mail_sender=" + mail_sender + ", member_num=" + member_num + ", mail_from_date=" + mail_from_date
				+ ", mail_to_date=" + mail_to_date + ", mail_fileNum=" + mail_fileNum + ", mail_fileName="
				+ mail_fileName + ", mail_filePath=" + mail_filePath + ", mail_fileReName=" + mail_fileReName + "]";
	}
	
	
	
}
