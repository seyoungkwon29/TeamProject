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

<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.7/chosen.jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />
<link href="resources/vue-wbs/css/chunk-vendors.33d0c618.css" rel="stylesheet">
<link href="resources/vue-wbs/css/app.a785884f.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.7/chosen.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/menu.css">

<script defer="defer" src="resources/vue-wbs/js/chunk-vendors.4b9174c6.js"></script>
<script defer="defer" src="resources/vue-wbs/js/app.c0eb2f4c.js"></script>
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