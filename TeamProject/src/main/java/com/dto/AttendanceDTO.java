package com.dto;

public class AttendanceDTO {

	private int att_no;
	private int member_num;
	private String att_date;
	private String att_start;
	private String att_fin;
	private String att_total;
	private String att_status;
	
	public AttendanceDTO() {
		super();
	}

	public AttendanceDTO(int att_no, int member_num, String att_date, String att_start, String att_fin,
			String att_total, String att_status) {
		super();
		this.att_no = att_no;
		this.member_num = member_num;
		this.att_date = att_date;
		this.att_start = att_start;
		this.att_fin = att_fin;
		this.att_total = att_total;
		this.att_status = att_status;
	}

	public int getAtt_no() {
		return att_no;
	}

	public void setAtt_no(int att_no) {
		this.att_no = att_no;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getAtt_date() {
		return att_date;
	}

	public void setAtt_date(String att_date) {
		this.att_date = att_date;
	}

	public String getAtt_start() {
		return att_start;
	}

	public void setAtt_start(String att_start) {
		this.att_start = att_start;
	}

	public String getAtt_fin() {
		return att_fin;
	}

	public void setAtt_fin(String att_fin) {
		this.att_fin = att_fin;
	}

	public String getAtt_total() {
		return att_total;
	}

	public void setAtt_total(String att_total) {
		this.att_total = att_total;
	}

	public String getAtt_status() {
		return att_status;
	}

	public void setAtt_status(String att_status) {
		this.att_status = att_status;
	}

	@Override
	public String toString() {
		return "AttendanceDTO [att_no=" + att_no + ", member_num=" + member_num + ", att_date=" + att_date
				+ ", att_start=" + att_start + ", att_fin=" + att_fin + ", att_total=" + att_total + ", att_status="
				+ att_status + "]";
	}
}
