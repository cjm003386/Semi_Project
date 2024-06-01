<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
  <head>
	 <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>주문 완료</title>
	<jsp:include page="/mainpage/header.jsp"/>
	<script src="js/jquery-3.6.0.js"></script>
	  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  footer{position:fixed; 
			bottom:0px; 
			height:3rem;
			background:#ccc;
			width:100%; 
			text-align:center;
			line-height:3rem;}
@media (min-width: 768px) {
    .my-custom-container{
        width:85%;
    }
}

  </style>
<%-- 필요한 클래스를 import 하려면 <%@ page %>  page directive(페이지 지시어)를 사용하여 import 를 한다. --%>    
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>


<% 
	//현재날짜와 시간을 알아오는 메소드 생성하기
	Date now = new Date(); // 현재시각
	
	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String today = sdformat.format(now);
%>
<body>
<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
<br>
<div class="container">
	<div class="jumbotron">
	<!-- 점보트론은 특별한 내용이나 정보에 대한 추가 주의를 환기시키기 위한 큰 회색 상자를 나타내는 것입니다. -->
		<h4 class="display-4"><img src="${pageContext.request.contextPath}/img/check.png" width="80" height="80"/>&nbsp;<i class="fas fa-shopping-basket"></i>고객님의 주문이 완료 되었습니다.</h4><br>
		<p class="lead">주문번호 : 202208xxxxxx <br>주문일자 : <%= today%></p>
		<hr class="my-4">
		<p>고객님의 주문이 완료 되었습니다.</p>
		 주문내역 및 배송에 관한 안내는 <a href="orderlist.pg?id=${id }" style="color: blue;">주문정보</a> 를 통하여 확인 가능합니다.
	</div>	
</div>

<br>
<div style="margin-top: 40px; margin-bottom: 10px; font-weight: bold; font-size: 16px;">결제 정보</div>
		<table class="table table-bordered" style="text-align: center; margin-bottom: 0px;">
			<thead>
				<tr style="font-weight: bolder; font-size: 13pt;">
					<th>총 주문 금액</th>
					<td style="width: 85%"><fmt:formatNumber  value="${order_info.order_cost}" pattern="#,###" />
									원</td>
				</tr>	
			</thead>
			<tbody>	
				<tr style="font-weight: bolder; font-size: 13pt;">
					<th>결제 수단</th>
					<td style="width: 85%">${order_info.payment_option }</td>
				</tr>
			</tbody>
		</table>
<br>

				<br>
				<div>
					<b>주문 내역</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>

							<table class="table">
								<tr class="table-active">
									<td>이미지</td>
									<td>상품명</td>
									<td>옵션</td>
									<td>수량</td>
									<td>상품구매금액</td>
									<td>배송비</td>
									<td>합계</td>
								</tr>
								<tr class="align-middle">
									<td><img src="${pageContext.request.contextPath}/image/main/product/${order_item.product_image}.jpg" alt="${order_item.product_image}" width="77px"></td>
									<td>${order_item.product_name}</td>
									<td>${order_item.product_color}[${order_item.product_size }]</td>
								 <td>${order_item.product_count }</td>
								<td><fmt:formatNumber  value="${order_item.product_price }" pattern="#,###" /></td>
								<td><fmt:formatNumber  value="2500" pattern="#,###" /></td>
									<c:set var="sum" value="2500"/>
								<td>
									<fmt:formatNumber  value="${order_info.order_cost}" pattern="#,###" /> 
									<input type="hidden" name="totalprice" value="${sum }"><!-- 결제 시 결제화면에 담아갈 총금액 정보-->
									</td>

										
							</table>
						</div>
				</div>		
		</div>


<div class="mt-5 p-4 bg-light text-center">
  <p>Footer</p>
</div>
 </body>
</html> 