<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/appMenu.css">
<meta charset="UTF-8">
<title>Insert title here</title>
 <style type="text/css">
 .selfMailFormContainer {
 	position: absolute;
 	top:150px;
	left: 400px;
 }
 
 </style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
<form action="sendSelfMailProcess" method="post" enctype="multipart/form-data" id="mailWriteForm">
		<div class="mailHeader">
			<div class="mailWriteMenu">
				<div>
					<button type="submit" style="width: 110px;">
						&nbsp;<strong>내게 쓰기</strong>
					</button>
					&nbsp;&nbsp; <a href="writeMail">메일 쓰기</a>&nbsp;&nbsp;
				</div>
			</div>
		</div>

		<div id="mailRegister">
			<div>
				<div>
					<strong>제목 </strong>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
					<input type="text" size="130" style="height: 30px"
						name="mail_title" id="mail_title">
				</div>
			</div>
			<br> <br>
			<div>
				<div>
					<strong>파일첨부 </strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="file" name="mail_file">
				</div>
			</div>
			<br> <br>
			<textarea rows="20" cols="150" name="mail_content" class="mail_content"></textarea>
		</div>
	</form>
</div>
	
</body>
</html>
