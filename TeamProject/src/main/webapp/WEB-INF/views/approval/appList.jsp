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
		<h2 id="h-title">결재 문서함</h2>
		<span class="type ${docStatus eq '전체' ? 'active' : ''}"><a href="/approval/${type }ListView.sw">전체</a></span>
		<span class="type ${docStatus eq '대기' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=대기">대기</a></span>
		<span class="type ${docStatus eq '예정' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=예정">예정</a></span>
		<span class="type ${docStatus eq '진행' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=진행">진행</a></span>
		<span class="type ${docStatus eq '완료' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=완료">완료</a></span>
		<span class="type ${docStatus eq '반려' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=반려">반려</a></span>
		
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
		
		<jsp:include page="appPaging.jsp"></jsp:include>  <!-- 페이징 --> 
		
		<div class="l-search">
			<form action="/approval/${type}Search.sw" method="get">
				<select class="l-select" id="s-condition" name="searchCondition" style="text-align: left; width: 80px;">
					<option value="all">전체</option>
					<option value="docDate">기안일</option>
					<option value="formName">문서양식</option>
					<option value="docTitle">제목</option>
					<option value="memName">기안자</option>
				</select>
				<div class="l-input">
					<input type="text" id="s-value" name="searchValue" class="l-text">
					<input type="submit" id="btn-search" class="i-search" value="검색">
				</div>
			</form>
		</div>
		
	</div>
</html>