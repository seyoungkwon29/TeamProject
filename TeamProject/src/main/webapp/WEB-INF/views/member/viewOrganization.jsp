<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">

.tree {
	margin-left: 300px;
	width: 100%; /* 원하는 너비(px) 설정 */
	height: 100%; /* 원하는 높이(px) 설정 */
}

/* li 태그 전체 */
.tree li {
	float: left; 
	text-align: center;
	list-style-type: none;
	position: relative;
	padding: 20px 5px 0 5px;
}

/* 부서 전체  가운데 정렬*/
.tree li ul {
	display: table;
	margin-left: auto;
	margin-right: auto;
	padding-left: 0;
	width: 100%
}

/* li 태그 사이 선 그어주기 */
.tree li::before, .tree li::after{
	content: '';
	position: absolute; top: 0; right: 50%;
	border-top: 1px solid #ccc;
	width: 50%; height: 20px;
}

.tree li::after{
	right: auto; left: 50%;
	border-left: 1px solid #ccc;
}


/* 오른쪽 태그의 왼쪽 선 -> 세로 선 */
.tree li::after{
	right: auto; left: 50%;	
	border-left: 1px solid #ccc;
}

.tree li li:last-child::before {
	border-right: 1px solid #ccc;
}

/* 불필요한 선 : display:none */
/* 자식 태그 하나일 경우 선 제거(대표이사) */
.tree li:only-child::after, .tree li:only-child::before {
	display: none;
}

/* 첫번째 부서 좌측 선 제거 */
.tree li li:first-child::before {
	display: none;
}

.tree li li:last-child::after {
	display: none;
}

/* a 태그의 박스형태 및 폰트 사이즈 */
.tree li a{
	border: 1px solid #ccc;
	padding: 5px 10px;
	text-decoration: none;
	color: #666;
	font-family: arial, verdana, tahoma;
	font-size: 11px;
	display: inline-block;
	width: 150px;
	
	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	
	transition: all 0.5s;
	-webkit-transition: all 0.5s;
	-moz-transition: all 0.5s;
}

/* hover effect */
.tree li a:hover, .tree li a:hover+ul li a {
	background: #c8e4f8; color: #000; border: 1px solid #94a0b4;
}
/*Connector styles on hover*/
.tree li a:hover+ul li::after, 
.tree li a:hover+ul li::before, 
.tree li a:hover+ul::before, 
.tree li a:hover+ul ul::before{
	border-color:  #94a0b4;
}

</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

</script>
<div class="tree">
    <ul>
		<li>
			<a href="#">대표이사</a>
			<ul><li></li></ul>
			<ul><li></li></ul>
			<ul>
			<!-- 기획 -->
				<li>
					<a href="#">기획</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '기획'}">
					<ul>
						<li>
							${ org.photo }<br>
							<a href="#">
							${ org.rank }
							${ org.member_name }<br>
							${ org.mail }
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				</li>
			<!-- 기획 -->
			<!-- 영업 -->
				<li>
					<a href="#">영업</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '영업'}">
					<ul>
						<li>
							${ org.photo }<br>
							<a href="#">
							${ org.rank }
							${ org.member_name }<br>
							${ org.mail }
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				<li>
			<!-- 영업 -->
			<!-- 인사 -->
					<a href="#">인사</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '인사'}">
					<ul>
						<li>
							${ org.photo }<br>
							<a href="#">
							${ org.rank }
							${ org.member_name }<br>
							${ org.mail }
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				</li>
				
				<li>
					<a href="#">총무</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '총무'}">
					<ul>
						<li>
							${ org.photo }<br>
							<a href="#">
							${ org.rank }
							${ org.member_name }<br>
							${ org.mail }
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				</li>
			</ul>
		</li>
	</ul>
</div>