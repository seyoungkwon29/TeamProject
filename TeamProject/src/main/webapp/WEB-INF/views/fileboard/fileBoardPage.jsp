<%@page import="com.dto.PageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> -->
<%-- ==========================<br>
${pageDTO} --%>
<div>
<div class="file-page-top">

	<c:if test="${pageDTO.isPrev()}">
		<a class="page-a" href="fileBoardList?page=${pageDTO.getStartPage()-pageDTO.getRangeSize()}">&laquo;</a> 
	</c:if>
	<c:forEach begin="${pageDTO.getStartPage()}" end="${pageDTO.getEndPage()}" varStatus="status">
		<a class="page-a"  href="fileBoardList?page=${status.index}">${status.index}</a>
	</c:forEach>
	<c:if test="${pageDTO.isNext()}">
		<a class="page-a" href="fileBoardList?page=${pageDTO.getEndPage()+1}">&raquo;</a>
	</c:if>
</div>
</div>
