<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- link : css 설정 -->
<link rel="stylesheet" type="text/css" href="resources/css/sideBar.css">
<link rel="stylesheet" type="text/css" href="resources/css/organization.css">

<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>조직도</title>
</head>
<body>
<div class="wrapper" style="align-content: center">
	<jsp:include page="common/sideBar.jsp" flush="true" /><br>
	<jsp:include page="member/viewOrganization.jsp" flush="false"></jsp:include><br>
</div>
</body>
</html>