<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="resources/css/sideBar.css">
</head>
<body>
	<div>
		<div>
			<jsp:include page="common/sideBar.jsp" flush="true" /> <br>  <!-- 왼쪽 메뉴바 -->
		</div>
		<div>
			<jsp:include page="chatting/chatting.jsp" flush="true" /> <br> <!-- 채팅창 -->
		</div>
	</div>
	
</body>
</html>