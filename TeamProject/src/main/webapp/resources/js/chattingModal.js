	$(document).ready(function() {
	    $("#r-setting").click(function() {
	        if ($("#chatModal").css("display") == "none") {
	            $("#chatModal").fadeIn(200);
	        } else {
	            $("#chatModal").fadeOut(200);
	        }
	    });

	    // 모달 밖을 클릭했을 때
	    $(document).on("click", function(event) {
	        if (!$(event.target).closest("#chatModal, #r-setting").length) {
	            $("#chatModal").fadeOut(200);
	        }
	    });
	    
	    
	    $("#title-setting").click(function(){
	   	 if ($("#titleModal").css("display") == "none") {
		     $("#titleModal").fadeIn(200);
		} else {
		     $("#titleModal").fadeOut(200);
	    }
	    });
	    
	    
	    $("#cancel").click(function(){
			 $("#titleModal").fadeOut(200);
	    });
	    

	    // 모달 밖을 클릭했을 때
	    $(document).on("click", function(event) {
	    	if (!$(event.target).closest("#titleModal, #title-setting").length) {
	    		$("#titleModal").fadeOut(200);
	    	}
	    });

	    
	    $("#update").click(function(){

	    	if ($("#updateTitle").val()!='') {
	    		$.ajax({
	    	        url: 'updateTitle',
	    	        method: 'post',
	    	        data: JSON.stringify({
	    	            "chatroom_title": $("#updateTitle").val(),
	    	            "chatroom_num": $("#chatroom_num").val()
	    	        }),
	    	        contentType: "application/json; charset=utf-8",
	    	        dataType : "text",
	    	        success: function(date) {
	    	        	console.log(date);
	    	        	$("#room-title").text(date);
	    	        	$("#titleModal").fadeOut(200);
	    	        	
	    	        },
	    	        error: function(error) {
	    	            alert("제목 변경 실패");
	    	        }
	    	    });
			}else {
				alert("1글자 이상 입력하세요");
			}
	    	
	    });
	    
	    //채팅 인원 초대
	    $("#invite-user").click(function() {
	    	if ($("#app-t-modal").css("display") == "none") {
	            $("#app-t-modal").fadeIn(200);
	        } else {
	            $("#app-t-modal").fadeOut(200);
	        }
	    
	    
	    
	    });
});//end document
	
