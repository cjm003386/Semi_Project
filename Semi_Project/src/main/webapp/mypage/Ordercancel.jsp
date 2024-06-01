<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>주문내역</title>
<style>
a{text-decoration:none;}
</style>
</head>
<body>
<section class="py-5">
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<br>
				<div>
					<b>내 주문 내역</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>
				<%-- <c:if test="${!empty list || !empty cancel }">  --%>
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
						<a href="orderlist.pg"><button class="nav-link " id="userinfo-tab"
							data-bs-toggle="tab" data-bs-target="#userinfo" type="button"
							role="tab" aria-controls="userinfo" aria-selected="true">주문내역조회</button></a>
					</li>
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="myreview-tab" data-bs-toggle="tab"
							data-bs-target="#myreview" type="button" role="tab"
							aria-controls="myreview" aria-selected="false">주문취소내역</button><!-- 눌렀을 때 새페이지로 이동?? 거기서 바로 취소내역 탭열리게 하려면..?  -->
					</li>
				</ul>

				<!-- 내용 -->
				<div class="tab-content" id="myTabContent">
					
					<div class="tab-pane fade show active" id="myreview" role="tabpanel"
						aria-labelledby="myreview-tab">
						<div class="tab-pane fade show active" id="userinfo"
							role="tabpanel" aria-labelledby="userinfo-tab">
							<div class="container custom">
								<br>
								<div>
									<b>주문 취소 정보</b>
								</div>
								<hr
									style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
								<br>
									<c:if test="${!empty list}">
								<table class="table">
									<tr class="table-active">
										<td>주문일자</td>
										<td>이미지</td>
										<td>상품명</td>
										<td>수량</td>
										<td>상품구매금액</td>
										<td>주문상태</td>
										<td>상품 옵션</td>
									</tr>
									<c:forEach var="c" items="${list }"  varStatus="vs">
									<tr class="align-middle">
										<td>${c.order_date }</td>
										<td><img src="${pageContext.request.contextPath}/image/main/product/${c.product_image}.jpg" alt="${c.product_image}" width="77px"></td>
										<td>${c.product_name}</td>
										<td>${c.product_count}</td>
										<td><fmt:formatNumber value="${c.order_cost}" pattern="#,###" />원</td>
										<td>${c.orderstate}</td>
										<td>
									${c.product_color}[${c.product_size }]</td>
										</tr>
										</c:forEach>
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
							
							<c:forEach var="b" begin="${startpage }" end="${endpage }">
								<c:if test="${b==page}"> 
								<li class="page-item active">
									<a class="page-link">${b }</a>
								</li>
								</c:if>
								<c:if test="${b!=page}"> 
								<li class="page-item">
									<a href="ordercancellist.pg?id=${id }&page=${b}"  
									class="page-link">${b}</a>
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
								<a class="page-link" href="ordercancellist.pg?id=${id }&page=${page+1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
								
								</c:if>
								<c:if test="${empty list}">
								<h3>취소내역이 없습니다.</h3>
								</c:if> 
							</div>
						</div>
					</div>
				</div>
			<%-- </c:if> --%>
			<%-- <c:if test="${empty list && empty cancel }">
				<h1>주문 내역이 존재하지 않습니다.</h1>
				<a href="main.net"><button type="button" class="btn btn-primary float-end">쇼핑하러가기</button>
			</a>
			</c:if> --%>
			</div>
		</div>
	</div>
	</section>
	<br>
<br>
<br>
	<br>
<br>
	<br>
<br>
<br>
<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>