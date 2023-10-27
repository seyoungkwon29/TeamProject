$(document).ready(function() {
    var hideDropdownTimeout;
    
    $('.link-ul li').mouseenter(function() {
        clearTimeout(hideDropdownTimeout);
        $(this).find('.dropdown').stop().slideDown(100); 
    });

    $('.link-ul li').mouseleave(function() {
        var dropdown = $(this).find('.dropdown');
        hideDropdownTimeout = setTimeout(function() {
            dropdown.stop().slideUp(100);
        }, 25);
    });
    
    //안읽은 메일 가져오기
    $.ajax({
		type: "get",
		url: "countMailNotReading",//서버 요청 주소
		dataType:"json", //응답data타입 text, json, xml, html
		success: function(data) {
			if(data.length != 0){
				console.log("읽지않은 메일이 있습니다.");
				console.log(data);
				$("#mail > img").attr("src","/resources/image/icon/mailOn.png");
			}
		},
		error: function(xhr,status, e){
			console.log("데이터를 가져올 수 없습니따");
		}//error
	});//end ajax
    
    
    //안읽은 메일 확인하기
	$.ajax({
		type: "get",
		url: "notiListNotReading",//서버 요청 주소
		dataType:"json", //응답data타입 text, json, xml, html
		success: function(data) {
			if(data.length != 0) {
				$("#alarm > img").attr("src","/resources/image/icon/alarmOn.png");
			}
		},
		error: function(xhr,status, e){
			console.log("데이터를 가져올 수 없습니따");
		}//error
	});//end ajax
    
    //안읽은 알림 확인
    $("#alarm").click(function() {
		$.ajax({
			type: "get",
			url: "notiListNotReading",//서버 요청 주소
			dataType:"json", //응답data타입 text, json, xml, html
			success: function(data) {
				var notiListDiv = $("#notiList");
				notiListDiv.empty();
				
				for(var i=0; i<data.length; i++){
					var link = $("<span></span>")
									.attr("class","noti")
									.data("noti_num", data[i].noti_num)
									.text(data[i].noti_content);
					link.append("<b></b>");
									
					notiListDiv.append(link);
				}
							
			},
			error: function(xhr,status, e){
				console.log("데이터를 가져올 수 없습니따");
			}//error
		});//end ajax
		
   	$("#notiList").slideToggle();
   });//end alarm clcik

    
    $("#notiList").on("click","span", function(){
    	$.ajax({
			type: "get",
			url: "deleteNotification",//서버 요청 주소
			data:{
				noti_num : $(this).data("noti_num")
				},
			dataType:"text", //응답data타입 text, json, xml, html
			success: function(data) {
				console.log("삭제완료");
			},
			error: function(xhr,status, e){
				console.log("데이터를 가져올 수 없습니따");
			}//error
		});//end ajax
    	
    	//b태그가 클릭된 경우라면, 페이지이동 하지 않고 리스트만 리로드
    	if ($(event.target).is("b")) {
            event.preventDefault(); // b가 클릭되었을 때만 기본 동작을 막습니다.
            
            $.ajax({
    			type: "get",
    			url: "notReadingNotification",//서버 요청 주소
    			dataType:"json", //응답data타입 text, json, xml, html
    			success: function(data) {
    				var notiListDiv = $("#notiList");
    				notiListDiv.empty();
    				
    				for(var i=0; i<data.length; i++){
    					var link = $("<span></span>")
										.attr("class","noti")
										.data("noti_num", data[i].noti_num)
										.text(data[i].noti_content);
    					
    					link.append("<b></b>");				
    					notiListDiv.append(link);
    				}
    							
    			},
    			error: function(xhr,status, e){
    				console.log("데이터를 가져올 수 없습니따");
    			}//error
    		});//end ajax
            
            
            
        } else {
    		window.location ="projects";
        }
    });//end click .noti
    
    

    
    
});//end document.ready();