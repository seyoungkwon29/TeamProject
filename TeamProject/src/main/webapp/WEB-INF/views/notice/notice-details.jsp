<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
<link href="${bootstrapCss}" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
	    <div class="sidebar col-md-3 col-lg-2 p-0">
      		<jsp:include page="/WEB-INF/views/common/sideBar.jsp" flush="true" /> <br> 
      	</div>
      	
      	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      		<article class="mt-3 mb-3 pb-3">
      			<h2 class="h2 mb-3">${notice.title}</h2>
      			<div class="d-flex gap-2">
      				<p>글번호: ${notice.noticeNum}
	      			<p>작성자: ${notice.memberName}</p>
					<p>작성일: <fmt:formatDate value="${notice.createdAt}" pattern="YYYY년 M월 d일 hh:mm"/></p>
					<p>조회수: ${notice.views}</p>
      			</div>
      			<p class="text-break">${notice.content}</p>
      		</article>
    		<div class="btn-group me-2 w-100">
    			<spring:url var="editNoticeUrl" value="/notices/${notice.noticeNum}/edit" />
				<a href="${editNoticeUrl}">
					<button type="button" class="btn btn-outline-primary">수정</button>
				</a>
				<spring:url var="deleteNoticeUrl" value="/notices/${notice.noticeNum}/delete"/>
				<form action="${deleteNoticeUrl}" method="post">
					<button type="submit" class="btn btn-outline-danger">삭제</button>
				</form>
				<spring:url var="noticeListUrl" value="/notices"/>
				<a href="${noticeListUrl}">
					<button type="button" class="btn btn-outline-dark">뒤로가기</button>
				</a>
			</div>
      	</main>
   	</div>
</div>
</body>
</html>