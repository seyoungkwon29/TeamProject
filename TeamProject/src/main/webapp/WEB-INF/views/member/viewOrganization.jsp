<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="tree">
	<h1 style="margin-left: 70px; color: #333; font-size:30px;">조직도</h1>
	<div>
    <ul>
		<li>
			<a href="#" class="top-mem">대표이사</a>
			<ul><li></li></ul>
			<ul><li></li></ul>
			<ul>
			<!-- 기획 -->
				<li>
					<a href="#" class="top-li">기획</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '기획'}">
					<ul>
						<li>
							<br>
							<!-- 사용자속성  -->
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
								<div class="org-name">
									${ org.rank }
									${ org.member_name }
								</div>
								<div class="org-mail">
									${ org.mail }
								</div>
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				</li>
			<!-- 기획 -->
			<!-- 영업 -->
				<li>
					<a href="#" class="top-li">영업</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '영업'}">
					<ul>
						<li>
							<br>
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
								<div class="org-name">
									${ org.rank }
									${ org.member_name }
								</div>
								<div class="org-mail">
									${ org.mail }
								</div>
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				<li>
			<!-- 영업 -->
			<!-- 인사 -->
					<a href="#" class="top-li">인사</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '인사'}">
					<ul>
						<li>
							<br>
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
								<div class="org-name">
									${ org.rank }
									${ org.member_name }
								</div>
								<div class="org-mail">
									${ org.mail }
								</div>
							</a>
						</li>
					</ul>
					</c:if>
					</c:forEach>
				</li>
				
				<li>
					<a href="#" class="top-li">총무</a>
					<c:forEach items="${ organizationList }" var="org" varStatus="status">
					<c:if test="${ org.div_name eq '총무'}">
					<ul>
						<li>
							<br>
							<a class="memberModal" data-toggle="modal" href="#myModal" data-div="${ org.div_name }"
								data-photo="${ org.photo }" data-rank="${ org.rank }"
								data-name="${ org.member_name }" data-mail="${ org.mail }">
								<div class="org-name">
									${ org.rank }
									${ org.member_name }
								</div>
								<div class="org-mail">
									${ org.mail }
								</div>
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
</div> 
<!-- tree 끝 -->

<!-- Modal -->

<div class="modal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="h3-class" id="m-title">
			
       	</h3>
      </div>
      <div class="modal-body">
      	<table>
      		<tr>
      			<td rowspan='4'>
			      	<img id="photo" class="modal-img">
      			</td>
      			<th class="th-modal">부서</th>
      			
      			<td class="th-left-modal"><div id="div" style="margin-left: 13px;"></div></td>
      		</tr>
      		<tr>
      			<th class="th-modal">직급</th>
      			
      			<td class="th-left-modal"><div id="rank" style="margin-left: 13px;" ></div></td>
      		</tr>
      		<tr>
      			<th class="th-modal">이름</th>
      			
      			<td class="th-left-modal"><div id="name" style="margin-left: 13px;"></div></td>
      		</tr>
     		<tr>
      			<th class="th-modal">메일</th>
      			
      			<td class="th-left-modal"><div id="mail" style="margin-left: 13px;"></div></td>
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
			
			$("#m-title").html("<span class='title-span1'>"+rank+"</span><span class='title-span2'>"+name+"</span>");
			$("#photo").attr("src", "resources/memberphoto/" + photo + ".png");
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