<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script> 
<style>
.d-flex {margin : 0;}
.nav-item {font-size : 13pt; style:bold;}
.navbar {padding : 10px 20px;}
a{text-decoration : none; color:black;}
.navbar-brand img {position : relative; left: 30px }
.container-fluid  img {height : 230px; width: 400px; object-fit: none; position : relative; top : 5px}

</style> 


<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">
  <a class="navbar-brand" href="#!"><img src="image/home.png" width="45px" class="header_img"></a>
  <div class="container-fluid">
   <div class="collapse navbar-collapse flex-row-reverse">
      <ul class="navbar-nav">
      <c:if test="${!empty id}">   
        <li class="nav-item">
          <a class="nav-link" href="#"><small>로그아웃</small></a>
        </li>
        <c:if test="${id=='admin'}">
        <li class="nav-item">
          <a class="nav-link" href="#"><small>공지사항</small></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"><small>회원정보</small></a>
        </li>  
        <li class="nav-item">
          <a class="nav-link" href="#"><small>문의처리</small></a>
        </li>  
        <li class="nav-item">
          <a class="nav-link" href="#"><small>FAQ</small></a>
        </li>  
       <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Dropdown</a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Link</a></li>
            <li><a class="dropdown-item" href="#">Another link</a></li>
            <li><a class="dropdown-item" href="#">A third link</a></li>
          </ul>
        </li>
        </c:if>
        <c:if test="${!empty id && id != 'admin'}">  
        <li class="nav-item">
          <a class="nav-link" href="#"><small>정보수정</small></a>
        </li>  
        </c:if>
        </c:if>
        <c:if test="${empty id}">
        <li class="nav-item">
          <a class="nav-link" href="#"><small>로그인</small></a>
        </li>  
        </c:if>
        <li class="nav-item">
          <a class="nav-link" href="#"><small>장바구니</small></a>
        </li>  
        <li class="nav-item">
          <a class="nav-link" href="#"><small>관심상품</small></a>
        </li>  
        <li class="nav-item">
          <a class="nav-link" href="mypage.pg"><small>마이페이지</small></a>
        </li>  
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"><small>커뮤니티</small></a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">FAQ</a></li>
            <li><a class="dropdown-item" href="#">공지사항</a></li>
            <li><a class="dropdown-item" href="#">문의사항</a></li>
            <li><a class="dropdown-item" href="reviewlist.pg">리뷰</a></li>
            <li><a class="dropdown-item" href="#">중고거래</a></li>
          </ul>
        </li>
      </ul>
    </div>
    <form class="d-flex">
      <input class="form-control me-2" type="search" placeholder="Search">
      <button class="btn btn-outline-success" type="submit">Search</button>
    </form>
  </div>
</nav>

<div class="container-fluid text-center">
	<h1><a href="#"><img src="image/logo2.png"></a></h1>
</div>
 
<div class="container-fluid border">
	<ul class="nav justify-content-center">
    	<li class="nav-item">
      		<a class="nav-link text-body" href="#">상의</a>
    	</li>
    	<li class="nav-item">
      		<a class="nav-link text-body" href="#">하의</a>
    	</li>
    	<li class="nav-item">
     		 <a class="nav-link text-body" href="#">아우터</a>
    	</li>
    	<li class="nav-item">
      		<a class="nav-link text-body" href="#">악세서리</a>
    	</li>
   	</ul>
</div>

     