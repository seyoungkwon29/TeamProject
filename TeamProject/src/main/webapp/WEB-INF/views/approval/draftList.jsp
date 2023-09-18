<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기안 문서함</title>
<link href="resources/css/draftModal.css" rel="stylesheet">
<link href="resources/css/appDraftList.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>	
</head>

<body>
	
	<div class="s-container">
		<h2 id="h-title">기안 문서함</h2>
		<span class="type ${docStatus eq '전체' ? 'active' : ''}"><a href="/approval/${type }ListView.sw">전체</a></span>
		<span class="type ${docStatus eq '대기' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=대기">대기</a></span>
		<span class="type ${docStatus eq '진행' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=진행">진행</a></span>
		<span class="type ${docStatus eq '완료' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=완료">완료</a></span>
		<span class="type ${docStatus eq '반려' ? 'active' : ''}"><a href="/approval/${type }ListView.sw?docStatus=반려">반려</a></span>
		
		<button class="app-btn" id="app-btn">문서 기안</button>
		
		<table class="t-List">
			<tr>
				<th class="th-1">기안일</th>
				<th class="th-1">문서양식</th>
				<th class="th-2">제목</th>
				<th class="th-1">문서번호</th>
				<th class="th-1">결재상태</th>
			</tr>
			
			<c:forEach var="docList" items="${docList}">
				<tr>
					<td>${docList.doc_date}</td> <!-- 기안일 -->
					
					<td>${docList.form_name}</td> <!-- 문서 양식 -->
					
					<c:url var="aDetail" value="/approval/detail.sw?type=${type }&docStatus=${appDoc.docStatus }">
						<c:param name="docNo" value="${appDoc.docNo }"></c:param>
					</c:url>
					
					<td><a href="${aDetail }">${docList.doc_title}</a></td> <!-- 제목-->
					
					<td>${docList.doc_no}</td> <!-- 문서 번호-->
					
					<c:if test="${docList.doc_status == '대기'}">
						<td><span class="status-1">${docList.doc_status }</span></td>
					</c:if>
					
					<c:if test="${docList.doc_status == '진행'}">
						<td><span class="status-2">${docList.doc_status }</span></td>
					</c:if>
					
					<c:if test="${docList.doc_status == '완료'}">
						<td><span class="status-3">${docList.doc_status }</span></td>
					</c:if>
					
					<c:if test="${docList.doc_status == '반려'}">
						<td><span class="status-4">${docList.doc_status }</span></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
		
		 <!-- 페이징 -->
		<jsp:include page="appPaging.jsp" flush="true"/>
		
		<div class="l-search">
			<form action="#" method="get">
					<select class="l-select" id="s-condition" name="searchCondition" style="text-align: left; width: 80px;">
						<option value="all">전체</option>
						<option value="doc_date">기안일</option>
						<option value="form_name">문서양식</option>
						<option value="doc_title">제목</option>
						<option value="doc_no">문서번호</option>
					</select>
					
					<div class="l-input">
						<input type="text" id="s-value" name="searchValue" class="l-text">
						<input type="submit" value="검색" id="btn-search" class="i-search">
					</div>
					
			</form>
		</div>
	</div>


	<!--문서 양식 선택 모달-->
	<div class="total-modal" id="t-modal"> <!-- modal 전체를 감싸는 검은 배경 -->
		<div class="modal-content" title="close on click"> <!-- 모달의 내용 -->
		
				<div class="modal-header" id="modal_header"> 기안 양식 선택 </div>
				
				<div class="modal-body" id="modal_body">
					<select class="modal-select" id="modal-select" style ="height:28px; width:170px;">
						<option>문서를 선택하세요</option>
						<option value="기안서">기안서</option>
						<option value="품의서">품의서</option>
						<option value="지출결의서">지출결의서</option>
						<option value="휴가신청서">휴가신청서</option>
					</select>
				</div>

				<div class="modal-footer">
					<button class="confirm-btn" id="confirm-btn">확인</button>
					<button class="cancel-btn" id="cancel-btn">취소</button>
				</div>			
		</div>		
	</div>



<script>

	$(function() {	
		//문서 기안: 모달창 처리하기	
		//문서 기안 버튼
		$("#app-btn").click(function() {
			$("#t-modal").fadeIn(); //클릭시, 모달창 나옴
		});
		
		//문서 선택
		$("#confirm-btn").click(function() {
			var form_name = $("#modal-select").val(); //선택된 값을 가져옴
			
			if (form_name !== "문서를 선택하세요") { //"문서를 선택하세요"를 제외한 문서 양식 선택하면 servlet 이동
			   window.location.href = "DocFormName?form_name=" + form_name; //선택된 문서종류 url에 같이 전송
			} 
			else { //선택된 값이 없다면, 알림창
			   alert("문서 양식을 선택하세요!");
			}
		 });
		
		//취소
		$("#cancel-btn").click(function(){
			$("#t-modal").fadeOut(); //클릭시, 모달창이 꺼짐
		});
		
	});//end script
	
</script>


</body>
</html>