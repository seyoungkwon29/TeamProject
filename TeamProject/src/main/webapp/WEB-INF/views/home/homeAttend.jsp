<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.dto.MemberDTO"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%
	//출퇴관련
	Date time = new Date();
	SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm:ss");
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(time); // 10분 더하기
%>

<% 	
	//memberPhoto
	MemberDTO dto = (MemberDTO)session.getAttribute("login"); 
	int member_num = dto.getMember_num(); 
	String member_name = dto.getMember_name();
	
 	String photo =dto.getPhoto();
%>


<script type="text/javascript">
	$(document).ready(function() {
		startDate();
		// 날짜 검색
		if ("${ date }" != "") {
			$("#nowMonth").val("${ date }");
		} else {
			$("#nowMonth").val(new Date().toISOString().slice(0, 7));
		}
		
        // 출근 버튼 클릭 이벤트
		 $("#punchIn").click(function() {
             var currentTime = getCurrentTime();
             $("#punchInTime").text(currentTime);
             
             // AJAX를 사용하여 출근 시각을 서버로 전송
             $.ajax({
                 type: "POST",
                 url: "attendance/punchIn",
                 data: {
                     att_start: currentTime
                 },
                 success: function(response) {
                     console.log("출근 처리 완료");
                     // 로컬 세션 스토리지에 출근 시각을 저장
                     sessionStorage.setItem("punchInTime", currentTime);
                 },
                 error: function(xhr, status, error) {
                     console.error("오류 발생: " + error);
                 }
             }); // ajax
         }); // punchIn
		 
         // 퇴근 버튼 클릭 이벤트
         $("#punchOut").click(function() {
             var currentTime = getCurrentTime();
             $("#punchOutTime").text(currentTime);
             
          // AJAX를 사용하여 출근 시각을 서버로 전송
             $.ajax({
                 type: "POST",
                 url: "attendance/punchOut",
                 data: {
                     att_fin: currentTime
                 },
                 success: function(response) {
                     console.log("퇴근 처리 완료");
               		 // 로컬 세션 스토리지에 퇴근 시각을 저장
                     sessionStorage.setItem("punchOutTime", currentTime);
                 },
                 error: function(xhr, status, error) {
                     console.error("오류 발생: " + error);
                 }
             }); // ajax
         }); // punchOut
         
         
         // 이전 출근 및 퇴근 시각을 로컬 세션 스토리지에서 가져옴
         var previousPunchInTime = sessionStorage.getItem("punchInTime");
         var previousPunchOutTime = sessionStorage.getItem("punchOutTime");
         
         if (previousPunchInTime) {
             $("#punchInTime").text(previousPunchInTime);
         }
         
         if (previousPunchOutTime) {
             $("#punchOutTime").text(previousPunchOutTime);
         }
         
         
     	 // 현재 시각 가져오는 함수
         function getCurrentTime() {
			var dateString = "";
			var now = new Date();
			// slice(-2) : 두자리가 안될 경우 앞에 0으로 채워서 처리
			dateString += ("0" + now.getHours()).slice(-2) + ":";
			dateString += ("0" + now.getMinutes()).slice(-2) + ":";
			dateString += ("0" + now.getSeconds()).slice(-2);
			
			$("#att_start").val(dateString); // 출근 시각
			$("#att_fin").val(dateString); // 퇴근 시각
			
			return dateString;
		}
      
		function startDate() { // 시간 관리 함수
			// setInterval(fn, 1000) : 1초에 한번 해당 함수 실행
			date = setInterval(function() {
				var dateString = "";
				var newDate = new Date();
				// slice(-2) : 두자리가 안될 경우 앞에 0으로 채워서 처리
				dateString += ("0" + newDate.getHours()).slice(-2) + ":";
				dateString += ("0" + newDate.getMinutes()).slice(-2) + ":";
				dateString += ("0" + newDate.getSeconds()).slice(-2);
	
				$("#now").text(dateString); // 현재 시각
				$("#att_start").val(dateString); // 출근 시각
				$("#att_fin").val(dateString); // 퇴근 시각
			}, 1000); // 1000밀리초  = 1초 (현재 시각 업데이트) 
		}
	
		
	}); // document ready
	

</script>

<div class="attend-body">
		<div class="main-mypage-section">
			<img src="resources/memberphoto/<%=dto.getMember_num()%>.png" width="100">
			<div class="mem-name"><%=member_name%></div>
		</div>

		<div id="now">
			<%=simpleTime.format(calendar.getTime())%>
		</div>
		
		<div class="attend-btn">
			<div class="all-att-btn">
			<!-- 출근 / 퇴근 버튼 -->
			<!-- 출근 -->
				<div>
					<form action="attendance/punchIn" method="post">
						<input type="hidden" id="att_start" name="att_start"> 
						<input type="button" id="punchIn" class="btn" value="출근">
					</form>
				</div>
				<!-- 출근 -->
				<!-- 퇴근 -->
				<div>
					<form class="attForm" action="attendance/punchOut" method="post">
						<input type="hidden" id="att_fin" name="att_fin"> 
						<input type="button" id="punchOut" class="btn" value="퇴근">
					</form>
				</div>
			</div>
			<!-- 퇴근 -->
			<!-- 출근 / 퇴근 버튼 -->
			
			<!-- 출 퇴근 시간 찍히는 곳 -->	
			<div class="time_log">
				<div class="time-attend">
					<span>출근시간</span> <span id="punchInTime"> </span>
				</div>
				<div class="time-attend">
					<span>퇴근시간</span> <span id="punchOutTime"> </span>
				</div>
			</div>
		</div>
	<!-- left -->
</div>

<style>
.attend-body {
	padding: 40px 60px 31px 60px;
    background: #7c6c6c14;
    border-radius: 20px;
}
.mem-name{
    margin: 19px 0px 4px 0px;
    letter-spacing: 2px;
    color: #2b2525eb;
    font-size: 13px;
}
#now{
    font-size: 37px;
    margin-bottom: 8px;
    letter-spacing: -1px;
}
.all-att-btn {
	display: flex;
    justify-content: center;
    gap: 10px;
}
.btn {
    padding: 6px 10px;
    font-size: 10px;
    border-radius: 8px;
    border: 2px solid #80808012;
    color: #372b2bc7;
    background: #a39b9b1a;
}
.time_log {
	margin-top: 15px;
}
#punchInTime, #punchOutTime{
    letter-spacing: 1px;
    font-size: 13px;
    padding: 4px;
    color: #1a1616;
}

.time-attend {
	letter-spacing: 1px;
    font-size: 13px;
    padding: 4px;
    color: #948282a3;
}
</style>