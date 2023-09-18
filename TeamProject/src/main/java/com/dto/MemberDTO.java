package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MemberDTO")
public class MemberDTO {
	
	private int member_num;
	private String member_name;
	private String div_name;
	private String rank;
	private String address;
	private String phone;
	private String mail;
	private String hire_date;
	private String retire_date;
	private String ssn;
	private String password;
	private String gender;
	private String photo;
	private int annual_leave;
	
	public MemberDTO() {
		super();
	}

	public MemberDTO(int member_num, String member_name, String div_name, String rank, String address, String phone,
			String mail, String hire_date, String retire_date, String ssn, String password, String gender, String photo,
			int annual_leave) {
		this.member_num = member_num;
		this.member_name = member_name;
		this.div_name = div_name;
		this.rank = rank;
		this.address = address;
		this.phone = phone;
		this.mail = mail;
		this.hire_date = hire_date;
		this.retire_date = retire_date;
		this.ssn = ssn;
		this.password = password;
		this.gender = gender;
		this.photo = photo;
		this.annual_leave = annual_leave;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}

	public String getRetire_date() {
		return retire_date;
	}

	public void setRetire_date(String retire_date) {
		this.retire_date = retire_date;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getAnnual_leave() {
		return annual_leave;
	}

	public void setAnnual_leave(int annual_leave) {
		this.annual_leave = annual_leave;
	}

	@Override
	public String toString() {
		return "MemberDTO [member_num=" + member_num + ", member_name=" + member_name + ", password=" + password + "]";
	}
	
	
}
	
