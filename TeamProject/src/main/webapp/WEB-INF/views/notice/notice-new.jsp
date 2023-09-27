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
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
<link href="${bootstrapCss}" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
	<div class="row">
<%-- 	    <div class="sidebar col-md-3 col-lg-2 p-0">
      		<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" /> <br>
      	</div> --%>
      	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      		<section class="mt-3 mb-3">
      			<spring:url var="newNoticeUrl" value="/notices/new"/>
				<form:form action="${newNoticeUrl}" method="post" modelAttribute="noticeForm">
					<div class="mb-3">
						<form:label path="title" cssClass="form-label">제목</form:label>
						<form:input path="title" cssClass="form-control"/>
						<form:errors path="title"/>
					</div>
					<div class="mb-3">
						<form:label path="content" cssClass="form-label">내용</form:label>
						<form:textarea path="content" cssClass="form-control" rows="3"/>
						<form:errors path="content"/>
					</div>
					<div>
						<button type="submit" class="btn btn-outline-primary">작성</button>
						<spring:url var="noticeListUrl" value="/notices"/>
			            <a href="${noticeListUrl}" class="btn btn-outline-dark">뒤로가기</a>
					</div>
				</form:form>
			</section>
      	</main>
   	</div>
</div>
</body>
</html>