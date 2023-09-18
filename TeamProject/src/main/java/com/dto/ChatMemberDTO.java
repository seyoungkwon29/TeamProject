package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("ChatMemberDTO")
public class ChatMemberDTO {
	private int chatroom_num;
	private int member_num;
	private String div_name;
	private String member_name;
	private String rank;
	private String member_status;
	
	public ChatMemberDTO() {
		super();
	}

	public ChatMemberDTO(int chatroom_num, int member_num, String div_name, String member_name, String rank,
			String member_status) {
		super();
		this.chatroom_num = chatroom_num;
		this.member_num = member_num;
		this.div_name = div_name;
		this.member_name = member_name;
		this.rank = rank;
		this.member_status = member_status;
	}

	public int getChatroom_num() {
		return chatroom_num;
	}

	public void setChatroom_num(int chatroom_num) {
		this.chatroom_num = chatroom_num;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getDiv_name() {
		return div_name;
	}

	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMember_status() {
		return member_status;
	}

	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}

	@Override
	public String toString() {
		return "ChatMemberDTO [chatroom_num=" + chatroom_num + ", member_num=" + member_num + ", div_name=" + div_name
				+ ", member_name=" + member_name + ", rank=" + rank + ", member_status=" + member_status + "]";
	}

	
	
}
