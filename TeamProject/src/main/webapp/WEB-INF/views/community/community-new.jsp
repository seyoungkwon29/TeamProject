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
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" flush="true" />
	<main class="mt6 w-100 center w-60-l">
		<section class="mw8 center flex-auto">
			<h1 class="f3">자유게시판 새 글쓰기</h1>
			<div class="flex flex-auto items-center justify-start center">
				<spring:url var="newCommunityUrl" value="/communities/new"></spring:url>
				<form:form action="${newCommunityUrl}" method="post" enctype="multipart/form-data" modelAttribute="communityForm" cssClass="flex flex-column flex-auto">
					<div class="flex flex-column mb3">
						<form:label path="title" cssClass="db lh-copy f5 mb2">제목</form:label>
						<form:input path="title" cssClass="pa2 input-reset ba bg-transparent hover-bg-black hover-white w-100 mb2"/>
						<form:errors path="title" cssClass="f6 dark-red db mb2"/>
					</div>
					<div class="flex flex-column mb3">
						<form:label path="content" cssClass="db lh-copy f5 mb2">내용</form:label>
						<textarea id="summernote" name="content"></textarea>
						<form:errors path="content" cssClass="f6 dark-red db mb2"/>
					</div>
					<div class="flex flex-column mb3">
						<label for="files">첨부파일</label>
						<input name="files" type="file" multiple>
						<label for="images">첨부파일</label>
						<input name="images" type="file" multiple>
					</div>
					
					<div class="flex flex-column mb3">
						<button type="submit" class="button-reset b ph3 pv3 ba b--white white bg-green dim f5 dib w-100 mb3">작성</button>
						<spring:url var="communityListUrl" value="/communities"/>
						<a href="${communityListUrl}" class="link-reset flex w-100">
							<input class="button-reset b ph3 pv3 ba b--black bg-transparent dim f5 dib w-100 tc" value="뒤로가기">
						</a>
					</div>
				</form:form>
			</div>
		</section>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	<script>
	 $(document).ready(function() {
	        $('#summernote').summernote({
	        	height: 300,
	        	lang: "ko-KR",
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
			 url: "images",
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