package com.dto;

import java.util.Date;
import java.util.List;

import com.domain.Notice;

public class NoticeDTO {
	private Long noticeNum;
	private Long memberNum;
	private String memberName;
	private String title;
	private String content;
	private Integer views;
	private Date createdAt;

	private List<UploadFileDTO> files;
	
	

	public NoticeDTO() {}

	public NoticeDTO(Long memberNum, String title, String content) {
		this.memberNum = memberNum;
		this.title = title;
		this.content = content;
		this.views = Integer.valueOf(0);
		this.createdAt = new Date();
	}

	public Long getNoticeNum() {
		return noticeNum;
	}

	public void setNoticeNum(Long noticeNum) {
		this.noticeNum = noticeNum;
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

	@Override
	public String toString() {
		return "NoticeDTO [noticeNum=" + noticeNum + ", memberNum=" + memberNum + ", memberName=" + memberName
				+ ", title=" + title + ", content=" + content + ", views=" + views + ", createdAt=" + createdAt + "]";
	}

	
	public Notice ToNotice() {
		Notice notice = new Notice(this.memberNum, this.title, this.content);
		return notice;
	}
	
	public List<UploadFileDTO> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFileDTO> files) {
		this.files = files;
	}
	
	public static NoticeDTO from(Notice notice) {
		
		NoticeDTO noticeDTO = new NoticeDTO(); 
		noticeDTO.setNoticeNum(notice.getNoticeNum());
		noticeDTO.setMemberNum(notice.getMemberNum());
		noticeDTO.setTitle(notice.getTitle());
		noticeDTO.setContent(notice.getContent());
		noticeDTO.setViews(notice.getViews());
		noticeDTO.setCreatedAt(notice.getCreatedAt());
		noticeDTO.setFiles(notice.getFiles());
		return noticeDTO;
	}
}
