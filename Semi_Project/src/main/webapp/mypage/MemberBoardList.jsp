<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>작성 거래글 목록</title>
<jsp:include page="${pageContext.request.contextPath}/mainpage/header.jsp"/>
<style>
input{border:1px solid black}
b{font-size:0.9em}
</style>
</head>
<body>
<br>
<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>작성한 거래글</b>
				</div>
				<hr style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
			<br>
 	<table class="table">
		<tbody>
	 			<tr class="table-active">
	 				<td>리뷰 번호</td>
	 				<td>제목</td>
	 				<td>작성자</td>
	 				<td>날짜</td>
	 				<td>조회수</td>
				</tr>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
		</tbody>
 	</table>

	<div class="center-block">
		<div class="container">
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>
		</div>
	</div>
		<form action="#" method="post" >
		<div class="input-group">
			<select id="viewcount" name="search_field">
				<option value="0" selected>제목</option>
				<option value="1">내용</option>
			</select>
				<input name="search_word" type="text"
					placeholder="제목을 입력하세요">
				<button  class="btn btn-secondary" type="submit">검색</button>
		</div>
	</form>

</div>
</div>
</div>
</body>
</html>