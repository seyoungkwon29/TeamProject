<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url var="menuCss" value="/resources/css/menu.css" htmlEscape="true"/>
<link rel="stylesheet" href="${menuCss}">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<spring:url var="menuJs" value="/resources/js/menu.js" htmlEscape="true"/>
<script src="${menuJs}"></script>
<div class="container">
	<div>
		<a class="brand" href="homePage">EVERYWARE</a>
	</div>	
	<div class="link-ul">
		<ul>
			<li><a class="link" aria-current="page" href="homePage">홈</a></li>
			<li><a class="link" href="myPage">마이 페이지</a></li>
			<li><a class="link" href="loginCheck/attendance/attendanceList">근태 관리</a></li>
			<li><a class="link" href="organization">조직도</a></li>
			<li><a class="link" href="<spring:url value="/notices"/>">공지사항</a></li>
			<li><a class="link" href="<spring:url value="/communities"/>">자유게시판</a></li>
			<li><a class="link" href="FileBoard">자료함</a></li>
			<li><a class="link" href="draftList">전자결재</a></li>
				<div class="dropdown">
					<a href="draftList?parameter=draft">기안 문서함</a>			
					<a href="draftList?parameter=app">결재 문서함</a>			
					<a href="draftList?parameter=temp">임시 저장함</a>			
				</div>
			<li><span class="link"><a href="meetingRoom">회의실</a></span>
				<div class="dropdown">
				<a href="meetingRoom">회의실 예약</a>			
				<a href="meetingRoomCheck">예약 확인</a>			
				</div>
			</li>
			<li><a class="link" href="chatting">메신저</a></li>
			
			<li><span class="link">메일</span>
				<div class="dropdown">
				<a href="writeMail">메일쓰기</a>			
				<a href="mailReceiveList">받은 메일함</a>
				<a href="mailSendList">보낸 메일함</a>
				<a href="mailSelfList">내게 쓴 메일함</a>
				</div>
			</li>
		</ul>
	</div>
	<div class="icon-container">
        <ul>
            <li><a class="icon" href="#"><img
                    src="resources/image/icon/alarm.png" alt="알람"></a></li>
            <li><a class="icon" href="#"><img
                    src="resources/image/icon/mail.png" alt="메일"></a></li>
            <li><a class="icon" href="loginCheck/logout"><img
                    src="resources/image/icon/logout.png" alt="로그아웃"></a></li>
        </ul>
    </div>
</div>
