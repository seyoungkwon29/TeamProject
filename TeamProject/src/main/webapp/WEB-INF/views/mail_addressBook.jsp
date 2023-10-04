<%@page import="com.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn_closeAddressBook").click(function(){
		window.opener.$("#mail_receiver").val("");
		window.close();
	});
	$("#btn_selectAddressBook").click(function(){
		var arr = $(".selectedMail:checked");
		var addressList="";
		
		for(var i=0; i<arr.length; i++){
			addressList += arr[i].value.replaceAll("'","") + " ";
		}
		addressList = addressList.substring(0,(addressList.length-1)); //마지막 문자열 공백 지우기
		window.opener.$("#mail_receiver").val(addressList);
		window.close();


	});
	
	
	
}); //end doc function
	
</script>

<style>
	body {
	    margin: 0;
	    min-height: 100vh;
	    display: flex;
	    justify-content: center; /* 가로 중앙 정렬 */
	    align-items: center;     /* 세로 중앙 정렬 */
	}
	
	.addressBookContain {
	    width: 450px;
	    height: 500px;
	    border: solid 1px;
	    padding: 20px;
	    text-align: center;
		
	}
	.addressBookContain .addressBook-body {
		
		
	}
	.addressBookContain .addressBook-body .address-list{
		height: 400px;
		overflow: scroll;
	}
</style>

<head>

<title>주소록</title>
    
</head>

<body>

    <!-- 주소록 모달 -->
    <div class="addressBookContain" class="modal">
        <div class="modal-header">
            <h2>주소록</h2>

        </div>
        <div class="addressBook-body">
	        	<div class="address-list">
	            <table>
	            	<tr>
	            		<th>&emsp;</th>
	            		<th>&emsp;&emsp;부서&emsp;</th>
	            		<th>&emsp;&emsp;이름&emsp;</th>
	            		<th>&emsp;이메일&emsp;</th>
	            	</tr>
	            	
	            	<c:forEach var="member" items="${memberList }">
	            	<tr>
	            		<td><input type="checkbox" value="'${member.mail }'" class="selectedMail"/></td>
	            		<td>&emsp;${member.div_name }</td>
						<td>&emsp;${member.member_name }</td>
						<td>&emsp;${member.mail }</td>
					</tr>
					</c:forEach>
				</table>
	            </div>
	            <br>
		        <div class="addressBook-btn">
		        <button id="btn_closeAddressBook">취소</button>
		        <button id="btn_selectAddressBook">확인</button>
<!-- 					<input type="submit" id="btn_closeAddressBook" value="취소">&emsp;
					<input type="submit" id="btn_selectAddressBook" value="확인"><br> -->
		        </div>

        </div>

    </div>

</body>

</html>