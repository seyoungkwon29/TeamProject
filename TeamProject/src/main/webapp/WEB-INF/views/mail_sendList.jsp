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
<title>메일 발신함</title>

<style>
.mail_container {
    position: absolute;
    top: 180px;
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

.mail_list_box .mail_list {
 	line-height: 1.5;
	width: 1100px;

}
.mail_list_box .mail_list thead th {
	padding: 10px;
    text-align: center;
    color: #113626;
    width: 20%;
    border-bottom: 2px solid #e0e0e0;
}
.mail_list_box .mail_list .checkMail{
	width: 50px;
}
.mail_list_box .mail_list .read_mail{
	color: gray;
}
.mail_list_box .mail_list .new_mail{
	color:black;
	font-weight:700;
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
    background: rgb(27 29 28 / 5%);
}
.mail_list_box .mail_list tbody .mail_title{
	text-decoration: none;
    color: #413c3c;
}
.mail_list_box .mail_list tbody .mail_from_date{
    width: 160px;
}
</style>





<script>

    
</script>
</head>
<body>
<jsp:include page="common/menu.jsp" flush="true" />
    <div class="mail_container">
    <div style="margin-bottom: 50px;">
    	<span id="Mail_MenuTitle">보낸 메일</span>
    </div>
        <div class="mail_list_box">
            <table class="mail_list">
                <thead>
                    <tr>
                      <th scope="cols">
                      	&emsp;수신자
                      </th>
                      <th scope="cols" style="width: 40%;">제목</th>
                      <th>보낸 시간</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="mail" items="${sentMailList }" varStatus="status">
					<tr>
                      <td scope="row" class="sender">
                      	&emsp; ${firstRecMember.get(status.index).getDiv_name()}
                      	<c:if test="${recMemberCount.get(status.index) eq 0}">
                      		&nbsp;${firstRecMember.get(status.index).getMember_name()}
                      	</c:if>
                      	<c:if test="${recMemberCount.get(status.index) > 0}">
                      		&nbsp;${firstRecMember.get(status.index).getMember_name()} 
	                      	외 ${recMemberCount.get(status.index)}명
                      	</c:if>
                      </td>
                      <td style="width: 40%;"><a href="viewMail?mail_num=${mail.getMail_num()}" class="mail_title">${mail.getMail_title()}</a></td>
                      <td class="mail_from_date">${mail.getMail_from_date()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <jsp:include page="mail/sendListPaging.jsp"></jsp:include>
    </div>
    

    
</body>
</html>