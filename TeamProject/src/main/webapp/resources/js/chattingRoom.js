	var ws;
	
	function wsOpen(chatroom_num) {
		ws = new WebSocket("ws://" + location.host + "/chat/" + chatroom_num);
		wsEvt();
	}
	function wsEvt() {
		ws.onopen = function(data) {
			console.log("소켓 열었다 두둥~!~!!!~!!!");
		}
		
		ws.onmessage = function(data) {
			//메시지를 받으면 동작
			var msg = data.data;
			if(msg != null && msg.trim() != ''){
				var d = JSON.parse(msg);
				// 시간 정보가 있다면 포맷팅합니다.
		        var formattedTime = d.chat_date ? new Date(d.chat_date).toLocaleTimeString() : "";
		        formattedTime = formattedTime.substring(0, 8);
				if(d.chat_type == "getId"){
					var si = d.sessionId != null ? d.sessionId : "";
					if(si != ''){
						$("#sessionId").val(si);
					}
				}else if(d.chat_type == 0){
					if(d.sessionId == $("#sessionId").val()){
						$("#chatting").append(
						"<p class='me'><span class='time-r'>"+formattedTime+"</span>" +
						"<span class='my-msg'>"+d.chat_content+"</span></p>"
						);	
					}else{
						$("#chatting").append(
						"<div class='others'>"+
						"<span class='otherName'>"+ d.div_name +" "+ d.member_name +" "+ d.rank+"</span>"+
						"<div class='wrapper'>"+
						"<span class='other-msg'>"+d.chat_content+"</span>"+
						"<span class='time-l'>"+formattedTime+"</span></div></div>");
					}
				}
				else if (d.chat_type == 1) {
					$("#chatting").append(
				            "<div class='c-info'><p>"+ d.chat_content + "</p></div>"
				        );
				}
				else{
					console.warn("unknown type!")
				}
				$("html, body").animate({ scrollTop: $(document).height() }, "fast");
			}
		}//onmessage
		
		$("#sendChatting").keypress(function(e) {
			if (e.keyCode == 13) { //enter press
				send();
			}
		});
		
		ws.onerror = function(error) {
	        console.error("웹소켓 오류 발생:", error);
	    };

	    ws.onclose = function() {
	        console.log("웹소켓 연결 종료");
	    };
}
	
	function send() {
		var option = {
			chat_type:0,
			chatroom_num:$("#chatroom_num").val(),
			sessionId:$("#sessionId").val(),
			member_num : $("#member_num").val(),
			member_name : $("#member_name").val(),
			div_name : $("#div_name").val(),
			rank : $("#rank").val(),
			chat_content : $("#sendChatting").val(),
			chat_date : new Date().toISOString() //현재 시간을 ISO 포맷 문자열로 추가
		};
			ws.send(JSON.stringify(option));
			$('#sendChatting').val("");
		}
	
	
	function goRoom(chatroom_num) {
		var url = "moveChatting?chatroom_num=" + chatroom_num;
		var options = "width=400,height=550";
		var childWindow = window.open(url, '_blank', options);
		childWindow.isChild = true; // 플래그 추가
		// 자식창이 로드되었을 때 웹소켓 연결
	    childWindow.onload = function() {
	        this.wsOpen(chatroom_num);
	     // 자식 창의 스크롤을 최대로 이동
	        this.scrollTo(0, this.document.body.scrollHeight);
	    };
	}
	
	
	window.onload = function() {
	    if (window.isChild) {
	        wsOpen($("#chatroom_num").val());
	    }
	};
	
