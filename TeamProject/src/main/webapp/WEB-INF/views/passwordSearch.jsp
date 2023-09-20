<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>

<form action="#" method="get">
	<table border='1'>
		<tr>
			<th>사원번호</th>
			<td width=300px align="center">
				<input type="text" id="member_num" placeholder="사원번호 입력">
			</td>
			<td>
				<span id="idCheck"></span>
			</td>
		</tr>	
		<tr>
			<th>메일</th>
			<td width=300px align="center" rowspan="2">
				<input type="text" id="mail" placeholder="메일 입력">@
				
			</td>
		</tr>
	</table>	 

</form>
</body>
</html>