<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>리뷰 답글 작성</title>
<jsp:include page="../mainpage/header.jsp"/>
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
<title>리뷰 답글 작성</title>
</head>
<body>
	<div class="container">
	<form action="reviewreplyaction.pg" method="post" name="boardform">
		<input type="hidden" name="review_re_ref" value="${review.review_re_ref }">
			<input type="hidden" name="review_re_lev" value="${review.review_re_lev }">
			<input type="hidden" name="review_re_seq" value="${review.review_re_seq }">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>리뷰답변 작성</b>
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
						<td><input name="review_subject" id="review_subject" value="Re:${review.review_subject }" type="text" class="form-control"></td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="10" cols="50" name="review_content" id="review_content"
								class="form-control"></textarea></td>
					</tr>
					<tr>
						<td class="table-active text-center">비밀번호</td>
						<td><input type="password" name="review_pass" class="form-control"></td>
					</tr>
				</table>
				<div style="text-align: center">
					<button type="reset" class="btn btn-light float-right ml-1"
						data-mdb-ripple-color="dark " style="width: 90px" onClick="history.go(-1)">취소</button>
					<button type="submit" class="btn btn-dark float-right ml-1">등록하기</button>
				</div>
			</div>
		</div>
		</form>
	</div>
</body>
</html>