<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>	 
<title>Top</title>
<style>
.carousel-inner { text-align : center; }	
img { margin : 0 auto;  object-fit: cover;}
.card { margin : 10px;}
.subject{margin:50px 0px 0px 270px;font-weight:bold;font-size:1.5rem;}
.container{margin:0 auto;}
.button{
     text-align:center;
     background-color:white;
     color:gray;
     border:1px solid gray;
     width:200px;
     height:40px;
     margin:10px;
}
</style>
</head>
<body>
<div class="subject"><b>상의</b></div>
   <div class="container">
   <hr>
   <div>
      <ul class="nav justify-content-center">
        <button type="button" class="button" id="short-tee">Short Tee</button>
        <button type="button" class="button" id="long-tee">Long Tee</button>
        <button type="button" class="button" id="shirts">Shirts</button>
        <button type="button" class="button" id="knit">Knit</button>
        <button type="button" class="button" id="hoodie">Hoodie</button>
      </ul>
   </div>

        <section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">
			<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="t" items="${toplist}">			
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top" src="${pageContext.request.contextPath}/image/main/product/${t.product_image}.jpg" alt="${t.product_image}" />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${t.product_name}</h5>
									<!-- Product price-->
									&#8361;<fmt:formatNumber type="number" maxFractionDigits="3" value="${t.product_price}" />

								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<!-- product_code 상세페이지로 get방식으로 넘겨주기 --> 
									<a class="btn btn-outline-dark mt-auto" href=" detail.do?product_code=${t.product_code} ">상세보기</a>  
								</div>
							</div>
						</div>
					</div>
				</c:forEach>	
			</div>	
		</div>			
	 </section>
     </div>
     <jsp:include page="../mainpage/footer.jsp" />
     <script src="js/jquery-3.6.0.js"></script>
     <script>
       $("#short-tee").click(function(){
    	   location.href="top.do?type=short-tee";
       })
       
       $("#long-tee").click(function(){
    	   location.href="top.do?type=long-tee";
       })
       
        $("#shirts").click(function(){
    	   location.href="top.do?type=shirts";
       })
       
       $("#knit").click(function(){
    	   location.href="top.do?type=knit";
       })
       
       $("#hoodie").click(function(){
    	   location.href="top.do?type=hoodie";
       })
       
     </script>
</body>
</html>