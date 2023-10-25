<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
</head>
<body>

	<jsp:include page="common/menu.jsp" flush="true" /> <br>  <!-- 전체 메뉴바 -->
	<jsp:include page="approval/tempList.jsp" flush="true" /> <br>  <!-- 임시저장함 -->

</body>
</html>