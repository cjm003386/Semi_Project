<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>

<html>
<title>공지 글 작성</title>
<head>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/noticewriteform.js"></script>
<style>
h1 {
	font-size: 1.5rem;
	text-align: center;
	color: black
}

.container {
	width: 50%
}

#upfile {
	display: none
}

.sub {float:right;}

img{width:20px;}

textarea{resize:none;}
#board_content{height:600px;}


</style>
</head>
<body>
	<div class="container-fluid border text-center" >
	</div>
 	<br>
	<div class="container">
		 <div>
			<b>공지 글 작성</b>
		</div>
			<hr style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
			<br>
			
		<form action="noticeadd.com" method="post"
			enctype="multipart/form-data" name="Modify">
			<input type="hidden" name="board_num">
			
			
			<div class="form-group">
				<label for="board_subject">제목</label>
				<textarea name="board_subject" id="board_subject" rows="1"
					class="form-control" maxlength="100"></textarea>
			</div>
			
			<div class="form-group">
				<label for="board_name">작성자</label> <input type="text" readonly
				 name="board_name"	class="form-control" value="${id }">
			</div>

			<div class="form-group">
				<label for="board_content">내용</label>
				<textarea name="board_content" id="board_content"
					class="form-control" rows="10"></textarea>
			</div>
			<%--원문글인 경우에만 파일 첨부 수정 가능합니다. --%>
				<div class="form-group">
					<label for="board_file">파일 첨부</label> <label for="upfile">
						<img src="${pageContext.request.contextPath}/image/mypage/attach.png"
								alt="파일첨부">
					</label> <input type="file" id="upfile" name="board_file"> <span
						id="filevalue"></span> 
					<img src="${pageContext.request.contextPath}/image/mypage/remove.png" alt="파일삭제" width="10px" class="remove">
				</div>
			
			<br>
			<div class="form-group">
				<button type=submit class="btn btn-dark sub">등록</button>
				<button type=reset class="btn btn-dark cac" onClick="history.go(-1)">취소</button>
			</div>
		</form>
	</div>
	
	<div>
	 <jsp:include page="../mainpage/footer.jsp"/>
	</div>
</body>
</html>