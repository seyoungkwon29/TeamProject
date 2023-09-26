<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.dto.MemberDTO"%>
    
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script type="text/javascript">

 
 <% MemberDTO dto = (MemberDTO)session.getAttribute("login"); 
 int member_num = dto.getMember_num(); %>
 
	$(function() {


		
		
		
	});//모달끝

	
	
 </script>
    
    
    
    
<!-- pwchangeModal.jsp -->
<div class="pwchange" id="pwchange">
  <div class="modal-dialog">
    <div class="pwchange_modal-content">

      <!-- Modal Header -->
      <div class="pwchange_modal-header">
        <h4 class="modal-title">비밀번호 변경</h4>
      </div>

      <!-- Modal body -->
      <div class="pwchange_modal-body">
        <form id="pwchange_form" action="loginCheck/pwchange" method="POST">
          <input type="hidden" name="userId" value="<%=dto.getMember_num()%>">
          <table>
            <tr>
              <td>변경할 비밀번호</td>
              <td><input type="password" name="updatePwd" id="pwd1" required></td>
            </tr>
            <tr>
              <td>변경할 비밀번호 확인</td>
              <td><input type="password" name="checkPwd" id="pwd2" required></td>
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