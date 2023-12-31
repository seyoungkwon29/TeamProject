<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.ckeditor.com/4.18.0/full-all/ckeditor.js"></script>
<link rel="stylesheet" href="resources/css/docForm.css">

	<c:set var="login" value="${login}"></c:set> <!-- 본인 로그인 정보 -->
	<c:set var="doc" value="${docDetail}"></c:set> <!-- 문서 상세 내용 -->
	<c:set var="file" value="${fileList}"></c:set>  <!-- 파일 -->
	<c:set var="app" value="${appMemList}"></c:set>  <!-- 결재자 -->
	<c:set var="ref" value="${refMemList}"></c:set>  <!-- 참조자 -->

	<div class="doc-form-container">
	
		<h1 class="head-title" id="h-title" style="letter-spacing: 10px; margin: 30px 0;text-align: center;"> 
			${doc.form_name } <!-- 문서 양식 이름 -->
		</h1>

		<form id="myForm" action="#" method="post" onsubmit="return chkValidity()" enctype="multipart/form-data">
			<!-- onsubmit: 폼이 제출되기 전 실행할 js함수를 지정, 반환값에 따라 제출 동작을 제어 
				 => return true: 제출, return false: 제출 중지 -->
			<!-- enctype="multipart/form-data": 파일 업로드와 같이 이진 데이터를 전송할 때 사용 -->	
			<input type="hidden" value="${doc.form_name}" name="form_name" readonly> <!-- 문서 양식 -->
			<input type="hidden" value="${nowDate }" name="doc_date" readonly> <!-- 기안일 -->
			<input type="hidden" value="${doc.member_name }" name="member_name" readonly> <!-- 기안자 -->
			<input type="hidden" value="${doc.member_num }" name="member_num" readonly> <!-- 기안자 사번 -->
 			<input type="hidden" id="num-app" name="appMemNum" readonly> <!-- 결재자 정보 -->
 			<input type="hidden" id="num-ref" name="refMemNum" readonly> <!-- 참조자 정보 -->
 			<input type="hidden" id="parameter" name="parameter" readonly> <!-- 문서정보 -->

			<table id="table">
				<tr class="tr-s">
					<td class="td-1" rowspan="2">문서번호</td>
					<c:if test="${type == 'temp' }">
						<td class="td-2" rowspan="2">${doc.doc_no }
						<input type="hidden" name="doc_no" value="${doc.doc_no }" readonly>
						</td>
					</c:if>
					<c:if test="${type == 'rej' }">
						<td class="td-2" rowspan="2">${doc.doc_no }
						<input type="hidden" name="doc_no" value="${doc.doc_no }" readonly>
						</td>
					</c:if>
					<td class="td-3" rowspan="5" style="writing-mode: vertical-rl;">결재</td>
					<td class="td-4">담당</td>
					<td class="td-4" id="r-app0">${app[0].rank }</td>
					<td class="td-4" id="r-app1">${app[1].rank }</td>
					<td class="td-4" id="r-app2" colspan="2">${app[2].rank }</td>
				</tr>
				<tr class="tr-s">
					<td rowspan="3"></td>
					<td align="center" rowspan="3">
						<button type="button" class="btn-select-app" onclick="appBtn('app');">결재자 선택</button>
					</td>
					<td rowspan="3"></td>
					<td rowspan="3" colspan="2"></td>
				</tr>
				<tr class="tr-m">
					<td class="td-1">기안일</td>
					<td class="td-2">${nowDate }
					</td>
				</tr>
				<tr class="tr-s">
					<td class="td-1" rowspan="2">기안자</td>
					<td class="td-5" rowspan="2">${doc.member_name }</td> <!-- 기안자 -->
				</tr>
				<tr class="tr-s">
					<td class="td-5">${doc.member_name }</td>
					<td class="td-5" id="name-app0">${app[0].member_name }</td> <!-- 결재자 -->
					<td class="td-5" id="name-app1">${app[1].member_name }</td>
					<td class="td-5" id="name-app2" colspan="2">${app[2].member_name }</td>
				</tr>
				<tr class="tr-m">
					<td class="td-1">참조자</td>
					<td colspan="6" style="border-right: none;">
						<span class="s-refList" id="ref-list">
							<c:forEach items="${refMemList}" var="ref" varStatus="index">
								<c:choose>
									<c:when test="${!index.last}">
										${ref.div_name } ${ref.member_name } ${ref.rank },
									</c:when>
									<c:when test="${index.last}">
										${ref.div_name } ${ref.member_name } ${ref.rank }
									</c:when>
		    					</c:choose>
							</c:forEach>
						</span>
					</td>
					<td class="td-btn" style="border-left: none;"> <!-- border-left: none; 왼쪽 테두리 삭제 -->
						<button id="app-btn" type="button" class="btn-select-ref" onclick="appBtn('ref');">+</button>
					</td>
				</tr>
				<tr id="tr-title" class="tr-m">
					<td class="td-1">제목</td>
					<td colspan="7">
						<input type="text" name="doc_title" id="td-title" class="i-title" value="${doc.doc_title}">
					</td>
				</tr>
				
				<tr id="tr-title" class="tr-m">
					<td class="td-1">파일첨부</td>
					<td colspan="7">
    					<div class="file-div">						
							<!-- 임시 저장 -->
							<c:if test="${type == 'temp' }">
								<label for="select_file">파일 선택</label>
							
								<input id="select_file" type="file" name="upload_file" onchange="fileSelect(this.value)">
								<span id="fileName" class="file-name">
									<c:if test="${file.file_name != null }">
										${file.file_name }
										<button type="button" id="btn-delete" class="file-del2" onclick="deleteFile('${file.file_path}','${file.doc_no}' );">X</button>
									</c:if>
									
									<c:if test="${file.file_name == null }">
										선택된 파일이 없습니다.
									</c:if>
								</span>
							</c:if>
							
							<!-- 반려 재상신 -->
							<c:if test="${type == 'rej' }">			
								<label for="select_file">파일 선택</label>
			               		<input id="select_file" type="file" name="upload_file" onchange="fileSelect(this.value)">
			               		<span id="fileName" class="file-name"> 선택된 파일이 없습니다. </span>
							</c:if>
							
							<button type="button" id="fileDel" class="file-del" style="display: none;" onclick="fileDelBtn()">X</button>
                    	</td>
				</tr>
				
				
				
			<!-- 문서 양식 : 휴가 신청서일 경우 -->				
				<c:set var="form_name" value="${doc.form_name}" />		
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
		                    	<input type="date" name="leaveStart" onchange="leaveStartDate(event)" id="startDate" value="${appDoc.leaveStart }">
		                        <span id="tilde">~</span>
		                        <input type="date" name="leaveEnd" id="endDate" value="${appDoc.leaveEnd }" onchange="leaveEndDate(event)"> 
		                        <span id="leaveTime" class="leave-time" style="display: none;">
		                        	<input type="radio" name="leaveTime" id="am" value="오전">오전
		                        	<input type="radio" name="leaveTime" id="pm" value="오후">오후
		                        </span>
		                        <span class="leave-day">
	                        		<span>휴가 일수 : </span>
	                        		<span id="s-leaveDay">${appDoc.leaveDay }</span>
	                        	</span>
	                        	<input type="hidden" name="leaveDay" id="i-leaveDay" value="${appDoc.leaveDay }" readonly>
	                    	</div>
	                    </td>
	                </tr>
	                <tr class="tr-m">
	                    <td class="td-1">연차일수</td>
	                    <td colspan="7" id="td-leave-day">
                        	<span>잔여 연차 : </span><input type="text" name="leaveLeft" id="left-leave" value="${appDoc.leaveLeft}" readonly>
                        	<span>신청 연차 : </span><input type="text" name="leaveApply" id="apply-leave" value="${appDoc.leaveApply}" readonly>
	                    </td>
	                </tr>
	                <tr>
	                    <td class="td-1">휴가사유</td>
	                    <td colspan="7" id="td-leave-reason">
	                    	<textarea id="summernote" name="doc_content">${appDoc.docContent}</textarea>
                    	</td>
	                </tr>
				</c:if>
				
			<!-- 문서 양식 : 휴가 신청서 아닐 경우 -->				
				<c:if test="${form_name ne '휴가신청서'}">
					<tr class="tr-m">
						<td colspan="8" class="td-content">내용</td>
					</tr>
					<tr>
						<td colspan="8">
							<textarea id="summernote" name="doc_content" class="td-content">${doc.doc_content }</textarea>
						</td>
					</tr>
				</c:if>
			</table>
			

			<!-- 각종 버튼-->			
			<c:if test="${type == 'temp' }">
				<div class="foot-div" style="margin: 30px 50px 50px 30px; float: right;">
					<input type="button" value="결재 요청" onclick="docSave()" class="footer-btn">
					<input type="button" value="임시 저장" onclick="tempSave()" class="footer-btn">
					<input type="button" id="btn-cancel" class="footer-btn" value="삭제">
					<input type="button" value="취소" onclick="location.href='draftList?parameter=temp'" class="footer-btn">
				</div>

			</c:if>
			
			<!-- 반려 재상신 -->
			<c:if test="${type == 'rej' }">
				<div class="foot-div" style="margin: 30px 50px 50px 30px; float: right;">
					<input type="button" value="결재 요청" onclick="docSave()" class="footer-btn">
					<input type="button" value="임시 저장" onclick="rejTempSave()" class="footer-btn">
					<input type="button" value="취소" onclick="location.href='draftList?parameter=draft'" class="footer-btn">
				</div> 
			</c:if>
		</form>
	</div>
	
	<!-- 결재자/참조자 선택 모달 -->				
	<jsp:include page="approverModal.jsp"></jsp:include>
	
<!-- script -->	
	<script>
	
	//파일 선택 이벤트 리스너
	function fileSelect(value) {
	    if($("#select_file").val() == "") { //파일 선택 안했을 때
	        $("#fileDel").css("display", "none");
	    	console.log(value);
	    } 
	    else { //파일 선택 했을 때
	        $("#fileDel").css("display", "inline-flex"); // X버튼 화면 출력 
 	        $("#fileName").text( value.slice(12) ); 
	        //파일 이름을 나타내는 요소(fileName)에서 현재 파일 경로에서 파일 이름만 추출(value.slice(12))=  memo.text
	        //그냥 value값만 출력= C:\fakepath:memo.text
	    }
	}
	
	//반려: 저장된 파일이 없을 경우에 X버튼 화면에서 안보이기	
	if("${file.file_name}" == null ) { 
		$(".file-del2").css("display", "none");
	}
	
	//임시저장: 선택한 파일 삭제
	function fileDelBtn() {
	    $("#select_file").val("");
	    $("#fileName").text("선택된 파일이 없습니다.");
	    fileSelect(); //파일 삭제하고 버튼 숨기기
	}
	    
	//저장된 파일 삭제	
	function deleteFile(file_path, doc_no){
		location.href="uploadFileDelete?filePath="+file_path+"&docNo="+doc_no;
	}
	
	//반려 문서 재상신
	if( "${type}" == "rej") {
		$("#parameter").val("rejDoc");
		
		$("#myForm").attr("action", "SaveDocForm");
	}

	//반려 재상신 => 결재 요청
	function docSave() {
		if ( $("#num-app").val() == "" )  { //결재자 선택 안했을 경우
			$("#parameter").val("tempRedraft");
    	} else { //결재자 선택 시
    		$("#parameter").val("Doc")
    	}
	    var result = confirm("결재 요청하시겠습니까?"); //메시지를 포함한 확인 상자(확인, 취소 버튼) 표시
	 	 //확인(ok)클릭 result 변수에 true저장, 취소(cancel)클릭 result 변수에 false 저장
		if(result == true) { //확인(ok)버튼 클릭한 경우
			$("#myForm").attr("action", "SaveDocForm");
			$("#myForm").submit();
		}
	}

    //form을 전송하기 전에 유효성 체크
    function chkValidity() {
        if($("#td-title").val() == "") { //제목 입력 안했을 경우
            alert("제목을 입력해주세요.");
            $("#td-title").focus();
            return false; //제출 중지 
        } 
        else if( "${app[0].member_name}".length === 0) { //결재자 안했을 경우
            alert("결재자를 선택해주세요.");
            return false; //제출 중지 
        }
    }
	
    //임시 저장을 임시저장
    function tempSave() {
    	if ( $("#num-app").val() == "" )  { //결재자 선택 안했을 경우
			$("#parameter").val("tempTemp");
    	} else { //결재자 선택 시
    		$("#parameter").val("Temporary")
    	}
        $("#myForm").attr("action", "SaveDocForm");
        $("#myForm").submit();
    } 
	
	//반려 문서 임시 저장
	function rejTempSave() {
		$("#parameter").val("RejTemp");
		
		$("#myForm").attr("action", "SaveDocForm");
		$("#myForm").submit();
	}
	
	//삭제 확인
	$("#btn-cancel").click(function() { //삭제 클릭 시 확인창 뜨고 확인 누르면 삭제 실행
		var result = confirm("삭제하시겠습니까?");
		if(result == true) {
			location.href = 'draftDocCancel?type=temp&docNo=${doc.doc_no}'
		}
	})
	
	if("${form.form_name}" !== "휴가신청서"){
		CKEDITOR.replace( 
			'doc_content', 
		{
			height: 300,
			removePlugins: "exportpdf"
		} );
	}
	
	// 휴가신청서
	if("${appDoc.formName }" === "휴가신청서"){
		$("#tr-title").css("display", "none"); // 제목 행 숨기기
	    var leaveStart = new Date($("#startDate").val()); // 휴가 시작일 변수 선언
	    var leaveEnd = new Date($("#endDate").val());   // 휴가 종료일 변수 선언
	    var leaveDay = $("#i-leaveDay").val();   // 휴가 일수 변수 선언
	    var totalLeave = 0; // 잔여 연차
	    var memberNum = "${loginUser.memberNum}"; // 로그인 한 유저의 사원번호
	    var breakTotal = "${loginUser.breakTotal}"; // 로그인 한 유저의 총 연차수
		$("#leaveType").val("${appDoc.leaveType }").prop("selected", true); // 휴가 종류 불러와서 선택 값으로 넣기
		if("${appDoc.leaveType }" === "반차") { // 휴가 종류가 반차인 경우
			$("#endDate").css("display", "none"); // 휴가 종료일 숨기기
            $("#tilde").css("display", "none"); // 휴가 시작일과 종료일 사이 '~' 숨기기
            $("#leaveTime").css("display", "inline-flex"); // 오전/오후 보이기
			if("${appDoc.leaveTime }" === "오전") {
				$("#am").prop("checked", true);
			}else if("${appDoc.leaveTime }" === "오후") {
				$("#pm").prop("checked", true);
			}
		}
		$.ajax({ // 잔여 연차 조회
	 		url : "/approval/leaveDocSearch.sw",
	 		type : "get",
	 		data : { "memberNum" : memberNum },
	 		success : function(leaveLeft) {
	 			totalLeave = leaveLeft;
	 			$("#left-leave").val(totalLeave - leaveDay); // 잔여 연차 조회해서 휴가 일수 빼기
	 		},
	 		error : function() {
	 			console.log("잔여 연차 조회 실패");
	 		}
	 	})
	    function leaveStartDate(e) { // 휴가 시작일 값 날짜화
	        leaveStart = new Date(e.target.value);
	        calLeaveDate();
	        chkLeave();
	    }
	    function leaveEndDate(e) { // 휴가 종료일 값 날짜화
	        leaveEnd = new Date(e.target.value);
	        calLeaveDate();
	        chkLeave();
	    }
	    function calLeaveDate() { // 휴가 날짜 차이 계산(연차, 반차인 경우에만)
	        if($("#leaveType").val() == "반차") { // 반차인 경우
	            leaveDay = 0.5;
	        }else {
	            if(leaveStart !== 0 && leaveEnd !== 0){
	                var dateDiff = leaveEnd.getTime() - leaveStart.getTime();
	                leaveDay = Math.abs(dateDiff / (1000 * 3600 * 24) + 1);
	            }else {
	            	leaveDay = 0;
	            }
	        }
	        setLeaveDay(leaveDay);
	        viewLeaveDay(leaveDay);
	    }
	    $("#leaveType").change(function() { // 휴가 종류 선택에 따라 처리하는 함수
	        var leaveType = $("#leaveType").val();
	        $("#td-title").val(leaveType + " 신청합니다.");
	        if($(this).val() == "반차") {
	            leaveDay = 0.5;
	            $("#leaveTime").css("display", "inline-flex"); // 오전/오후 보이기
	            $(":radio[name='leaveTime'][value='오전']").prop("checked", true); // 휴가 시간 라디오 버튼 오전 자동으로 체크
	            $("#endDate").css("display", "none"); // 휴가 종료일 숨기기
	            $("#tilde").css("display", "none"); // 휴가 시작일과 종료일 사이 '~' 숨기기
	            setLeaveDay(leaveDay);
	            viewLeaveDay(leaveDay);
	        }else{
	        	calLeaveDate();
	            $("#leaveTime").css("display", "none"); // 오전 오후 숨기기
	            $(":radio[name='leaveTime']").prop("checked", false); // 휴가 시간 라디오 버튼 체크 해제
	            $("#endDate").css("display", "inline-flex"); // 휴가 종료일 보이기
	            $("#tilde").css("display", "inline-flex"); // 휴가 시작일과 종료일 사이 '~' 보이기
	            setLeaveDay(leaveDay);
	            viewLeaveDay(leaveDay);
	        }
	    });
	    function viewLeaveDay(leaveDay) { // 휴가 종류에 따라 연차 일수 표시
	        if($("#leaveType").val() == "연차" || $("#leaveType").val() == "반차") { // 반차 또는 연차인 경우
	            $("#apply-leave").val(leaveDay);
	            $("#left-leave").val(totalLeave - leaveDay);
	        }else {
	            $("#left-leave").val(totalLeave);
	            $("#apply-leave").val(0);
	        }
	    }
	    function setLeaveDay(leaveDay) { // 휴가 일수 표시 및 value 값 넣기
	        $("#s-leaveDay").text(leaveDay);
	        $("#i-leaveDay").val(leaveDay);
	    }
	    function chkLeave(){ // 신청 연차가 잔여 연차보다 클 경우 alert창 띄우고 종료일 초기화
		    if(leaveDay > totalLeave) { 
		    	if($("#leaveType").val() == "연차" || $("#leaveType").val() == "반차"){
		    		alert("신청 연차 수가 잔여 연차 수를 초과했습니다.");
		    		$("#endDate").val("");
		    		leaveDay = 0;
		    	}
		    	setLeaveDay(leaveDay);
		        viewLeaveDay(leaveDay);
		    }
	    }
	};
	
</script>