package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatContentDTO")
public class ChatContentDTO {
	private int chat_content_no;
	private int chatroom_num;
	private String chat_content;
	private int member_num;
	private String chat_date;
	public ChatContentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatContentDTO(int chat_content_no, int chatroom_num, String chat_content, int member_num,
			String chat_date) {
		super();
		this.chat_content_no = chat_content_no;
		this.chatroom_num = chatroom_num;
		this.chat_content = chat_content;
		this.member_num = member_num;
		this.chat_date = chat_date;
	}
	public int getChat_content_no() {
		return chat_content_no;
	}
	public void setChat_content_no(int chat_content_no) {
		this.chat_content_no = chat_content_no;
	}
	public int getChatroom_num() {
		return chatroom_num;
	}
	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getCaht_date() {
		return chat_date;
	}
	public void setCaht_date(String chat_date) {
		this.chat_date = chat_date;
	}
	@Override
	public String toString() {
		return "ChatContentDTO [chat_content_no=" + chat_content_no + ", chatroom_num=" + chatroom_num
				+ ", chat_content=" + chat_content + ", member_num=" + member_num + ", chat_date=" + chat_date + "]";
	}
	
	
}
