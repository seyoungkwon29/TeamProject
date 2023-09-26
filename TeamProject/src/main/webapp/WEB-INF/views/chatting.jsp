<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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