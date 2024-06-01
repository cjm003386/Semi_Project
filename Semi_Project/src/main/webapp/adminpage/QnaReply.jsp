<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>
<html>
<head>
<title>문의처리</title>
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
</head>
<body>
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>문의 처리</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">작성자</td>
						<td><input type="text" class="form-control" name="writer"></td>
					</tr>
					<tr>
						<td class="table-active text-center">문의 제목</td>
						<td><input type="text" class="form-control" name="subject"></td>
					</tr>
					<tr>
						<td class="table-active text-center">문의 카테고리</td>
						<td><input type="text" class="form-control" name="categories" readOnly></td>
					</tr>
					<tr>
						<td colspan="2">문의 내용<textarea rows="10" cols="50" name="content"
								class="form-control" disabled></textarea></td>
					</tr>
					<tr>
						<td colspan="2">답변<textarea rows="20" cols="60" name="re_content"
								class="form-control"></textarea></td>
					<tr>
						<td class="table-active text-center">문의 상품</td>
						<td><input type="text" class="form-control" name="subject"></td>
					</tr>
					<tr>
						<td class="table-active text-center">첨부파일</td>
						<td><label for="upfile"> <img src="../image/attach.png"
								alt="파일첨부">
						</label> <input type="file" id="upfile" name="board_file"> <span
							id="filevalue"></span></td>
					</tr>
				</table>
				<div style="text-align: center">
					<button type="button" class="btn btn-light float-right ml-1"
						data-mdb-ripple-color="dark " style="width: 90px">취소</button>
					<button type="button" class="btn btn-dark float-right ml-1">답변완료</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>