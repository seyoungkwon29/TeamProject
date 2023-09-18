package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatRoomDTO")
public class ChatRoomDTO {
	private int chatroom_num;
	private String chatroom_title;
	private int chatroom_type;
	
	public ChatRoomDTO() {
		super();
	}

	public ChatRoomDTO(int chatroom_num, String chatroom_title, int chatroom_type) {
		super();
		this.chatroom_num = chatroom_num;
		this.chatroom_title = chatroom_title;
		this.chatroom_type = chatroom_type;
	}

	public int getChatroom_num() {
		return chatroom_num;
	}

	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}

	public String getChatroom_title() {
		return chatroom_title;
	}

	public void setChatroom_title(String chatroom_title) {
		this.chatroom_title = chatroom_title;
	}

	public int getChatroom_type() {
		return chatroom_type;
	}

	public void setChatroom_type(int chatroom_type) {
		this.chatroom_type = chatroom_type;
	}

	@Override
	public String toString() {
		return "ChatRoomDTO [chatroom_num=" + chatroom_num + ", chatroom_title=" + chatroom_title + ", chatroom_type="
				+ chatroom_type + "]";
	}

	
	
}
