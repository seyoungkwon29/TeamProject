<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판/글쓰기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<spring:url value="/resources/css/utility.css"/>">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<jsp:include page="../common/liveNotification.jsp" flush="true" />
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
	<main class="mt6 w-100 center w-60-l">
		<section class="mw8 center flex-auto">
			<h1 class="f3">자유게시판 새 글쓰기</h1>
			<div class="flex flex-auto items-center justify-start center">
				<spring:url var="newCommunityUrl" value="/communities/new"></spring:url>
				<form:form action="${newCommunityUrl}" method="post" enctype="multipart/form-data" modelAttribute="communityForm" cssClass="flex flex-column flex-auto">
					<div class="flex flex-column mb3" style="margin-top: 15px;">
						<form:label id="title" path="title" cssClass="db lh-copy f5 mb2">제목</form:label>
						<form:input id="title-input" path="title" cssClass="pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100 mb2"/>
						<form:errors path="title" cssClass="f6 dark-red db mb2"/>
					</div>
					<div class="flex flex-column mb3">
						<form:label id="t-content" path="content" cssClass="db lh-copy f5 mb2">내용</form:label>
						<textarea id="summernote" name="content"></textarea>
						<form:errors path="content" cssClass="f6 dark-red db mb2"/>
					</div>
					<div class="flex flex-column mb3">
						<label id="t-file" for="files">첨부파일</label>
						<input name="files" type="file" multiple>
					</div>
					
					<div class="flex flex-column mb3" style="margin-top: 15px;">
						<button type="submit" id="write-button" class="button-reset b ba b--white white dim f5 dib w-100 mb3">작성</button>
						<spring:url var="communityListUrl" value="/communities"/>
						<a href="${communityListUrl}" class="link-reset flex w-100">
							<input id="back-button" class="button-reset b ba b--black dim f5 dib w-100 tc" value="뒤로가기">
						</a>
					</div>
				</form:form>
			</div>
		</section>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<script>
	// JSP > HTML > JS 순으로 해석되고 실행된다.
	const baseUrl = "${pageContext.request.contextPath}";
	 $(document).ready(function() {
	        $('#summernote').summernote({
	        	height: 300,
	        	lang: "ko-KR",
	        	focus : true,
	    	  	toolbar: [
	    		    // 글꼴 설정
	    		    ['fontname', ['fontname']],
	    		    // 글자 크기 설정
	    		    ['fontsize', ['fontsize']],
	    		    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
	    		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	    		    // 글자색
	    		    ['color', ['forecolor','color']],
	    		    // 표만들기
	    		    ['table', ['table']],
	    		    // 글머리 기호, 번호매기기, 문단정렬
	    		    ['para', ['ul', 'ol', 'paragraph']],
	    		    // 줄간격
	    		    ['height', ['height']],
	    		    // 그림첨부, 링크만들기, 동영상첨부
	    		    ['insert',['picture','link','video']],
	    		    // 코드보기, 확대해서보기, 도움말
	    		    ['view', ['codeview','fullscreen', 'help']]
	    		  ],
	    		  // 추가한 글꼴
	    		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
	    		 // 추가한 폰트사이즈
	    		fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
	        	callbacks: {
	        		onImageUpload: function(files) {
	        			let clearFiles = {
	        				count: 0,
	        				increase : function () {
	        					this.count++;
	        					if (this.count === files.length) {
	        						this.clear();
	        					}
	        				},
	        				clear : function () {
	        					$(".note-form-group.note-group-select-from-files")
		        					.find("input.note-image-input")
		        					.val("");
	        				}
	        			
	        			};
	        			
	        			for (file of files) {
	        				uploadImageFile(file, this, clearFiles);
	        			}	
	        		},
	        	}
	        });
   	 });
	 function uploadImageFile(file, editor, clearFiles) {
		 data = new FormData();
		 data.append("image", file);
		 $.ajax({
			 data: data,
			 type: "POST",
			 url: baseUrl + "/communities/images",
			 cache: false,
			 contentType: false,
			 processData: false,
			 success: function(data) {
				 console.log(data);
				 $(editor).summernote('insertImage', data.url);
				 clearFiles.increase();
			 }
		 });
	 };
    </script>
</body>
</html>