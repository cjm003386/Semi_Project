<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>리뷰 작성</title>
<jsp:include page="../mainpage/header.jsp"/>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/reviewwriteform.js"></script>
</head>
<style>
table td {
  position: relative;
}
#upfile{display:none}

table td input {
  position: absolute;
  top:0;
  left:0;
  margin: 0;
  height: 100%;
  width: 100%;
  border: none;
}
img{width:20px}
</style>
<title>MVC 게시판</title>
</head>
<body>
	<div class="container">
	<form action="reviewaddaction.pg" method="post" enctype="multipart/form-data" name="reviewform">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>리뷰 작성</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">작성자</td>
						<td><input name="review_name" id="review_name" type="text" class="form-control" readOnly value=${id }></td>
					</tr>
					<tr>
						<td class="table-active text-center">제목</td>
						<td><input name="review_subject" id="review_subject" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="10" cols="50" name="review_content" id="review_content"
								class="form-control"></textarea></td>
					</tr>
					<tr>
						<td class="table-active text-center">구매제품</td>
						<td><input type="text" readOnly name="review_product" value="${product.product_name }" id="review_product" class="form-control"></td>
					</tr>
					<tr>
						<td class="table-active text-center">첨부파일</td>
						<td><label for="upfile"> <img src="${pageContext.request.contextPath}/image/mypage/attach.png"
								alt="파일첨부">
						</label> <input type="file" id="upfile" name="review_file"> <span
							id="filevalue"></span></td>
					</tr>
					<tr>
						<td class="table-active text-center">비밀번호</td>
						<td><input type="password" name="review_pass" id="review_pass" class="form-control"></td>
					</tr>
				</table>
				<div style="text-align: center">
					<button type="button" id="cancel" class="btn btn-light float-right ml-1"
						data-mdb-ripple-color="dark " style="width: 90px">취소</button>
					<button type="submit" class="btn btn-dark float-right ml-1">등록하기</button>
				</div>
			</div>
		</div>
		</form>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>