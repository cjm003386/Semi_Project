<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<jsp:include page="header.jsp" />

<style>
.carousel-inner {text-align: center;}
img { margin: 0 auto; object-fit: cover; }
.card {	margin: 10px; }
.card-img-top{ height : 250px; object-fit:scale-down;}
</style>
</head>
<body>
	<div id="carouselExampleIndicators" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" 
					class="active" aria-current="true" aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="${pageContext.request.contextPath}/image/main/ad1.jpg" class="d-block w-100 h-50" alt="...">
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/image/main/ad2.jpg" class="d-block w-100 h-50" alt="...">
			</div>
			<div class="carousel-item">
				<img src="${pageContext.request.contextPath}/image/main/ad3.jpg" class="d-block w-100 h-50" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> 
			<span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Next</span>
		</button>
	</div>

	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="p" items="${productlist}">			
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top" src="${pageContext.request.contextPath}/image/main/product/${p.product_image}.jpg" alt="${p.product_image}" />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${p.product_name}</h5>
									<!-- Product price-->
									&#8361;<fmt:formatNumber type="number" maxFractionDigits="3" value="${p.product_price}" />

								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<!-- product_code 상세페이지로 get방식으로 넘겨주기 --> 
									<a class="btn btn-outline-dark mt-auto" href=" detail.do?product_code=${p.product_code} ">상세보기</a>  
								</div>
							</div>
						</div>
					</div>
				</c:forEach>	
			</div>	
		</div>			
	</section>
	<jsp:include page="footer.jsp" />
</body>
</html>
