<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>자료함</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
	<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
	<link rel="stylesheet" type="text/css" href="resources/css/fileBoardList.css">
</head>
<body>

	<jsp:include page="common/menu.jsp" flush="true" />
	<jsp:include page="fileboard/searchFileBoard.jsp" flush="true" /> <br>  <!-- 자료함목록-->

</body>
</html>