package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MailRecDTO")
public class MailRecDTO {
	private int mail_num;			//메일 고유 번호
	private String mail_receiver;	//메일 받는 사람
	private int rec_num;			//메일 받는 사람 번호
	private String rec_status;		//수신 확인 정보
	
	
	public MailRecDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailRecDTO(int mail_num, String mail_receiver, int rec_num, String rec_status) {
		super();
		this.mail_num = mail_num;
		this.mail_receiver = mail_receiver;
		this.rec_num = rec_num;
		this.rec_status = rec_status;
	}
	public int getMail_num() {
		return mail_num;
	}
	public void setMail_num(int mail_num) {
		this.mail_num = mail_num;
	}
	public String getMail_receiver() {
		return mail_receiver;
	}
	public void setMail_receiver(String mail_receiver) {
		this.mail_receiver = mail_receiver;
	}
	public int getRec_num() {
		return rec_num;
	}
	public void setRec_num(int rec_num) {
		this.rec_num = rec_num;
	}
	public String getRec_status() {
		return rec_status;
	}
	public void setRec_status(String rec_status) {
		this.rec_status = rec_status;
	}
	@Override
	public String toString() {
		return "MailRecDTO [mail_num=" + mail_num + ", mail_receiver=" + mail_receiver + ", rec_num=" + rec_num
				+ ", rec_status=" + rec_status + "]";
	}
	
	
	
}
