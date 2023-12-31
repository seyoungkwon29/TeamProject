package com.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommunityDTO {

	private Long comNum;
	private Long memberNum;
	private String memberName;
	private String title;
	private String content;
	private Integer views;
	private Date createdAt;

	private List<UploadFileDTO> files;
	
	public CommunityDTO() {
	}

	public CommunityDTO(Long memberNum, String title, String content) {
		this.memberNum = memberNum;
		this.title = title;
		this.content = content;
		this.views = Integer.valueOf(0);
		this.createdAt = new Date();
	}

	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
	}

	public Long getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Long memberNum) {
		this.memberNum = memberNum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<UploadFileDTO> getFiles() {
		if (this.files == null) {
			this.files = new ArrayList<>();
		}
		return files;
	}

	public void setFiles(List<UploadFileDTO> files) {
		this.files = files;
	}

	
	public void addFile(UploadFileDTO file) {
		getFiles().add(file);
	}
	
	public static CommunityDTO from(CommunityDTO community) {
		
		CommunityDTO communityDTO = new CommunityDTO(); 
		communityDTO.setComNum(community.getComNum());
		communityDTO.setMemberNum(community.getMemberNum());
		communityDTO.setTitle(community.getTitle());
		communityDTO.setContent(community.getContent());
		communityDTO.setViews(community.getViews());
		communityDTO.setCreatedAt(community.getCreatedAt());
		communityDTO.setFiles(community.getFiles());
		
		return communityDTO;
	}
}
