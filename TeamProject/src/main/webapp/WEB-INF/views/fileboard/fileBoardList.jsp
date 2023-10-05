<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<link href="./resources/css/fileBoardList.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function new_Post() { /* 새글쓰기 */
		var url = "fileBoardPost";
		location.href = url;
		console.log("newPost 실행");
	}

	function fn_prev(page, range, rangeSize) {
		var page = ((range - 2) * rangeSize) + 1;
		var range = range - 1;
		var url = "${pageContext.request.contextPath}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}

	//페이지 번호 클릭
	function fn_pagination(page, range, rangeSize, searchType, keyword) {
		var url = "${pageContext.request.contextPath}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}

	//다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {
		var page = parseInt((range * rangeSize)) + 1;
		var range = parseInt(range) + 1;

		var url = "${pageContext.request.contextPath}/board/getBoardList";
		url = url + "?page=" + page;
		url = url + "&range=" + range;
		location.href = url;
	}
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
	<div id="paginationBox">
		<ul class="pagination">
			<c:if test="${pageDTO.isPrev()}">
				<li class="page-item"><a class="page-link"
					href="fileBoardList?page=${pageDTO.getStartPage()-pageDTO.getRangeSize() }">PREV</a></li>
			</c:if>
			<c:forEach begin="${pageDTO.getStartPage()}"
				end="${pageDTO.getEndPage()}" varStatus="status">
				<li class="page-item"><a class="page-link"
					href="fileBoardList?page=${status.index}">${status.index}</a></li>
			</c:forEach>
			<c:if test="${pageDTO.isNext()}">
				<li class="page-item"><a class="page-link"
					href="fileBoardList?page=${pageDTO.getEndPage()+1}">NEXT</a></li>
			</c:if>
		</ul>
	</div>
	<!-- 페이징 -->
</div>
</div>
</html>