<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="home" align="center">
    <div class="row">
		<div>
             <jsp:include page="homeAttend.jsp" flush="true"/>
        </div>
        
		<div class="row-reverse">
			<div>
	            <jsp:include page="homeNoticeList.jsp" flush="true"/>
	        </div>
	        
	        <div>
	             <jsp:include page="homeCommunityList.jsp" flush="true"/>
	        </div>
        </div>
        
		<div class="homecal">
        	<jsp:include page="homeCalendar.jsp" flush="true"/>
       	</div>
    </div>
    
    <div class="row">
        <div class="full-width">
            <jsp:include page="homeAppro.jsp" flush="true"/>
        </div>
        
        <div class="full-width">
            <jsp:include page="homeMail.jsp" flush="true"/>
        </div>
    </div>
</div>

<style>

.home {
    margin-left: auto;
    margin-top: 135px;
}
.row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
    width: 80%;
}
.left {
    flex: 0.5;
}

.right {
    flex: auto;
}

.full-width {
    width: 48%;
}
.homecal{
	margin-top: 17px;
}
   
</style>
