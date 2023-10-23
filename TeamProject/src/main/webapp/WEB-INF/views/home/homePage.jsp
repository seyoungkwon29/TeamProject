<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="home" align="center">
	<h2>홈</h2>
	<br>
	<ul>
		<jsp:include page="homePhoto.jsp" flush="true"/>
		<jsp:include page="homeAttend.jsp" flush="true"/>			
		<jsp:include page="homeNoticeList.jsp" flush="true"/>	
		<jsp:include page="homeCommunityList.jsp" flush="true"/>	
		<jsp:include page="homeAppro.jsp" flush="true"/>	
	</ul>
</div>
