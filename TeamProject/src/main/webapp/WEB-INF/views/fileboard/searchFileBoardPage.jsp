<%@page import="com.dto.PageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport">
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->
<%	
String searchField = request.getParameter("searchField");                                
String searchVal = request.getParameter("searchVal");        
%>

<%
System.out.println("searchFileBoardPage>>>>>>>>>>>>F "+searchField);
System.out.println("searchFileBoardPage>>>>>>>>>>>>V "+searchVal);
%>
<div>
<div class="file-page-top">

	<c:if test="${pageDTO.isPrev()}">
		<a
			href="searchFileBoard?searchField=<%=searchField%>&searchVal=<%=searchVal%>&page=${pageDTO.getStartPage()-pageDTO.getRangeSize()}"
			class="page-a">&laquo;</a>
	</c:if>
	<c:forEach begin="${pageDTO.getStartPage()}"
		end="${pageDTO.getEndPage()}" varStatus="status">
		<a class="page-a" href="searchFileBoard?searchField=<%=searchField%>&searchVal=<%=searchVal%>&page=${status.index}">${status.index}</a>
	</c:forEach>
	<c:if test="${pageDTO.isNext()}">
		<a class="page-a" href="searchFileBoard?searchField=<%=searchField%>&searchVal=<%=searchVal%>&page=${pageDTO.getEndPage()+1}">&raquo;</a>
	</c:if>
</div>
</div>