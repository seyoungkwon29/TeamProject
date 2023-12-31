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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
		<main class="mt10 w-100 center w-60-l">
			<section class="mw8 center flex-auto">
				<h1 class="f3">공지사항</h1>
				<article id="article" class="pb3">
					<span id="title-top" class="f4">${notice.title}</span>
					<div id="title-mid">
						<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">글번호</small>${notice.noticeNum}</p>
						<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">작성자</small>${notice.memberName}</p>
						<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">작성일</small><fmt:formatDate value="${notice.createdAt}" pattern="YYYY년 M월 d일 hh:mm"/></p>
						<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">조회수</small>${notice.views}</p>
					</div>
					<div id="title-content">
						${notice.content}
					</div>
					<c:if test="${not empty notice.files}">
					<div class="flex flex-column">
						<p class="f5 mb1"><small class="title-mr">첨부 파일</small></p>
						<c:forEach var="file" items="${notice.files}">
						<spring:url var="fileUrl" value="/notices/${noticeNum}/files/${file.encodedOriginalFilename}"/>
						<div class="mb1">					
							<a href="${fileUrl}" class="link-reset black dim">${file.originalFilename}</a>
						</div>
						</c:forEach>
					</div>
					</c:if>
				</article>
			</section>
			<section class="mw8 center flex-auto">
				<div class="flex flex-row">
					<spring:url var="editNoticeUrl" value="/notices/${notice.noticeNum}/edit"/>
					<a href="${editNoticeUrl}" class="link-reset flex w-50">
						<button type="button" class="modify-button button-reset b ph3 pv3 ba b--green green bg-transparent dim f5 dib w-100 mt3 mb3">수정</button>
					</a>
					<spring:url var="deleteNoticeUrl" value="/notices/${notice.noticeNum}/delete"/>
					<form action="${deleteNoticeUrl}" method="post" class="w-50">
						<button type="submit" id="removeCheck" class="del-button button-reset b ph3 pv3 ba b--dark-red dark-red bg-transparent dim f5 dib w-100 mt3 mb3">삭제</button>
					</form>
				</div>
				<spring:url var="noticeListUrl" value="/notices"/>
				<a href="${noticeListUrl}" class="link-reset flex w-100">
					<input class="back-button button-reset b ph3 pv3 ba b--black bg-transparent dim f5 dib w-100 tc" value="뒤로가기">
				</a>
			</section>
		</main>
	<script>
	$(function(){
	    $('#removeCheck').click(function(){
	        if(!confirm('정말로 삭제하시겠습니까?')){
	            return false;
	        }
	    });
	});
	</script>
</body>
</html>