<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${ !empty mesg }">
	<script>
		alert('${mesg}');
	</script>
</c:if>

<%
	//"mesg" session이 있을 경우 삭제
	if (session.getAttribute("mesg") != null) {
		session.removeAttribute("mesg"); 
	}
%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	// 쿠키에 사원번호, 비밀번호 저장 
	function setCookie(cookieName, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var cookieValue = escape(value)
				+ ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
		document.cookie = cookieName + "=" + cookieValue;
	}

	// 쿠키 삭제 
	function deleteCookie(cookieName) {
		var expireDate = new Date();
		expireDate.setDate(expireDate.getDate() - 1); // 어제날짜를 쿠키 소멸날짜로 설정
		document.cookie = cookieName + "= " + "; expires="
				+ expireDate.toGMTString();
	}

	// 쿠키 가져오기 
	function getCookie(cookieName) {
		cookieName = cookieName + '=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cookieName);
		var cookieValue = '';
		if (start != -1) {
			start += cookieName.length;
			var end = cookieData.indexOf(';', start);
			if (end == -1)
				end = cookieData.length;
			cookieValue = cookieData.substring(start, end);
		}
		return unescape(cookieValue);
	}
	// 쿠키 끝
	
	$(document).ready(function() {
		// 사원 번호 / 비밀번호 쿠키 저장
		var member_num = getCookie("member_num");
		$("#member_num").val(member_num);
		
		var password = getCookie("password");
		$("#password").val(password); 
		
		// 쿠키에 저장된 계정이 있을 경우 checked 속성값 = true
		if ($("#member_num").val().length != 0) {
			$("#saveCheck").attr("checked", "checked");
		}

		$("#saveCheck").on("change", saveCheck);
	})

	// 체크박스 함수 
	function saveCheck() {
		if ($(this).is(":checked")) { 			// 체크할 경우
			
			// 사원번호 저장 
			var member_num = $("#member_num").val();
			setCookie("#member_num", member_num, 365);
			// 비밀번호 저장 
			var password = $("#password").val();
            setCookie("password", password, 365);
			console.log("쿠키 저장");
		}else {									// 체크 해제할 경우 
			deleteCookie("member_num");			// 쿠키 삭제 
			console.log("쿠키 삭제");
		}
	}
</script>

<form action="login" method="get">
    <div class="logoDiv"><img class="logo" src="resources/image/logo.png" alt=""></div>
    <div class="fieldset">
        <fieldset>
            <div class="input">
                <div class="input_text">
                    <input type="text" name="member_num" placeholder="사원번호" style="text-align: center"><br>
                    <input type="password" name="password" placeholder="비밀번호" style="text-align: center"><br>
                </div>
                <div class="input_btn">
                    <button class="login_btn" onclick="submit">로그인</button><br>
                </div>
            </div>
            <div class="saveCheckDiv">
            	<input type="checkbox" id="saveCheck"/>
            	로그인 상태 유지
                <label for="saveCheck"></label>
            </div>
        </fieldset>
    </div>
    <div class="passwordSearch">
        <a href="passwordSearch"> 비밀번호 찾기  </a>
    </div>
</form>

