<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainpage/header.jsp"/>
<script src="js/jquery-3.6.0.js"></script>
<html>
<head>
<style>
a{text-decoration:none; color:black;}

.btn1{float:right}
.btn1{height:50px;}
.btn2{float:end}
.btn2{height:50px;}

 /* footer{position:fixed; 
            bottom:0px; 
            height:3rem;
            background:#ccc;
            width:100%; 
            text-align:center;
            } */
</style>
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



 <body>
  
 
<br>
	<div class="container">
	<c:if test="${listcount > 0 }">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>공지사항</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				
				<table class="table text-center">
					<tbody>
						<tr class="table-active">
							<td>번호</td>
							<td>제목</td>
							<td>작성자</td>
							<td>날짜</td>
							<td>조회수</td>
						</tr>
						<c:set var="num" value="${listcount-(page-1)*limit }"/>
						<c:forEach var="n" items="${totallist }">
						<tr>
							<td><!-- 글번호 -->
								<c:out value="${num }"/>
								<c:set var="num" value="${num-1 }"/>
							</td>
							<td>
							
							<a href="noticedetail.co?num=${n.notice_num }">
							<c:if test="${n.notice_title.length()>=20 }">
							<c:out value="${n.notice_title.substring(0,20) }..."/>
								</c:if>
								<c:if test="${n.notice_title.length()<20 }">
								<c:out value="${n.notice_title }"/>
								</c:if>
								</a>
							</td>
							<td>${n.notice_id }</td>
							<td>${n.notice_date }</td>
							<td>${n.notice_readcount}</td>
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
									<a href="noticelist.co?page=${a}"  
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
								<a class="page-link" href="noticelist.co?page=${page+1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
		
					<form action="noticelist.co" method="post">
					<div class="input-group">
						<select id="viewcount" name="search_field">
							<option value="0" selected>제목</option>
							<option value="1">내용</option>
						</select> <input name="search_word" type="text" placeholder="검색어를 입력하세요">
						<button class="btn btn-secondary" type="submit">검색</button>
					</div>
				<c:if test="${!empty sessionScope.id && sessionScope.id == 'admin'}">
				<a href="noticewrite.com"><button type="button" class="btn btn-dark float-end">공지 추가</button></a>
				</c:if>
				
				</form>
			</div>
		</div>
</c:if>

<%-- 게시글이 없는 경우--%>

 <c:if test="${listcount == 0 }">
	<font size=5>등록된 글이 없습니다.</font><br>
	<button type="button" onclick="history.back()" class="btn btn-dark float-left back">뒤로가기</button>
	<c:if test="${!empty sessionScope.id && sessionScope.id == 'admin'}">
	<a href="noticewrite.com"><button type="button" class="btn btn-danger float-left">공지 추가</button></a>
	</c:if>
 </c:if>
</div>

<br><br><br>
 </body>
<jsp:include page="../mainpage/footer.jsp"/>
</html>
</html>