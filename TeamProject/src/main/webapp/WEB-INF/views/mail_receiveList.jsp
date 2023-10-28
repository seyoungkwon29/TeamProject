<%@page import="com.dto.MailDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/appMenu.css">
<meta charset="UTF-8">
<title>메일 수신함</title>

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="common/liveNotification.jsp" flush="true" />
<script type="text/javascript">
$(document).ready(function() {
	$("#checkAll").click(function(){
		if($("#checkAll").prop("checked")){
			$(".checked_mail").prop("checked",true);
		} else {
			$(".checked_mail").prop("checked",false);
		}
	})
	
	$("#deleteBtn").click(function() {

	}) //end StrList
	
	
	$("#deleteBtn").click(function() {
		var temp = $(".checked_mail:checked");
		var list = []
		for(var i=0; i<temp.length; i++){
			list.push(temp[i].value);
		}		
		
		$.ajax({
		type: "post", //서버에 get/post방식 요청
		url: "deleteMail",//서버 요청 주소
		traditional: true,
		data: {
			"list":list,
		},
		success: function(data,status,xhr) { //요청에 대한 응답이 성공했을 때 동작할 코드
			console.log(data);
			window.location.reload();
		},
		error: function(xhr,status, e){ //요청에 대한 응답이 error인 경우에 동작할 코드
			
			console.log(e);
		}//error	
	})//end ajax
	})
	
	
});//end function

</script>

<style>
.mail_container {
    position: absolute;
    top: 150px;
    left: 325px;
}


#Mail_MenuTitle {
    font-size: 30px;
    font-weight: 700;
}

.mail_list {
    margin: 20px 0;
    font-size: 14px;
    text-align: center;
    border-collapse: collapse;
    border-top: 2px solid rgb(200, 200, 200);
    border-bottom: 2px solid rgb(200, 200, 200);
    width: 1100px;
}

.mail_list_box{
	line-height: 1.5;
	width: 1100px;
}
.new_mail td, .read_mail td{
	padding: 10px;
    text-align: center;
    border-bottom: 1px solid #e0e0e0;
    width: 26%;
}
.mail_list_box .mail_list thead th {
	padding: 10px;
    text-align: center;
    color: #113626;
    border-bottom: 2px solid #e0e0e0;
}

.mail_list_box .mail_list .checkMail{
	width: 50px;
}
.mail_list_box .mail_list .read_mail{
	color: gray;
}
.mail_list_box .mail_list .new_mail{
    color: black;
    font-weight: 600;
}
.mail_list_box .mail_list td {
  padding: 10px;
  vertical-align: top;
  border-bottom: 1px solid #ccc;
}
.mail_list_box .mail_list tbody .checkMail{
	text-align: center;
}
.mail_list_box .mail_list tbody .sender {
    background: rgb(21 23 22 / 5%);
}
.mail_list_box .mail_list tbody .mail_title{
    width: 500px;
    text-decoration: none;
}
.mail_list_box .mail_list tbody .mail_from_date{
    width: 160px;
}
.delete-btn {
    background-color: #c9cdce21;
    color: #323030c9;
    border: 1px solid #80808075;
    padding: 8px 15px;
    cursor: pointer;
    border-radius: 8px;
    font-size: 12px;
    font-weight: bold;
}
.delete-btn:hover{
    color: #000;
    background: rgba(230, 230, 230, 0.25);
    border: 2px solid #21875a;
    font-weight: bold;
}

</style>

</head>
<body>
<jsp:include page="common/menu.jsp" flush="true" />
    <div class="mail_container">
    <div style="margin-bottom: 70px;">
    	<span id="Mail_MenuTitle">받은 메일</span>
    </div>

	<div>
		 	<button id="deleteBtn" class="delete-btn">삭제</button>
	</div>

 
        <div class="mail_list_box">
            <table class="mail_list">
                <thead>
                    <tr>
                      <th style="width: 10%;">
                      	<input type="checkbox" id="checkAll">
                      </th>
                      <th scope="cols" style="width: 15%;">
                      	발신자
                      </th>
                      <th>읽음</th>
                      <th scope="cols" style="width:40%;">제목</th>
                      <th>보낸 시간</th>

                    </tr>
                </thead>
                <tbody>
                <c:forEach var="mail" items="${recMailList}" varStatus="status">
					<c:if test="${mailRecDTOList.get(status.index).getRec_status() eq'Y'}">
                     	<tr class="read_mail">
							<td style="width: 10%;">
								<input type="checkbox" class="checked_mail" id="chkMailNum${mail.getMail_num()}" name="chkMail" value="${mail.getMail_num()}">
							</td>
							<td scope="row" class="sender" style="width: 15%;">
								${memberDTOList.get(status.index).getDiv_name()}
								&nbsp;${memberDTOList.get(status.index).getMember_name()}
							</td>
							<td class="checkMail"><img alt="읽음" src="resources/image/mail2.png" height="18px" width="18px"></td>
							<td style="width:40%;"><a href="viewMail?mail_num=${mail.getMail_num()}" class="mail_title" style="color:gray">${mail.getMail_title()}</a></td>
							<td class="mail_from_date">${mail.getMail_from_date()}</td>
						</tr>
                    </c:if>
                    <c:if test="${mailRecDTOList.get(status.index).getRec_status() eq'N'}">
                     	<tr class="new_mail">
                     		<td style="width: 6%;">
								<input type="checkbox" class="checked_mail" id="chkMailNum${mail.getMail_num()}" name="chkMail" value="${mail.getMail_num()}">
							</td>
							<td scope="row" class="sender" style="width: 15%;">
								${memberDTOList.get(status.index).getDiv_name()}
								&nbsp;${memberDTOList.get(status.index).getMember_name()}
							</td>
							
							<td class="checkMail"><img alt="안 읽음" src="resources/image/mail1.png" height="14px" width="18px"></td>
							<td style="width:40%;"><a href="viewMail?mail_num=${mail.getMail_num()}" class="mail_title" style="color:black">${mail.getMail_title()}</a></td>
							<td class="mail_from_date">${mail.getMail_from_date()}</td>
						</tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
       
        <jsp:include page="mail/receiveListPaging.jsp"></jsp:include>
    </div>
    <div>
    	
    </div>
    

    
</body>
</html>