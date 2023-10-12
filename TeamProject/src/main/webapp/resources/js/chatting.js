$(document).ready(function() {
var selectedMembers = []; // 선택한 사용자 담을 배열 선언
    
    // 채팅방 생성시 사용자 초대 뜨도록
    $("#addchat").click(function() {
        $(".invite").css("display", "flex");
    });

    // 체크박스
    $(".memberList").on("change", 'input[name="memberCheckbox"]', function() {
        var selectedValue = $(this).val();
        var selectedMemberNum = $(this).prev('span').text();
        // 체크박스가 선택된 경우
        if ($(this).is(':checked')) {
            if(!selectedMembers.includes(selectedMemberNum)) {//중복된 사용자가 있는지 체크
                selectedMembers.push(selectedMemberNum);
                $('#selected').append("<p><span>"+selectedValue +"</span></p>");
            }
        } else {
            // 배열에서 값을 제거
            const index = selectedMembers.indexOf(selectedMemberNum);
            if (index > -1) {
            	selectedMembers.splice(index, 1);
            }
            // 값을 .selected 요소에서 제거
            $('#selected').find('p:contains("' + selectedValue + '")').remove();
        }
        
        // 출력된 값이 6개를 초과하면 스크롤 생성
        if (selectedMembers.length > 6) {
          $('#selected').css('overflow-y', 'scroll');
        } else {
          $('#selected').css('overflow-y', 'auto');
        }
     });
    	
    	//사용자 초대 - 조건 검색
    	$(".searchValue").click(function(){
    		$(this).val('');
    	});
    	
    	$(".searchValue").keyup(function(e){
	    	var searchCondition = $(".searchCondition").val();
	    	var searchValue = $(".searchValue").val();
	    	if(e.keyCode==13){
	    		$.ajax({
	                type: "post",
	                url: "searchValue",
	                data: JSON.stringify({ "searchCondition": searchCondition, "searchValue": searchValue }),
	                contentType: "application/json; charset=utf-8",
	                success: function(data, status, xhr) {
	                	var $ul = $("<ul></ul>");
	                    $.each(data, function(index, member) {
	                        var $li = $("<li></li>");
	                        $li.html(member.div_name + " " + member.member_name + " " + member.rank);
	                        $li.append('<span style="display: none">'+member.member_num+'</span>');
	                        $li.append('<input type="checkbox" name="memberCheckbox" value="' + member.div_name + " " + member.member_name + " " + member.rank + '">');
	                        $ul.append($li);
	                    });
	                    // 생성한 리스트를 페이지에 추가
	                    $(".memberList").empty().append($ul);	                	
	                }
	            });
	    	}//end keyCode
	    });
    
    	$("#chatTitle").click(function(){
    		$(this).val('');
    	});
    
    	
    	//생성 버튼 클릭시
    	$("#create2").click(function() {
    	    var chatTitle = $("#chatTitle").val();
    	    
    	    if (chatTitle.length == 0) {
    	        alert("한글자 이상 입력해주세요.");
    	    } else {
    	        $(".invite").css("display", "none");
    	        
    	        $.ajax({
    	            url: 'createChatRoom',
    	            method: 'post',
    	            data: JSON.stringify({
    	                "chatroom_title": chatTitle,
    	                "chat_member": selectedMembers  // 배열의 값을 사용하여 AJAX 요청을 보냅니다.
    	            }),
    	            contentType: "application/json; charset=utf-8",
    	            dataType: "text",
    	            success: function(chatroom_num) {
    	                window.location.reload(); // 창 리로드
    	                chatPop(chatroom_num);
    	            },
    	            error: function(error) {
    	                alert("채팅방 생성 실패");
    	            }
    	        });
    	    }
    	});

});//end document