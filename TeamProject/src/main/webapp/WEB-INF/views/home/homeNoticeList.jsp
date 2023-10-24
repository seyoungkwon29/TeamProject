<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.NoticeDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="left-panel" style="margin-top: 21px;">
	<!-- 좌측 패널 내용 -->
	<div class="top-notice">최근 공지사항</div>
	<table class="notice-List">
		<tbody>
			<c:forEach var="notice" items="${noticeList}">
				<spring:url var="noticeDetailsUrl" value="/notices/${notice.noticeNum}" />
				<tr>
					<td class="pv2 pr1 tl"  id="notice-td">
						<a href="${noticeDetailsUrl}"
						class="link-reset black dim">${notice.title}</a>
					</td>
					<td class="pv2 pr1 tc" id="notice-td2">${notice.memberName}</td>
					<td class="pv2 pr1 tc" id="notice-td2">
						<fmt:formatDate value="${notice.createdAt}" pattern="YYYY-MM-dd" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<style>
.top-notice {
	text-align: justify;
    font-size: 13px;
    letter-spacing: 0px;
    font-weight: 600;
    margin-left: 4px;
    margin-bottom: -8px;
    color: #000000de;
}
.notice-List {
	margin: 20px 0;
    font-size: 14px;
    text-align: left;
    border-collapse: collapse;
    border-top: 2px solid rgb(200 200 200 / 52%);
    border-bottom: 2px solid rgb(200 200 200 / 52%);
}

#notice-td {
    width: 390px;
    font-size: 13px;
    padding: 11px 11px 11px 13px;
}

#notice-td2{
	width: 85px; 
	color: #a49595a8;
	font-size: 13px;
}

#notice-td a, #notice-td2 a {
	text-decoration: none;
	color: black;
}


</style>