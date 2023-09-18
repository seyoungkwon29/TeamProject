<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/meetingRoom.css">
<link href='resources/css/calendar.css' rel='stylesheet' />
<title>회의실 예약</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='resources/js/calendar.js'></script>
	
	<c:if test="${!empty mesg }">
		<script>
			alert('${mesg}');
		</script>
	</c:if>
	<% if(session.getAttribute("mesg")!=null){
		session.removeAttribute("mesg");
	} %>
</head>
<body>

<div id="s-container">
				<form action="loginCheck/meetingRoomReserve" method="get" id="roomForm">
                <div id="divLeft">
					<div id="rDiv">
						<div class="reserveDiv">
							<span>1. 날짜&nbsp;&nbsp;&nbsp;</span>
							<input type=date id="roomRDate" name="meeting_date">
						</div>
						<div class="reserveDiv">
							<span>2. 회의실</span>
								<select id="room" name="meeting_num">
								<option value="1 회의실"  selected>1 회의실</option>
								<option value="2 회의실" >2 회의실</option>
								<option value="3 회의실" >3 회의실</option>
								</select>
									
						</div>
						<div class="reserveDiv">
							<span>3. 시간&nbsp;&nbsp;&nbsp;</span>
								<select id="time" name="meeting_time">
								<option value="a">=== 선택 ===</option>
								<option id="time1">10:00~12:00</option>
								<option id="time2">13:00~15:00</option>
								<option id="time3">15:00~17:00</option>
								<option id="time4">17:00~19:00</option>
								<option id="time5">19:00~21:00</option>
								</select>
						</div>
									<div class="guide">
											<p>* 회의실 이용안내 및 예약 방법 *</p>	
											<div>
												<ul>
													<li>원하시는 날짜, 회의실, 시간을 선택해주세요.</li>
													<li>회의실 예약은 최대 3주 까지 가능합니다.</li>
													<li>원할한 이용을 위해 10분전 퇴실을 준비해주세요.</li>	
													<li>회의실 내 취식을 엄금합니다.</li>
													<li>회의실 내에서는 마스크를 착용해주세요.</li>	
												</ul>
											</div>
									</div>
						</div>				
				</div>
                            <div id="divRight">
								<div class="calendar">
									
								</div>
								
							<div id = "buttonDiv">
								<input type="hidden" name="reservation" value="1">
								<button type="submit" form="roomForm">예약하기</button>
							</div>
                            </div>
                       </form>
				</div>
</body>
<script type="text/javascript">
$(document).ready(function() {
    $("#roomForm").submit(function(event) {
        if ($("#roomRDate").val() === "") {
            alert("날짜를 선택하세요.");
            event.preventDefault();
        }

        if ($("#time").val() === "a") {
            alert("시간을 선택하세요.");
            event.preventDefault();
        }
    });

});

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.querySelector('.calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth'
        });
        calendar.render();
      });

</script>

</html>
















