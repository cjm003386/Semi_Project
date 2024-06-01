<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<title>상품 상세보기</title>
<script src="js/jquery-3.6.0.js"></script>
 <style>
 .fakeimg {
    height: 440px;
    width: 320px;
  } 
 b{font-size:1.5rem;}
 p{text-align:left;font-weight:bold;}
 .total td{width:42%}
 .explain{width:auto;text-align:center;font-weight:bold;font-size:1.5rem;}

.tab body {
            color: #555;
            background: #eeeeee;
            margin:0;
            padding: 0;
            box-sizing: border-box;}

.tab h1 {
            padding: 50px 0;
            font-weight: 400;
            text-align: center;}

.tab p {
            margin: 0 0 20px;
            line-height: 1.5;}

.tab .main {
            min-width: 320px;
            max-width: 800px;
            padding: 50px;
            margin: 0 auto;
            background: #ffffff;}
            
.tab section {
            display: none;
            padding: 20px 0 0;
            border-top: 1px solid #ddd;}

        /*라디오버튼 숨김*/
.tab input {
              display: none;}

.tab label {
            display: inline-block;
            margin: 0 0px -1px;
            padding: 15px 76px;
            font-weight: 600;
            text-align: center;
            color: #bbb;
            border: 1px solid transparent;}

.tab label:hover {
            color: #2e9cdf;
            cursor: pointer;}

        /*input 클릭시, label 스타일*/
.tab input:checked + label {
              color: #555;
              border: 1px solid #ddd;
              border-top: 1px solid black;
              border-bottom: 1px solid #ffffff;}

        #tab1:checked ~ #content1,
        #tab2:checked ~ #content2,
        #tab3:checked ~ #content3,
        #tab4:checked ~ #content4 {
            display: block;}
  </style>
</head>
<body>
<form id="frm1" method="post">
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<br>
				<div>
					<b>상품 상세보기</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>
				
				<div class="container">
      				<div class="row text-center">
         				  <img class='fakeimg' src="${pageContext.request.contextPath}/image/main/product/${product.product_image}.jpg" alt="${product.product_image}">
        				   <div class="col-sm-6">
                        
                           <input type="hidden" name="img" value="${product.product_image}">
                           <input type="hidden" name="product_code" value="${product.product_code }">
                           <input type="hidden" name="name" value="${product.product_name }">
                           <input type="hidden" name="price" value="${product.product_price }">
          				   
          				   <p>상품명</p>${product.product_name }
						   <hr>
						   <p>판매가</p>${product.product_price }
						   <hr>
						   <p>배송정보</p>배송 기간 : 1~3 일
						   <hr>  
         				   
         				   <p>사이즈<span>&nbsp;&nbsp;</span>
         				   <select name="size" id="size-select" required>
         				      <option value="">--[필수] 사이즈를 선택해주세요--</option>
        				      <option value="xs">xs</option>
        				      <option value="s">s</option>
       			 	          <option value="m">m</option>
       				          <option value="l">l</option>
          		 		      <option value="xl">xl</option>
       				      </select></p>
       				      <hr>
           
          				  <p>색상<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          				  <select name="color" id="size-select" required>
       				          <option value="">--[필수] 색상을 선택해주세요--</option>
         				      <option value="black">black</option>
        				      <option value="white">white</option>
        				      <option value="blue">blue</option>
           				  </select></p><hr>
           				  
           				  <table class="total">
           				    <td>${product.product_name }</td>
           				    <td><input type="text" name="p_num1" id="p_num1" size="2" maxlength="4" class="p_num" value="1">
           				    </td>
           				    <td><div id="cal">${product.product_price }</div></td><!-- 곱하기 수량 추가 -->        				    
           				  </table><br>
           				  
       				      
           			      <hr style="opacity: 1; background-color: black; margin: 0 auto">
          				  <p>총 상품 금액</p><div id="cal2">${product.product_price }</div><br><input type="submit" value="바로 구매하기" class="btn btn-dark" onclick='btn_click("purchase");'>
          				   <!-- 링크. 상품 구매로 이동 -->
					      <button type="button" class="btn btn-primary btn-light" data-bs-toggle="modal" data-bs-target="#WishListModal">
                          관심상품 담기
                          </button>
                          <button type="button" class="btn btn-primary btn-light" data-bs-toggle="modal" data-bs-target="#CartModal">
                          장바구니 담기
                          </button>
                         
          				  </div>       
         		    </div><br>
         		    
         		              <!-- WishListModal  -->
                              <div class="modal fade" id="WishListModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">관심상품 담기</h5>
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                      관심상품에 추가하시겠습니까?
                                    </div>
                                    <div class="modal-footer">
                                      <input type="submit" value="추가" class="btn btn-dark" onclick='btn_click("wishList");'>
                                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>                                 
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <!-- CartModal  -->
                              <div class="modal fade" id="CartModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">장바구니 담기</h5>
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                      장바구니에 추가하시겠습니까?
                                    </div>
                                    <div class="modal-footer">
                                    <input type="submit" value="추가" class="btn btn-dark" onclick='btn_click("cart");'>
                                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                      
                                    </div>
                                  </div>
                                </div>
                              </div>  
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto"><br>
			  
			     <div class="tab">
  			        <input id="tab1" type="radio" name="tabs" checked> <!--디폴트 메뉴-->
   			        <label for="tab1">상품상세정보</label>

    			    <input id="tab2" type="radio" name="tabs">
  			        <label for="tab2">상품구매안내</label>

   			        <input id="tab3" type="radio" name="tabs">
  			        <label for="tab3">상품후기</label>

   			        <input id="tab4" type="radio" name="tabs">
   			        <label for="tab4">상품문의</label>

   			        <section id="content1">
   			          <p>
   	<iframe src="https://assets.pinterest.com/ext/embed.html?id=838232549375059771" height="4243" width="1000" frameborder="0" scrolling="no" ></iframe>
   			          </p>
  			        </section>

  			        <section id="content2">
  			          <p>tab menu2의 내용</p>
  			        </section>

  			        <section id="content3">
  			          <p>tab menu3의 내용</p>
  			        </section>

   			        <section id="content4">
   			          <p>tab menu4의 내용</p>
  			        </section>

			     </div>

             </div>
			</div>
		</div>
		<jsp:include page="../mainpage/footer.jsp" />
		<input type="hidden" name="num" value="${product.product_code}">
		<input type="hidden" name="id" value="${id}">
		
</form>
<script>
    function btn_click(str){                             
        if(str=="purchase"){                                 
            frm1.action="${pageContext.request.contextPath}/purchaseAction.go";      
        } else if(str=="cart"){      
        	frm1.action="${pageContext.request.contextPath}/cartadd.do";      
        }  else if(str=="wishList"){
        	frm1.action="${pageContext.request.contextPath}/wishlistadd.do";
        }
    }
    
    $("#p_num1").on("input", function(){
    	  var price = ${product.product_price };
    	  $("#cal").text(price* $(this).val())
    })
    
    $("#p_num1").on("input", function(){
    	  var price = ${product.product_price };
    	  $("#cal2").text(price* $(this).val())
    })
    
   function go(url){
    	$("form").attr("action", url);
    	$("form").submit();
    }
</script>
</body>
</html>