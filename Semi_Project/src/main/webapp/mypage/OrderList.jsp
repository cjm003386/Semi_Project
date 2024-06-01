<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<title>주문내역</title>
<style>
a{text-decoration:none}
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
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active" id="userinfo-tab"
							data-bs-toggle="tab" data-bs-target="#userinfo" type="button"
							role="tab" aria-controls="userinfo" aria-selected="true">주문내역조회</button>
					</li>
					<li class="nav-item" role="presentation">
						<a href="ordercancellist.pg"><button class="nav-link" id="myreview-tab" data-bs-toggle="tab"
							data-bs-target="#myreview" type="button" role="tab"
							aria-controls="myreview" aria-selected="false">주문취소내역</button></a><!-- 눌렀을 때 새페이지로 이동?? 거기서 바로 취소내역 탭열리게 하려면..?  -->
					</li>
				</ul>

				<!-- 내용 -->
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="userinfo"
						role="tabpanel" aria-labelledby="userinfo-tab">
						<div class="container custom">
							<br>
							<div>
								<b>주문 상품 정보</b>
							</div>
							<hr
								style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
							<br>
							<c:if test="${!empty list }">
							<table class="table">
								<tr class="table-active">
									<td>주문일자</td>
									<td>이미지</td>
									<td>상품명</td>
									<td>수량</td>
									<td>상품구매금액</td>
									<td>주문상태</td>
									<td>상품 옵션</td>
									<td align="center">취소</td>
								</tr>
								<!-- foreach문 시작 -->
								<c:forEach var="l" items="${list }"  varStatus="vs">
								<c:if test="${l.orderstate=='배송 완료'||l.orderstate=='배송 전' }">
								
								<tr class="align-middle">
									<td>${l.order_date}</td>
									<td><img src="${pageContext.request.contextPath}/image/main/product/${l.product_image}.jpg" alt="${p.product_image}" width="77px"></td>
									<td>${l.product_name }</td>
									<td>${l.product_count }</td>
									<td><fmt:formatNumber value="${l.order_cost}" pattern="#,###" />원</td>
									<td>${l.orderstate }</td>
									<td>
									${l.product_color}[${l.product_size }]</td>
									<td align="center"><!-- td 내부 모달 -->

										<div class="container justify-content-center">
											<c:if test="${l.orderstate=='배송 전'}"> 
											<button type="button" class="ms-3 btn btn-small btn-danger my-1"
												data-bs-toggle="modal" data-bs-target="#myModal${vs.index }">
												주문취소</button><br>
											<a href="deliveryok.pg?num=${ l.orderitem_code}&id=${id}">
											<button type="button" class="ms-3 btn btn-small btn-outline-dark my-1" id="deliveryok" >
												배송완료</button>
											</a>
											</c:if> 
											<c:if test="${l.orderstate=='배송 완료'}"> 
											<a href="reviewwrite.pg?num=${ l.product_code}"><button type="button" class="ms-3 btn btn-small btn-primary"
												>
												리뷰쓰기</button></a>
											</c:if> 
										</div> <!-- The Modal -->
										<div class="modal" id="myModal${vs.index }">
											<div class="modal-dialog">
												<div class="modal-content">

													<!-- Modal Header -->
													<div class="modal-header">
														<h4 class="modal-title">주문취소</h4>
														<button type="button" class="btn-close"
															data-bs-dismiss="modal"></button>
													</div>

													<!-- Modal body -->
													<div class="modal-body"><b>'${ l.product_name}'</b> 주문을 취소하시겠습니까?</div>

													<!-- Modal footer -->
													<div class="modal-footer">
														<a href="ordercancel.pg?num=${ l.orderitem_code}&id=${id}"><button type="button" class="btn btn-sm btn-danger"
															data-bs-dismiss="modal">주문취소</button></a>
														<button type="button" class="btn btn-sm btn-primary"
															data-bs-dismiss="modal">뒤로가기</button>
													</div>

												</div>
											</div>
										</div>
									</td><!-- td 안 modal -->
								</tr>
								</c:if>
								</c:forEach>
								<!-- foreach문 끝 -->
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
									<a href="orderlist.pg?id=${id }&page=${a}"  
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
								<a class="page-link" href="orderlist.pg?id=${id }&page=${page+1}"
									aria-label="Next"> <span aria-hidden="true">&raquo;</span>
								</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
							</c:if>
							<c:if test="${empty list}">
							
							<h3>주문내역이 없습니다.</h3>
							<a href="main.net"><button type="button" class="btn btn-primary float-end">쇼핑하러가기</button>
							</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$("#userinfo > div > table > tbody >tr > td:nth-child(7) > div.container.justify-content-center > a > #deliveryok").click(function(event){
		var answer = confirm("배송 완료로 변경하시겠습니까?");
		console.log(answer);
		if(!answer){
			event.preventDefault();
		}
	})
	</script>
	<br>
<br>
<br>
<br>
	<br>
<br>
<br>
<br>
</section>

<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>