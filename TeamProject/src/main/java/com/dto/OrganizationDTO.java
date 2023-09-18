package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("OrganizationDTO")
public class OrganizationDTO {

	private String member_name;
	private String div_name;
	private String rank;
	private String mail;
	private String photo;
	
	public OrganizationDTO() {
		super();
	}

	public OrganizationDTO(String member_name, String div_name, String rank, String mail, String photo) {
		super();
		this.member_name = member_name;
		this.div_name = div_name;
		this.rank = rank;
		this.mail = mail;
		this.photo = photo;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getDiv_name() {
		return div_name;
	}

	public void setDiv_name(String div_name) {
		this.div_name = div_name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "OrganizationDTO [member_name=" + member_name + ", div_name=" + div_name + ", rank=" + rank + ", mail="
				+ mail + ", photo=" + photo + "]";
	}
}
