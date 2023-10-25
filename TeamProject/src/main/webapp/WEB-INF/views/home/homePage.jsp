<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="home" align="center">
	<h2>홈</h2>
	<br>
	<ul>
		<ol>프로필사진
		<jsp:include page="homePhoto.jsp" flush="true"/>
		</ol>
		<ol>근태 관리
		<jsp:include page="homeAttend.jsp" flush="true"/>			
		</ol>
		<%-- <ol>공지사항</ol>
		<jsp:include page="homeNoti.jsp" flush="true"/>	 --%>
		<ol>게시판</ol>
		<jsp:include page="homeBored.jsp" flush="true"/>	
		<ol>전자결재</ol>
		<jsp:include page="homeAppro.jsp" flush="true"/>
		<ol> <a href="calendar">캘린더</a> </ol>
		<ol> <a href="loginCheck/logout">로그아웃</a> </ol>
	</ul>
		<span>${login.t_key}</span>
</div>
