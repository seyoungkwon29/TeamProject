package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ProjectManagementDTO")
public class ProjectManagementDTO {
	
	private int project_num;
	private String project_title;
	private String stauts;
	private String start_date;
	private String due_date;
	private String project_manager;
	private int member_num;

	public ProjectManagementDTO() {
		super();
	}

	public ProjectManagementDTO(int project_num, String project_title, String stauts, String start_date,
			String due_date, String project_manager, int member_num) {
		super();
		this.project_num = project_num;
		this.project_title = project_title;
		this.stauts = stauts;
		this.start_date = start_date;
		this.due_date = due_date;
		this.project_manager = project_manager;
		this.member_num = member_num;
	}

	public int getProject_num() {
		return project_num;
	}

	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}

	public String getProject_title() {
		return project_title;
	}

	public void setProject_title(String project_title) {
		this.project_title = project_title;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getProject_manager() {
		return project_manager;
	}

	public void setProject_manager(String project_manager) {
		this.project_manager = project_manager;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	@Override
	public String toString() {
		return "ProjectManagementDTO [project_num=" + project_num + ", project_title=" + project_title + ", stauts="
				+ stauts + ", start_date=" + start_date + ", due_date=" + due_date + ", project_manager="
				+ project_manager + ", member_num=" + member_num + "]";
	}
}
