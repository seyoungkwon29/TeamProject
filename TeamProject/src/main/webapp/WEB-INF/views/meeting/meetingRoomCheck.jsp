<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.util.List"%>
<%@page import="com.dto.MeetingRoomDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/meetingRoomCheck.css">
<link rel="stylesheet" type="text/css" href="resources/css/meeting_modal.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<div class="table-container">
	<table>
		<tr class="title">
		<th>예약 날짜</th>
		<th>이용 시간</th>
		<th>회의실</th>
		<th></th>
		</tr>
    
    	<c:forEach var="room" items="${list}" varStatus="loop">
		<tr class="contents">
       	 	<td>${room.meeting_date}</td>
        	<td>${room.meeting_time}</td>
       		<td>${room.meeting_num}</td>
       		<td><button class="show-modal" data-index="${loop.index}"
       		data-date="${room.meeting_date}" data-time="${room.meeting_time}" data-num="${room.meeting_num}">
       		상세보기</button></td>
		</tr>
   		</c:forEach>
</table>
</div>
	<div id="myModal" class="modal">
        <div class="modal-content">
            <!-- 모달 내용을 여기에 추가 -->
            <div class="contentTop">
                    <p></p><!-- 회의실 번호 -->
                    <ul class="contentul">
                        <li></li><!-- 날짜 -->
                        <li></li><!-- 시간 -->
                    </ul>
            </div>
            <div class="contentCenter">
                <div class="centerL"><img id="roomImg" src="" alt=""></div>
                <div class="centerR">
                    <p>&#x1F4D6; Option</p>

                    <ul class="roomInfo">
                        <li></li><!-- 인원 -->
                        <li>&#x2713; 빔프로젝트 </li>
                        <li>&#x2713; 화이트보드</li>
                        <li>&#x2713; 24인치 모니터 </li>
                    </ul>
                </div>
            </div>
            <div class="contentBottom">
                <div class="modal-buttons">
                    <button class="confirm-button">확인</button>
                    <button class="delete-button">예약 취소</button>
            </div>
            
            <span class="close">&times;</span>
            </div>
        </div>
    </div>
</body>
    <!-- JQuery를 사용하여 모달 열기/닫기 -->
    <script>
    $(document).ready(function() {
        var modal = $("#myModal");
        var modalContent = $(".modal-content");
        
        // "상세보기" 버튼 클릭 시 모달 표시
        $(".show-modal").click(function() {
            var index = $(this).data("index");
            var date = $(this).data("date");
            var time = $(this).data("time");
            var num = $(this).data("num");
            
            // 모달에 데이터 채우기
            $(".contentul li:nth-child(1)").html("날짜: <span>"+ date+"</span>");
            $(".contentul li:nth-child(2)").html("시간: <span>" + time+"</span>");
            $(".contentTop p").html("&#x1F3E2; <span>"+num+"</span> &#x1F3E2;");
            
            //모달에 이미지, 인원수 넣기
            if (num==("1 회의실")) {
            $("#roomImg").attr("src","resources/image/room1.jpg");
            $(".roomInfo li:nth-child(1)").html("&#x2713; 인원: 3~5");
			}else if (num==("2 회의실")) {
            $("#roomImg").attr("src","resources/image/room2.jpg");
            $(".roomInfo li:nth-child(1)").html("&#x2713; 인원: 5~7");
			}else {
            $("#roomImg").attr("src","resources/image/room3.jpg");
            $(".roomInfo li:nth-child(1)").html("&#x2713; 인원: 10~20");
			};
			
            
            // 모달 표시
            modal.css("display", "block");
        });

        // 모달 닫기 버튼 클릭 시 모달 숨기기
        $(".close, .confirm-button").click(function() {
            modal.css("display", "none");
        });

        // 모달 외부 클릭 시 모달 숨기기
        $(window).click(function(event) {
            if (event.target == modal[0]) {
                modal.css("display", "none");
            }
        });
        
        
        //삭제하기 버튼
        $(".delete-button").click(function() {
        	var date = $(".contentul li:nth-child(1) span").text();
            var time = $(".contentul li:nth-child(2) span").text();
            var num = $(".contentTop p span").text();    
        	
            $.ajax({
                type: "post",
                url: "cancelReserve", // 서블릿 URL을 여기에 지정합니다.
                data: { "meeting_date": date, "meeting_time": time, "meeting_num": num },
                success: function(response) {
                    // 서버에서 받은 응답을 처리합니다.
                    alert("예약이 취소되었습니다.");
                    modal.css("display", "none");
                    window.location.href = "meetingRoomCheck";
                }
            });
        
        
        
        });
        
        
    });


    </script>
</html>















