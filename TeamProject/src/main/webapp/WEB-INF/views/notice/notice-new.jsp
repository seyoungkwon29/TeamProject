<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항/글쓰기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<spring:url value="/resources/css/utility.css"/>">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
	<main class="w-100 center w-60-l">
		<section class="mw8 center flex-auto">
			<h1 class="f3">공지사항 새 글쓰기</h1>
			<div class="flex flex-auto items-center justify-start center">
				<spring:url var="newNoticeUrl" value="/notices/new"/>
				<form:form action="${newNoticeUrl}" method="post" modelAttribute="noticeForm" cssClass="flex flex-column flex-auto">
					<div class="flex flex-column mb3">
						<form:label path="title" cssClass="db lh-copy f5 mb2">제목</form:label>
						<form:input path="title" cssClass="pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100 mb2"/>
						<form:errors path="title" cssClass="f6 dark-red db mb2"/>
					</div>
					<div class="flex flex-column mb3">
						<form:label path="content" cssClass="db lh-copy f5 mb2">내용</form:label>
						<form:textarea path="content" cssClass="db border-box hover-black w-100 ba b--black-20 pa2 br2 mb2" rows="10"/>
						<form:errors path="content" cssClass="f6 dark-red db mb2"/>
					</div>
					<div class="flex flex-column mb3">
						<button type="submit" class="button-reset b ph3 pv3 ba b--white white bg-green dim f5 dib w-100 mb3">작성</button>
						<spring:url var="noticeListUrl" value="/notices"/>
						<a href="${noticeListUrl}" class="link-reset flex w-100">
							<input class="button-reset b ph3 pv3 ba b--black bg-transparent dim f5 dib w-100 tc" value="뒤로가기">
						</a>
					</div>
				</form:form>
			</div>
		</section>
	</main>
</body>
</html>