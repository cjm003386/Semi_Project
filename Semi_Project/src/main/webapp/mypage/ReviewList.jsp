<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../mainpage/header.jsp"/>
<style>
a{text-decoration:none; color:black}
</style>
<script src="js/jquery-3.6.0.js"></script>
<script>
$(document).ready(function() { 
	
	var selectedValue='${search_field}'
	if(selectedValue !='-1')
		$("#viewcount").val(selectedValue);
	
	$(".search").click(function(){
		if($('#search').val()==''){
			alert("검색어를 입력하세요");
			$('input').focus();
			return false;
		}
	})

	
	$("#viewcount").change(function(){
		selectedValue=$(this).val();
		$("input").val('');
		message=["제목", "내용"]
		$("input").attr("placeholder", message[selectedValue]+"을 입력하세요")
	})
	
	});
</script>
</head>
<style>
input{border:1px solid black}
b{font-size:0.9em}
footer{position:fixed; 
			bottom:0px; 
			height:3rem;
			background:#ccc;
			width:100%; 
			text-align:center;
			}
</style>
<title>리뷰 게시판</title>
</head>
<body>
<br>
	<div class="container">
	<c:if test="${listcount > 0 }">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>리뷰 게시판</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>

				<table class="table ">
					<tbody>
						<tr class="table-active">
							<td>리뷰 번호</td>
							<td>제목</td>
							<td>작성자</td>
							<td>날짜</td>
							<td>조회수</td>
						</tr>
						<c:set var="num" value="${listcount-(page-1)*limit }"/>
						<c:forEach var="r" items="${totallist }">
						<tr>
							<td>
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td>
							<c:if test="${r.review_re_lev !=0 }">
								<c:forEach var="a" begin="0" end="${r.review_re_lev*2 }" step="1">
									&nbsp;
								</c:forEach>
								<img src="${pageContext.request.contextPath}/image/mypage/line.gif">
							</c:if>
							
							<c:if test="${r.review_re_lev ==0 }">
								&nbsp;
							</c:if>
							
							<a href="reviewdetail.pg?num=${r.review_num }">
							<c:if test="${r.review_subject.length()>=20 }">
							<c:out value="${r.review_subject.substring(0,20) }..."/>
								</c:if>
								<c:if test="${r.review_subject.length()<20 }">
								<c:out value="${r.review_subject }"/>
								</c:if>
								</a>[${r.cnt}]
							</td>
							<td>${r.review_name }</td>
							<td>${r.review_date }</td>
							<td>${r.review_readcount }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="center-block">
					<div class="container">
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center">
							<c:if test="${page <=1 }"> 
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:if>
							<c:if test="${page > 1 }">
								<li class="page-item"><a class="page-link" href="#"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								</a></li>
							</c:if>
							
							<c:forEach var="a" begin="${startpage }" end="${endpage }">
								<c:if test="${a==page}"> 
								<li class="page-item active">
									<a class="page-link">${a }</a>
								</li>
								</c:if>
								<c:if test="${a!=page}"> 
								<li class="page-item">
									<a href="reviewlist.pg?page=${a}"  
									class="page-link">${a }</a>
								</li>
								</c:if>
							</c:forEach>
								
								<c:if test="${page >=maxpage}"> 
								<li class="page-item">
								<a class="page-link gray"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
								<c:if test="${page <maxpage}"> 
								<li class="page-item">
								<a class="page-link" href="reviewlist.pg?page=${page+1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
				<form action="reviewlist.pg" method="post">
					<div class="input-group">
						<select id="viewcount" name="search_field">
							<option value="0" selected>제목</option>
							<option value="1">내용</option>
						</select>
						 <input id="search" name="search_word" type="text"  placeholder="제목을 입력하세요" value="${search_word }">
						<button class="btn btn-secondary" class="search" type="submit">검색</button>
					</div>
				</form>
			</div>
		</div>
		</c:if>
		<c:if test="${listcount==0 }">
		<section class="py-5">
 		<font size=5>등록된 리뷰가 없습니다.</font><br><br>
 		<a href="main.net"><button type="button" class="btn btn-dark float-left back">메인으로</button></a>
 		</section>
 	</c:if>
	</div>
	<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>