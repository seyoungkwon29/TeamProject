<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/fileBoardList.css">

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="resources/js/summernote-ko-KR.js"></script>

<!-- <script src="resources/js/summernote-lite.js"></script> -->
<!-- <link rel="stylesheet" href="resources/css/summernote-lite.css"> -->

</head>
<body>
	<jsp:include page="../common/menu.jsp" flush="true" />
	
	<div class="container" style="margin:0 60px; position:absolute; left: 250px; padding-top: 130px;">
		<h2>게시판 글 등록</h2>
		<form action="form" method="post">
			<input type="hidden" id="no" name="no" value="${fileBoardDetail.file_board_no}">
			<div class="form-group">
				<!-- 제목 -->
				<label class="label-title" for="title">제목</label>
				<!-- required 속성을 설정하면 필수입력 사항이된다. -->
				<!-- pattern 속성을 이용한 정규표현식으로 데이터의 유효성 검사를 할 수 있다. -->
				<input type="text" class="form-control" id="title"
					placeholder="제목 입력(4-100)" name="title" maxlength="100"
					required="required" pattern=".{4,100}" value="${fileBoardDetail.file_board_title}">
			</div>
			<div class="form-group">
				<!-- 내용 -->
				<label class="label-title"  for="content">내용</label>
				<!--  textarea 안에 있는 모든 글자는 그대로 나타난다. 공백문자, tag, enter -->
				<textarea class="form-control" rows="5" id="summernote"
					name="editor" value="">${fileBoardDetail.file_board_content}</textarea>
			</div>
			<div class="form-group">
				<!-- 사번 -->
				<label class="label-title" for="writer">사번</label><input type="text"
					class="form-control" id="writerNumber"
					name="writerNumber" value="${fileBoardDetail.member_num}">
			</div>
			<!-- 파일업로드 -->
			<div class="button">
				<label class="label-title"  for="chooseFile">파일 첨부</label> <input type="file" id="chooseFile"
					name="chooseFile" accept="image/*" onchange="loadFile(this)">
			</div>

			<button type="submit" class="btn btn-default" onclick="javascript: form.action='fileBoardUpdate'">수정</button>
			<button type="submit" class="btn btn-default" onclick="javascript: form.action='fileBoardDelete'">삭제</button>
			<!-- onclick alert 창 -->
		</form>
	</div>
</body>
<script>
$('#summernote').summernote({
	height: 300,                 // 에디터 높이
	minHeight: null,             // 최소 높이
	maxHeight: null,             // 최대 높이
	focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
	lang: "ko-KR",					// 한글 설정
	placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
	callbacks: {	//여기 부분이 이미지를 첨부하는 부분
		onImageUpload : function(files) {
			uploadSummernoteImageFile(files[0],this);
		},
		onPaste: function (e) {
			var clipboardData = e.originalEvent.clipboardData;
			if (clipboardData && clipboardData.items && clipboardData.items.length) {
				var item = clipboardData.items[0];
				if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
					e.preventDefault();
				}
			}
		}
	}
});

/**
* 이미지 파일 업로드
*/
function uploadSummernoteImageFile(file, editor) {
data = new FormData();
data.append("file", file);
$.ajax({
data : data,
type : "POST",
url : "uploadSummernoteImageFile",
contentType : false,
processData : false,
success : function(data) {
	//항상 업로드된 파일의 url이 있어야 한다.
	console.log("성공>>>>>>>>>>");
	console.log(data.url);
	console.log($("editor"));
	$("editor").summernote('insertImage', data.url);
}
});
}
</script>
</html>

