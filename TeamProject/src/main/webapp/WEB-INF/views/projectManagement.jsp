<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 관리</title>

<link rel="stylesheet" type="text/css" href="resources/css/menu.css">

<title>프로젝트 관리</title>
</head>
<body>
	<div>
		<jsp:include page="common/menu.jsp" flush="true" />
	</div>
	<div>
		<jsp:include page="project/projectManagement.jsp" flush="false" />
	</div>
</body>
</html>