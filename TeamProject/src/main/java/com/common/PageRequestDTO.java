package com.common;

public class PageRequestDTO {
		
	private int page;
	private int size;
	
	public PageRequestDTO(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", size=" + size + "]";
	}
	
}
