<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../resources/css/boardForm.css" rel="styleSheet" />
<!--  jQuery, bootstrap -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

<!-- summernote css/js-->
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>

</head>
<body>
	<h1>${board}UpdatePage</h1>

	<form action="./qnaUpdate" method="POST" enctype="multipart/form-data"
		onsubmit="return updateSubmit();">
		<!-- submit 버튼 누를 경우 updateSubmit 함수 호출 -->
		<input type="hidden" name="num" value="${dto.num}"> 글 제목<input
			type="text" name="title" id="title" value="${dto.title}"> 작성자<input
			type="text" name="id" disabled="disabled" value="${auth.id}">
		<input type="hidden" name="productNum" value="${param.productNum}">
		본문
		<textarea name="contents" rows="" cols="" id="summernote">${dto.contents}</textarea>

		<hr>
		<div id="files">
			<c:forEach items="${dto.fileDTOs}" var="f">
				<div>
					${f.oriName}
					<button type="button" data-fileNum="${f.fileNum}"
						class="fileDeleteBtn">X</button>

					<img class="qnaImg" alt=""
						src="../resources/upload/qnas/${f.fileName}">
				</div>
			</c:forEach>
		</div>

		<div id="fileContainer">
			<div id="fileResult" class="alignCenter"></div>
			<div class="fileAdd">
				<button type="button" id="fileAdd">FileAdd</button>
			</div>
		</div>
		<hr>

		<div id="btnList">
			<button type="submit">UPDATE</button>
			<!-- 취소 버튼 누를 시 뒤로 가기-->
			<button type="button" id="addCancel">취소</button>
		</div>
	</form>
	<script>
		$(document)
				.ready(
						function() {
							$('#summernote')
									.summernote(
											{
												height : 500,
												lang : 'ko-KR',
												toolbar : [

														// [groupName, [list of button]]
														[ 'Font Style',
																[ 'fontname' ] ],
														[
																'style',
																[
																		'bold',
																		'italic',
																		'underline' ] ],
														[
																'font',
																[ 'strikethrough' ] ],
														[ 'fontsize',
																[ 'fontsize' ] ],
														[ 'para',
																[ 'paragraph' ] ],
														[ 'color', [ 'color' ] ],
														[ 'table', [ 'table' ] ],
														[ 'height',
																[ 'height' ] ],
														[ 'highlight',
																[ 'highlight' ] ],
														[ 'Insert', [ 'link' ] ], ]
											});
						});
	</script>


	<script type="text/javascript" src="../resources/js/updateFile.js"></script>
</body>
</html>