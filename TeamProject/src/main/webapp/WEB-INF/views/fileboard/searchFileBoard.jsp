<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/fileBoardList.css" rel="stylesheet">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function refresh() {	/* 목록보기 */
		var url="fileBoardList";
		location.href = url;
	}

</script>
</head>

<body>
	<div class="f-container">
		<div>
			<h1 id="f-title" class="f_title">자료함</h1>
		</div>
		<div class="f-s-container">
		<div class="row">
		</div>
	</div>
<%	String searchField = null;
	String searchVal = null;
if(request.getParameter("searchField")!=null){
	searchField = (String) request.getParameter("searchField");
	System.out.println("프론트에서 알립니다>>>searchword is :" + searchVal);
} 
if(session.getAttribute("searchVal")!=null){
	searchVal = (String) request.getAttribute("searchVal");
			System.out.println(">>>searchword is :" + searchVal);
}
	request.getAttribute("map");
%>

	<!-- 새글쓰기 -->
	<!-- <input type="hidden" name="buttionValue" value="1"> -->
	<div>
	<input type="button" class="f-btn" id="fileBoardPostBtn" onClick="refresh()" value="목록보기"></input>
	
	</div>
		
		<!-- 테이블 -->
	<table border=1 class="t-List">
	<tbody style="border: 1px solid #80808000;">
		<tr>
				<th class="th-1">글번호</th>
				<th class="th-1">작성자</th>
				<th class="th-2">제목</th>
				<th class="th-3">작성일</th>
				<th class="th-1">조회수</th>
		</tr>
		<c:forEach var="file" items="${getFileBoardDTOList}" varStatus="loop">
			<tr>
				<td>${file.file_board_no}</td>
				<td>${file.member_num}</td>
				<td><a href="fileBoardDetail?file_board_no=${file.file_board_no}">${file.file_board_title}</a></td>
				<td>${file.file_board_date}</td>
				<td>${file.file_board_view}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<!-- page -->
	  <jsp:include page="searchFileBoardPage.jsp" flush="true">
	    <jsp:param name="searchField" value="${map.searchField}" />
	    <jsp:param name="searchVal" value="${map.searchVal}" />
      </jsp:include>
	</div>
</body>
</html>