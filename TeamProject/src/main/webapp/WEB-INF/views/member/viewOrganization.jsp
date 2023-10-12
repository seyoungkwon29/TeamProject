<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
							<br>
							<!-- 사용자속성  -->
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
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
							<br>
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
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
							<br>
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
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
							<br>
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
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
<!-- tree 끝 -->

<!-- Modal -->
<div class="modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3>사원 정보</h3>
      </div>
      <div class="modal-body">
      	<table>
      		<tr align="center">
      			<td rowspan='4'>
			      	<img id="photo">
      			</td>
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<th>부서</th>
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<td><div id="div"></div></td>
      		</tr>
      		<tr align="center">
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<th>직급</th>
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<td><div id="rank"></div></td>
      		</tr>
      		<tr align="center">
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<th>이름</th>
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<td><div id="name"></div></td>
      		</tr>
     		<tr>
     			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<th align="center">메일</th>
      			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      				&nbsp;&nbsp;&nbsp;</td>
      			<td align="right"><div id="mail"></div></td>
      		</tr>
      	</table>
      </div>
   	</div>
  </div>
</div>
<!-- modal -->

<script type="text/javascript">
	$(document).ready(function(){
		var modal = $(".modal");
		$(".memberModal").on("click", function() {
			var photo = $(this).data('photo');
			var div = $(this).data('div');
			var rank = $(this).data('rank');
			var name = $(this).data('name');
			var mail = $(this).data('mail');
			
			$("#photo").attr("src", "resources/image/member/" + photo + ".png");
			$("#div").html(div);
			$("#rank").html(rank);
			$("#name").html(name);
			$("#mail").html(mail);
			
			$(".modal").css("display", "block");
			
		});
		
		$(window).on("click", function(event) {
			if (event.target == modal[0]) {
				$(".modal").css("display", "none");
			}
		})
	});
</script>