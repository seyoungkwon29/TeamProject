package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MeetingRoomDTO")
public class MeetingRoomDTO {
	private int member_num;
	private String meeting_num;
	private String meeting_date;
	private String meeting_time;
	private String reservation;
	
	public MeetingRoomDTO() {
		super();
	}
	public MeetingRoomDTO(int member_num, String meeting_num, String meeting_date, String meeting_time,
			String reservation) {
		super();
		this.member_num = member_num;
		this.meeting_num = meeting_num;
		this.meeting_date = meeting_date;
		this.meeting_time = meeting_time;
		this.reservation = reservation;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getMeeting_num() {
		return meeting_num;
	}
	public void setMeeting_num(String meeting_num) {
		this.meeting_num = meeting_num;
	}
	public String getMeeting_date() {
		return meeting_date;
	}
	public void setMeeting_date(String meeting_date) {
		this.meeting_date = meeting_date;
	}
	public String getMeeting_time() {
		return meeting_time;
	}
	public void setMeeting_time(String meeting_time) {
		this.meeting_time = meeting_time;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	@Override
	public String toString() {
		return "MeetingRoomDTO [member_num=" + member_num + ", meeting_num=" + meeting_num + ", meeting_date="
				+ meeting_date + ", meeting_time=" + meeting_time + ", reservation=" + reservation + "]";
	}
	
	
	
	
	
}
