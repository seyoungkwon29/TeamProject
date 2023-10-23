<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<%
	//출퇴관련
	Date time = new Date();
	SimpleDateFormat simpleTime = new SimpleDateFormat("HH:mm:ss");
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(time); // 10분 더하기
%>


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		startDate();

		// 날짜 검색
		if ("${ date }" != "") {
			$("#nowMonth").val("${ date }");
		} else {
			$("#nowMonth").val(new Date().toISOString().slice(0, 7));
		}

		// 출근
		$("#puncIn").on("click", punchIn);
	});

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
</script>

<div class="attend-body">
	<div class="left">
		<div class="attend-btn">
			<!-- 출근 / 퇴근 버튼 -->
			<!-- 출근 -->
			<div class="form1">
				<form action="attendance/punchIn" method="post">
					<input type="hidden" id="att_start" name="att_start"> 
					<input type="submit" id="punchIn" class="btn" value="출근">
				</form>
			</div>
			<!-- 출근 -->
			<!-- 퇴근 -->
			<div class="form2">
				<form action="attendance/punchOut" method="post">
					<input type="hidden" id="att_fin" name="att_fin"> <input
						type="submit" id="punchOut" class="btn" value="퇴근"> <br>
				</form>
			</div>
			
			<div id="now">
				<%=simpleTime.format(calendar.getTime())%>
			</div>
			<div></div>
			<!-- 퇴근 -->
			<!-- 출근 / 퇴근 버튼 -->
		</div>
	</div>
	<!-- left -->