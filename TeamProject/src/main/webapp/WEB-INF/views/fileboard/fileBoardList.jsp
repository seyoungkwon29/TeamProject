<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link href="resources/css/fileBoardList.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<script>
	function new_Post() {	/* 새글쓰기 */
		var url="loginCheck/fileBoardPost";
		location.href = url;
	}
	function searchBtn() { /* 검색하기 */
		var url = "searchFileBoard";
		location.href = url;
	} 
</script>

	<div class="f-container">
		<div>
			<h1 id="f-title" class="f_title">자료함</h1>
		</div>

		<!-- 검색창 -->
		<div class="f-s-container">
		<div class="row" style="margin-left: 1028px;">
			<form method="get" name="search" action="searchFileBoard">
				<table class="pull-right">
					<tr>
						<td>
							<select id="searchField" name="searchField">
								<option value="title">제목</option>
								<option value="userId">작성자</option>
							</select>
						</td>
						<td>
							<input type="text" id="searchVal" name="searchVal" placeholder="검색어 입력" maxlength="100">
						</td>
						<td><button type="submit" class="search-btn" id="btnSearch" value="">검색</button></td>
					</tr>
				</table>
				<input type="hidden" name="page" value="1">
			</form>
		</div>
		</div>
		<%
		String searchField = null;
		String searchVal = null;
		if(session.getAttribute("searchField")!=null){
			searchField = (String) session.getAttribute("searchField");
			System.out.println("프론트에서 알립니다>>>searchField is :" + searchField);
		} 
		if(session.getAttribute("searchVal")!=null){
			searchVal = (String) session.getAttribute("searchVal");
					System.out.println(">>>searchVal is :" + searchVal);
		}%>

		<!-- 새글쓰기 -->
		<!-- <input type="hidden" name="buttionValue" value="1"> -->
		<div>
			<input type="button" class="f-btn" id="fileBoardPostBtn" onClick="new_Post()" value="새 글쓰기"></input>
		
		</div>
		
		<!-- 테이블 -->
	<table border=1  class="t-List">
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
	  <jsp:include page="fileBoardPage.jsp" flush="true"></jsp:include>
</div>