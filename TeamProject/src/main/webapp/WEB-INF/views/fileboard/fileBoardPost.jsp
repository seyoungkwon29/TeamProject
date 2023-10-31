<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료함</title>
<meta name="viewport">
<link rel="stylesheet" href="resources/css/menu.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-3.2.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<!-- <script src="resources/js/summernote-lite.js"></script> -->
<script src="resources/js/summernote-ko-KR.js"></script>
<!-- <link rel="stylesheet" href="resources/css/summernote-lite.css"> -->

</head>
<jsp:include page="../common/menu.jsp" flush="true" />

<div class="container"
	style="margin: 0 60px; position: absolute; left: 250px; padding-top: 130px;">

	<h2>게시판 글 등록</h2>
	<form action="fileBoardInsert" method="post" enctype="multipart/form-data">
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
			<textarea class="form-control" rows="5" id="summernote" name="editor"
				placeholder="내용 작성"></textarea>
		</div>
		<div class="form-group">
			<!-- 사번 -->
			<label for="writer">사번</label><input type="text" class="form-control"
				id="writerNumber" name="writerNumber"
				value="${fDTO.getMember_num()}">
		</div>
		<!-- 파일업로드 -->
		<div>
		<table border="1">
			<tr>
				<th>첨부파일
					<button type="button" id="id_btn_new_file"> 추가 </button>
				</th>
				<tr>
				<td class="file_area">
					<div class="form-inline">	<!-- postFiles = insertForm-->
						<input type="file" name="postAttachFiles" class="form-control">
						<button type="button" class="btn_delete btn btn-sm">삭제</button>
					</div>
				</td>
				</tr>
			</tr>
		</table>
		</div>
		
		<button type="submit" class="btn btn-default">등록</button>
		<!-- onclick alert 창 -->
	</form>
</div>
<script type="text/javascript">
	$(document).ready(
		function() {
			$('#summernote').summernote(
				{
					height : 300, // 에디터 높이
					minHeight : null, // 최소 높이
					maxHeight : null, // 최대 높이
					focus : true, // 에디터 로딩후 포커스를 맞출지 여부
					lang : "ko-KR", // 한글 설정
					placeholder : '최대 2048자까지 쓸 수 있습니다', //placeholder 설정
					callbacks : { //여기 부분이 이미지를 첨부하는 부분
						onImageUpload : function(files) {
							uploadSummernoteImageFile(
							files[0], this);
						},
						onPaste : function(e) {
							var clipboardData = e.originalEvent.clipboardData;
							if (clipboardData&& clipboardData.items
								&& clipboardData.items.length) {
									var item = clipboardData.items[0];
									if (item.kind === 'file'&& item.type.indexOf('image/') !== -1) {
										e.preventDefault();
									}
								}
							}
						}
					});
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
				console.log(editor);
				$(editor).summernote('insertImage', data.url);
			}
		});
	}
	
	$('#id_btn_new_file').click(function(){ 
		$('.file_area').append('<div class="form-inline">' 
		+ '<input type="file" name="postAttachFiles" class="form-control">' 
		+ ' <button type="button" class="btn_delete btn btn-sm">삭제</button>' + '</div>'); 
		}); 
	$('.file_area').on('click','.btn_delete', function(){ 
		$(this).closest('div').remove(); 
		});
</script>
</body>

</html>

