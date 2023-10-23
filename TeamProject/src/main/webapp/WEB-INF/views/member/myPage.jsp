<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dto.MemberDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%   if (session.getAttribute("mesg") != null) { %>
		<script type="text/javascript">
			alert("${mesg}");
		</script>
<% session.removeAttribute("mesg"); } %>
 
<% 
	MemberDTO dto = (MemberDTO)session.getAttribute("login"); 
	int member_num = dto.getMember_num();
	String member_name = dto.getMember_name();
	String div_name = dto.getDiv_name();
	String rank = dto.getRank();
	String address = dto.getAddress();
	String phone = dto.getPhone();
	String mail = dto.getMail();
	String hire_date = dto.getHire_date();
	String retire_date = dto.getRetire_date();
	String ssn = dto.getSsn();
	String password = dto.getPassword();
	String gender = dto.getGender();
	String photo =dto.getPhoto();
	int annual_leave = dto.getAnnual_leave();
%>     
<link href="resources/css/pwchange.css" rel="stylesheet">
<link href="resources/css/photochange.css" rel="stylesheet">
	
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	$(function() {
		   
		$(".close").click(function(){
			$("#pwchange").fadeOut(); /*클릭시, 모달창이 꺼짐*/
	    });//모달창위에 x
		
		
		$("#pw_change").click(function() {
	 		$("#pwchange").fadeIn(); /*클릭시, 모달창이 나옴*/
		});//클릭시 모달시작
		
	//이메일 주소 입력방식? 
		$("#mail").blur(function(){
			console.log("dddddd");
		var emailInput = document.getElementById("mail");
		   var email = this.value;
           var isValid = validateEmail(email);
			
           function validateEmail(email) {
               // 이메일 유효성을 검사하는 정규 표현식
               var regex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
               return regex.test(email);
           }
           
           if (!isValid) {
               alert("유효한 이메일 주소를 입력하세요.");
               this.value = "";
           }
       });
	
	$("#profile_pic").click(function() {
 		$("#photochange").fadeIn(); /*클릭시, 모달창이 나옴*/
	});//클릭시 모달시작

	
	$(".close").click(function(){
		$("#photochange").fadeOut(); /*클릭시, 모달창이 꺼짐*/
    });//모달창위에 x
	
    //휴대폰 입력 방식
    document.getElementById("phone").addEventListener("input", function() {
        var input = this.value.replace(/\D/g, ""); // 숫자만 남기도록 정규식 사용
        var formatted = input.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3"); // 하이픈 추가
        this.value = formatted;
        console.log("버노잘들어갔나??")
    });
	
});//윈도우 end
</script>

<div class="mypage-container">
	<form action="loginCheck/update" method="post">
	<input type="hidden" value="<%=member_num%>" " name="member_num">
		<h1 style="margin: 0; color: #333; font-size: 30px; padding-bottom:60px;">마이 페이지</h1>

		<div class="div-table-top" style="width: 100%;">
			<table class="top-table">
			  <tbody><tr>
			    <td class="tr-left">프로필 사진</td>
			    <td class="tr-right">
		    	 	
				     <img class="mem-img" src="resources/memberphoto/<%=dto.getMember_num()%>.png" width="230" height="auto" /><br><br>
<%-- 						<img class="mem-img" src="/profilepic/<%=dto.getPhoto()%>"><br><br>					     --%>
	        		<a class="prof-button" id="profile_pic">프로필사진 변경</a> &nbsp;&nbsp;
	       			<a class="prof-button" id="pw_change">비밀번호 변경</a>
	     		</td>
			  </tr>
			  <tr>
			  	<td class="tr-left"> 이름 </td>
			  	<td class="tr-right"> 			
					<span class="member-name"><%=dto.getMember_name()%></span>&nbsp;&nbsp;
					<span class="mem-rank"><%=dto.getRank()%></span>
			   </td>
			  </tr><tr>
	        
	          </tr><tr>
	            <td class="tr-left">사번</td>
	            <td class="tr-right"><%=dto.getMember_num()%></td>
	          </tr>
	          <tr>
	            <td class="tr-left">소속부서</td>
	            <td class="tr-right"><%=dto.getDiv_name()%></td>
	          </tr>
	          <tr>
	            <td class="tr-left">주소</td>
	            <td class="tr-right"><%=dto.getAddress()%></td>
	          </tr>
	          <tr>
	            <td class="tr-left">휴대폰번호</td>
	            <td class="tr-right"><input type="text" value="<%=phone%>" name="phone" id="phone" maxlength="11" class="input-phone-mail"></td>
	          </tr>
	          <tr>
	            <td class="tr-left">메일주소</td>
	            <td class="tr-right"><input type="email" value="<%=mail%>" name="mail" id="mail" class="input-phone-mail"></td>
	          </tr>
	          <tr>
	            <td class="tr-left">입사일</td>
	            <td class="tr-right"><%=dto.getHire_date()%></td>
	          </tr>
	     </tbody></table>
		 </div>
	
		 <div class="mypage-footer">
			<input class="mypage-footer-btn" type="submit" value="수정">
			<input class="mypage-footer-btn" type="reset" value="취소">
		 </div>
	</form>
</div>


<!-- The Modal 포함-->
<jsp:include page="photochangeModal.jsp" flush="true"/>
<jsp:include page="pwchangeModal.jsp" flush="true"/>
