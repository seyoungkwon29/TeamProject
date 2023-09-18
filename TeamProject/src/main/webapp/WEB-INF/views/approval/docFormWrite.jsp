<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>

<body>
	<!-- <jsp:include page="docForm.jsp" flush="true"></jsp:include> -->
	
<script>
	// 휴가신청서
	if("${form.form_name}" === "휴가신청서"){
		$("#tr-title").css("display", "none"); // 제목 행 숨기기
	    var leaveStart = 0; // 휴가 시작일 변수 선언
	    var leaveEnd = 0;   // 휴가 종료일 변수 선언
	    var leaveDay = 0;   // 휴가 일수 변수 선언
	    var totalLeave = 0; // 잔여 연차
	    var memberNum = "${login.member_num}"; // 로그인 한 유저의 사원번호
	    var breakTotal = "${loginUser.breakTotal}"; // 로그인 한 유저의 총 연차수
	    
	 	$.ajax({ // 잔여 연차 조회
	 		url : "/approval/leaveDocSearch.sw",
	 		type : "get",
	 		data : { "memberNum" : memberNum },
	 		success : function(leaveLeft) {
	 			totalLeave = leaveLeft;
	 			$("#left-leave").val(totalLeave);
	 		},
	 		error : function() {
	 			console.log("잔여 연차 조회 실패");
	 		}
	 	})
	    function leaveStartDate(e) { // 휴가 시작일 값 날짜화
	        leaveStart = new Date(e.target.value);
	        calLeaveDate();
	        chkLeave();
	    }
	    function leaveEndDate(e) { // 휴가 종료일 값 날짜화
	        leaveEnd = new Date(e.target.value);
	        calLeaveDate();
	        chkLeave();
	    }
	    function calLeaveDate() { // 휴가 날짜 차이 계산(연차, 반차인 경우에만)
	        if($("#leaveType").val() == "반차") { // 반차인 경우
	            leaveDay = 0.5;
	        }else {
	            if(leaveStart !== 0 && leaveEnd !== 0){
	                var dateDiff = leaveEnd.getTime() - leaveStart.getTime();
	                leaveDay = Math.abs(dateDiff / (1000 * 3600 * 24) + 1);
	            }else {
	            	leaveDay = 0;
	            }
	        }
	        setLeaveDay(leaveDay);
	        viewLeaveDay(leaveDay);
	    }
	    $("#leaveType").change(function() { // 휴가 종류 선택에 따라 처리하는 함수
	        var leaveType = $("#leaveType").val();
	        $("#td-title").val(leaveType + " 신청합니다.");
	        if($(this).val() == "반차") {
	            leaveDay = 0.5;
	            $("#leaveTime").css("display", "inline-flex"); // 오전/오후 보이기
	            $(":radio[name='leaveTime'][value='오전']").prop("checked", true); // 휴가 시간 라디오 버튼 오전 자동으로 체크
	            $("#endDate").css("display", "none"); // 휴가 종료일 숨기기
	            $("#tilde").css("display", "none"); // 휴가 시작일과 종료일 사이 '~' 숨기기
	            setLeaveDay(leaveDay);
	            viewLeaveDay(leaveDay);
	        }else{
	        	calLeaveDate();
	            $("#leaveTime").css("display", "none"); // 오전 오후 숨기기
	            $(":radio[name='leaveTime']").prop("checked", false); // 휴가 시간 라디오 버튼 체크 해제
	            $("#endDate").css("display", "inline-flex"); // 휴가 종료일 보이기
	            $("#tilde").css("display", "inline-flex"); // 휴가 시작일과 종료일 사이 '~' 보이기
	            setLeaveDay(leaveDay);
	            viewLeaveDay(leaveDay);
	        }
	    });
	    function viewLeaveDay(leaveDay) { // 휴가 종류에 따라 연차 일수 표시
	        if($("#leaveType").val() == "연차" || $("#leaveType").val() == "반차") { // 반차 또는 연차인 경우
	            $("#apply-leave").val(leaveDay);
	            $("#left-leave").val(totalLeave - leaveDay);
	        }else {
	            $("#left-leave").val(totalLeave);
	            $("#apply-leave").val(0);
	        }
	    }
	    function setLeaveDay(leaveDay) { // 휴가 일수 표시 및 value 값 넣기
	        $("#s-leaveDay").text(leaveDay);
	        $("#i-leaveDay").val(leaveDay);
	    }
	    function chkLeave(){ // 신청 연차가 잔여 연차보다 클 경우 alert창 띄우고 종료일 초기화
		    if(leaveDay > totalLeave) { 
		    	if($("#leaveType").val() == "연차" || $("#leaveType").val() == "반차"){
		    		alert("신청 연차 수가 잔여 연차 수를 초과했습니다.");
		    		$("#endDate").val("");
		    		leaveDay = 0;
		    	}
		    	setLeaveDay(leaveDay);
		        viewLeaveDay(leaveDay);
		    }
	    }
	};
</script>
</body>

</html>