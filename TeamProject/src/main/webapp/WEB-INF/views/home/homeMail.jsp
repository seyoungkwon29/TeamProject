<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<div class="left-panel">
    <div class="home-Mail-top">받은 메일함</div>
        
    <table class="home-Mail-List">
		<tr>
			<th class="th-title">제목</th>
			<th class="th-man">발신자</th>
			<th class="th-time">받은 시간</th>
		</tr>
		<c:forEach var="mail" items="${recMailList}" begin="0" end="2">
			<tr>
				<td><a href="viewMail?mail_num=${mail.getMail_num()}">${mail.mail_title}</a>
				</td> <!-- 제목-->
				
				<td>${memberDTOList.get(status.index).getMember_name()}</td> <!-- 기안자 -->
				
				<td>${mail.mail_from_date}</td> <!-- 기안일 -->							
			</tr>
		</c:forEach>
	</table>
</div>


<style>
.home-Mail-top {
    text-align: justify;
    font-size: 14px;
    letter-spacing: 0px;
    font-weight: 600;
    margin-left: 4px;
    margin-top: 13px;
    color: #000000de;
}
.home-Mail-List{
    margin: 18px 0;
    font-size: 13px;
    text-align: center;
    border-collapse: collapse;
    border-top: 2px solid rgb(200 200 200 / 49%);
    border-bottom: 2px solid rgb(200 200 200 / 49%);
}
.home-Mail-List tr {
    border-top: 1px solid rgb(200 200 200 / 49%);
    height: 42px;
}

.home-Mail-List tr:hover {
	background-color: rgb(250, 250, 250);
}

.home-Mail-List th {
	background-color: #f9f9f9;
}

.home-Mail-List .th-title { /* 테이블 헤더 부분 간격*/
	width: 430px;
}

.home-Mail-List .th-man {
	width: 160px;
}

.home-Mail-List .th-time { /* 임시저장함: 제목*/
	width: 280px; 
}

.home-Mail-List a {
	text-decoration: none;
	color: black;
	cursor: pointer;
}

.home-Mail-List span {
	padding: 5px 10px;
	border-radius: 4px;
	border: 1px;
	color: white;
}
</style>