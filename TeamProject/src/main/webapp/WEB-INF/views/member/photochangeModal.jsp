<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dto.MemberDTO"%>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 <script type="text/javascript">

 
 <% MemberDTO dto = (MemberDTO)session.getAttribute("login"); 
 	int member_num = dto.getMember_num();
  	String photo = dto.getPhoto();
  	
  	System.out.println(dto.toString()); 
  	System.out.println(member_num); 
  %>
 
  	function imageChange(thisObj) {
  		
  	    var files = thisObj.files[0];
  	    var $result = document.getElementById('result');
  	    if (typeof files == 'undefined') {
  	        $result.innerHTML = '';
  	        return false;
  	    }
  	    var reader = new FileReader();
  	    var img = new Image();
  	    reader.addEventListener("load", function () {
  	        img.src = this.result;
  	    });
  	    img.onload = function () {
  	      // 이미지 크기를 가로 크기에 맞춰 조정
  	        var targetWidth = 150;
  	        var aspectRatio = img.width / img.height;
  	        var targetHeight = targetWidth / aspectRatio;

  	        img.width = targetWidth;
  	        img.height = targetHeight;

  	        // 이미지를 결과에 추가 (가로 크기는 고정, 세로 크기 자동 조정)
  	        var view = "이미지 미리보기";
  	        view += "<p><img src='" + img.src + "' style='border:solid 1px #000000; width:" + targetWidth + "px; height:auto;'/></p>";
  	        $result.innerHTML = view;
  	    }

  	    reader.readAsDataURL(files);
  	}
 </script>

    
    
<!-- photochangeModal.jsp -->
<div class="photochange" id="photochange">
  <div class="modal-dialog">
    <div class="photochange_modal-content">

      <!-- Modal Header -->
      <div class="photochange_modal-header">
        <h4 class="modal-title">프로필사진 변경</h4>
      </div>

      <!-- Modal body -->
      <div class="photochange_modal-body">
      <div id="result"></div><br/><!-- 업로드사진 넣는 result -->	  
           <div class="card">           
              <form action="loginCheck/profilepic" method="post" enctype="multipart/form-data">
                <input type="file" name="theFile" onchange="imageChange(this)">
                <input type="hidden" name="member_num" id="membernum" value="<%=dto.getMember_num()%>">
                <button type="submit" class="btn btn-primary" id="change-btn">사진변경</button>
                <button type="button" class="close" data-dismiss="modal" id="close-btn">취소</button>
             </form>
      
	      </div>
      </div>
    </div>
  </div>
</div>