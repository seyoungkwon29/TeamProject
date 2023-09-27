<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
	<div class="container">
		<h2>게시판 글 등록</h2>
		<form action="fileBoardInsert" method="post">
			<!-- 
			<input type="hidden" id="fileBoardDTO" name="fileBoardDTO" value=""> -->
			<div class="form-group">
				<!-- 제목 -->
				<label for="title">제목</label>
				<!-- required 속성을 설정하면 필수입력 사항이된다. -->
				<!-- pattern 속성을 이용한 정규표현식으로 데이터의 유효성 검사를 할 수 있다. -->
				<input type="text" class="form-control" id="title"
					placeholder="제목 입력(4-100)" name="title" maxlength="100"
					required="required" pattern=".{4,100}" value="">
			</div>
			<div class="form-group">
				<!-- 내용 -->
				<label for="content">내용</label>
				<!--  textarea 안에 있는 모든 글자는 그대로 나타난다. 공백문자, tag, enter -->
				<textarea class="form-control" rows="5" id="summernote"
					name="content" placeholder="내용 작성"></textarea>
			</div>
			<div class="form-group">
				<!-- 사번 -->
				<label for="writer">사번</label> <input type="text"
					class="form-control" id="writerNumber" placeholder="작성자(2자-10자)"
					name="writerNumber" value="">
			</div>
			<!-- 파일업로드 -->
			<div class="button">
				<label for="chooseFile"></label> <input type="file" id="chooseFile"
					name="chooseFile" accept="image/*" onchange="loadFile(this)">
			</div>

			<button type="submit" class="btn btn-default">등록</button>
			<!-- onclick alert 창 -->
		</form>
	</div>
</body>
<script>
	$(document).ready(function() {
		var toolbar = [
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
		  ];

		var setting = {
			height : 300,
			minHeight : null,
			maxHeight : null,
			focus : true,
			lang : 'ko-KR',
			toolbar : toolbar,
			//콜백 함수
			callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					// 파일 업로드(다중업로드를 위해 반복문 사용)
					for (var i = files.length - 1; i >= 0; i--) {
						uploadSummernoteImageFile(files[i], this);
					}
				}
			}
		};
		$('#summernote').summernote(setting);
	});

	function uploadSummernoteImageFile(file, el) {
		data = new FormData();
		data.append("file", file);
		$.ajax({
			data : data,
			type : "POST",
			url : "uploadSummernoteImageFile",
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			success : function(data) {
				$(el).summernote('editor.insertImage', data.url);
			}
		});
	}
</script>
</html>

