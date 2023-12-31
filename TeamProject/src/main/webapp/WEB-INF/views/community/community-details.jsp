<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<spring:url value="/resources/css/utility.css"/>">
<title>${communityDetails.title}</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
	<main class="mt6 w-100 center w-60-l">
		<section class="mw8 center flex-auto">
			<h1 class="f3">자유게시판</h1>
			<article id="article">
				<span id="title-top" class="f4">${communityDetails.title}</span>
				<div id="title-mid">
					<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">글번호</small>${communityDetails.comNum}</p>
					<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">작성자</small>${communityDetails.memberName}</p>
					<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">작성일</small><fmt:formatDate value="${communityDetails.createdAt}" pattern="YYYY년 M월 d일 hh:mm"/></p>
					<p id="title-mv" class="dib mv0 mr2 f5"><small class="title-mr">조회수</small>${communityDetails.views}</p>
				</div>
				<div id="title-content2">
					${communityDetails.content}
				</div>
				
				<c:if test="${not empty communityDetails.files}">
				<div class="flex flex-column">
					<p class="f5 mb1"><small class="mr1">첨부 파일</small></p>
					<c:forEach var="file" items="${communityDetails.files}">
					<spring:url var="fileUrl" value="/communities/${comNum}/files/${file.encodedOriginalFilename}"/>
					<div class="mb1">					
						<a href="${fileUrl}" class="link-reset black dim">${file.originalFilename}</a>
					</div>
					</c:forEach>
				</div>
				</c:if>
			</article>
		</section>
		
		<section class="mw8 center flex-auto">
			<article class="flex flex-column bb b--light-silver mt3">
        		<spring:url var="newReplyUrl" value="/communities/${communityDetails.comNum}/replies/new"/>
        		<form action="${newReplyUrl}" method="post">
					<label for="content" id="reply" class="form-label f5">새 댓글</label>
					<textarea id="textarea-reply" name="content" class="db border-box hover-black ba b--black-20 pa2 br2 mb2" rows="3"></textarea>
	      			<button type="submit" id="reply-btn" class="button-reset b ph3 ba b--gray gray bg-transparent dim f5 dib mb3">새 댓글 달기</button>
      			</form>
           	</article>
		</section>
		
		<section class="mw8 center flex-auto flex flex-column items-end">
           	<c:forEach var="replyDetails" items="${replyDetailsList}">
           	
           	<article class="w-${100 - 5 * (replyDetails.level-1) } flex flex-column bb b--light-silver mt3" data-reply-num="${replyDetails.replyNum}" data-article-type="content">
        		<div class="flex flex-row flex-wrap">
	      			<p class="reply-mr">${replyDetails.memberName}</p>
					<p class="reply-mr"><fmt:formatDate value="${replyDetails.createdAt}" pattern="YYYY/MM/dd h:m"/></p>
					<%-- <p class="dib mv1">댓글 깊이 : ${replyDetails.level}</p> --%>
      			</div>
				<p id="inner-reply" class="dib mv1-1 w-100">${replyDetails.content}</p>
      			<div class="flex flex-row item-start pb2" style="gap: 7px; margin: 4px;">
      				<button id="reply-modify-btn" class="reply-toggle-button button-reset dib ml0 pl0 b--transparent bg-transparent dim dib">댓글 달기</button>
   					<button id="reply-modify-btn" class="edit-toggle-button button-reset dib b--transparent bg-transparent dim dib">편집</button>
   					<spring:url var="deleteReplyUrl" value="/communities/${communityDetails.comNum}/replies/${replyDetails.replyNum}/delete"/>
	      			<form action="${deleteReplyUrl}" method="post">
      					<button id="reply-modify-btn" class="button-reset button-reset dib b--transparent bg-transparent dim dib">삭제</button>
      				</form>
      			</div>
           	</article>
        	<article class="w-${100 - 5 * (replyDetails.level-1) } flex flex-column bb b--light-silver dn mt3" data-reply-num="${replyDetails.replyNum}" data-article-type="edit">
        		<spring:url var="updateReplyUrl" value="/communities/${communityDetails.comNum}/replies/${replyDetails.replyNum}/edit"/>
        		<form action="${updateReplyUrl}" method="post">
        		<div class="flex flex-row flex-wrap">
	      			<p class="reply-mr">${replyDetails.memberName}</p>
					<p class="reply-mr"><fmt:formatDate value="${replyDetails.createdAt}" pattern="YYYY/MM/dd h:m"/></p>
      			</div>
				<textarea name="content" class="db border-box hover-black w-100 ba b--black-20 pa2 br2 mb2" rows="3">${replyDetails.content}</textarea>
      			<div class="flex flex-row item-start">
      				<button type="submit" id="reply-modify-btn" class="rbutton-reset dib ml0 pl0 b--transparent bg-transparent dim dib">수정</button>
   					<button id="reply-modify-btn" class="edit-toggle-button button-reset dib b--transparent bg-transparent dim dib">취소</button>
      			</div>
	      		</form>
           	</article>
           	<article class="w-${100 - 5 * (replyDetails.level) } flex flex-column bb b--light-silver dn mt3" data-reply-num="${replyDetails.replyNum}" data-article-type="reply">
        		<spring:url var="newReplyUrl" value="/communities/${communityDetails.comNum}/replies/new"/>
        		<form action="${newReplyUrl}" method="post">
        		<input type="hidden" name="parentReplyNum" value="${replyDetails.replyNum}">
        		<div class="flex flex-row flex-wrap">
	      			<p class="reply-mr">${login.member_name}</p>
      			</div>
				<textarea name="content" class="db border-box hover-black w-100 ba b--black-20 pa2 br2 mb2" rows="3"></textarea>
      			<div class="flex flex-row item-start" style="gap:5px; margin: 11px;">
      				<button type="submit" id="reply-modify-btn" class="button-reset dib ml0 pl0 b--transparent bg-transparent dim dib">대댓글 달기</button>
   					<button id="reply-modify-btn" class="reply-toggle-button button-reset dib b--transparent bg-transparent dim dib">취소</button>
      			</div>
      			</form>
           	</article>
           	</c:forEach>
		</section>
			
		
		<section class="mw8 center flex-auto">
			<div class="flex flex-row">
				<spring:url var="editCommunityUrl" value="/communities/${communityDetails.comNum}/edit"/>
				<a href="${editCommunityUrl}" class="link-reset flex w-50">
					<button type="button" class="modify-button button-reset b ph3 pv3 ba b--green green bg-transparent dim f5 dib w-100 mt3 mb3">수정</button>
				</a>
				<spring:url var="deleteCommunityUrl" value="/communities/${communityDetails.comNum}/delete"/>
				<form action="${deleteCommunityUrl}" method="post" class="w-50">
					<button type="submit" id="removeCheck" class="del-button button-reset b ph3 pv3 ba b--dark-red dark-red bg-transparent dim f5 dib w-100 mt3 mb3">삭제</button>
				</form>
			</div>
			<spring:url var="communityListUrl" value="/communities"/>
			<a href="${communityListUrl}" class="link-reset flex w-100">
				<input class="back-button button-reset b ph3 pv3 ba b--black bg-transparent dim f5 dib w-100 tc" value="뒤로가기">
			</a>
		</section>
	</main>
<script>
document.addEventListener("DOMContentLoaded", () => {
	console.log("DOMContentLoaded");
	
	const toggleEditForm = (event) => {
		if (event.target.tagName.toLowerCase() === 'button') {
			console.log(event.target);
			console.log(event.currentTarget);
			if (event.target.classList.contains('edit-toggle-button')) {
				event.preventDefault();
				let articles = document.getElementsByTagName('article');
				for (let article of articles) {
					if (article.dataset.replyNum === event.currentTarget.dataset.replyNum &&
						(article.dataset.articleType === "edit" || article.dataset.articleType === "content")) {
						article.classList.toggle('dn');
					}
				}
			}
		}
	}
	
	const toggleReplyForm = (event) => {
		if (event.target.tagName.toLowerCase() === 'button') {
			if (event.target.classList.contains('reply-toggle-button')) {
				event.preventDefault();
				let articles = document.getElementsByTagName('article');
				for (let article of articles) {
					if (article.dataset.replyNum === event.currentTarget.dataset.replyNum &&
						article.dataset.articleType === "reply") {
						article.classList.toggle('dn');
					}
				}
			}
		}
	}
	
	let articles = document.getElementsByTagName('article');
	
	for (let article of articles) {
		if (article.dataset.replyNum) {
			article.addEventListener('click', toggleEditForm);
			article.addEventListener('click', toggleReplyForm);
		}
	}
});

$(function(){
    $('#removeCheck').click(function(){
        if(!confirm('정말로 삭제하시겠습니까?')){
            return false;
        }
    });
});

</script>
</body>
</html>