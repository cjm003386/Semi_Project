<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<jsp:include page="../mainpage/header.jsp"/>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/noticemodifyform.js"></script>
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

<%--게시판 수정 --%>
	<div class="container">
		<form action="noticemodifyaction.com" method="post" 
			enctype="multipart/form-data" name="noticeform">
			<input type="hidden" name="notice_num" value="${boarddata.notice_num}">
			
			<div class="form-group">
				<label for="notice_id">글쓴이</label> <input type="text"
					class="form-control" value="${boarddata.notice_id}" readOnly>
			</div>

			<div class="form-group">
				<label for="notice_title">제목</label>
				<textarea name="notice_title" id="board_subject" rows="1"
					class="form-control" maxlength="100">${boarddata.notice_title}</textarea>
			</div>
			<div class="form-group">
				<label for="notice_content">내용</label>
				<textarea name="notice_content" id="board_content"
					class="form-control" rows="10">${boarddata.notice_content}</textarea>
			</div>
			<%--원문글인 경우에만 파일 첨부 수정 가능합니다. --%>
			<c:if test="${boarddata.notice_re_lev==0}">
				<div class="form-group">
					<label for="notice_file">파일 첨부</label> <label for="upfile">
						<img src="image/mypage/attach.png" alt="파일 첨부" width="20px">
					</label> <input type="file" id="upfile" name="notice_file"> <span
                        id="filevalue">${boarddata.notice_file}</span>
					<img src="image/mypage/remove.png" alt="파일삭제" width="10px" class="remove">
				</div>
			</c:if>
			
			<div class="form-group">
				<button type=submit class="btn btn-dark">등록</button>
				<button type=reset class="btn btn-danger" onClick="history.go(-1)">취소</button>
			</div>
		</form>
	</div>
	

</body>
</html>