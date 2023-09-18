package com.dto;

public class StatusDTO {

	private String status;
	private String count;
	private String memNum;
	private int late;
	private int attendance;
	private int absence;

	public StatusDTO() {
		super();
	}

	public StatusDTO(String status, String count, String memNum, int late, int attendance, int absence) {
		super();
		this.status = status;
		this.count = count;
		this.memNum = memNum;
		this.late = late;
		this.attendance = attendance;
		this.absence = absence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getMemNum() {
		return memNum;
	}

	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}

	public int getLate() {
		return late;
	}

	public void setLate(int late) {
		this.late = late;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public int getAbsence() {
		return absence;
	}

	public void setAbsence(int absence) {
		this.absence = absence;
	}

	@Override
	public String toString() {
		return "StatusDTO [status=" + status + ", count=" + count + ", memNum=" + memNum + ", late=" + late
				+ ", attendance=" + attendance + ", absence=" + absence + "]";
	}
}
