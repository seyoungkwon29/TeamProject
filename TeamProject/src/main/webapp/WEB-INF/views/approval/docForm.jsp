<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="resources/css/docForm.css">
<script src="https://cdn.ckeditor.com/4.18.0/full-all/ckeditor.js"></script>

	<c:set var="login" value="${login}"></c:set> 
	<c:set var="form" value="${form}"></c:set> 
	<% 	
		//오늘 날짜
        java.util.Date today = new java.util.Date(); //오늘 날짜 얻는 java코드      
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd"); //SimpleDateFormat사옹: yyyy-MM-dd 형식으로 포맷팅
        String doc_date = sdf.format(today);	
	%>
	
	<div class="doc-form-container">
	
		<h1 class="head-title" id="h-title" style="letter-spacing: 10px; text-align: center;"> 
			${form.form_name} <!-- 문서 양식 이름 -->
		</h1>
		
		<form id="myForm" action="#" method="post" onsubmit="return chkValidity()" enctype="multipart/form-data">
			<!-- onsubmit: 폼이 제출되기 전 실행할 js함수를 지정, 반환값에 따라 제출 동작을 제어 
				 => return true: 제출, return false: 제출 중지 -->
			<!-- enctype="multipart/form-data": 파일 업로드와 같이 이진 데이터를 전송할 때 사용 -->
			<input type="hidden" value="${form.form_name}" name="form_name" readonly>
			<input type="hidden" value="${form.form_no}" name="form_no" readonly>
			<input type="hidden" value="${login.member_num}" name="member_num" readonly>
			<input type="hidden" value="<%= doc_date %>" name="doc_date" readonly> 			
 			<input type="hidden" id="num-app" name="appMemNum" readonly> <!-- 결재자 정보를 저장할 숨겨진 폼 필드 -->
 			<input type="hidden" id="num-ref" name="refMemNum" readonly> <!-- 참조자 정보를 저장할 숨겨진 폼 필드 -->
 			<input type="hidden" id="parameter" value="" name="parameter" readonly> <!-- 문서 정보 -->
					
			<table id="table">
				<tr class="tr-s">
					<td class="td-1" rowspan="2">문서번호</td>
					<td class="td-2" rowspan="2"></td>
					<td class="td-3" rowspan="5" style="writing-mode:vertical-rl;">결재</td>
					<td class="td-4">담당</td>
					<td class="td-4" id="r-app0"></td>
					<td class="td-4" id="r-app1"></td>
					<td class="td-4" id="r-app2" colspan="2"></td>
				</tr>
				<tr class="tr-s">
					<td rowspan="3"></td>
					<td align="center" rowspan="3">
						<button type="button" class="btn-select-app" onclick="appBtn('app')">결재자 선택</button></td>
					<td rowspan="3"></td>
					<td rowspan="3" colspan="2"></td>
				</tr>
				<tr class="tr-m">
					<td class="td-1">기안일</td>
					<td class="td-2"><%= doc_date %> </td>
				</tr>
				<tr class="tr-s">
					<td class="td-1" rowspan="2">기안자</td>
					<td class="td-5" rowspan="2">${login.member_name}</td>
				</tr>
				<tr class="tr-s">
					<td class="td-5">${login.member_name} </td>
					<td class="td-5" id="name-app0"></td>
					<td class="td-5" id="name-app1"></td>
					<td class="td-5" id="name-app2" colspan="2"></td>
				</tr>
				<tr class="tr-m">
					<td class="td-1">참조자</td>
					<td colspan="6" style="border-right: none;">
						<span class="s-refList" id="ref-list"></span>
					</td>
					<td class="td-btn" style="border-left: none;"> <!-- border-left: none; 왼쪽 테두리 삭제 -->
						<button type="button" class="btn-select-ref" onclick="appBtn('ref')">+</button></td>
				</tr>
				<tr id="tr-title" class="tr-m">
					<td class="td-1">제목</td>
					<td colspan="7">
						<input type="text" name="doc_title" id="td-title" class="i-title" value="">
					</td>
				</tr>
				
				<tr id="tr-title" class="tr-m">
					<td class="td-1">파일첨부</td>
					<td colspan="7">
						<div class="file-div">
			                <!-- lable: 파일 입력 필드(input[type="select_file"])를 활성화하기 위한 레이블(Label) 요소(for 속성을 사용하여 연결된 입력 필드를 지정)
			                	 주의점: 입력 필드의 id와 lable for의 이름 같을 것 -->
			                <label for="select_file">파일 선택</label> 
			               	<input type="file" id="select_file" name="upload_file" onchange="fileSelect(this.value)">
		               	
		      	       	 	<span id="fileName" class="file-name"> 선택된 파일이 없습니다. </span>
							<button type="button" id="fileDel" class="file-del" onclick="fileDelBtn()">X</button>
						</div>
				</tr>
				
		<!-- 문서 양식: 휴가신청서인 경우 -->
				<c:set var="form_name" value="${form.form_name}" />
				<c:if test="${form_name eq '휴가신청서'}">
	                <tr class="tr-m">
	                    <td class="td-1">휴가종류</td>
	                    <td colspan="7">
	                        <select id="leaveType" name="leaveType" class="leave-type">
	                            <option value="">선택</option>
	                            <option value="연차">연차</option>
	                            <option value="반차">반차</option>
	                            <option value="특별휴가">특별휴가</option>
	                            <option value="공가">공가</option>
	                            <option value="병가">병가</option>
	                        </select>
	                    </td>
	                </tr>
	                <tr class="tr-m">
	                    <td class="td-1">휴가기간</td>
	                    <td colspan="7" id="td-leave-date">
	                    	<div class="leave-date">
		                        <input type="date" name="leaveStart" onchange="leaveStartDate(event)" id="startDate">
		                        <span id="tilde">~</span>
		                        <input type="date" name="leaveEnd" id="endDate" onchange="leaveEndDate(event)"> 
		                        <span id="leaveTime" class="leave-time" style="display: none;">
		                        	<input type="radio" name="leaveTime" value="오전">오전
		                        	<input type="radio" name="leaveTime" value="오후">오후
		                        </span>
		                        <span class="leave-day">
		                        	<span>휴가 일수 : </span>
		                        	<span id="s-leaveDay"></span>
		                        </span>
		                        <input type="hidden" name="leaveDay" id="i-leaveDay" readonly>
	                   		</div>
	                    </td>
	                </tr>
	                <tr class="tr-m">
	                    <td class="td-1">연차일수</td>
	                    <td colspan="7" id="td-leave-day">
                        	<span>잔여 연차 : </span><input type="text" name="leaveLeft" id="left-leave" readonly>
                        	<span>신청 연차 : </span><input type="text" name="leaveApply" id="apply-leave" readonly>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-1">휴가사유</td>
	                    <td colspan="7" id="td-leave-reason">
	                    	<textarea name="doc_content"></textarea>
                    	</td>
	                </tr>
				</c:if>
				
		<!-- 문서 양식: 휴가신청서가 아닐 경우에 내용 -->
				<c:if test="${form_name ne '휴가신청서'}">
					<tr class="tr-m">
						<td colspan="8" class="td-content">내용</td>
					</tr>
					<tr>
						<td colspan="8">
							<textarea name="doc_content"></textarea>
						</td>
					</tr>
				</c:if>
			</table>
				
	<!-- 버튼 -->				
			<div class="footer-div">
				<input type="button" value="결재 요청" onclick="docSave()" class="footer-btn" id="i-left">
				<input type="button" value="임시 저장" onclick="tempSave()" class="footer-btn">
				<input type="button" value="취소" onclick="location.href='draftList?parameter=draft'" class="footer-btn">
			</div>
		</form>	
	</div>	
	
	<!-- 결재자/참조자 선택 모달창 -->
	<jsp:include page="approverModal.jsp" flush="true" />


<script>

	//파일 선택 이벤트 리스너
	function fileSelect(value) {
	    if($("#select_file").val() == "") { //파일 선택 안했을 때
	    	console.log(value);
	        $("#fileDel").css("display", "none");
	    } 
	    else { //파일 선택 했을 때
	    	console.log(value);
	        $("#fileDel").css("display", "inline-flex"); // X버튼 화면 출력 
 	        $("#fileName").text( value.slice(12) ); 
	        //파일 이름을 나타내는 요소(fileName)에서 현재 파일 경로에서 파일 이름만 추출(value.slice(12))= memo.text
	        //그냥 value값만 출력= C:\fakepath:memo.text
	    }
	}
	
	//선택한 파일 삭제
	function fileDelBtn() {
	    $("#select_file").val("");
	    $("#fileName").text("선택된 파일이 없습니다.");
	    fileSelect(); //파일 삭제하고 버튼 숨기기
	}
	
	//결재 요청
    function docSave() {
        var result = confirm("결재 요청하시겠습니까?"); //메시지를 포함한 확인 상자(확인, 취소 버튼) 표시
      //확인(ok)클릭 result 변수에 true저장, 취소(cancel)클릭 result 변수에 false 저장
        if(result == true) { //확인(ok)버튼 클릭한 경우
            $("#parameter").val("Doc"); //parameter 필드의 값을 "Doc"로 설정
        
        	//form 요소의 action 속성 변경
            $("#myForm").attr("action", "SaveDocForm");
            $("#myForm").submit();
        }
    }
	
    //임시 저장
    function tempSave() {
		$("#parameter").val("Temporary"); //parameter 필드의 값을 "Temporary"로 설정
		
        $("#myForm").attr("action", "SaveDocForm");
        $("#myForm").submit();
    } 
	
    //$("#form").submit() 전에 유효성 체크
    function chkValidity() {
        if($("#td-title").val() == "") { //제목 입력 안했을 경우
            alert("제목을 입력해주세요.");
            $("#td-title").focus();
            return false; //제출 중지 
        } 
        else if($("#num-app").val() == "") { //결재자 안했을 경우
            alert("결재자를 선택해주세요.");
            return false; //제출 중지 
        }
    }
	
	if("${form.form_name}" !== "휴가신청서"){
		CKEDITOR.replace( 
			'doc_content', 
		{
			height: 300,
			removePlugins: "exportpdf"
		} );
	}

</script>