package com.common;

import java.util.List;

public class PageResponseDTO<T> {
	// 페이지네이션에서 보여줘야할 페이지 번호의 개수.
	private final static int PAGINATION_LIST_SIZE = 5;
	private List<T> items;
	private int count;
	private int page;
	private int size;
	private int start;
	private int end;
	private int last;
	private boolean hasPrevious;
	private boolean hasNext;
	
	
	public PageResponseDTO(PageRequestDTO page, List<T> items, int count) {
		// 현재 페이지의 번호.
		this.page = page.getPage();
		// 페이지 당 아이템 개수.
		this.size = page.getSize();
		
		// 현재 페이지의 아이템 목록.
		this.items = items;
		// 전체 아이템의 개수.
		this.count = count;

		// 전체 페이지 중 마지막 페이지의 번호.
		this.last = (int) Math.ceil(count/(double)size);
		// 현재 페이지네이션의 마지막 페이지의 번호.
		this.end = (int)(Math.ceil(this.page / (double) PAGINATION_LIST_SIZE)) * PAGINATION_LIST_SIZE;
		// 현재 페이지네이션의 시작 페이지 번호.
		this.start = this.end - (PAGINATION_LIST_SIZE - 1);
		// 현재 페이지네이션의 마지막 번호는 전체 페이지의 마지막 페이지 번호보다 클 수 없다.
		this.end = end > last ? last : end;
		// 이전 페이지가 존재하는지 확인
		this.hasPrevious = this.start > 1;
		// 다음 페이지가 존재하는지 확인
		this.hasNext = this.last > this.end;
		
	}


	public List<T> getItems() {
		return items;
	}


	public void setItems(List<T> items) {
		this.items = items;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	public int getLast() {
		return last;
	}


	public void setLast(int last) {
		this.last = last;
	}


	public boolean isHasPrevious() {
		return hasPrevious;
	}


	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}


	public boolean isHasNext() {
		return hasNext;
	}


	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

}
