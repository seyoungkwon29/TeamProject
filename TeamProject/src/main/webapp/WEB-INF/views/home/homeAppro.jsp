<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<div class="left-panel">
    <div class="home-app-top">결재 대기 문서</div>
      
    <table class="home-App-List">
		<tr>
			<th class="th-title">제목</th>
			<th class="th-man">기안자</th>
			<th class="th-time">기안일</th>
		</tr>

		<c:forEach var="app" items="${appDocList}" begin="0" end="2">
			<tr>
				<td><a href="clickDocContent?type=app&docNo=${app.doc_no}&docStatus=${app.app_status}">
					${app.doc_title}</a>
				</td> <!-- 제목-->
				
				<td>${app.member_name}</td> <!-- 기안자 -->
				
				<td>${app.doc_date }</td> <!-- 기안일 -->							
			</tr>
		</c:forEach>
	</table>
</div>

<style>
.home-app-top {
    text-align: justify;
    font-size: 14px;
    letter-spacing: 0px;
    font-weight: 600;
    margin-left: 4px;
    margin-top: 13px;
    color: #000000de;
}
.home-App-List{
    margin: 18px 0;
    font-size: 13px;
    text-align: center;
    border-collapse: collapse;
    border-top: 2px solid rgb(200 200 200 / 49%);
    border-bottom: 2px solid rgb(200 200 200 / 49%);
}
.home-App-List tr {
    border-top: 1px solid rgb(200 200 200 / 49%);
    height: 42px;
}

.home-App-List tr:hover {
	background-color: rgb(250, 250, 250);
}

.home-App-List th {
	background-color: #f9f9f9;
}

.home-App-List .th-title { /* 테이블 헤더 부분 간격*/
	width: 430px;
}

.home-App-List .th-man {
	width: 160px;
}

.home-App-List .th-time { /* 임시저장함: 제목*/
	width: 280px; 
}

.home-App-List a {
	text-decoration: none;
	color: black;
	cursor: pointer;
}

.home-App-List span {
	padding: 5px 10px;
	border-radius: 4px;
	border: 1px;
	color: white;
}
</style>