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
 	top:150px;
	left: 400px;
}

.mail_container .member {
    background-color: rgba(139, 184, 139, 0.4);
    border-radius: 1em;
    padding: 1px 5px 1px 5px;
    font-size: small;
}

#mail_title {
	font-size: 30px;
	font-weight: 700;
}
</style>
</head>

<body>
<jsp:include page="common/menu.jsp" flush="true" />
	<div class="mail_container">
        <div>
            <div>
                <span size="100" style="height: 30px"name="mail_from_date" id="mail_from_date"> 
                	${mailDTO.getMail_from_date()}
                </span>
				<br>
                <span size="100" style="height: 30px"name="mail_title" id="mail_title"> 
                	${mailDTO.getMail_title()}
                </span>
            </div>
        </div>
        <br>

		<div id="mailRegister">
			<div>
				<div>
					<strong>보낸사람 </strong>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; 
					<div size="100" style="height: 30px" name="mail_receiver" id="mail_sender">
						<span class="member">${mailSender.getDiv_name()}&nbsp;${mailSender.getMember_name()}&nbsp;${mailSender.getMail()}</span>	
					</div>
				</div>
                <div>
					<strong>받는사람 </strong>
					<div>
						<c:forEach var="mem" items="${RecMemberList}" varStatus="status">
						<span class="member" size="100" style="height: 30px" name="mail_receiver">${mem.getDiv_name()}&nbsp;${mem.getMember_name()}&nbsp;${mem.getMail()}</span>
						</c:forEach>
					</div>
				</div>
				<!-- <p id="mailReceiver"> -->
			</div>
			
			<div>
			<c:if test="${mailDTO.getMail_fileName() != null}">
				<div>
				<br><br>
				<strong>첨부파일 </strong> <br>
				<a href="/mailFileDownload?mail_num=${mailDTO.getMail_num()}">${mailDTO.getMail_fileName()}</a>
				</div>
			</c:if>
			</div>
			<br> <br>
			<textarea rows="20" cols="150" name="mail_content" class="mail_content" readonly>${mailDTO.getMail_content() }</textarea>
		</div>
		<button id="listBtn">목록보기</button>
		
	</div>
</body>
</html>
