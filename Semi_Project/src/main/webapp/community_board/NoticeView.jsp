<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainpage/header.jsp"/>
<html>
<head>
<title>공지 글 보기</title>
</head>
<style>
#upfile{display:none}
textarea{resize:none; width:200px; height:500px;}

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
</style>
</head>
<body>
	<div class="container-fluid border text-center" >
    </div>
 <br>
	<div class="container" >
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>공지 글 확인</b>
				</div>
				<hr style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">작성자</td>
						<td><input type="text" value="${boarddata.notice_id }" class="form-control" name="writer" readOnly></td>
					</tr>
					<tr>
						<td class="table-active text-center">제목</td>
						<td><input type="text" value="${boarddata.notice_title }" class="form-control" name="subject" readOnly></td>
					</tr>
					<tr>
						<td colspan="2"><textarea rows="10" cols="50" name="content"
				class="form-control" readOnly>${boarddata.notice_content }</textarea></td>
					</tr>
					<tr>
						<td class="table-active text-center">첨부파일</td>
						<td>${boarddata.notice_file }</td>
					</tr>
				</table>
					<a href="noticelist.co"><button type="button" class="btn btn-dark float-left">글목록</button></a>
					<c:if test="${!empty sessionScope.id && sessionScope.id == 'admin'}">
					<a href="noticemodifyview.com?num=${boarddata.notice_num}"><button type="button" class="btn btn-dark float-right">수정</button></a>
					<a href="noticedelete.com?num=${boarddata.notice_num}"><button type="button" class="btn btn-danger float-end">삭제</button></a>
					</c:if>
			</div>
		</div>
	</div>
	

</body>
</html>