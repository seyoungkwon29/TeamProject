<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.dto.MemberDTO"%>
    
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script type="text/javascript">

 
 <% MemberDTO dto = (MemberDTO)session.getAttribute("login"); 
 int member_num = dto.getMember_num(); 
  	String photo =dto.getPhoto();%>
 
	$(function() {
		
		
	});//  모달끝

 </script>
    
    
    
<!-- pwchangeModal.jsp -->
<div class="photochange" id="photochange">
  <div class="modal-dialog">
    <div class="photochange_modal-content">

      <!-- Modal Header -->
      <div class="photochange_modal-header">
        <h4 class="modal-title">프로필사진변경</h4>
      </div>

      <!-- Modal body -->
      <div class="photochange_modal-body">
		 <form action="/member/updateImg" method="post" enctype="multipart/form-data">
		 <div class="div_container">
		 	<div class="photochange_image">
				<img src="resources/memberphoto/<%=dto.getMember_num()%>.png"
					width="150" height="150"><br>
			</div> 
			<br> 
			<div class="photochange_name">
				<p>파일명 : <input type="file" name="file"></p>
				<p><input type="hidden" name="member_num" id="membernum"  value="<%=dto.getMember_num()%>"></p>	
				<p><input type="submit" value="upload">사진변경</p>	
				 <button type="button" class="close" data-dismiss="modal">취소</button>
			</div> 
		</div>	
		 </form>
      </div>
    </div>
  </div>
</div>