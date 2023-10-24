<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- link : css -->
<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
<link rel="stylesheet" type="text/css" href="resources/css/myPage.css">

<title>마이페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
</head>
<body>
	<jsp:include page="common/menu.jsp" flush="true" />
	<jsp:include page="member/myPage.jsp" flush="true" />
</body>
</html>