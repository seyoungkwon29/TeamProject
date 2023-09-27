<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>비밀번호 찾기</title>
	<link href="resources/css/pwsearch.css" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(function(){
		
		
		
		
	});
</script>

</head>
<body>
	<div class="p-5">
		<div class="text-center">
			<h1 class="h4 text-gray-900 mb-2">비밀번호 찾기</h1>
			<p class="mb-4">아이디와 이메일을 입력해주세요!</p>
		</div>
		
		<form class="user" action="/member/findPw" method="post">
			<div class="form-group">
				<input type="text" class="form-control form-control-user"
					id="memberId" aria-describedby="IdHelp" name="memberId"
					placeholder="사원번호를 입력하세요.">
			</div>
			<div class="form-group">
				<input type="email" class="form-control form-control-user"
					id="memberEmail" aria-describedby="emailHelp" name="memberEmail"
					placeholder="이메일 주소를 입력하세요">
			</div>

		<button type="submit" id="findBtn" class="btn btn-primary btn-user btn-block">비밀번호 찾기</button>
		</form>
		<button type="button" onclick="history.go(-1);" class="w3-button w3-hover-white w3-ripple w3-margin-top w3-round mybtn">로그인으로</button>
		<hr>
	</div>
</body>
</html>