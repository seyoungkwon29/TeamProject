<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- link : css 설정 -->
<link rel="stylesheet" href="resources/css/loginForm.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
</head>
<body>
<div class="wrapper" style="align-content: center">
	<jsp:include page="member/loginForm.jsp" flush="false"></jsp:include><br>
</div>
</body>
</html>