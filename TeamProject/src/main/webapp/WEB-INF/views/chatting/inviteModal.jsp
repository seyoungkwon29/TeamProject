<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<meta charset="UTF-8">
<title>초대인원 선택</title>
<link rel="stylesheet" href="resources/css/inviteModal.css"> 
<script src="resources/js/chattingRoom.js"></script>
<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script> <!-- 배열, 객체, 문자열 및 함수와 관련된 다양한 작업 -->

	<div class="app-t-modal" id="app-t-modal"> <!-- 전체 모달창 지정 -->
		<div class="app-modal-content" title="close on click"> <!-- 모달 내용 부분 -->	
				<div class="app-modal-header">
						<span class="m-header-title" id="header">초대 인원 선택</span>
				</div>
				
				<div class="app-modal-body">
					<div class="a-m-search"> <!-- 선택 -->
						<select class="m-s-select" id="s-condition" name="searchCondition" style="text-align:left; width:80px;">
							<option value="all">전체</option> <!-- s-condition의 value -->
							<option value="div_name">부서</option>
							<option value="member_name">이름</option>
						</select>
						
						<div class="a-m-input"> <!-- 검색창 -->
							<input type="text" id="s-value" name="searchValue" class="m-select-text">
							<button type="button" id="search-btn" class="m-i-search"> 검색 </button>
						</div>
					</div>
						
					<div class="a-m-list">
						<table class="m-s-list-table" id="m-list-table"> <!-- 사원 전체 정보 뿌려주기 --> </table>
					</div>
					
					<div class="s-m-select">
						<div class="s-list-div" id="s-list-div" >
							<p id="s-list"> <!-- 결재자, 참조자 선택 뿌려질 화면 --></p>
						</div>
					</div>
				</div>
				
				<div class="app-modal-footer" id="m-footer"> &nbsp;
					<button class="m-confirm-btn" id="m-confirm-btn">확인</button>
					<button class="m-cancel-btn" id="m-cancel-btn">취소</button>
				</div>
		</div>
	</div>

<script>
	var appArr = new Array();
	var appArrText = new Array();
	
	//채팅방 참여 멤버를 제외한 인원 조회
	$.ajax({
		url : "inviteMemberList",
		type : "post",
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify({
            "chatroom_num": $("#chatroom_num").val(),
        }),
		dataType : "json",
		success : function(mList) { //mList를 받아오기		
				$("#s-value").val(""); //검색 입력창 지우기
				appList(mList);
		},
		error : function() {
			alert("사원 목록 조회 실패");
		}
	})
	$("#s-list").html(appArrText.join("<br>")); //배열 내의 모든 요소를 문자열로 합치고, 요소 사이에 <br>태그
	
		
// 	}
	
	//초대
	$("#m-confirm-btn").click(function() {
		$("#app-t-modal").fadeOut(200); //클릭시, 모달창이 꺼짐
		
		var memberNums = appArr.map(function(obj) {
			return obj.memberNum;
        });
		$.ajax({//서버로 데이터를 비동기적으로 요청하는 부분
			url : "inviteMember", //요청을 보낼 URL
			type : "post",
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify({ "chatroom_num": $("#chatroom_num").val(), "invite_member" : memberNums }), 
			dataType : "html",		
			success : function(data) {
				var inviteMessage = {
			            chat_type: 1,
			            chatroom_num:$("#chatroom_num").val(),
			            chat_content : data,
			            member_num : $("#member_num").val(),
			            
			        };
			    ws.send(JSON.stringify(inviteMessage));
			    $("html, body").animate({ scrollTop: $(document).height() }, "fast");
				window.opener.location.reload();
			},
			error : function() {
				alert("초대 실패");
			}
		})
		
	 });
	
	//채팅방 나가기
	$("#exit").click(function() {
		$("#app-t-modal").fadeOut(200); //클릭시, 모달창이 꺼짐
		$.ajax({//서버로 데이터를 비동기적으로 요청하는 부분
			url : "exitChatroom", //요청을 보낼 URL
			type : "post",
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify({ "chatroom_num": $("#chatroom_num").val(), "member_num" : $("#member_num").val()}), 
			dataType : "html",		
			success : function(data) {
				var exitMessage = {
			            chat_type: 1, // 1을 퇴장 메세지로 정의
			            chatroom_num:$("#chatroom_num").val(),
			            chat_content : data,
			            member_num : $("#member_num").val(),
			            
			        };
			        ws.send(JSON.stringify(exitMessage));
			window.close();
			$("html, body").animate({ scrollTop: $(document).height() }, "fast");
			window.opener.location.reload();
			},
			error: function(xhr, status, err) {
				alert("채팅방 나가기 실패");
			}
		})
		
	 });
	
	
	
	//결재자, 참조자 선택 취소
	$("#m-cancel-btn").click(function(){
		$("#app-t-modal").fadeOut(200); //클릭시, 모달창이 꺼짐
	});
	
		
	//검색 내용 입력 후 엔터 눌러도 검색되도록 처리
	$("#s-value").keyup(function (e) { //s-value창에서 키가 떼어질 때 이벤트 발생
		e.preventDefault(); //기본 동작 방지: 현재 입력란 내용을 제출 방지

		if(e.keyCode == 13) { //엔터 키(키 코드 13)를 눌렀을 때
			$("#search-btn").click(); //검색 버튼이 클릭 실행
		}
	})

 				
	//검색 조건(조건, 내용)에 맞는 사원 검색하기(ajax)
	$("#search-btn").click(function() {
		var searchCondition = $("#s-condition").val(); //어떤 조건(전체, 부서, 이름) 선택했는지를 나타내는 값
		var searchValue = $("#s-value").val(); //어떤 내용 검색헀는지 나타내는 값
			
		$.ajax({//서버로 데이터를 비동기적으로 요청하는 부분
			url : "searchMember", //요청을 보낼 URL
			type : "get",
			data : { "searchCondition" : searchCondition,  "searchValue" : searchValue }, 
			//보낼 데이터: 검색 조건, 검색 값을 객체 형태(json)로 전달
			dataType : "json",			
			success : function( mList ) { //mList(사원 리스트)를 매개변수로 appList 함수를 호출
				appList(mList); //아래 appList 함수 호출
			},
			error : function() {
				alert("사원 목록 검색 실패");
			}
		})

	});
	
	//사원 목록에 저장된 사원 정보 불러오기
	function appList(mList) { //mList: 사원 목록, type: 결재자/참조자 변수
		$("#m-list-table").html(""); //이전에 표시되었던 테이블 내용(사원 목록을 표시하는 테이블) 삭제
		var tr; //테이블의 행(row)을 생성할 때 사용될 문자열을 담을 것
		
		$.each(mList, function(i) { //$.each() 반복문: mList 배열을 순회하며 사원 정보 추출
			tr += '<tr class="tr"><td style="display:none;">'  
					+ mList[i].member_num //숨긴 셀은 화면 출력X
					+ '</td><td>' + mList[i].div_name + '&nbsp;'
					+ '</td><td>' + mList[i].member_name + '&nbsp;'
					+ '</td><td>' + mList[i].rank + '</td></tr>';
		});
		
		$("#m-list-table").append(tr); //생성한 행(tr 변수에 저장된 HTML 문자열)을 추가
		//append: HTML 요소에 새로운 내용을 추가
		
		appSelect(); //결재자 or 참조자 선택
	}
	
	
	//결재자/참조자 선택
	function appSelect() {
		$("#m-list-table tr").click( function(){ //행 클릭시 함수 실행
			var trArr = new Object(); //한 행의 배열 담을 객체를 생성
			var tdArr = new Array(); //각 셀(사원번호, 부서, 이름, 직급)의 데이터를 담을 배열을 생성
			
			//현재 클릭된 Row(<tr>)
			var tr = $(this); //현재 클릭된 행
			var td = tr.children(); //선택한 행의 모든 자식요소(td)를 td변수에 저장
						
			//반복문을 이용해서 배열에 값을 담아 사용
			td.each( function(i){ //=$("td").each(): 각 셀의 데이터를 추출하고, tdArr 배열에 순서대로 push
				tdArr.push( td.eq(i).text() ); //td.eq(i).text(): 현재 td의 텍스트값 가져옴
			}); //tdArr배열에 td 텍스트 값을 저장
			console.log("tdArr : " + tdArr);
			
			
			//td.eq(index)를 통해 값 가져와서 trArr 객체에 넣기
			trArr.memberNum = td.eq(0).text(); //td의 0번째 text값 객체에 저장
			trArr.division = td.eq(1).text();
			trArr.memberName = td.eq(2).text();
			trArr.rank = td.eq(3).text();
			
			console.log("trArr : " + trArr);
			
			//객체에 데이터가 있는지 여부 판단
				//appArr 순회하면서, memberNum속성이 trArr.memberNum과 일치하는 요소의 인덱스 저장
				var checkedArrIdx = _.findIndex(appArr, { memberNum : trArr.memberNum }); //동일한 값 인덱스 찾기
							
				appArrText = []; // 배열 비우기
				
				//선택한 행에 memberNum이 appArr배열에 있으면 해당 항목을 제거하고, 그렇지 않으면 배열에 추가
				if(checkedArrIdx > -1) { //checkedArrIdx가 -1보다 크면 : 동일한 값을 찾은 경우, 일치 요소 찾지 못할 시: checkedArrIdx는 -1 반환
					_.remove(appArr, { memberNum : trArr.memberNum }); //동일한 값 인덱스 지우기
				}
				else {
					appArr.push(trArr); //객체를 배열에 담기
				}
				
				appArr.forEach(function(ele, index) {//appArr순회 => 배열 내의 객체 정보를 appArrText배열에 문자열 추가
					appArrText.push(ele.division+ " " +ele.memberName+ " " +ele.rank); //화면에 표시할 결재자 목록 저장
				});
				
				$("#s-list").html(appArrText.join("<br>")); //join("<br>")을 통해 개행을 추가하여 s-list 영역에 텍스트를 출력
		});
	}
</script>
