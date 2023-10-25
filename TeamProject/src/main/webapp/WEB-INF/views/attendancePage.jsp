<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근태 관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
	<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
	<link rel="stylesheet" href="resources/css/attendancePage.css">
</head>
<body>
	<jsp:include page="common/menu.jsp" flush="true" /><br>
	<jsp:include page="attendance/attendancePage.jsp" flush="true"></jsp:include>
</body>
</html>