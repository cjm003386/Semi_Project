<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>상품 상세보기</title>
 <style>
  .fakeimg {
    height: 440px;
    width: 320px;
    background: #aaa;
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
         				  <div class="fakeimg"></div>
           
        				   <div class="col-sm-6">
           
          				   <p>상품명</p>
						   <hr>
						   <p>판매가</p>
						   <hr>
						   <p>배송정보</p>
						   <hr>
			
        				  
         				   
         				   <p>사이즈<span>&nbsp;&nbsp;</span>
         				   <select name="size" id="size-select">
         				      <option value="">--[필수] 사이즈를 선택해주세요--</option>
        				      <option value="xs">xs</option>
        				      <option value="s">s</option>
       			 	          <option value="m">m</option>
       				          <option value="l">l</option>
          		 		      <option value="xl">xl</option>
       				      </select></p>
       				      <hr>
           
          				  <p>색상<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          				  <select name="size" id="size-select">
       				          <option value="">--[필수] 사이즈를 선택해주세요--</option>
         				      <option value="black">블랙</option>
        				      <option value="white">화이트</option>
        				      <option value="blue">블루</option>
           				  </select></p><hr>
           				  
           				  <table class="total">
           				    <tr>
           				    <td>상품명</td>
           				    <td><input type="text" name="p_num1" id="p_num1" size="2" maxlength="4" class="p_num" value="2">
           				    </td>
           				    <td>상품금액</td>
           				    </tr>
           				  </table><br>
           				  
       				      
           			      <hr style="opacity: 1; background-color: black; margin: 0 auto">
          				  <p>총 상품 금액</p><br>
          				  <button type="button" class="btn btn-dark">바로 구매하기</button>
					      <button type="button" class="btn btn-light">관심상품 담기</button>
					      <button type="button" class="btn btn-light">장바구니 담기</button>
          				  </div>       
         		    </div><br>
				   
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
</body>
</html>