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
<link rel="stylesheet" href="<spring:url value="/resources/css/utility.css"/>">
<title>${notice.title}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
	<main class="w-100 center w-60-l">
		<section class="mw8 center flex-auto">
			<h1 class="f3">공지사항</h1>
			<article class="pb3">
				<h2 class="f4">${notice.title}</h2>
				<p class="dib mv0 mr2 f5"><small class="mr1">작성자</small>${notice.memberName}</p>
				<p class="dib mv0 mr2 f5"><small class="mr1">작성일</small><fmt:formatDate value="${notice.createdAt}" pattern="YYYY년 M월 d일 hh:mm"/></p>
				<p class="measure lh-copy">${notice.content}</p>
			</article>
		</section>
		<section class="mw8 center flex-auto">
			<div class="flex flex-row">
				<spring:url var="editNoticeUrl" value="/notices/${notice.noticeNum}/edit"/>
				<a href="${editNoticeUrl}" class="link-reset flex w-50">
					<button type="button" class="button-reset b ph3 pv3 ba b--green green bg-transparent dim f5 dib w-100 mt3 mb3">수정</button>
				</a>
				<spring:url var="deleteNoticeUrl" value="/notices/${notice.noticeNum}/delete"/>
				<form action="${deleteNoticeUrl}" method="post" class="w-50">
					<button type="submit" class="button-reset b ph3 pv3 ba b--dark-red dark-red bg-transparent dim f5 dib w-100 mt3 mb3">삭제</button>
				</form>
			</div>
			<spring:url var="noticeListUrl" value="/notices"/>
			<a href="${noticeListUrl}" class="link-reset flex w-100">
				<input class="button-reset b ph3 pv3 ba b--black bg-transparent dim f5 dib w-100 tc" value="뒤로가기">
			</a>
		</section>
	</main>
</body>
</html>