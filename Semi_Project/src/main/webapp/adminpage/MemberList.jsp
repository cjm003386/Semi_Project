<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainpage/header.jsp"/>
<title>회원 정보 list</title>
<html>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<head>
<style>
a{text-decoration:none; color:black;}
b{font-size:25px;}
 /* footer{ position:fixed; 
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
			message=["아이디", "이름"]
			$("input").attr("placeholder", message[selectedValue]+"을 입력하세요")
		})
		
		});
</script>
		
 	
</head>

<body>

  
<div class="container">
	<c:if test="${listcount > 0 }">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>회원 리스트</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>

				<table class="table table-hover text-center">
					<tbody>
						<tr class="table-active">
							<th>이름</th>
							<th>성별</th>
							<th>아이디</th>
							<th>등급</th>
							<th>회원 정보수정</th>
						</tr>
						<c:set var="num" value="${listcount-(page-1)*limit }"/>
						<c:forEach var="c" items="${totallist }">
						<tr>
							<td>${c.name}</td>
							<c:if  test="${c.gender=='m'}">
							    <td>남자</td>
							</c:if>
							<c:if  test="${c.gender=='f'}">
							    <td>여자</td>
							</c:if>
							<td>${c.id}</td>
							<td>${c.grade}</td>
							<td><a href="memberinfo.com?id=${c.id}"><button type="submit" class="btn btn-primary">정보수정</button></a>
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
									<a href="memberlist.com?page=${a}"  
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
								<a class="page-link" href="memberlist.com?page=${page+1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
				<form action="memberlist.com" method="post">
					<div class="input-group">
						<select id="viewcount" name="search_field">
							<option value="0" selected>아이디</option>
							<option value="1">이름</option>
						</select>
						 <input id="search" name="search_word" type="text"  placeholder="검색어를 입력하세요" value="${search_word }">
						<button class="btn btn-secondary" class="search" type="submit">검색</button>
					</div>
				</form>
			</div>
		</div>
		</c:if>
		<c:if test="${listcount==0 }">
 		<font size=5>가입된 회원 정보가 조회되지 않습니다.</font><br>
 		<button type="button" onclick="history.back()" class="btn btn-dark float-left back">뒤로가기</button>
 	</c:if>
	</div>
 </body>
 
 <jsp:include page="../mainpage/footer.jsp"/>
</html>
