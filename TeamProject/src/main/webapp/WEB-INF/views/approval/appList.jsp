<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			
			<c:forEach items="${dList}" var="appDoc">
				<tr>
					<td>${appDoc.docDate }</td>
					<td>${appDoc.formName }</td>
					<c:url var="aDetail" value="/approval/detail.sw?type=${type }&docStatus=${appDoc.appStatus }">
						<c:param name="docNo" value="${appDoc.docNo }"></c:param>
					</c:url>
					<td><a href="${aDetail }">${appDoc.docTitle }</a></td>
					<td>${appDoc.memName }</td>
					<c:if test="${appDoc.appStatus == '대기'}">
						<td><span class="status-1">${appDoc.appStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.appStatus == '예정' && appDoc.docStatus == '반려'}">
						<td><span class="status-4">${appDoc.docStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.appStatus == '예정' && appDoc.docStatus != '반려'}">
						<td><span class="status-5">${appDoc.appStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.appStatus == '완료' && appDoc.docStatus == '진행'}">
						<td><span class="status-2">${appDoc.docStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.appStatus == '완료' && appDoc.docStatus == '완료'}">
						<td><span class="status-3">${appDoc.appStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.appStatus == '완료' && appDoc.docStatus == '반려'}">
						<td><span class="status-4">${appDoc.docStatus }</span></td>
					</c:if>
					<c:if test="${appDoc.appStatus == '반려'}">
						<td><span class="status-4">${appDoc.appStatus }</span></td>
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