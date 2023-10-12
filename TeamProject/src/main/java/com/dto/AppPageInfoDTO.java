package com.dto;

public class AppPageInfoDTO { //페이징 처리 하기 위해  필요한 변수
	
	int currentPage; //현재 페이지
	int docLimit; 	 //한 페이지당 게시글 개수
	int naviLimit;   //한 페이지당 표시되는 페이지 네비게이션(페이지 번호 링크) 개수
	int startNavi; 	 //페이지 네비게이션의 시작 번호를 나타내는 정수
	int endNavi; 	 //페이지 네비게이션의 끝 번호를 나타내는 정수
	int totalCount;  //전체 게시글 개수를 나타내는 정수
	int maxPage; 	 //페이지의 마지막 번호를 나타내는 정수
	boolean prev;  	 //이전 페이지가 있는지 여부
	boolean next; 	 //다음 페이지가 있는지 여부
	
	public AppPageInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppPageInfoDTO(int currentPage, int docLimit, int naviLimit, int startNavi, int endNavi, int totalCount,
			int maxPage, boolean prev, boolean next) {
		super();
		this.currentPage = currentPage;
		this.docLimit = docLimit;
		this.naviLimit = naviLimit;
		this.startNavi = startNavi;
		this.endNavi = endNavi;
		this.totalCount = totalCount;
		this.maxPage = maxPage;
		this.prev = prev;
		this.next = next;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getDocLimit() {
		return docLimit;
	}
	public void setDocLimit(int docLimit) {
		this.docLimit = docLimit;
	}
	public int getNaviLimit() {
		return naviLimit;
	}
	public void setNaviLimit(int naviLimit) {
		this.naviLimit = naviLimit;
	}
	public int getStartNavi() {
		return startNavi;
	}
	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}
	public int getEndNavi() {
		return endNavi;
	}
	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "AppPageInfoDTO [currentPage=" + currentPage + ", docLimit=" + docLimit + ", naviLimit=" + naviLimit
				+ ", startNavi=" + startNavi + ", endNavi=" + endNavi + ", totalCount=" + totalCount + ", maxPage="
				+ maxPage + ", prev=" + prev + ", next=" + next + "]";
	}
	
	
	
}
