<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/mailing.css" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
<script type="text/javascript">
$(function(){
var childWindow = null; //주소록 중복창 못띄우게 하기 위한 변수

function checkMail(){
	if($("#mail_receiver").val().length==0){
		event.preventDefault();
		alert("받는 사람을 입력하세요");
		} else if($("#mail_title").val().length==0){
			event.preventDefault();
			alert("메일 제목을 입력하세요");
		} else if($("#mail_content").val().length==0){
			event.preventDefault();
			alert("내용을 입력하세요");
		}
	}
	
$("#mailWriteForm").submit(checkMail);  //submit은 폼의 아이디를 가지고 처리한다.

//주소록 창 띄우기 , 이미 띄워진 경우, 자식창 포커스 및 새로고침
$(".btn_addressBook").click(function() {
	
	if(!childWindow || childWindow.closed){
		childWindow = window.open("mailAddressBook","","self,width=600, height=700");
		
	} else {
        childWindow.focus();
        childWindow.location.reload();
	}
	
})
	
});


</script>
<%
	// 회원가입 성공여부
	String msg = (String) session.getAttribute("msg");
	if (msg != null) {
%>
<script type="text/javascript">
		alert("<%=msg%>");
</script>
<%
		session.removeAttribute("msg"); // 사용된 경고창 삭제
	}
%>



</head>
<body>
<jsp:include page="common/menu.jsp" flush="true" />
<div class="mailFormContainer">

	<h1 style="margin: 0; color: #333; padding-bottom: 20px; font-size: 30px;">메일 쓰기</h1>

	<div class="mail-top-container">
	<form action="sendMailProcess" method="post" enctype="multipart/form-data" id="mailWriteForm">
		<div class="mailHeader">
				<div>
					<button type="submit" class="mail-send-btn">
						보내기
					</button>
					
					<button class="mail-sendMe-btn"><a href="writeSelfMail">내게 쓰기</a></button>
				</div>
		</div>

		<div id="mailRegister">
			<div class="top-title">
				<div>
					<div>
						<span class="span-style">받는 사람</span>
						<input type="text" size="100" class="input-mail" name="mail_receiver" id="mail_receiver" readonly="readonly">
						&nbsp;
						<button type="button" style="width: 80px" id="mail-add-btn" class="btn_addressBook">&nbsp;주소록</button>
					</div>
				</div>
				
				<div style="margin: 20px 0px 20px 0px;">
					<div>
						<span class="span-style">메일 제목 </span>
						<input type="text" class="input-mail-title" size="100" style="height: 23px"
							name="mail_title" id="mail_title">
					</div>
				</div>
				
				<div>			
					<div class="file-input-container">
						<span class="file-span">파일 첨부 </span>
						<input type="file" name="mail_file" id="mail-file">
					</div>
				</div>
			</div>
			
			<div class="div-content-span">
				<span class="content-span">내용</span>
			</div>
			
			<div class="div-mail-content">
				<textarea rows="20" cols="150" name="mail_content" class="mail_content" id="mail_content"></textarea>
			</div>
		</div>
	</form>
	</div>
</div>
</body>
</html>
