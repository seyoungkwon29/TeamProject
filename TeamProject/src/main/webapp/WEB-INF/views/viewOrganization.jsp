<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- link : css 설정 -->
<link rel="stylesheet" type="text/css" href="resources/css/sideBar.css">

<title>조직도</title>
</head>
<body>
<div class="wrapper" style="align-content: center">
	<jsp:include page="common/sideBar.jsp" flush="true" /><br>
	<jsp:include page="member/viewOrganization.jsp" flush="false"></jsp:include><br>
</div>
</body>
</html>