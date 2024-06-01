<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainpage/header.jsp"/>
<title>문의처리 list</title>
<html>
<head>
<style>
 /* footer{position:fixed; 
            bottom:0px; 
            height:3rem;
            background:#ccc;
            width:100%; 
            text-align:center;
            } */
</style>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
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
<style>

.btn1 {display : inline-block; float:right;}
.btn2 {display : inline-block; float:left;} 

footer{margin-top:100px;}
</style>

 <body>
  
  <div class="container-fluid border text-center" >
 </div>
 
  <br>
  <br>
  <br>
<div class="container">
	<c:if test="${listcount > 0 }">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>문의사항 게시판</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				
				<table class="table text-center">
					<tbody>
						<tr class="table-active">
							<td>문의번호</td>
							<td>카테고리</td>
							<td>제목</td>
							<td>작성자</td>
							<td>처리상태</td>
						</tr>
						<c:set var="num" value="${listcount-(page-1)*limit }"/>
						<c:forEach var="q" items="${totallist }">
						<tr>
							<td><!-- 문의번호 -->
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td>${q.qna_cate}</td>
							<td>
							<c:if test="${!empty sessionScope.id && sessionScope.id == 'admin'}">
							<a href="qnadetail.co?num=${q.qna_num }">
						
							<c:if test="${q.qna_title.length()>=20 }">
							<c:out value="${q.qna_title.substring(0,20) }..."/>
								</c:if>
								<c:if test="${q.qna_title.length()<20 }">
								<c:out value="${q.qna_title }"/>
								</c:if>
								</a>
							</c:if>
							<c:if test="${sessionScope.id != 'admin'}">
							<c:if test="${q.qna_title.length()>=20 }">
							<c:out value="${q.qna_title.substring(0,20) }..."/>
								</c:if>
								<c:if test="${q.qna_title.length()<20 }">
								<c:out value="${q.qna_title }"/>
								</c:if>
							</c:if>
							</td>
							<td>${q.id}</td>
							<td>${q.qna_state}</td>
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
									<a href="qnalist.co?page=${a}"  
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
								<a class="page-link" href="qnalist.co?page=${page+1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
		
					<form action="qnalist.co" method="post">
					<div class="input-group">
						<select id="viewcount" name="search_field">
							<option value="0" selected>제목</option>
							<option value="1">내용</option>
						</select> <input name="search_word" type="text" placeholder="제목을 입력하세요">
						<button class="btn btn-secondary" type="submit">검색</button>
					</div>
				<c:if test="${!empty sessionScope.id && sessionScope.id != 'admin'}">
				<a href="qnawrite.co"><button type="button" class="btn btn-dark float-end">문의하기</button></a>
				</c:if>
				</form>
			</div>
		</div>
</c:if>

<%-- 문의글이 없는 경우--%>

 <c:if test="${listcount == 0 }">
	<font size=5>등록된 글이 없습니다.</font><br>
	<button type="button" onclick="history.back()" class="btn btn-dark float-left back">뒤로가기</button>
	<c:if test="${!empty sessionScope.id && sessionScope.id != 'admin'}">
	<a href="qnawrite.co"><button type="button" class="btn btn-dark float-left">문의하기</button></a>
	</c:if>
 </c:if>

</div>



 </body>
 <jsp:include page="../mainpage/footer.jsp"/>
</html>
