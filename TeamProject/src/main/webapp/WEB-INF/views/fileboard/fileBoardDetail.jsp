<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세 페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
</head>
<body>
	<div class="container">
		<h2>게시판 글 수정 및 삭제</h2>
		<form method="get" name="form">
			<input type="hidden" id="no" name="no" value="${fileBoardDetail.file_board_no}">
			<div class="form-group">	<!-- 제목 -->
				<label for="title">제목</label>
				<!-- required 속성을 설정하면 필수입력 사항이된다. -->
				<!-- pattern 속성을 이용한 정규표현식으로 데이터의 유효성 검사를 할 수 있다. -->	
				<input type="text" class="form-control" id="title"
					placeholder="제목 입력(4-100)" name="title" maxlength="100"
					required="required" pattern=".{4,100}" value="${fileBoardDetail.file_board_title}">
			</div>
			<div class="form-group"> <!-- 내용 -->
				<label for="content">내용</label>
				<!--  textarea 안에 있는 모든 글자는 그대로 나타난다. 공백문자, tag, enter -->
				<textarea class="form-control" rows="5" id="summernote" name="content"
					placeholder="내용 작성">${fileBoardDetail.file_board_content}</textarea>
			</div>
			<div class="form-group"> <!-- 사번 -->
				<label for="writer">사번</label> <input type="text"
					class="form-control" id="writerNumber" placeholder="작성자(2자-10자)"
					name="writerNumber" value="${fileBoardDetail.member_num}">
			</div>
			<div class="button">
        	<label for="chooseFile"></label>
        	<input type="file" id="chooseFile" name="chooseFile" accept="image/*" onchange="loadFile(this)">
    		</div>
   
			<button type="submit" class="btn btn-default" onclick="javascript: form.action='fileBoardUpdate'">수정</button>
			<button type="submit" class="btn btn-default" onclick="javascript: form.action='fileBoardDelete'">삭제</button>
		</form>
	</div>
</body>
<script>
$('#summernote').summernote({
 height: 300,                 // set editor height
 minHeight: null,             // set minimum height of editor
 maxHeight: null,             // set maximum height of editor
 focus: true,                  // set focus to editable area after initializing summernote

});
</script>
</html>

