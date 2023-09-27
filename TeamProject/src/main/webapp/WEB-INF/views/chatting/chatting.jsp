<%@page import="java.util.List"%>
<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<link rel="stylesheet" type="text/css" href="resources/css/chatting.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="resources/js/chatting.js"></script>
</head>
<body>
<div class="c-container">
<div class="chatList">
        <div class="head">
            <p>채팅방 목록</p>
        </div>
    <div class="contents">
        <div class="contentsList">
            <div class="left">
                <img class="chatImg" src="resources/image/chat.jpg">
            </div>
            <div class="center">
            <!--  onclick="chatPop(${chatRoom.chatRoomNum})" -->
                <span>채팅방1</span>
                <span>채팅내용</span>
            </div>
            <div class="right">
                <span>2023-11-11</span>
                <span>오후 2:50</span>
            </div>
        </div>
    </div>
    <div class="chatbtn">
        <button id="create">채팅방 생성</button>
        <button id="exit">채팅방 나가기</button>
    </div>
</div>

<div class="invite">
    <div class="head"><p>사용자 초대&#x1F4EC;</p></div>
    <div class="invite_center">
        <div class="invite_L">
            <div class="select">
                <select name="searchCondition" class="searchCondition">
                    <option value="all">전체</option>
					<option value="division">부서</option>
					<option value="memberName">이름</option>
                </select>
                <input type="text" value="검색" class="searchValue">
            </div>
            <div class="memberList">
                <ul>
            <c:forEach var="member" items="${memberList}" varStatus="loop">
            	<li>
                ${member.div_name} ${member.member_name} ${member.rank}
                <Span style="display: none">${member.member_num}</Span>
            	<input type="checkbox" name="memberCheckbox" value="${member.div_name} ${member.member_name} ${member.rank}">
                </li>
            </c:forEach>
                </ul>
            </div>
            <div class="memberSelected">
            	<div id="selected">
            	</div>
            </div>
        </div>
        <div class="invite_R">
            <div class="R_top">
                <input type="text" value="채팅방 제목" id="chatTitle"><br>
            </div>
            <div class="R_bottom">
                <button id="create2">채팅방 생성</button>
            </div>
        </div>
    </div>
</div>
</div>
	<script>
		// 채팅 팝업
		function chatPop(chatroom_num) {
			var url = "openChatRoom?chatroom_num=" + chatroom_num;
			var options = "width = 500px, height = 700px";
		    window.open(url, "", options); // 팝업 이름을 "" 빈값으로 둬야 다른 채팅창을 눌러도 새창으로 열림
		}
	</script>
</body>
</html>