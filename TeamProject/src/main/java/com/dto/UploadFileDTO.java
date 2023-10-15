package com.dto;

public class UploadFileDTO {
	
	private Long id;
	private Long comNum;
	private String originalFilename;
	private String storeFilename;
	private String encodedOriginalFilename;
	
	public UploadFileDTO() {}
	
	public UploadFileDTO(String originalFilename, String storeFilename) {

		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
	}
	
	public UploadFileDTO(Long comNum, String originalFilename, String storeFilename) {
		this.comNum = comNum;
		this.originalFilename = originalFilename;
		this.storeFilename = storeFilename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getComNum() {
		return comNum;
	}

	public void setComNum(Long comNum) {
		this.comNum = comNum;
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

	public String getEncodedOriginalFilename() {
		return encodedOriginalFilename;
	}

	public void setEncodedOriginalFilename(String encodedOriginalFilename) {
		this.encodedOriginalFilename = encodedOriginalFilename;
	}
	
}
