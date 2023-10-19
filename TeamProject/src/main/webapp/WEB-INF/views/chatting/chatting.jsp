<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css" href="resources/css/chatting.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="resources/js/chatting.js"></script>
<script src="resources/js/chattingRoom.js"></script>

<div class="c-container">
<div class="chatList">
        <div class="head">
            <p>채팅</p>
            <img src="resources/image/icon/test.png" id="addchat" class="toggleButton">
        </div>
    <div class="contents">
           	<c:forEach var="chat" items="${chatList}" varStatus="status">
	        <div class="contentsList" onclick="goRoom(${chat.chatroom_num})">
	            <div class="left">
    	            <c:if test="${chat.chatroom_type == 0}">
    	            <img class="chatImg" src="resources/image/icon/chat_user.png">
    	            </c:if>
    	            <c:if test="${chat.chatroom_type == 1}">
    	            <img class="chatImg" src="resources/image/icon/chat_users.png">
    	            </c:if>
        	    </div>
    	       <div class="center">
                	<span class="center-span">${chat.chatroom_title}</span>
                	<span>${latestChatList[status.index].chat_content}</span>
	           </div>
	            <div class="right">
	                <span>${fn:substring(latestChatList[status.index].chat_date, 0, 10)}</span>
	                <span>${fn:substring(latestChatList[status.index].chat_date, 11, 19)}</span>
	            </div>
        	</div>
        </c:forEach>
    </div>
</div>

<div class="invite">
    
    
    <div class="invite_center">  
   		<div class="head2"><p>사용자 초대 <span> &#x1F4EC;</span></p></div>
   		
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
                <span style="display: none">${member.member_num}</span>
            	<input type="checkbox" name="memberCheckbox" value="${member.div_name} ${member.member_name} ${member.rank}">
                </li>
            </c:forEach>
                </ul>
            </div>
            <div class="memberSelected">
            	<div id="selected" style="	margin-top: 3px; max-height: 55px; min-width: 200px; display: flex; flex-wrap: wrap;
				    						margin-bottom: 10px;">
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