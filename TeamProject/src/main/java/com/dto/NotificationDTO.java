package com.dto;

public class NotificationDTO {
	private int noti_num;
	private int member_num;
	private String noti_content;
	private String noti_status;
	private String noti_date;
	public NotificationDTO() {
		super();
		
	}
	public NotificationDTO(int member_num, String noti_content) {
		super();
		this.member_num = member_num;
		this.noti_content = noti_content;
		this.noti_status = "N";
	}
	public int getNoti_num() {
		return noti_num;
	}
	public void setNoti_num(int noti_num) {
		this.noti_num = noti_num;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getNoti_content() {
		return noti_content;
	}
	public void setNoti_content(String noti_content) {
		this.noti_content = noti_content;
	}
	public String getNoti_status() {
		return noti_status;
	}
	public void setNoti_status(String noti_status) {
		this.noti_status = noti_status;
	}
	public String getNoti_date() {
		return noti_date;
	}
	public void setNoti_date(String noti_date) {
		this.noti_date = noti_date;
	}
	@Override
	public String toString() {
		return "NotificationDTO [noti_num=" + noti_num + ", member_num=" + member_num + ", noti_content=" + noti_content
				+ ", noti_status=" + noti_status + ", noti_date=" + noti_date + "]";
	}
	
	
}
