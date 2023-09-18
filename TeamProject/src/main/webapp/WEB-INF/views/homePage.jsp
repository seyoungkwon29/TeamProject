<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>

<!-- link : css -->
<link rel="stylesheet" type="text/css" href="resources/css/sideBar.css">
<link rel="stylesheet" type="text/css" href="resources/css/homePage.css">

</head>
<body>
	<!-- 고정 메인 메뉴바 -->
	<jsp:include page="common/sideBar.jsp" flush="true" /><br>
	<jsp:include page="member/homePage.jsp" flush="true"></jsp:include>
</body>
</html>