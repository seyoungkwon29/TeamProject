
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="resources/css/docDetail.css">
</head>

<body>
	<c:set var="login" value="${login}"></c:set> <!-- 본인 로그인 정보 -->
	<c:set var="doc" value="${docDetail}"></c:set> <!-- 문서 상세 내용 -->
	<c:set var="file" value="${fileList}"></c:set>  <!-- 파일 -->
	<c:set var="app" value="${appMemList}"></c:set>  <!-- 결재자 -->
	<c:set var="ref" value="${refMemList}"></c:set>  <!-- 참조자 -->
	
	<div class="doc-container"> 
	
		<h1 class="head-title" id="h-title"> ${doc.form_name} </h1> <!-- 문서 제목 -->
		<table id="table">
			<tr class="tr-s">
				<td class="td-1" rowspan="2">문서번호</td>
				<td class="td-2" rowspan="2">${doc.doc_no}</td> <!-- 문서 번호 -->
				<td class="td-3" rowspan="5" style="writing-mode: vertical-rl;">결재</td>
				<td class="td-4">담당</td>
				<td class="td-4" id="r-app0">${app[0].rank}</td> <!-- 결재자 직급 -->
				<td class="td-4" id="r-app1">${app[1].rank}</td>
				<td class="td-4" id="r-app2">${app[2].rank}</td>
			</tr>
			
			<tr class="tr-s">  <!-- 승인, 반려 구분 -->
				<td class="td-6" rowspan="2" style="border-bottom: none;">
					<img alt="승인" src="resources/image/stamp.png" height="45px" width="45px">
				</td>
				
				<td class="td-6" rowspan="2" style="border-bottom: none;">
					<c:if test="${app[0].app_status == '완료'}">
						<img alt="승인" src="resources/image/stamp.png"  height="45px" width="45px">
					</c:if>
					<c:if test="${app[0].app_status == '반려'}">
						<img alt="반려" src="resources/image/redX.png"  height="45px" width="45px">
					</c:if>
				</td>
				
				<td class="td-6" rowspan="2" style="border-bottom: none;">
					<c:if test="${app[1].app_status == '완료'}">
						<img alt="승인" src="resources/image/stamp.png"  height="45px" width="45px">
					</c:if>
					<c:if test="${app[1].app_status == '반려'}">
						<img alt="반려" src="resources/image/redX.png"  height="45px" width="45px">
					</c:if>
				</td>
				
				<td class="td-6" rowspan="2" style="border-bottom: none;">
					<c:if test="${app[2].app_status == '완료'}">
						<img alt="승인" src="resources/image/stamp.png"  height="45px" width="45px">
					</c:if>
					<c:if test="${app[2].app_status == '반려'}">
						<img alt="반려" src="resources/image/redX.png"  height="45px" width="45px">
					</c:if>
				</td>
			</tr>
			
			<tr class="tr-m">
				<td class="td-1">기안일</td>
				<td class="td-2">${doc.doc_date}</td>
			</tr>
			<tr class="tr-s">
				<td class="td-1" rowspan="2">기안자</td>
				
				<c:if test="${type == 'draft'}"> <!-- 기안문서함 : 기안자 본인 이름 -->
					<td class="td-5" rowspan="2">${login.member_name}</td>
				</c:if>
			
				<c:if test="${type == 'app' || type == 'rej' }"> <!-- 결재문서함: 결재 올린 사원 이름 -->
					<td class="td-5" rowspan="2">${doc.member_name}</td> 
				</c:if>
				
				<td class="td-6" style="border-top: none;">${doc.doc_date}</td> <!-- 기안 상신 일자 -->
				<td class="td-6" style="border-top: none;">${app[0].app_date }</td> <!-- 결재자의 결재 일자 -->
				<td class="td-6" style="border-top: none;">${app[1].app_date }</td>
				<td class="td-6" style="border-top: none;">${app[2].app_date }</td>
			</tr>
			
			<tr class="tr-s">
			
				<c:if test="${type == 'draft'}"> <!-- 기안문서함 : 기안자 본인 이름 -->
					<td class="td-5">${login.member_name}</td> 
				</c:if>
				
				<c:if test="${type == 'app' || type == 'rej'}"> <!-- 결재문서함: 결재 올린 사원 이름 -->
					<td class="td-5">${doc.member_name}</td> 
				</c:if>
				
				<td class="td-5" id="name-app0">${app[0].member_name }</td> <!-- 결재자 이름 -->
				<td class="td-5" id="name-app1">${app[1].member_name }</td>
				<td class="td-5" id="name-app2">${app[2].member_name }</td>
			</tr>
			
			<tr class="tr-m">
				<td class="td-1">참조자</td>
				<td colspan="6" id="ref-list" class="indent">
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
				</td>
			</tr>
			
			<tr id="tr-title" class="tr-m">
				<td class="td-1">제목</td>
				<td colspan="6" class="indent">${doc.doc_title}</td>
			</tr>
			
<!-- 문서 양식 : 휴가 신청서일 경우 -->
			<c:set var="form_name" value="${doc.form_name}" />		
			<c:if test="${form_name eq '휴가신청서'}">
                <tr class="tr-m">
                    <td class="td-1">휴가종류</td>
                    <td colspan="6" class="indent">
                        ${appDoc.leaveType }
                    </td>
                </tr>
                <tr class="tr-m">
                    <td class="td-1">휴가기간</td>
                    <td colspan="6" id="td-leave-date" class="indent">
                    	<c:if test="${appDoc.leaveType ne '반차'}">
                        	${appDoc.leaveStart } ~ ${appDoc.leaveEnd }
                        </c:if>
                        <c:if test="${appDoc.leaveType eq '반차'}">
                        	${appDoc.leaveStart }
                        	&nbsp;&nbsp;${appDoc.leaveTime }
                        </c:if>
                        	&nbsp;&nbsp;휴가 일수 : ${appDoc.leaveDay }
                    </td>
                </tr>
                <tr class="tr-m">
                    <td class="td-1">연차일수</td>
                    <td colspan="6" id="td-leave-day" class="indent">
                       	잔여 연차 : <c:if test="${appDoc.leaveLeft != null}">${appDoc.leaveLeft}</c:if><c:if test="${appDoc.leaveLeft == null}">0</c:if>
                       	&nbsp;&nbsp;신청 연차 : <c:if test="${appDoc.leaveApply != null}">${appDoc.leaveApply}</c:if><c:if test="${appDoc.leaveApply == null}">0</c:if>
                    </td>
                </tr>
                <tr>
                    <td class="td-1">휴가사유</td>
                    <td colspan="6" id="td-leave-reason" class="indent">${appDoc.docContent}</td>
                </tr>
			</c:if>

<!-- 문서 양식 : 휴가 신청서 아닐 경우 -->
			<c:if test="${form_name ne '휴가신청서'}">
				<tr class="tr-m">
					<td colspan="7" class="td-content">내용</td>
				</tr>
				<tr>
					<td colspan="7" class="td-content-val" style="padding-left: 10px; padding-bottom: 15px">
						${doc.doc_content} <!-- 문서 내용 -->
					</td>
				</tr>
			</c:if>
		</table>
<!-- 파일 첨부 -->
		<div class="div-span">
			<c:if test="${file.file_path != null}">
				<div class="div-file">
					<span class="material-icons" id="file-icon">  </span>
					<span>첨부 파일</span>
					<a href="resources/uploadFiles/${file.file_name}"download>${file.file_name}</a>
				</div>
			</c:if>

			<c:forEach items="${appMemList}" var="app">
				<c:if test="${app.rej_reason != null }">
					<div class="div-rej">
						<span class="rej-icons">
							<img alt="승인" src="resources/image/redX.png"  height="40px" width="40px">
						</span>
						<span class="div-rejName">반려 사유</span>
						<span class="div-rejReason">${app.rej_reason}</span>
					</div>
				</c:if>
			</c:forEach>
		</div>
		
<!-- 각종 버튼-->
		<div class="div-btn">
			<c:if test="${type == 'draft'}">
				<input type="button" id="btn-cancel" value="상신 취소" class="i-left">
			</c:if>
			
			<c:if test="${type == 'app'}">
				<input type="button" id="btn-app" value="승인" class="i-left">
				<input type="button" id="btn-rej" value="반려">
			</c:if>
			
			<c:if test="${type == 'draft'}">
				<c:if test="${doc.doc_status == '반려' }">
					<input type="button" value="재상신" class="i-left" 
					 onclick="location.href='clickDocContent?type=rej&docNo=${doc.doc_no}&docStatus=${doc.doc_status }'">
					<input type="button" value="삭제" id="rej-delete">
				</c:if>
			</c:if>
			
<!-- 목록 버튼 -->
			<c:if test="${type == 'draft'}">
				<input type="button" value="목록" onclick="location.href='draftList?parameter=draft'"> <!-- 기안 문서함 -->
			</c:if>
			
			<c:if test="${type == 'app' || type == 'rej' }"> <!-- 반려 등록 시, 결재문서함 -->
				<input type="button" value="목록" onclick="location.href='draftList?parameter=app'">  <!-- 결재 문서함 -->
			</c:if>
			
			
		</div>
	</div>
	
	
	<script>
		$("#btn-app").click(function() { // 승인 확인 창
			var result = confirm("기안을 승인 하시겠습니까?"); //확인 버튼을 누를 때 result == true로 저장
			if(result == true) {
				location.href = 'approveAppStatus?type=app&docNo=${doc.doc_no}';
				
			}
		})
		
		$("#btn-rej").click(function() { //반려 확인 창
			var rej_reason = prompt("반려 사유를 입력해주세요");
		
			if(rej_reason != null) { //취소 누르면 null 반환하므로 null이 아닌 경우에만 처리
				var result = confirm( "기안을 반려하시겠습니까?" );
				if(result == true) {
					location.href = 'approveAppStatus?type=rej&docNo=${doc.doc_no}&rejReason=' + rej_reason;
				}
			}
		})
		
		$("#btn-cancel").click(function() { //상신 취소
			var result = confirm("기안 상신을 취소하시겠습니까?"); // 메시지를 포함한 확인 상자(확인, 취소 버튼) 표시
			 // 확인(ok)클릭 => result 변수에 true저장, 취소(cancel)클릭 => result 변수에 false 저장
			 
			if(result == true) { //확인(ok)버튼 클릭한 경우
				location.href = 'draftDocCancel?type=draft&docNo=${doc.doc_no}';
			}
		})
		
		$("#rej-delete").click(function() { //반려 문서 삭제
			var result = confirm("문서를 삭제하시겠습니까?"); // 메시지를 포함한 확인 상자(확인, 취소 버튼) 표시
			 // 확인(ok)클릭 => result 변수에 true저장, 취소(cancel)클릭 => result 변수에 false 저장
			 
			if(result == true) { //확인(ok)버튼 클릭한 경우
				location.href = 'draftDocCancel?docNo=${doc.doc_no}&type=draft';
			}
		})
		
		$(function() { 
			if("${doc.doc_status}" != "대기") { //기안 문서함에서 문서 상태가 대기가 아닌 경우 상신 취소 버튼 숨기기
				$("#btn-cancel").css("display", "none"); //대기일 경우만, 상신 취소
			}
			
			if("${doc_status}" != "대기") { //결재 문서함에서 문서 상태가 대기가 아닌 경우 상신 취소 버튼 숨기기
				$("#btn-app").css("display", "none");
				$("#btn-rej").css("display", "none");
			}
			
		})

	</script>
</body>
</html>