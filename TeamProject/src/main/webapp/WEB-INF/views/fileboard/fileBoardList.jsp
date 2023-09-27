<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<link href="./resources/css/fileBoardList.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>
<script>
$(	//아니 문법이 이게 맞아?
	function() {	/* 새글쓰기 */
		$("#fileBoardPostBtn").on("click", function() {
		location.href='fileBoardPost';
	})}
/* 	function() {
		$("#btnSearch").on("click", function() {
			var url = "/fileboard/fileBoardList";
			
			location.href=url;
			console.log(url);
		})
	} */
	)

</script>

<div class="f-container">
	<h2 id="f-title">자료함</h2>

	<!-- 검색창 -->
	<div class="f-s-container">
		<div class="row">
			<form method="post" name="search" action="#">
				<table class="pull-right">
					<tr>
						<td><select id="searchField">
								<option value="">전체</option>
								<option value="title">제목</option>
								<option value="userId">작성자</option>
						</select></td>
						<td><input type="text" id="searchVal" placeholder="검색어 입력"
							maxlength="100"></td>
						<td><button type="submit" class="f-btn" id="btnSearch">검
								색</button></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
	<br>
	<hr>
	<br>
	<!-- 테이블 -->
	<table border=1>
		<tr>
			<th>NO</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>

		<c:forEach var="file" items="${fileBoardList}" varStatus="loop">
			<tr>
				<td>${file.file_board_no}</td>
				<td>${file.member_num}</td>
				<td><a
					href="fileBoardDetail?file_board_no=${file.file_board_no}">${file.file_board_title}</a></td>
				<td>${file.file_board_date}</td>
				<td>${file_board_view}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 새글쓰기 -->
	<br>
	<!-- <input type="hidden" name="buttionValue" value="1"> -->
	<button class="f-btn" id="fileBoardPostBtn" style="float: right;">
		<!-- FileBoardPost?file_board_no=0 -->
		새글쓰기
	</button>

	<!-- 페이징 -->
</div>
</html>