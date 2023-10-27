<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="resources/css/draftModal.css" rel="stylesheet">
<link href="resources/css/appDraftList.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"> </script>	
<script type="text/javascript">
	$(document).ready(function() {
		  //모든 링크 요소를 선택
		  var links = $('.type a');
	
		  links.on('click', function() {
				console.log("click");
			    //클릭한 링크에 'active' 클래스 추가
			    links.removeClass('active'); //이전 링크에서 'active' 클래스 제거
			    $(this).addClass('active'); //현재 클릭한 링크에 'active' 클래스 추가
		  });
	});
</script>

 <!-- 문서 보기  -->
	<div class="s-container">
		<h1 id="h-title">기안 문서함</h1>

		<div class="top-List">
			<span class="type"><a href="draftList?parameter=draft">전체</a></span>
			<span class="type"><a href="draftList?parameter=draft&docStatus=대기">대기</a></span>
			<span class="type"><a href="draftList?parameter=draft&docStatus=진행">진행</a></span>
			<span class="type"><a href="draftList?parameter=draft&docStatus=반려">반려</a></span>
			<span class="type"><a href="draftList?parameter=draft&docStatus=완료">완료</a></span>
			
			
			<button class="app-btn" id="app-btn">문서 기안</button>
		</div>
		
		<table class="t-List">
			<tr>
				<th class="th-1">기안일</th>
				<th class="th-1">문서양식</th>
				<th class="th-2">제목</th>
				<th class="th-1">문서번호</th>
				<th class="th-1">결재상태</th>
			</tr>
			
			<c:forEach var="doc" items="${appDocList}">
				<tr>
					<td>${doc.doc_date}</td> <!-- 기안일 -->
					
					<td>${doc.form_name}</td> <!-- 문서 양식 -->
					
					<td><a href="clickDocContent?type=draft&docNo=${doc.doc_no}&docStatus=${doc.doc_status}">
						${doc.doc_title}</a>
					</td> <!-- 제목, 제목 클릭 시 기안문서함(type), 문서번호(doc_no), 문서 결재 정보(doc_status) 전송  -->
					
					<td>${doc.doc_no}</td> <!-- 문서 번호-->
					
					<!-- 문서 결재 상태에 따라 변경 -->
					<c:if test="${doc.doc_status == '대기'}"> <!-- 기안하고, 1번 결재자 결재 전 -->
						<td><span class="status-1">${doc.doc_status }</span></td>
					</c:if>
					
					<c:if test="${doc.doc_status == '진행'}">  <!-- 1번 결재자의 결재 -->
						<td><span class="status-2">${doc.doc_status }</span></td>
					</c:if>
					
					<c:if test="${doc.doc_status == '완료'}">
						<td><span class="status-3">${doc.doc_status }</span></td>
					</c:if>
					
					<c:if test="${doc.doc_status == '반려'}">
						<td><span class="status-4">${doc.doc_status }</span></td>
					</c:if>
									
				</tr>
			</c:forEach>
		</table>
		
 <!-- 페이징  -->
	<div class="paging">
		<c:if test="${search.member_num == null}">
	        <!-- 첫 페이지일 경우, 해당 페이지로 이동하는 링크 -->
	        <c:if test="${api.startNavi == 1 }">
	            <a href="draftList?parameter=draft&page=1&docStatus=${pageStatus}"></a>
	        </c:if>
	
	        <!-- 이전 페이지 버튼 생성 -->
	        <c:if test="${api.prev}">
	            <a href="draftList?parameter=draft&page=${api.startNavi-1}&docStatus=${pageStatus}">
	                <button class="page-btn">＜   </button>
	            </a>
	        </c:if>
	        
	        <!-- 페이지 번호에 대한 버튼을 생성 , 시작 페이지(begin)와 끝 페이지(end)를 설정 -->
			<c:forEach var="page" begin="${api.startNavi }" end="${api.endNavi }">
				<a href="draftList?parameter=draft&page=${page}&docStatus=${pageStatus}">
					<button class="page-btn">${page}</button>
				</a>
			</c:forEach>
			
			<!-- 다음 페이지 버튼 생성 -->
			<c:if test="${api.next && api.endNavi > 0}">
	             <a href="draftList?parameter=draft&page=${api.endNavi+1}&docStatus=${pageStatus}"> 
	             	<button class="page-btn"> ＞</button>
	             </a>
	        </c:if>
	 	</c:if>
	 	
	 	
		<c:if test="${search.member_num != null }"> <!-- 검색한 결과가 있는 경우 -->
			<c:if test="${api.startNavi == 1 }">
			 	<a href="searchConditionList?parameter=draft&page=1&
			 	searchCondition=${search.searchCondition }&searchValue=${search.searchValue }"></a>
			</c:if>
			
			<c:if test="${api.prev}">
				<a href="searchConditionList?parameter=draft&page=${api.startNavi-1}&
				searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
					<button class="page-btn">Prev</button>
				</a>
			</c:if>
			
	        <!-- 페이지 번호에 대한 버튼을 생성 , 시작 페이지(begin)와 끝 페이지(end)를 설정 -->
			<c:forEach var="page" begin="${api.startNavi }" end="${api.endNavi }">
				<a href="searchConditionList?parameter=draft&page=${page}&
				searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
					<button class="page-btn">${page}</button>
				</a>
			</c:forEach>
	
			<c:if test="${api.next && api.endNavi > 0}">
				 <a href="searchConditionList?parameter=draft&page=${api.endNavi+1}&
				 searchCondition=${search.searchCondition }&searchValue=${search.searchValue }">
				 	<button class="page-btn">Next</button>
				 </a>
			</c:if>
		</c:if>
	</div>


<!-- 검색하기 -->		
	<div class="l-search">
		<form action="searchConditionList" method="get">
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
					<input type="hidden" value="draft" name="parameter">
				</div>
				
		</form>
	</div>

</div> <!-- 전체 div -->

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
		$("#app-btn").click(function() { //문서 기안 버튼
			$("#t-modal").fadeIn(); //클릭시, 모달창 나옴
		});
		
	
		$("#confirm-btn").click(function() { //문서 선택
			var form_name = $("#modal-select").val(); //선택된 값을 가져옴
			
			if (form_name !== "문서를 선택하세요") { //"문서를 선택하세요"를 제외한 문서 양식 선택하면 servlet 이동
			   window.location.href = "docFormName?form_name=" + form_name; //선택된 문서종류 url에 같이 전송
			} 
			else { //선택된 값이 없다면, 알림창
			   alert("문서 양식을 선택하세요!");
			}
		 });
		
		
		$("#cancel-btn").click(function(){ //취소
			$("#t-modal").fadeOut(); //클릭시, 모달창이 꺼짐
		});
		
	});//end script
	
</script>