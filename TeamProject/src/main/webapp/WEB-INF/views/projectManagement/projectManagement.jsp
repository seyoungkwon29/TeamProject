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

<script defer="defer" src="resources/vue-calendar/js/chunk-vendors.11ee905a.js"></script>
<script defer="defer" src="resources/vue-calendar/js/app.8d675c34.js"></script>
<link href="resources/vue-calendar/css/app.a48133f8.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="resources/css/menu.css">

</head>
<body>
	<input type="hidden" name="tKey" value="${login.t_key}"/>
	<!-- 고정 메인 메뉴바 -->
	<jsp:include page="../common/menu.jsp" flush="true" />
	<br>
	<br>
	<br>
	<div id="app"></div>
</body>
</html>