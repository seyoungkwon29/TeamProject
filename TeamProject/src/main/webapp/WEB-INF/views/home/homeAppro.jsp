<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="resources/css/appDraftList.css"  rel="stylesheet">

<style>
/*     body { */
/*         margin: 0; */
/*         padding: 0; */
/*         font-family: Arial, sans-serif; */
/*     } */
/*     .container { */
/* 		display: flex; */
/* 	    margin-top: 80px; */
/*     } */
/*     .left-panel { */
/*         width: 50%; */
/*         background-color: #f2f2f2; */
/*         padding: 20px; */
/*     } */
/*     .right-panel { */
/*         flex: 1; */
/*         padding: 20px; */
/*     } */
</style>
    
  <div class="container">
      <div class="left-panel">
          <!-- 좌측 패널 내용 -->
          <h4>결재 대기 문서</h4>
          
          <table class="t-List">
				<tr>
					<th class="th-1">기안일</th>
					<th class="th-1">문서양식</th>
					<th class="th-2">제목</th>
					<th class="th-1">기안자</th>
					<th class="th-1">결재상태</th>
				</tr>
				
				<c:forEach var="app" items="${appDocList}">
					<tr>
						<td>${app.doc_date }</td>
						<td>${app.form_name }</td>
						
						<td><a href="clickDocContent?type=app&docNo=${app.doc_no}&docStatus=${app.app_status}">
							${app.doc_title}</a>
						</td> <!-- 제목-->
						
						<td>${app.member_name}</td>
						
						<c:if test="${app.app_status == '대기'}"> <!-- 결재자의 결재 상태 -->
							<td><span class="status-1">${app.app_status }</span></td>
						</c:if>										
					</tr>
				</c:forEach>
				</table>
	</div>
 </div>