<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
<link href="${bootstrapCss}" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
	<div class="row">
<%-- 	    <div class="sidebar col-md-3 col-lg-2 p-0">
      		<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" /> <br> 
      	</div> --%>
		<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<h1 class="mt-3 mb-3">공지사항</h1>
			<div class="d-flex justify-content-end mb-3 gap-2">
				<form class="d-flex" role="search" method="get" action="">
					<select name="searchType">
			        	<option value="writer" selected >작성자</option>
			        	<option value="content">제목 + 내용</option>
			        </select>
			        <input name="searchKeyword" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
			        <button class="btn btn-outline-success" type="submit">Search</button>
		      	</form>
		      	<spring:url var="newNoticeUrl" value="/notices/new"/>
				<a href="${newNoticeUrl}">
					<button type="button" class="btn btn-outline-primary">작성</button>
				</a>
			</div>
		    <div class="table-responsive">
			    <table class="table">
			       <thead>
			           <tr>
			               <th scope="col">글번호</th>
			               <th scope="col">제목</th>
			               <th scope="col">작성자</th>
			               <th scope="col">작성일</th>
				           <th scope="col">조회수</th>
			           </tr>
			       </thead>
			       <tbody>               
		        	<c:forEach var="notice" items="${pageResponse.items}">
		        		<spring:url var="noticeDetailsUrl" value="/notices/${notice.noticeNum}"/>
			        	<tr>
					        <th scope="row">
					        	<a href="${noticeDetailsUrl}">${notice.noticeNum}</a>
				        	</th>
				        	<td>
				        		<a href="${noticeDetailsUrl}">${notice.title}</a>
				        	</td>
				        	<td>${notice.memberName}</td>
				        	<td><fmt:formatDate value="${notice.createdAt}" pattern="YYYY년 M월 d일 hh:mm"/></td>
				    	    <td>${notice.views}</td>
				        </tr>
		        	</c:forEach>
			       </tbody>
			    </table>
		    </div>
			<div class="d-flex justify-content-center">
		    	<nav aria-label="Page navigation example">
				  <ul class="pagination">
				  	<c:choose>
				  	<c:when test="${pageResponse.hasPrevious}">
				  		<spring:url var="previousPageUrl" value="/notices">
				  			<spring:param name="page" value="${pageResponse.start-1}"/>
				  			<spring:param name="size" value="${pageResponse.size}"/>
				  			<c:if test="${not empty param.searchType}">
				  			<spring:param name="searchKeyword" value="${param.searchType}"/>
				  			</c:if>
				  			<c:if test="${not empty param.searchKeyword}">
				  			<spring:param name="searchKeyword" value="${param.searchKeyword}"/>
				  			</c:if>
				  		</spring:url>
					    <li class="page-item">
					      <a class="page-link" href="${previousPageUrl}" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
				    </c:when>
				    <c:otherwise>
				    	<li class="page-item disabled">
					      <a class="page-link" href="#" aria-label="Previous">
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
				  			<spring:param name="searchKeyword" value="${param.searchType}"/>
				  			</c:if>
				  			<c:if test="${not empty param.searchKeyword}">
				  			<spring:param name="searchKeyword" value="${param.searchKeyword}"/>
				  			</c:if>
			  		</spring:url>
				    <c:choose>
					    <c:when test="${pageResponse.page eq status.current}">
					    <li class="page-item active">
					    	<a class="page-link" href="${pageUrl}">${status.current}</a>
				    	</li>
					    </c:when>
					    <c:otherwise>
   						<li class="page-item">
					    	<a class="page-link" href="${pageUrl}">${status.current}</a>
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
				  			<spring:param name="searchKeyword" value="${param.searchType}"/>
				  			</c:if>
				  			<c:if test="${not empty param.searchKeyword}">
				  			<spring:param name="searchKeyword" value="${param.searchKeyword}"/>
				  			</c:if>
				  		</spring:url>
					    <li class="page-item">
					      <a class="page-link" href="${nextPageUrl}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:when>
				    <c:otherwise>
					    <li class="page-item disabled">
					      <a class="page-link" href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
				    </c:otherwise>
			    	</c:choose>
				  </ul>
				</nav>
		    </div>
		</main>
	</div>
</div>
</body>
</html>