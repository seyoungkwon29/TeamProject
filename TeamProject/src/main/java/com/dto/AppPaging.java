package com.dto;

public class AppPaging {
	
	//AppPageInfoDTO에 값 초기화: 객체 생성 및 초기화
	//static함수 접근 시에, 클래스 이름으로 바로  접근 가능(AppPaging.getAppPageInfoDTO)
	public static AppPageInfoDTO getAppPageInfo(int currentPage, int totalCount) {
		
		AppPageInfoDTO api = null;		
		int docLimit = 5;   //한 페이지당 게시글 개수
		int naviLimit = 5;   //페이지 번호 링크에 표시할 페이지 번호 개수로 5개
		int maxPage;         //페이지의 마지막 번호를 나타내는 정수
		int startNavi;       //페이지 네비게이션의 시작 번호를 나타내는 정수
		int endNavi;         //페이지 네비게이션의 마지막 번호를 나타내는 정수
		boolean prev, next;  //이전,다음 페이지가 있는지 여부
		
		maxPage = (int)((double)totalCount/docLimit + 0.9); //전체 항목 수(50개)를 한 페이지당 표시할 항목의 개수(10)로 나눈 뒤 0.9를 더한 값의 정수 부분
		startNavi = (((int)((double)currentPage/naviLimit+0.9))-1)*naviLimit +1;
		endNavi = startNavi + naviLimit -1; //
		prev = startNavi > 1; //이전 페이지가 있는지 여부: 시작 번호가 1보다 크면 true, 아니면 false
		next = endNavi < maxPage; //다음 페이지가 있는지 여부: 끝 번호가 최대 페이지 수(maxPage)보다 작으면 true, 아니면 false
		
		//현재 페이지네이션의 마지막 번호는 전체 페이지의 마지막 페이지 번호보다 클 수 없음
		if(maxPage < endNavi) { //endNavi가  maxPage보다 크다면, endNavi를 maxPage로 설정
			endNavi = maxPage;			
		}
		
		//AppPageInfoDTO 객체를 생성하고 초기화
		api = new AppPageInfoDTO
			  (currentPage, docLimit, naviLimit, startNavi, endNavi, totalCount, maxPage, prev, next);
		
		return api; //AppPageInfoDTO를 반환
	}
}
