<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재 문서함</title>
<link href="resources/css/appDraftList.css" rel="stylesheet">
</head>
<body>

	<div class="s-container">
		<h1 id="h-title">결재 문서함</h1>
		
		<span class="type"><a href="draftList?parameter=app">전체</a></span>
		<span class="type"><a href="draftList?parameter=app&docStatus=대기">대기</a></span>
		<span class="type"><a href="draftList?parameter=app&docStatus=예정">예정</a></span>
		<span class="type"><a href="draftList?parameter=app&docStatus=진행">진행</a></span>
		<span class="type"><a href="draftList?parameter=app&docStatus=반려">반려</a></span>
		<span class="type"><a href="draftList?parameter=app&docStatus=완료"">완료</a></span>
		
		
		<table class="t-List">
			<tr>
				<th class="th-1">기안일</th>
				<th class="th-1">문서양식</th>
				<th class="th-2">제목</th>
				<th class="th-1">기안자</th>
				<th class="th-1">결재상태</th>
			</tr>
			
			<c:forEach var="app" items="${appDocList}">
				<tr>
					<td>${app.doc_date }</td>
					<td>${app.form_name }</td>
					
					<td><a href="clickDocContent?type=app&docNo=${app.doc_no}&docStatus=${app.app_status}">
						${app.doc_title}</a>
					</td> <!-- 제목-->
					
					<td>${app.member_name}</td>
					
					<c:if test="${app.app_status == '대기'}"> <!-- 결재자의 결재 상태 -->
						<td><span class="status-1">${app.app_status }</span></td>
					</c:if>
					
					<c:if test="${app.app_status == '예정' && app.doc_status == '반려'}">
						<td><span class="status-4">${app.doc_status }</span></td>
					</c:if>
					
					<c:if test="${app.app_status == '예정' && app.doc_status != '반려'}">
						<td><span class="status-5">${app.app_status }</span></td>
					</c:if>
					
					<c:if test="${app.app_status == '완료' && app.doc_status == '진행'}">
						<td><span class="status-2">${app.doc_status }</span></td>
					</c:if>
					
					<c:if test="${app.app_status == '완료' && app.doc_status == '완료'}">
						<td><span class="status-3">${app.app_status }</span></td>
					</c:if>
					
					<c:if test="${app.app_status == '완료' && app.doc_status == '반려'}">
						<td><span class="status-4">${app.doc_status }</span></td>
					</c:if>
					
					<c:if test="${app.app_status == '반려'}">
						<td><span class="status-4">${app.app_status }</span></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
 <!-- 페이징 처리 -->
	<div class="paging">
		<c:if test="${search.member_num == null}">
	        <!-- 첫 페이지일 경우, 해당 페이지로 이동하는 링크 -->
	        <c:if test="${api.startNavi == 1 }">
	            <a href="draftList?parameter=app&page=1&docStatus=${pageStatus}"></a>
	        </c:if>
	
	        <!-- 이전 페이지 버튼 생성 -->
	        <c:if test="${api.prev}">
	            <a href="draftList?parameter=app&page=${api.startNavi-1}&docStatus=${pageStatus}">
	                <button class="page-btn">＜   </button>
	            </a>
	        </c:if>
	        
	        <!-- 페이지 번호에 대한 버튼을 생성 , 시작 페이지(begin)와 끝 페이지(end)를 설정 -->
			<c:forEach var="page" begin="${api.startNavi }" end="${api.endNavi }">
				<a href="draftList?parameter=app&page=${page}&docStatus=${pageStatus}">
					<button class="page-btn">${page}</button>
				</a>
			</c:forEach>
			
			<!-- 다음 페이지 버튼 생성 -->
			<c:if test="${api.next && api.endNavi > 0}">
	             <a href="draftList?parameter=app&page=${api.endNavi+1}&docStatus=${pageStatus}"> 
	             	<button class="page-btn"> ＞</button>
	             </a>
	        </c:if>
	 	</c:if>
	 	
		<c:if test= "${search.member_num != null}"> <!-- 검색한 결과가 있는 경우 -->
			<c:if test="${api.startNavi == 1 }">
			 	<a href="searchConditionList?parameter=app&page=1&
			 	searchCondition=${search.searchCondition }&searchValue=${search.searchValue }"></a>
			</c:if>
			
			<c:if test="${api.prev}">
				<a href="searchConditionList?parameter=app&page=${api.startNavi-1}&
				searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
					<button class="page-btn">Prev</button>
				</a>
			</c:if>
			
	        <!-- 페이지 번호에 대한 버튼을 생성 , 시작 페이지(begin)와 끝 페이지(end)를 설정 -->
			<c:forEach var="page" begin="${api.startNavi }" end="${api.endNavi }">
				<a href="searchConditionList?parameter=app&page=${page}&
				searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
					<button class="page-btn">${page}</button>
				</a>
			</c:forEach>
	
			<c:if test="${api.next && api.endNavi > 0}">
				 <a href="searchConditionList?parameter=app&page=${api.endNavi+1}&
				 searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
				 	<button class="page-btn">Next</button>
				 </a>
			</c:if>
		</c:if>
		
 	</div>

<!-- 검색하기 -->		
	<div class="l-search">
		<form action="searchConditionList" method="get">
				<select class="l-select" id="s-condition" name="searchCondition" style="text-align: left; width: 80px;">
					<option value="all">전체</option>
					<option value="doc_date">기안일</option>
					<option value="form_name">문서양식</option>
					<option value="doc_title">제목</option>
					<option value="member_name">기안자</option>
				</select>
				
				<div class="l-input">
					<input type="text" id="s-value" name="searchValue" class="l-text">
					<input type="submit" value="검색" id="btn-search" class="i-search">
					<input type="hidden" value="app" name="parameter">
				</div>
				
		</form>
	</div>
		
</div> <!-- 전체 div -->
</html>