<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<style>
	#pagingContainer {
		display: flex;
		
	}
	#pagingContainer .pagingList{
		margin: 0 auto;
	}
	
	#pagingContainer .pagingList {
		width:400px;
		
	}
	
	#pagingContainer .pagingList .pagingTable {
		width:400px;
		height: 30px;
		
	}
	#pagingContainer .pagingList .pagingTable .pagingLine {
		
	}
	#pagingContainer .pagingList .pagingTable .pagingLine .pagingBlock{
		width:30px;
		text-align: center;
	}
	
	#pagingContainer .pagingList .pagingBtn {
		text-decoration: none;
		color: black;
		font-size: 20px;
		font-weight: 700;
		
	}
	
	
</style>


<div id="pagingContainer">
	 <div class="pagingList">
		  <table class= "pagingTable">
		 	<tr class="pagingLine">
			 	<c:if test="${pageDTO.isPrev() }">
			 		<td class="pagingBlock"><a href="mailSendList?page=${pageDTO.getStartPage()- pageDTO.getRangeSize() }" class="pagingBtn">PREV</a></td>
			 	</c:if>
			 	
			 	
				<c:forEach begin="${pageDTO.getStartPage() }" end="${pageDTO.getEndPage() }" varStatus="status">
			 		<td class="pagingBlock"><a href="mailSendList?page=${status.index}" class="pagingBtn" >${status.index}</a></td>
			 	</c:forEach>
			  
			  	<c:if test="${pageDTO.isNext() }">
			 		<td class="pagingBlock"><a href="mailSendList?page=${pageDTO.getEndPage()+1 }" class="pagingBtn">NEXT</a></td>
			 	</c:if>
		 	</tr>
		 </table>	 
	 </div>
	 
</div>