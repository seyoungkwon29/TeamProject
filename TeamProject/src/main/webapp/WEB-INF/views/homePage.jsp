<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>

<script    src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />

<!-- link : css -->
<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
<link rel="stylesheet" type="text/css" href="resources/css/homePage.css">
</head>
<body>
<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<span>${login.t_key}</span>
    <!-- 고정 메인 메뉴바 -->
    <jsp:include page="common/menu.jsp" flush="true" />
    <jsp:include page="home/homePage.jsp" flush="true" />

</body>
</html>
