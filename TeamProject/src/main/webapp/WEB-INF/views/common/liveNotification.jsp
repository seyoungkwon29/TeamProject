<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function(){
	var member_num = ${login.getMember_num()};

	function wsEvt() {
		ws.onopen = function(data){
			console.log("웹소켓 서버 오픈!");	
		}
		
		ws.onmessage = function(data) {
			console.log("onmessage 실행");
			var msgArr = data.data.split(",");
			console.log("msgArr : "+msgArr);
			console.log("project_num : "+msgArr[1]);
			console.log("noti_num : "+msgArr[2]);
			//화면에 표시
			if(msgArr[0] != null && msgArr[0].trim() != ''){
				toast(msgArr[0],"고정!");
				$("b").data("noti_num",msgArr[2]);
				console.log($("b").data("noti_num"));
			}
		}//end onmessage
	}//end wsEvt
			
			
			var member = '${login.getMember_name()}'
			console.log(member);
			window.ws = new WebSocket("ws://"+location.host+"/notification");  //포트번호
			console.log("ws : " + ws);
			wsEvt();
		
	
/////////////////////////////////////////////////////
	//토스트 메세지
	function fillWidth(elem, timer, limit) {
	if (!timer) { timer = 3000; }	
	if (!limit) { limit = 100; }
	var width = 1;
	var id = setInterval(frame, timer / 100);
		function frame() {
		if (width >= limit) {
			clearInterval(id);
		} else {
			width++;
			elem.style.width = width + '%';
		}
	}
	};

	function toast(msg, timer) {
		if (!timer) { timer = 3000; }
		var $elem = $("<div class='toastWrap'><span class='toast'>" + msg + 
	  "<b></b><div class='timerWrap'><div class='timer'></div></div></span></div>");
		$("#toast").append($elem); //top = prepend, bottom = append
		$elem.slideToggle(100, function() {
			$('.timerWrap', this).first().outerWidth($elem.find('.toast').first().outerWidth() - 10);
			if (!isNaN(timer)) {
				fillWidth($elem.find('.timer').first()[0], timer);
				setTimeout(function() {
					$elem.fadeOut(function() {
						$(this).remove();
					});
				}, timer);			
			}
		});
	}
	$("#toast").on("click", function() {
	 	window.location.href = "projects";
	});
	
	$("#toast").on("click", "b", function(event) {
		event.stopPropagation();
		$.ajax({
			type: "get",
			url: "deleteNotification",//서버 요청 주소
			dataType:"text", //응답data타입 text, json, xml, html
			data:{
				noti_num:  $("b").data("noti_num")
			},
			success: function(data) {
				console.log("sucess : ",data);
			},
			error: function(xhr,status, e){
				console.log("데이터를 가져올 수 없습니따");
			}//error
		});//end ajax
		$(this).closest('.toastWrap').remove();
	});//end toast click
	
}); //end Ready
</script>


<style type="text/css">
		/*
		토스트 메세지 관련 css 
		*/
		
	#toast {
	  position: fixed;
	  min-width: 150px;
	  bottom: 50px;
	  right: 10px;
	  text-align: right;
	  z-index: 1000;
	}
	
	#toast .toastWrap {
	  margin: 6px 0 0;
	  padding: 18px 0 15px;
	  display: none;
	}
	
	.toast {
	  border: 1px solid #F1D031;
	  color: #444;
	  background: #FFFFA3;
	  box-shadow: 0 2px 3px #999;
	  padding: 18px 20px 15px 8px;
	  text-align: left;
	  border-radius: 5px;
	  -moz-border-radius: 5px;
	  -webkit-border-radius: 5px;
	  font-family: arial;
	  font-size: 13px;
	  white-space: pre;
	  position: relative;
	}
	
	#toast b {
	  display: block;
	  position: absolute;
	  top: 3px;
	  right: 4px;
	  width: 12px;
	  height: 12px;
	  font: normal 12px/1 Arial, sans-serif;
	  text-align: right;
	  cursor: pointer;
	  text-shadow: #FFF 0 1px 0;
	}
	
	#toast b:before {
	  content: "\2716";
	}
	
	.timerWrap {
	  position: absolute;
	  bottom: 4px;
	  left: 4px;
	  overflow: hidden;
	  border-radius: 5px;
	  -moz-border-radius: 5px;
	  -webkit-border-radius: 5px;
	}
	
	.timer {
	  display: block;
	  height: 4px;
	  width: 0;
	  background-color: #F1D031;
	}	
</style>

<div id="toast"></div>