<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="home" align="center">
	<h2>홈</h2>
	<br>
	<ul>
		<li>프로필사진
		<jsp:include page="homePhoto.jsp" flush="true"/>
		</li>
		<li>근태 관리
		<jsp:include page="homeAttend.jsp" flush="true"/>			
		</li>
		<li>공지사항</li>
		<jsp:include page="homeNoticeList.jsp" flush="true"/>	
		<li>게시판</li>
		<jsp:include page="homeCommunityList.jsp" flush="true"/>	
		<li>전자결재</li>
		<jsp:include page="homeAppro.jsp" flush="true"/>	
		<li> <a href="calendar">캘린더</a> </li>
		<li> <a href="loginCheck/logout">로그아웃</a> </li>
	</ul>
</div>
