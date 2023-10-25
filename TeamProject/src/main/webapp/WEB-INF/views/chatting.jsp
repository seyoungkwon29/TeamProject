<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
	<link rel="stylesheet" type="text/css" href="resources/css/menu.css">
</head>
<body>
	<div>
		<div>
			<jsp:include page="common/menu.jsp" flush="true" /> <br> 
		</div>
		<div>
			<jsp:include page="chatting/chatting.jsp" flush="true" /> <br> <!-- 채팅창 -->
		</div>
	</div>
	
</body>
</html>