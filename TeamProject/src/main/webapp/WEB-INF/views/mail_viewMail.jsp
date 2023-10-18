<%@page import="com.dto.MailDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>	
<script type="text/javascript">
	$(document).ready(function(){
			
		$("#listBtn").click(function(){
			//뒤로가기 새로고침
			location.href = document.referrer;
		})
		
		
	})
</script>

<html>
<head>
<meta charset="UTF-8">
<title>메일 보기</title>
<link rel="stylesheet" type="text/css" href="resources/css/appMenu.css"/>
<style>
.mail_container {
	position: absolute;
    top: 120px;
    left: 500px;
    width: 750px;
}
.mail-header{
	border-bottom: 1px solid #8080803d;
    padding: 20px;
}
.mail_container .member {
	background-color: rgb(41 119 22 / 19%);
    border-radius: 1em;
    padding: 5px 20px;
    font-size: 12px;
}
.mail-title {
	font-size: 23px;
}
.div-mail-mem, .div-mail-mem2 {
	 display: flex;
	 align-items: center;
	 margin-top: 15px;
}
.mail-member{
	font-size: 14px;
    font-weight: 900;
    margin-right: 30px;
    letter-spacing: -1px;
}
.send-time{
	font-size: 13px;
    font-weight: 600;
    margin-right: 30px;
    height: 30px;
    color: #8080807a;
}
.div-mail_content{
	padding: 25px 20px;
    border-bottom: 3px solid #80808066;
}
.mail_content{
    width: 650px;
    height: 250px;
    border-radius: 2px;
    border: 1px solid #ffffff59;
}
.div-listBtn{
    padding: 12px 0px 15px 10px;
    /* border-top: 3px solid #8080804a; */
    border-bottom: 3px solid #8080804a;
}
.listBtn{
    background-color: #c9cdce21;
    color: #000;
    border: 1px solid #80808075;
    padding: 7px 20px;
    cursor: pointer;
    border-radius: 8px;
    font-size: 12px;
    font-weight: bold;
    margin-left: 13px;
    letter-spacing: 1px;
}
.listBtn:hover {
    color: #000;
    background: rgba(230, 230, 230, 0.25);
    border: 2px solid #21875a;
    font-weight: bold;
}
.div-file {
	margin: 3px 0px;
}
.mail-file {
 	font-size: 14px;
    font-weight: 900;
    letter-spacing: -1px;
    padding: 0px 0px 10px 12px;
}
.a-file {
    margin-left: 30px;
	text-decoration: none;
	color: rgb(41 119 22 / 56%);
	letter-spacing: 2px;
}
.div-content-span {
    padding: 10px;
    border-bottom: 1px solid #8080803b;
    text-align: center;
    background: #8080800d;
}
.content-span {
    font-size: 13px;
    font-weight: 900;
    letter-spacing: 20px;
    color: #311616e3;
}
.textArea {
    letter-spacing: 1px;
    color: #2a2828f5;
    border: 1px solid #ffffff1a;
    width: 700px;
    height: 240px;
}
</style>
</head>

<body>
<jsp:include page="common/menu.jsp" flush="true" />
	<div class="mail_container">
		<div class="div-listBtn">
			<button id="listBtn" class="listBtn">목록 보기</button>
		</div>
		
	<div class="mail-top-header">
		<div class="mail-header">
		        <div style="padding-bottom: 15px;">
		            <span size="100" name="mail_title" id="mail_title" class="mail-title"> 
		            	${mailDTO.getMail_title()}
		            </span>
		        </div>

				<div class="div-mail-mem">
					<span class="mail-member">보낸 사람 </span>
					<div size="100" style="height: 30px" name="mail_receiver" id="mail_sender">
						<span class="member">${mailSender.getDiv_name()}&nbsp;${mailSender.getMember_name()}&nbsp;${mailSender.getMail()}</span>	
					</div>
				</div>
                <div class="div-mail-mem2">
					<span class="mail-member">받는 사람 </span>
					<div>
						<c:forEach var="mem" items="${RecMemberList}" varStatus="status">
						<span class="member" size="100" style="height: 30px" name="mail_receiver">${mem.getDiv_name()}&nbsp;${mem.getMember_name()}&nbsp;${mem.getMail()}</span>
						</c:forEach>
					</div>	         
				</div>
				<div style="margin-top: 18px;">
					<span size="100" class="send-time" name="mail_from_date" id="mail_from_date"> 
	                		${mailDTO.getMail_from_date()}
	           		</span>
				</div>
				<!-- <p id="mailReceiver"> -->
			</div>

		
			<div>
				<c:if test="${mailDTO.getMail_fileName() != null}">
				<div style="border-bottom: 1px solid #8080803d; padding: 10px;">
					<div class="div-file">
						<span class="mail-file">첨부 파일 <span>
						<a class="a-file" href="/mailFileDownload?mail_num=${mailDTO.getMail_num()}">${mailDTO.getMail_fileName()}</a>
					</div>
				</div>
				</c:if>
			</div>
			
			<div class="div-content-span">
				<span class="content-span">내용</span>
			</div>
		
			<div class="div-mail_content">
				<textarea class="textArea" rows="20" cols="150" name="mail_content" class="mail_content" readonly>${mailDTO.getMail_content() }</textarea>
			</div>
	
	</div>		
</div>
</body>
</html>
