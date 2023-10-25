<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<spring:url value="/resources/css/menu.css"/>">
<link rel="stylesheet" href="<spring:url value="/resources/css/utility.css"/>">
<!-- <link rel="stylesheet" href="/resources/css/notice2.css"> -->
<title>공지사항</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
	<main class="mt6 w-100 center w-60-l">
		<section id="section-top" class="flex-auto">
			<h1 class="f3">공지사항</h1>
			<div id="search-bar" class="flex items-center justify-end">
				<form:form action="" method="get" modelAttribute="searchCondition" cssClass="flex">
					<form:select path="searchType" id="search-type">
						<form:options items="${searchTypes}" itemLabel="description" itemValue="type"/>
					</form:select>
					<form:input type="search" path="searchKeyword" id="search-input" cssClass="b ph3 pv2 input-reset ba b--black bg-transparent f6 dib"/>
					<button id="search-btn" class="b ph3 pv2 button-reset ba b--black bg-transparent f6 dib" type="submit">검색 </button>
				</form:form>
		      	<spring:url var="newNoticeUrl" value="/notices/new"/>
				<a href="${newNoticeUrl}" class="link-reset">
					<button id="write-btn" class="b ph3 pv2 button-reset ba b--green white bg-green f6 dib" type="submit">작성</button>
				</a>
			</div>
		</section>
		<section class="center flex-auto justify-center">
			<div class="pv4">
				<div class="overflow-auto">
					<table id="table-list" class="f5 w-100 center">
						<thead>
							<tr id="table-tr">
								<th class="fw6 tl pv2 pr1 bg-white">글번호</th>
								<th class="fw6 tl pv2 pr1 bg-white w-30">제목</th>
								<th class="fw6 tc pv2 pr1 bg-white">작성자</th>
								<th class="fw6 tc pv2 pr1 bg-white">작성일</th>
								<th class="fw6 tc pv2 pr1 bg-white">조회수</th>
							</tr>
						</thead>
						<tbody class="lh-copy">
						<c:forEach var="notice" items="${pageResponse.items}">
							<spring:url var="noticeDetailsUrl" value="/notices/${notice.noticeNum}"/>
							<tr id="table-tr">
								<th class="pv2 pr1 tl">
									<a href="${noticeDetailsUrl}" class="link-reset black dim">${notice.noticeNum}</a>
								</th>
								<td class="pv2 pr1 tl">
									<a href="${noticeDetailsUrl}" class="link-reset black dim">${notice.title}</a>
								</td>
								<td class="pv2 pr1 tc">${notice.memberName}</td>
								<td class="pv2 pr1 tc"><fmt:formatDate value="${notice.createdAt}" pattern="YYYY년 MM월 dd일 hh:mm"/></td>
								<td class="pv2 pr1 tc">${notice.views}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</section>

		<section class="center flex-auto justify-center">
			<nav class="center">
			 	<ul class="list flex items-center justify-center">
				  	<c:choose>
				  	<c:when test="${pageResponse.hasPrevious}">
				  		<spring:url var="previousPageUrl" value="/notices">
				  			<spring:param name="page" value="${pageResponse.start-1}"/>
				  			<spring:param name="size" value="${pageResponse.size}"/>
				  			<c:if test="${not empty param.searchType}">
				  			<spring:param name="searchType" value="${param.searchType}"/>
				  			</c:if>
				  			<c:if test="${not empty param.searchKeyword}">
				  			<spring:param name="searchKeyword" value="${param.searchKeyword}"/>
				  			</c:if>
				  		</spring:url>
					    <li class="ph2">
					      <a class="link-reset black dim" href="${previousPageUrl}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:when>
				    <c:otherwise>
				    	<li class="ph2">
					      <a class="link-reset black dim" href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:otherwise>
			    	</c:choose>
			    	<c:forEach begin="${pageResponse.start}" end="${pageResponse.end}" step="1" varStatus="status">
				    <spring:url var="pageUrl" value="/notices">
				  			<spring:param name="page" value="${status.current}"/>
				  			<spring:param name="size" value="${pageResponse.size}"/>
				  			<c:if test="${not empty param.searchType}">
				  			<spring:param name="searchType" value="${param.searchType}"/>
				  			</c:if>
				  			<c:if test="${not empty param.searchKeyword}">
				  			<spring:param name="searchKeyword" value="${param.searchKeyword}"/>
				  			</c:if>
			  		</spring:url>
				    <c:choose>
					    <c:when test="${pageResponse.page eq status.current}">
					    <li class="ph2">
					    	<a class="link-reset b green dim f4" href="${pageUrl}">${status.current}</a>
				    	</li>
					    </c:when>
					    <c:otherwise>
   						<li class="ph2">
					    	<a class="link-reset black dim f5" href="${pageUrl}">${status.current}</a>
				    	</li>
					    </c:otherwise>
				    </c:choose>					   
				    </c:forEach>
				    
   				  	<c:choose>
				  	<c:when test="${pageResponse.hasNext}">
				  		<spring:url var="nextPageUrl" value="/notices">
				  			<spring:param name="page" value="${pageResponse.end+1}"/>
				  			<spring:param name="size" value="${pageResponse.size}"/>
				  			<c:if test="${not empty param.searchType}">
				  			<spring:param name="searchType" value="${param.searchType}"/>
				  			</c:if>
				  			<c:if test="${not empty param.searchKeyword}">
				  			<spring:param name="searchKeyword" value="${param.searchKeyword}"/>
				  			</c:if>
				  		</spring:url>
					    <li class="ph2">
					      <a class="link-reset black dim" href="${nextPageUrl}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:when>
				    <c:otherwise>
					    <li class="ph2">
					      <a class="link-reset black dim" href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:otherwise>
			    	</c:choose>
				  </ul>
			</nav>
		</section>
	</main>
</body>
</html>