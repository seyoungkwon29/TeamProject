<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.dto.MemberDTO"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
<% MemberDTO dto = (MemberDTO)session.getAttribute("login"); 
int member_num = dto.getMember_num(); 
 	String photo =dto.getPhoto();%>
</script>


<div class="main-mypage-section">
	<div class="main-mypage-image">
<%-- 		<img src="/profilepic/<%=dto.getPhoto()%>" width="250" height="auto"><br> --%>
		<img src="resources/memberphoto/<%=dto.getMember_num()%>.png" width="200" height="auto">
		<br>
	</div>
</div>
