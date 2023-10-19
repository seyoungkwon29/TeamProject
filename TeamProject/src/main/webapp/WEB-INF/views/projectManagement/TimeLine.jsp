<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="icon" href="/favicon.ico">
<title>타임라인</title>

<script defer="defer" src="resources/vue-timeline/js/chunk-vendors.97895575.js"></script>
<script defer="defer" src="resources/vue-timeline/js/app.c84fe729.js  "></script>
<link href="resources/vue-timeline/css/chunk-vendors.0512666e.css" rel="stylesheet">
<link href="resources/vue-timeline/css/app.b52e56dd.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="resources/css/menu.css">

</head>
<body>
	<input type="hidden" name="tKey" value="${login.t_key}"/>
	<!-- 고정 메인 메뉴바 -->
	<jsp:include page="../common/menu.jsp" flush="true" />
	<div id="app"></div>
</body>
</html>

