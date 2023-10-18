<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function(){
		$("#findBtn").click(function(){
			$.ajax({
				url : "findpw",
				type : "POST",
				data : {
					member_num : $("#member_num").val(),
					mail : $("#mail").val()
				},
				success : function(result) {
					alert(result);
				},
			})
		});
	})
</script>
<style type="text/css">
.mybtn{
    width: 100px;
    height: 40px;
    padding: 0;
    display: inline;
    border-radius: 6px;
    background: #e0e4e242;
    color: #282222;
    margin-top: 13px;
    border: solid 1px #172a2136;
    transition: all 0.2s ease-in-out 0s;
    font-size: 12px;
    margin-right: 10px;
    cursor: pointer;
}
.mybtn:hover, .mybtn:focus {
    color: #000;
    background: rgba(230, 230, 230, 0.25);
    border: 2px solid #21875a;
    font-weight: bold;
}
.input-style {
	padding: 8px;
    display: block;
    border: none;
    border-bottom: 1px solid #ccc;
    width: 100%;
    margin-top: 3px;
}
.find-pw {
    font-weight: bolder;
    margin-top: 35px;
    font-size: 15px;
    color: #433c3c;
    padding: 5px;
}
</style>
<title>비밀번호 찾기</title>
</head>
<body>
	<div style="margin-top: 10%;">
		<div class="w3-container w3-card-4 w3-auto" 
			 style="width: 360px; height: 330px; border-radius: 18px; box-shadow: 0 4px 10px 0 rgb(0 0 0 / 4%), 0 4px 20px 0 rgb(0 0 0 / 9%);">
			<div class="w3-center w3-large w3-margin-top">
				<h3 class="find-pw">비밀번호 찾기</h3>
			</div>
			<div style="margin: 30px; font-size: 13px;">
				<p>
					<label>사원번호</label>
					<input class="input-style" type="text" id="member_num" name="member_num" placeholder="사원번호를 입력하세요" required>
				</p>
				<p>
					<label>이메일</label>
					<input class="input-style" type="text" id="mail" name="mail" placeholder="이메일주소를 입력하세요" required>
				</p>
				<p class="w3-center">
					<button type="button" id="findBtn" class="mybtn">찾기</button>
					<button type="button" onclick="history.go(-1);" class="mybtn">로그인 화면</button>
				</p>
			</div>
		</div>
	</div>
</body>
</html>