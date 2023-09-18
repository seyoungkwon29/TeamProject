<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회의실 예약</title>
	<link rel="stylesheet" type="text/css" href="resources/css/main.css">
	<link rel="stylesheet" type="text/css" href="resources/css/appMenu.css">
	<link rel="stylesheet" type="text/css" href="resources/css/appDraftList.css">
</head>
<body>
	
	<jsp:include page="meeting/meeting_side.jsp" flush="true" /> <br>  <!-- 메뉴 + 회의실 예약 메뉴바 -->
	<jsp:include page="meeting/meetingRoom.jsp" flush="true" /> <br>  <!-- 회의실 예약하기 -->

</body>
</html>