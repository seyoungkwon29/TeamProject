<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<style>
	#pagingContainer {
	    display: inline-flex;
	    height: 40px;
	    width: 836px;
	    margin-left: 257px;
	    justify-content: center;
		
	}
	
	#pagingContainer .pagingList {
		width:400px;
		
	}
	
	#pagingContainer .pagingList .pagingTable .pagingLine {
		
	}
	#pagingContainer .pagingList .pagingTable .pagingLine .pagingBlock{
	  	width: 40px;
   		height: 40px;
	    text-align: center;
	    border: none;
	    background-color: transparent;
	    font-size: 14px;
	    margin: 0 5px;
	    cursor: pointer;
	}
	
	#pagingContainer .pagingList .pagingBtn {
		text-decoration: none;
    	color: black;	
	}
	
	#pagingContainer .pagingList .pagingBtn:hover {
   		border: 1px solid rgb(190 190 190 / 75%);
    	border-radius: 50%;
    	font-weight: 600;
    	padding: 10px 15px;
	}
	
	
</style>


<div id="pagingContainer">
	 <div class="pagingList">
		  <table class= "pagingTable">
		 	<tr class="pagingLine">
			 	<c:if test="${pageDTO.isPrev() }">
			 		<td class="pagingBlock"><a href="mailReceiveList?page=${pageDTO.getStartPage()- pageDTO.getRangeSize() }" class="pagingBtn">PREV</a></td>
			 	</c:if>
			 	
			 	
				<c:forEach begin="${pageDTO.getStartPage() }" end="${pageDTO.getEndPage() }" varStatus="status">
			 		<td class="pagingBlock"><a href="mailReceiveList?page=${status.index}" class="pagingBtn" >${status.index}</a></td>
			 	</c:forEach>
			  
			  	<c:if test="${pageDTO.isNext() }">
			 		<td class="pagingBlock"><a href="mailReceiveList?page=${pageDTO.getEndPage()+1 }" class="pagingBtn">NEXT</a></td>
			 	</c:if>
		 	</tr>
		 </table>	 
	 </div>
	 
</div>