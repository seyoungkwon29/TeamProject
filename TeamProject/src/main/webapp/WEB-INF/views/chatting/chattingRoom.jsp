<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/chattingRoom.css">
<link rel="stylesheet" type="text/css" href="resources/css/chattingModal.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="resources/js/chattingRoom.js"></script>
<script src="resources/js/chattingModal.js"></script>
<meta charset="UTF-8">
	<title>Chatting</title>
</head>
<body>
		<div id="room-container" class="room-container">
			<div class="r-header">
				<div class="h-top">
					<h4 id="room-title">${chatroom.chatroom_title}</h4>
				<div class="h-bottom">
				<div class="h-bottom-bind">
					<img src="resources/image/icon/user.png">
					<span>${countMember}</span>
			            <span class="tooltip-text">
		    	        <c:forEach var="mem" items="${cList}">
			    	        ${mem.div_name} ${mem.member_name} ${mem.rank}<br>
		            	</c:forEach>
		            	</span>
				</div>
				</div>
				</div>
				<div class="h-right">
					<img src="resources/image/icon/setting.png" id=r-setting>
				</div>
			</div>
			<div class="c-info">
				<p class="c-info-p">${createdDate}</p>
				<p class="c-info-p2">${createdMember}</p>
			</div>
			<div class="c-chatting">
				<input type="hidden" id="sessionId" value="">
				<input type="hidden" id="chatroom_num" value="${chatroom.chatroom_num}">
				<input type="hidden" id="member_num" value="${member.member_num}">
				<input type="hidden" id="member_name" value="${member.member_name}">
				<input type="hidden" id="div_name" value="${member.div_name}">
				<input type="hidden" id="rank" value="${member.rank}">
				
			<!-- 지난 날짜 표시 -->	
			<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="currentDate" />
			<c:set var="previousDate" value="" />
			<c:forEach var="contents" items="${contentsList}">
				 <c:set var="currentChatDate" value="${fn:substring(contents.chat_date, 0, 10)}" />
			    <!-- 첫 번째 항목이거나 이전 채팅의 날짜와 현재 채팅의 날짜가 다른 경우 -->
			    <c:if test="${(previousDate eq '') or (currentChatDate != previousDate)}">
			        <div class="chat-date">
			            <div class="chat-date-center">
			                <img src="resources/image/icon/calendar.png">
			                <p>${currentChatDate}</p>
			            </div>
			        </div>
			        <c:set var="previousDate" value="${currentChatDate}" />
			    </c:if>
					<!-- 초대, 나가기 공지 -->
					<c:if test="${contents.chat_type == 1 && contents.member_num != 0}">
					<div class="c-info">
					<p>${contents.chat_content}</p>
					</div>
					</c:if>
					
					<!-- 채팅 내용 -->
					<c:if test="${contents.chat_type == 0 && contents.member_num == member.member_num}">
					<p class='me'>
					<span class='time-r'>${fn:substring(contents.chat_date, 11, 19)}</span>
					<span class='my-msg'>${contents.chat_content}</span>
					</p>
					</c:if>
					<c:if test="${contents.chat_type == 0 && contents.member_num != member.member_num}">
					<div class='others'>
				    <span class='otherName'>${contents.div_name} ${contents.member_name} ${contents.rank}</span>
				    <div class="wrapper">
				        <span class='other-msg'>${contents.chat_content}</span>
				        <span class='time-l'>${fn:substring(contents.chat_date, 11, 19)}</span>
				    </div>
					</div>
					</c:if>
				</c:forEach>
				<div id="chatting">
				</div>
			</div>
		<div id="yourMsg">
			<table class="inputTable">
				<tr>
					<th><input id="sendChatting" placeholder="메시지를 입력하세요."></th>
					<th><button onclick="send()" id="sendBtn">전송</button></th>
				</tr>
			</table>
		</div>
	</div>
	
	<!--채팅방 설정 모달-->
	<div id="chatModal" class="modal-container">
    <div class="modal-content">
	    <div class="m-setting" id="title-setting">
	        <img class="modal-icon" src="resources/image/icon/title_setting.png">
	        <span class="modal-text">채팅방 이름 설정</span>
	    </div>
	     <div class="m-setting" id="invite-user">
	        <img class="modal-icon" src="resources/image/icon/invite.png">
	        <span class="modal-text">대화상대 초대</span>
	     </div>
		<div class="m-setting" id="exit">
	        <img class="modal-icon" src="resources/image/icon/logout.png">
	        <span class="modal-text">채팅방 나가기</span>
		</div>
    </div>
    </div>
    
    <!-- 채팅방 제목 설정 모달 -->
    <div id="titleModal">
	    <div class="t-modal-content">
	    <span style="font-size: 14px;">채팅방 이름을 입력해주세요</span>
			<input type="text" id="updateTitle">
		    <div class="t-button">
		    <button id="update">확인</button>
		    <button id="cancel">취소</button>
		    </div>
		</div>
	</div>
	
	<!-- 대화 상대 초대 모달 -->
	<jsp:include page="inviteModal.jsp" flush="true" />
</body>
</html>