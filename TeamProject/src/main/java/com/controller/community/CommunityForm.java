package com.controller.community;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.dto.UploadFileDTO;

public class CommunityForm {

	private Long comNum;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	private List<MultipartFile> files;
	private List<MultipartFile> images;
	
	public CommunityForm() {
	}

	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
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

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
	
}
