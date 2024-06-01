<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>리뷰 수정</title>
<jsp:include page="../mainpage/header.jsp"/>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/reviewmodifyform.js"></script>
<style>
#upfile{display:none}

table td {
  position: relative;
}

table td input {
  position: absolute;
  top:0;
  left:0;
  margin: 0;
  height: 100%;
  width: 100%;
  border: none;
}

[readonly]{
  background-color:#fff !important;
}
</style>
</head>
</head>
<body>
<form action="reviewmodifyprocess.pg" method="post" enctype="multipart/form-data" name="modifyform">
	<input type="hidden" id="loginid" value="${id }" name="loginid">
	<input type="hidden" name="review_num" value="${reviewinfo.review_num }">
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>리뷰 보기</b>
				</div>
				<hr style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">작성자</td>
						<td><input value="${reviewinfo.review_name}" name="review_name" type="text" class="form-control" ReadOnly ></td>
					</tr>
					<tr>
						<td class="table-active text-center">제목</td>
						<td><input id="review_subject" type="text" name="review_subject" value="${reviewinfo.review_subject}" class="form-control" ></td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="10" cols="50" 
							id="review_content"	name="review_content" class="form-control">${reviewinfo.review_content}</textarea></td>
					</tr>
					<tr>
						<td class="table-active text-center">구매제품</td>
						<td><input type="text" class="form-control" value=${reviewinfo.product_name } name="product" ReadOnly></td>
					</tr>
					<tr>
					<c:if test="${reviewinfo.review_re_lev==0}">
						<td class="table-active text-center">첨부파일</td>
						<td><label for="upfile"> <img src="${pageContext.request.contextPath}/image/mypage/attach.png"
								alt="파일첨부" width="20px">
						</label> <input type="file" id="upfile" name="review_file"> <span
							id="filevalue">${reviewinfo.review_file}</span>
							<img src="image/mypage/remove.png" alt="파일삭제" width="10px" class="remove"></td>
					</c:if>
					</tr>
					<tr>
						<td class="table-active text-center">비밀번호</td>
						<td><input type="password" class="form-control" id="review_pass" name="review_pass"></td>
					</tr>
				</table>
				<div style="text-align: center">
					<button type="reset" class="btn btn-light float-right ml-1"
						data-mdb-ripple-color="dark " onClick="history.go(-1)" style="width: 90px">취소</button>
					<button type="submit" class="btn btn-dark float-right ml-1">수정하기</button>
				</div>
			</div>
		</div>
	</div>
	</form>
	<br>
	<br>
	<br>
	<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>