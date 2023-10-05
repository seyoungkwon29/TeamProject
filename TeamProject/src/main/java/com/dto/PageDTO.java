package com.dto;

import java.util.List;

//참고 블로그 : https://freehoon.tistory.com/112
public class PageDTO {
	
	//아래 변수 2개는 초기값 세팅이라 변수 선언해서 setListSize / setRangeSize로 설정해주면 됨
	private int listSize = 2;   //한페이지 당 보여질 리스트의 개수 
	private int rangeSize = 5;  //한페이지 당 보여질 페이지의 개수
	
	//필요한 리스트 추가하기(PageDTO에 담아서 한번에 JSP로 넘겨줄 수 있기 때문임 . 따로 setAttribute해도 될 듯)
	List<MailRecDTO> mailRecDTOList;
	List<MailDTO> mailDTOList;
	List<MemberDTO> memberDTOList;
	
	private int page;			//현재 목록의 페이지번호  
	private int range;			//전체 페이지 인덱스.. 번호?
	private int listCnt;		//전체 게시물의 개수
	private int pageCnt;		//전체 페이지 범위의 개수
	private int startPage;		//각 페이지 범위 시작 번호
	private int endPage;		//각페이지 범위 끝 번호
	private boolean prev;		//이전 페이지 여부
	private boolean next;		//다음 페이지 여부
	private int startList;				//리스트 시작 번호 (몇번째 게시물인지...필요하면 검색해서 설정해줘야함)
	
	public List<MailRecDTO> getMailRecDTOList() {
		return mailRecDTOList;
	}

	public void setMailRecDTOList(List<MailRecDTO> mailRecDTOList) {
		this.mailRecDTOList = mailRecDTOList;
	}

	public List<MailDTO> getMailDTOList() {
		return mailDTOList;
	}

	public void setMailDTOList(List<MailDTO> mailDTOList) {
		this.mailDTOList = mailDTOList;
	}

	public List<MemberDTO> getMemberDTOList() {
		return memberDTOList;
	}

	public void setMemberDTOList(List<MemberDTO> memberDTOList) {
		this.memberDTOList = memberDTOList;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public void setRangeSize(int rangeSize) {
		this.rangeSize = rangeSize;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public int getRangeSize() {
		return rangeSize;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
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

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}

	public int getStartList() {
		return startList;
	}

	public void pageInfo(int page, int range, int listCnt) {
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		
		//전체 페이지수 
		this.pageCnt = (int)Math.ceil((double)listCnt/listSize);

		//시작 페이지
		this.startPage = (range - 1) * rangeSize + 1 ;

		//끝 페이지
		this.endPage = range * rangeSize;

		//게시판 시작번호
		this.startList = (page - 1) * listSize;

		//이전 버튼 상태
		this.prev = range == 1 ? false : true;

		//다음 버튼 상태
		this.next = endPage >= pageCnt ? false : true;
		if (this.endPage >= this.pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
	}
	

}
