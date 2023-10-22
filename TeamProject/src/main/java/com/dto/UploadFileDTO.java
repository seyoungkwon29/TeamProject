package com.dto;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.springframework.web.util.UriUtils;

public class UploadFileDTO {
	
	private Long id;

	protected String originalFilename;
	protected String storeFilename;
	
	public UploadFileDTO() {}
	
	public UploadFileDTO(String originalFilename, String storeFilename) {

		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getStoreFilename() {
		return storeFilename;
	}

	public void setStoreFilename(String storeFilename) {
		this.storeFilename = storeFilename;
	}

	public String getEncodedOriginalFilename() throws UnsupportedEncodingException {
		return UriUtils.encode(originalFilename, StandardCharsets.UTF_8.toString());
	}

	public boolean isNew() {
		return getId() == null;
	}
	
}
