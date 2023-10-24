<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="icon" href="/favicon.ico">
<title>프로젝트 관리</title>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />

<script defer="defer" src="resources/vue-calendar/js/chunk-vendors.677d6bb2.js"></script>
<script defer="defer" src="resources/vue-calendar/js/app.6c44de07.js"></script>
<link href="resources/vue-calendar/css/app.8ab73678.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="resources/css/menu.css">

</head>
<body>
	<input type="hidden" name="t_key" value="${login.t_key}"/>
	<!-- 고정 메인 메뉴바 -->
	<jsp:include page="../common/menu.jsp" flush="true" />
	<br>
	<br>
	<br>
	<div id="app"></div>
</body>
</html>