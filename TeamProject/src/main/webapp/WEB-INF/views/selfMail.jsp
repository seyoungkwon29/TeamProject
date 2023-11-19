<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/appMenu.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/selfMail.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
<script type="text/javascript">

$(function(){
	
function checkMail(){
	if($("#mail_title").val().length==0){
			event.preventDefault();
			alert("메일 제목을 입력하세요");
		} else if($("#mail_content").val().length==0){
			event.preventDefault();
			alert("내용을 입력하세요");
		}
	}
	
$("#mailWriteForm").submit(checkMail);  //submit은 폼의 아이디를 가지고 처리한다.
	
	
});


</script>




</head>
<body>
<jsp:include page="common/menu.jsp" flush="true" />
<div class="selfMailFormContainer">
<form action="sendMail" method="post" enctype="multipart/form-data" id="mailWriteForm">
		<h1 style="margin: 0;color: #333; padding-bottom: 20px; font-size: 30px;">내게 쓰기</h1>
		
		<div style="border:1px solid #8080806b;">
		<div class="mailHeader">
			<div class="mailWriteMenu">
				<div>
					<button type="submit" class="send-me-mail">
						내게 보내기
					</button>
					<button class="go-mail-btn"><a href="writeMail">메일 쓰기로 가기</a></button>
				</div>
			</div>
		</div>

		<div id="mailRegister">
			<div>
				<div>
					<span class="span-style">메일 제목 </span>
					<input type="text" name="mail_title" id="mail_title" style="	height: 24px;
					    width: 700px;border: 1px solid #a7a0a09e; font-size: 12px; background-color: rgb(41 119 22 / 19%);
					    border-radius: 1em; padding-left: 15px;">
				</div>
			</div>
			<br>
			<div>
				<div>
					<span class="span-style">파일 첨부 </span>
					<input type="file" name="mail_file">
				</div>
			</div>
			<br>
			<textarea rows="20" cols="150" name="mail_content" class="mail_content"></textarea>
		</div>
		</div>
	</form>
</div>
	
</body>
</html>
