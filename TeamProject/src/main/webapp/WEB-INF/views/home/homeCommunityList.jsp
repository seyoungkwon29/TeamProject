<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.dto.NoticeDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="left-panel">
		<!-- 좌측 패널 내용 -->

		<table class="t-List">
			<thead>
				<tr>
					<th class="th-1">글번호</th>
					<th class="th-2">제목</th>
					<th class="th-1">작성자</th>
					<th class="th-1">작성일</th>
					<th class="th-1">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="community" items="${communityList}">
					<spring:url var="communityDetailsUrl" value="/communities/${community.comNum}" />
					<tr>
						<th class="pv2 pr1 tl"><a href="${communityDetailsUrl}"
							class="link-reset black dim">${community.comNum}</a></th>
						<td class="pv2 pr1 tl"><a href="${communityDetailsUrl}"
							class="link-reset black dim">${community.title}</a></td>
						<td class="pv2 pr1 tc">${community.memberName}</td>
						<td class="pv2 pr1 tc"><fmt:formatDate
								value="${community.createdAt}" pattern="YYYY년 MM월 dd일 hh:mm" /></td>
						<td class="pv2 pr1 tc">${community.views}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>