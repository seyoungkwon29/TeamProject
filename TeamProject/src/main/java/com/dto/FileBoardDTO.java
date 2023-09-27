package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("FileBoardDTO")
public class FileBoardDTO{
	
	private int file_board_no;
	private int member_num;
	private String file_board_date; //FILE_BOARD_DATE, 자료작성날짜
	private String file_board_title;//	FILE_BOARD_TITLE, 자료제목
	private String file_board_content;//	FILE_BOARD_CONTENT, 자료본문
	private int file_board_view;//	FILE_BOARD_VIEW, 자료조회수
	private String file_name;//	FILE_NAME, 원본파일 이름
	private String file_rename;//FILE_RENAME, 재설정파일이름
	private String file_path;    //	FILE_PATH 파일경로 mainmessage
	
	public FileBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public FileBoardDTO(int member_num, int file_board_no, String file_board_date, String file_board_title,
			String file_board_content, int file_board_view, String file_name, String file_rename, String file_path) {
		super();
		this.member_num = member_num;
		this.file_board_no = file_board_no;
		this.file_board_date = file_board_date;
		this.file_board_title = file_board_title;
		this.file_board_content = file_board_content;
		this.file_board_view = file_board_view;
		this.file_name = file_name;
		this.file_rename = file_rename;
		this.file_path = file_path;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getFile_board_no() {
		return file_board_no;
	}
	public void setFile_board_no(int file_board_no) {
		this.file_board_no = file_board_no;
	}
	public String getFile_board_date() {
		return file_board_date;
	}
	public void setFile_board_date(String file_board_date) {
		this.file_board_date = file_board_date;
	}
	public String getFile_board_title() {
		return file_board_title;
	}
	public void setFile_board_title(String file_board_title) {
		this.file_board_title = file_board_title;
	}
	public String getFile_board_content() {
		return file_board_content;
	}
	public void setFile_board_content(String file_board_content) {
		this.file_board_content = file_board_content;
	}
	public int getFile_board_view() {
		return file_board_view;
	}
	public void setFile_board_view(int file_board_view) {
		this.file_board_view = file_board_view;
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
		return "FileBoardDTO [file_board_no=" + file_board_no + ", member_num=" + member_num + ", file_board_date="
				+ file_board_date + ", file_board_title=" + file_board_title + ", file_board_content="
				+ file_board_content + ", file_board_view=" + file_board_view + ", file_name=" + file_name
				+ ", file_rename=" + file_rename + ", file_path=" + file_path + "]";
	}
	
	
}