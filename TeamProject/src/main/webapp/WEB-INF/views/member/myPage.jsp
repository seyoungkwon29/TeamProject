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

<!-- 마이페이지 시작 -->
<h2>나의 정보</h2>
<br>
<br>
<form action="loginCheck/update" method="post">
	<div class="mypage-section">
		<div class="mypage-image">
			<img src="/profilepic/<%=dto.getPhoto()%>"
				width="250" height="auto"><br> <br> <!--server.xml에서 /profilepic경로 지정해줌ㄴ -->
				<a class="profile_pic" id="profile_pic">프로필사진변경</a><br> 
				<a class="pw_change" id="pw_change">비밀번호변경</a>
		</div>
		<div class="mypage-profile">

			<div class="horizontal-container">
				<h1 class="member-name"><%=dto.getMember_name()%></h1>&nbsp;
				&nbsp; <span class="rank"><%=dto.getRank()%></span>
			</div>
			<input type="hidden" value="<%=member_num%>" " name="member_num">
			
			<div class="link_set"> 사번&nbsp;:&nbsp; <%=dto.getMember_num()%></div><br> 
			<div class="link_set">소속부서&nbsp;:&nbsp;  <%=dto.getDiv_name()%></div><br>
			<div class="link_set">주소&nbsp;:&nbsp; <%=dto.getAddress()%></div><br> 
			<div class="link_set">휴대폰번호&nbsp;:&nbsp; <input type="text" value="<%=phone%>" name="phone" id="phone"  maxlength="11"></div><br>
			<div class="link_set">메일주소&nbsp;:&nbsp; <input type="email" value="<%=mail%>" name="mail" id="mail"></div><br>
			<div class="link_set">입사일&nbsp;:&nbsp;<%=dto.getHire_date()%></div><br> 
			<div class="link_set">잔여연차&nbsp;:&nbsp;<%=dto.getAnnual_leave()%></div><br>
			<br> 
			<input type="submit" value="수정">
			<input type="reset" value="취소">
		</div>
	</div>
</form>


<!-- The Modal 포함-->
<jsp:include page="photochangeModal.jsp" flush="true"/>
<jsp:include page="pwchangeModal.jsp" flush="true"/>

<%-- 
<!-- 비밀번호 변경 클릭 시 -->
<!-- The Modal -->
<div class="pwchange" id="pwchange">
  <div class="modal-dialog">
    <div class="pwchange_modal-content">

      <!-- Modal Header -->
      <div class="pwchange_modal-header">
        <h4 class="modal-title">비밀번호 변경</h4>
      
      </div>

      <!-- Modal body -->
      <div class="pwchange_modal-body">
          <form id="pwchange_form" action="loginCheck/pwUpdate" method="POST">
              <!-- 현재 비밀번호, 변경할 비밀번호, 비밀번호 확인 -->
              <!-- 회원정보 식별할 회원 아이디도 데이터를 보내야한다. 
                   이때 hidden으로 처리(보여줄 필요가 없기 때문.) -->
              <input type="hidden" name="userId" value="<%=dto.getMember_num()%>">
              
              <table>
                  <tr>
                     <td>변경할 비밀번호</td>
                     <td><input type="password" name="updatePwd" id="pwd1" required></td>
                  </tr>
                  <tr>
                     <td>변경할 비밀번호 확인</td>
                     <td><input type="password" name="checkPwd" id="pwd2" required>
                  </tr>
              </table>
              <br>
            <input type="submit" class="btn_btn-warning" value="비밀번호 변경">
            <button type="button" class="close" data-dismiss="modal">취소</button>
          </form>
               </div>
    </div>
  </div>
</div>
<!-- 끝 --> --%>