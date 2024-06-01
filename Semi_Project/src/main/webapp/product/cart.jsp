<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>	 
<title>장바구니</title>
<style>
.table-image{width:50px;}
.table-active>td{width:150px;}
.table-active>td:nth-child(4){width:180px;}	 
div#myTabContent{width:950px; height:auto;}
td{text-align:center;}
.mt-3 {
    margin-top: 0.1rem!important;
}

#opt-change{
    color: #fff;
    background-color: #000036;
    border-color: #000036;
    width:90px;
    height:30px;
    margin:5px;
    padding:4px 0;
    border:none;
    }
.btn{
    border:none;
    width:120px;
    background-color: #000036;
    border-color: #000036;
    margin:3px;
}
#subject{
    vertical-align: top;
}
#select>td{
    vertical-align: top;
}
p{text-align:left}
.btn:nth-child(3){background-color:#dcdcdc;color:black;}
.btn-secondary{background-color:#dcdcdc;color:black}
.select-btn{text-align:right;}
.btn-orderAll{background-color:#black;color:white;width:150px;}
.btn-deleteAll{background-color:white;color:black;border:1px solid;width:150px}
</style>
</head>
<body>

	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<br>
				<div>
					<b>내 장바구니</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>

				<c:if test="${!empty cart }">
				<section class="py-5">
				<ul class="nav nav-tabs" id="cartTab" role="cartlist">
					<li class="nav-item" role="presentation">
						<button class="nav-link active">장바구니 정보</button>
					</li>
					
				</ul>
				<div class="cart-content" id="mycartContent">
					<div class="tab-pane fade show active" id="userinfo"
						role="tabpanel" aria-labelledby="userinfo-tab">
						<div class="container custom">
							<br>
			
							<br>
							
							<table class="table">
								<tr class="table-active">
									<td class="table-image"><input type='checkbox' name='Allchoice' id='Allchoice' value='Allchoice' /></td>
									<td>이미지</td>
									<td>상품명</td>
									<td>수량</td>
									<td>상품구매금액</td>
									<td>합계</td>
									<td align="center">선택</td>
								</tr>
								<c:forEach var="c" items="${cart }"  varStatus="vs">
								<tr class="align-middle">
									<td><input type='checkbox' name='choice' id='choice' value='choice' /></td>   <!--  메뉴 체크박스 클릭시 아래 모든 체크박스 표시  -->
									<td><img src="${pageContext.request.contextPath}/image/main/product/${c.product_image}.jpg" alt="${c.product_image}" width="77px"></td>
									<td>${c.product_name }<br>${c.opt_color }, ${c.opt_size}<br>
									<button type="button" id="opt-change" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#optionModal${vs.index}">옵션변경</button>
									</td>
									<td>${c.cart_count}                           
									<td>${c.product_price }</td>
									<td>${c.product_price*c.cart_count }</td> <!-- 곱하기 수량 -->
									<td align="center">
									
							<!-- Button trigger modal -->
                              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#orderModal${vs.index }">
                              주문하기
                              </button>
                              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#moveModal${vs.index }">
                              관심상품으로
                              </button>
                              <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#removeModal${vs.index }">
                              삭제
                              </button>

                              <!-- orderModal -->
                              <div class="modal fade" id="orderModal${vs.index }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">주문하기</h5>
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                      주문하기로 이동하시겠습니까?
                                    </div>
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">뒤로가기</button>        
                                      <a href="purchaseAction.go?num=${ c.product_code}&p_num1=${c.cart_count}&color=${c.opt_color}&size=${c.opt_size}">
                                      <button type="button" class="btn btn-primary">주문하기</button></a>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <!-- moveModal  -->
                              <div class="modal fade" id="moveModal${vs.index }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">관심상품으로</h5>
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                      관심상품으로 이동하시겠습니까?
                                    </div>
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">뒤로가기</button>
                                      <a href="cartTowishlist.do?id=${id }&product_code=${c.product_code}&product_image=${c.product_image}
                                      &product_name=${c.product_name}&product_price=${c.product_price}&color=${c.opt_color}&size=${c.opt_size}">
                                      <button type="button" class="btn btn-primary">이동하기</button></a>   
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <!-- removeModal  -->
                              <div class="modal fade" id="removeModal${vs.index }" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">삭제</h5>
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                      정말로 삭제하시겠습니까?
                                    </div>
                                    <div class="modal-footer">
                                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">뒤로가기</button>
                                      <a href="cartdelete.do?id=${id }&cart_code=${c.cart_code}"><button type="button" class="btn btn-primary">삭제하기</button></a>    <!-- display none -->
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <!-- optionModal  -->
                              <div class="modal fade" id="optionModal${vs.index}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h5 class="modal-title" id="exampleModalLabel">옵션변경</h5>
                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form action="cartupdate.do">
                                    <input type="hidden" name="id" value="${id }">
                                    <input type="hidden" name="cart_code" value="${c.cart_code }">
                                    <input type="hidden" name="product_code" value="${c.product_code }">
                                    <div class="modal-body">
                                      <p>${c.product_name }</p>
                                      <hr>
                                      <div>
                                      <img src="${pageContext.request.contextPath}/image/main/product/${c.product_image}.jpg" alt="${c.product_image}" width="200px">
                                      </div><br>
                                      <div>
                                      필수 옵션[사이즈] : 
                                      <select name="size" id="size-select">
                                         <option value="">--사이즈를 선택하세요--</option>
                                         <option value="xs">xs</option>
                                         <option value="s">s</option>
                                         <option value="m">m</option>
                                         <option value="l">l</option>
                                         <option value="xl">xl</option>
                                      </select>
                                      <br><br>
                                      필수 옵션[색상]  :
                                      <select name="color" id="color-select">
                                         <option value="">-- 색상을 선택하세요 --</option>
                                         <option value="black">black</option>
                                         <option value="white">white</option>
                                         <option value="blue">blue</option>
                                      </select>
                                      </div>
                                    </div><%-- <a href="cartupdate.do?id=${id }&code=${c.cart_code}"> --%>
                                    <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">변경</button><!-- select로 정한 값을 가져가려면 form으로 처리되어야함  -->      <!-- 변경된 옵션을 적용 -->
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                                    </div> 
                                                                    
                                    </form>
                                  </div>	
                                </div>
                              </div>
							 </td>
                             </c:forEach>
                                     
							</table>
							
						</div>
					</div>
					
				</div>
				<div class="select-btn">
				   <button type="button" class="btn btn-orderAll" data-bs-dismiss="modal">전체 상품 주문</button>   
                   <button type="button" class="btn btn-deleteAll">장바구니 비우기</button>   <!-- display none -->
				</div>
				</section>
				</c:if>
				<c:if test="${empty id }">
				<section class="py-5">
				<h1>장바구니 기능은 로그인을 해주세요</h1>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				<br>
				</section>
				</c:if>
			</div>
		</div>
	</div>
	
    <script>
    
    $('#Allchoice').click(function(){
        if($("#Allchoice").prop("checked")){
           $("#choice").prop("checked",true); 
       }else{
           $("#choice").prop("checked",false); 
       }
   });


    </script>
</body>
</html>