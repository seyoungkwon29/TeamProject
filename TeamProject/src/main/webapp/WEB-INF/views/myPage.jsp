<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- link : css -->
<link rel="stylesheet" type="text/css" href="resources/css/sideBar.css">
<link rel="stylesheet" type="text/css" href="resources/css/myPage.css">

<title>마이페이지</title>
</head>
<body>
	<div>
		<div>
			<!-- 사이드바 -->
			<jsp:include page="common/sideBar.jsp" flush="true" /> <br>
		</div>
		<div>
			<jsp:include page="member/myPage.jsp" flush="true" /> <br>  <br>  <!-- 마이페이지 메뉴바 -->
		</div>
	</div>
</body>
</html>