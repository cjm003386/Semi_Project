<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
#h .d-flex { margin: 0;}
#h .nav-item { text-decoration: none;  font-size: 13pt; }
#h .navbar { padding: 10px 20px; }
#h .navbar-brand img {
	width: 45px !important;
	height: 45px !important;
	object-fit: cover !important;
	margin: 0 0 0 15px;
}
#h + .container-fluid img {
	height: 230px;
	width: 400px;
	object-fit: none;
	position: relative;
	top: 5px
}
</style>

<nav id="h" class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-end">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/image/main/home.png"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse flex-row-reverse">
			<ul class="navbar-nav mb-2 mb-lg-0 ">
				<c:if test="${!empty id}">
					<li class="nav-item"><a class="nav-link" href="#"><small>로그아웃</small></a></li>
					<c:if test="${id=='admin'}">
						<li class="nav-item"><a class="nav-link" href="#"><small>공지사항</small></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><small>회원정보</small></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><small>문의처리</small></a></li>
						<li class="nav-item"><a class="nav-link" href="#"><small>FAQ</small></a></li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Dropdown</a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="#">Link</a></li>
								<li><a class="dropdown-item" href="#">Another link</a></li>
								<li><a class="dropdown-item" href="#">A third link</a></li>
							</ul></li>
					</c:if>
					<c:if test="${!empty id && id != 'admin'}">
						<li class="nav-item"><a class="nav-link" href="#"><small>정보수정</small></a></li>
					</c:if>
				</c:if>
				<c:if test="${empty id}">
					<li class="nav-item"><a class="nav-link" href="login.net"><small>로그인</small></a></li>
					<li class="nav-item"><a class="nav-link" href="join.net"><small>회원가입</small></a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="cart.do"><small>장바구니</small></a></li> <!-- 링크 -->
				<li class="nav-item"><a class="nav-link" href="wishlist.do"><small>관심상품</small></a></li> <!-- 링크 -->
				<li class="nav-item"><a class="nav-link" href="mypage.pg"><small>마이페이지</small></a></li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><small>커뮤니티</small></a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="#">FAQ</a></li>
						<li><a class="dropdown-item" href="#">공지사항</a></li>
						<li><a class="dropdown-item" href="#">문의사항</a></li>
						<li><a class="dropdown-item" href="reviewlist.pg">리뷰</a></li>
						<li><a class="dropdown-item" href="#">중고거래</a></li>
					</ul></li>
			</ul>
		</div>
		<form class="d-flex">
			<input class="form-control me-2" type="search" placeholder="Search">
			<button class="btn btn-outline-success" type="submit">Search</button>
		</form>
	</div>
</nav>


<div class="container-fluid p-2 text-center">
	<a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/image/main/logo.png"></a>
</div>

<div class="container-fluid border">
	<ul class="nav justify-content-center">
		<li class="nav-item"><a class="nav-link text-body" href="top.do">상의</a> <!-- 링크 -->
		</li>
		<li class="nav-item"><a class="nav-link text-body" href="bottom.do">하의</a> <!-- 링크 -->
		</li>
		<li class="nav-item"><a class="nav-link text-body" href="outer.do">아우터</a> <!-- 링크 -->
		</li>
		<li class="nav-item"><a class="nav-link text-body" href="accessories.do">악세서리</a> <!-- 링크 -->
		</li>
	</ul>
</div>

