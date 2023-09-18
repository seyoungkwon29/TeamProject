<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>회의실 예약 소메뉴</title>
	<link rel="stylesheet" type="text/css" href="resources/css/sideBar.css">
	<link rel="stylesheet" type="text/css" href="resources/css/appMenu.css">
</head>
<body>
	
	<div>
		<div>
			<jsp:include page="../common/sideBar.jsp" flush="true" /><br>  <!-- 왼쪽 메뉴바 -->
		</div>
		<div>
			<jsp:include page="meetingMenu.jsp" flush="true" /> <br>  <!-- 회의실 메뉴바 -->
		</div>
	</div>

</body>
</html>