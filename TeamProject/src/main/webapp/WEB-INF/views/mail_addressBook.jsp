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
	.footer-btn{
		background: #eff5f275;
	    color: #2a1c1c;
	    padding: 7px 15px;
	    border: 1px solid #8080804a;
	    border-radius: 5px;
	    cursor: pointer;
	    margin-right: 12px;
    }
   	.footer-btn:hover{
		background: rgba(230, 230, 230, 0.25);
		border: 2px solid #21875a;
		font-weight: bold;
    }
    .td-st{
   		border-bottom: 1px solid #e8dfdf;
    }
    .table-st{
   		width: 420px;
        border-top: 2px solid rgb(200, 200, 200);
   		border-bottom: 2px solid rgb(200, 200, 200);
   		border-collapse: collapse;
    }
    .table-st td{
        border-top: 1px solid rgb(200, 200, 200);
    }
</style>

<head>

<title>주소록</title>
    
</head>

<body>

    <!-- 주소록 모달 -->
    <div class="addressBookContain" class="modal" style="width: 450px;
		height: 500px;border: solid 1px #8080806e;padding: 20px 30px 50px 30px;text-align: center;border-radius: 8px;">
        <div class="modal-header">
            <h2 style="letter-spacing: 3px;">주소록</h2>

        </div>
        <div class="addressBook-body">
	        	<div class="address-list">
	            <table class="table-st">
	            	<tr>
<!-- 	            		<th>&emsp;</th> -->
	            		<th colspan="2"  style="font-size: 13px;background: #69696921;padding: 8px;">&emsp;&emsp;&emsp;부서</th>
	            		<th style="font-size: 13px;background: #69696921;padding: 8px;text-align: center;">이름</th>
	            		<th style="font-size: 13px;background: #69696921;padding: 8px;text-align: center;">이메일</th>
	            	</tr>
	            	
	            	<c:forEach var="member" items="${memberList }">
	            	<tr class="td-st">
	            		<td><input type="checkbox" value="'${member.mail }'" class="selectedMail"/></td>
	            		<td style="padding: 8px;text-align: center;font-size: 13px;">${member.div_name }</td>
						<td style="padding: 8px;text-align: center;font-size: 13px;">${member.member_name }</td>
						<td style="padding: 8px;text-align: center;font-size: 13px;">${member.mail }</td>
					</tr>
					</c:forEach>
				</table>
	            </div>
	            <br>
		        <div class="addressBook-btn">
		        	<button id="btn_selectAddressBook" class="footer-btn">확인</button>
       		        <button id="btn_closeAddressBook" class="footer-btn">취소</button>
		        
<!-- 					<input type="submit" id="btn_closeAddressBook" value="취소">&emsp;
					<input type="submit" id="btn_selectAddressBook" value="확인"><br> -->
		        </div>

        </div>

    </div>

</body>

</html>