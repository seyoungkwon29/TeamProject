<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="approval/all_sideBar.jsp" flush="true" /> <br>  <!-- 전체 + 결재 메뉴바 -->
	<jsp:include page="approval/draftList.jsp" flush="true" /> <br>  <!-- 기안 문서함 -->

</body>
</html>