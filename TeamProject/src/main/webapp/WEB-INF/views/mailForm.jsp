<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.mailFormContainer {
	position: absolute;
	top:150px;
	left: 400px;
}

</style>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
	<form action="sendMailProcess" method="post" enctype="multipart/form-data" id="mailWriteForm">
		<div class="mailHeader">
			<div class="mailWriteMenu">
				<div>
					<button type="submit" style="width: 110px;">
						&nbsp;<strong>보내기</strong>
					</button>
					&nbsp;&nbsp; <a href="writeSelfMail">내게 쓰기</a>&nbsp;&nbsp;
				</div>
			</div>
			<br>
		</div>

		<div id="mailRegister">
			<div>
				<div>
					<strong>받는사람</strong>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 
					<input type="text" size="100" style="height: 30px" name="mail_receiver" id="mail_receiver" readonly="readonly">
					&nbsp;
					<button type="button" style="width: 80px" class="btn_addressBook">&nbsp;주소록</button>
					&nbsp;
				</div>
				<!-- <p id="mailReceiver"> -->
			</div>
			<br>
			<div>
				<div>
					<strong>제목 </strong>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
					<input type="text" size="100" style="height: 30px"
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
