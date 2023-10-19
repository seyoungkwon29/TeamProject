package com.dto;

public class TodoDTO {
	private int todo_num;
	private String todo_title;
	private String status;
	private String start_date;
	private String due_date;
	private String member_name;
	private String content;
	private float conversion_ratio = 0;
	private String top_task = null;
	private int member_num;
	
	public TodoDTO() {
		super();
	}
	
	public TodoDTO(int todo_num, String todo_title, String status, String start_date, String due_date,
			String member_name, String content, float conversion_ratio, String top_task, int member_num) {
		super();
		this.todo_num = todo_num;
		this.todo_title = todo_title;
		this.status = status;
		this.start_date = start_date;
		this.due_date = due_date;
		this.member_name = member_name;
		this.content = content;
		this.conversion_ratio = conversion_ratio;
		this.top_task = top_task;
		this.member_num = member_num;
	}
	
	public int getTodo_num() {
		return todo_num;
	}
	public void setTodo_num(int todo_num) {
		this.todo_num = todo_num;
	}
	public String getTodo_title() {
		return todo_title;
	}
	public void setTodo_title(String todo_title) {
		this.todo_title = todo_title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public float getConversion_ratio() {
		return conversion_ratio;
	}
	public void setConversion_ratio(float conversion_ratio) {
		this.conversion_ratio = conversion_ratio;
	}
	public String getTop_task() {
		return top_task;
	}
	public void setTop_task(String top_task) {
		this.top_task = top_task;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	@Override
	public String toString() {
		return "TodoDTO [todo_num=" + todo_num + ", todo_title=" + todo_title + ", status=" + status + ", start_date="
				+ start_date + ", due_date=" + due_date + ", member_name=" + member_name + ", content=" + content
				+ ", conversion_ratio=" + conversion_ratio + ", top_task=" + top_task + ", member_num=" + member_num
				+ "]";
	}
	
	
}
