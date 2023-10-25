<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회의실 예약 확인</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
	<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
</head>
<body>
	<jsp:include page="common/menu.jsp" flush="true" /> <br>
	<jsp:include page="meeting/meetingRoomCheck.jsp" flush="true" /> <br>  <!-- 회의실 예약확인 -->

</body>
</html>