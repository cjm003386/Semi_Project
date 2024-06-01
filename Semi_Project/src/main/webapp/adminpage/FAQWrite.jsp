<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"/>
<title>FAQ page</title>
<head>
<style>
body {
  font-family: Helvetica, Arial, sans-serif;
  margin: 0;
}
.panel-faq-container {
  margin-bottom: -16px;
}
.panel-faq-title {
  cursor: pointer;
}
.panel-faq-answer {
  height: 0;
  overflow: hidden;
  /* 변화가 시작되는 쪽에다가 transition 적용해준다 0 -> 300px 
  왜? 닫기 버튼을 누를 때 변화가 티남 */
  transition: all 1s;
}
#btn-all-close {
  margin-bottom: 10px;
  background-color: gray;
  border: none;
  color: #fff;
  cursor: pointer;
  padding: 10px 25px;
  float: right;
}
#btn-all-close:hover {
  background-color: red;
  color: #000;
  transition: all 0.35s;
}
#btn-search{
{margin-bottom: 10px;
background-color:#726996;
border: none;
color: #fff;
cursor: pointer;
float: left;}
}
.active {
  display: block;
  /* 높이를 정해줘야지만 transition이 적용됨 */
  height: 300px;
}

tr td:first-child{width:5%;}
</style>
</head>
<body>
<div class="container-fluid border text-center">
 </div>
<div class="container">
    <h3 style="text-align: center; padding-top: 50px;">
      FAQ
      <small class="text-muted">자주 묻는 질문</small>
    </h3>
	<input name="search_word" type="text" placeholder="자주 묻는 질문 검색">
    <button class="btn-search" type="submit">검색</button>
    <button id="btn-all-close">FAQ 모두 닫기</button>
    
    <table class="table table-bordered table-gray table-hover">
      <thead class="thead-light text-center">
        <tr>
          <th>No</th>
          <th>FAQ 내용</th>
        </tr>
      </thead>
      <tbody class="text-center">
        <tr>
          <td>1</td>
          <td class="text-left" width="50%">
            <div class="panel-faq-container">
              <p class="panel-faq-title">교환 및 반품은 어떻게 하나요? ▼</p>
              <div class="panel-faq-answer">
                <p>Answer ... ↓</p>
                <p>문의 사항 게시판을 이용해주세요 평균 3~5일정도 소요됩니다.</p>
                <p>그 외 고객센터로 문의 주세요.</p>
              </div>
            </div>
          </td>
          
        </tr>
        <tr>
          <td>2</td>
          <td class="text-left" width="50%">
            <div class="panel-faq-container">
              <p class="panel-faq-title">반품 신청 기간이 어떻게 되나요? ▼</p>
              <div class="panel-faq-answer">
                <p>Answer (2) ... ↓</p>
                <p>교환/반품은 배송 완료 후 7일 이내 접수 가능합니다.</p>
                <p>기한 경과 후 교환/반품이 필요하다면 판매자에게 문의해 주세요.</p>
                <p>기한 경과 후 판매자와 협의 없이 임의로 판매자에게 상품을 발송하는 경우 교환/반품이 거절될 수 있습니다.</p>
              </div>
            </div>
          </td>
          
        </tr>
        <tr>
          <td>3</td>
          <td class="text-left" width="50%">
            <div class="panel-faq-container">
              <p class="panel-faq-title">상품 미도착 접수가 무엇인가요? ▼</p>
              <div class="panel-faq-answer">
                <p>Answer (3) ... ↓</p>
                <p>상품미도착 접수는 주문 상태가 배송중, 배송완료일 때 접수 가능합니다.</p>
                <p>판매자가 처리 결과를 등록하면 안내톡, 메일로 안내됩니다.</p>
                <p>판매자 처리 후에도 상품을 받지 못한 경우 상품미도착 접수 후 30일 이내 이의제기 신청이 가능합니다.</p>
              </div>
            </div>
          </td>
          
        </tr>
        <tr>
          <td>4</td>
          <td class="text-left" width="50%">
            <div class="panel-faq-container">
              <p class="panel-faq-title">구매확정이란 무엇인가요? ▼</p>
              <div class="panel-faq-answer">
                <p>Answer (4) ... ↓</p>
                <p>상품 구매 최종 의사결정입니다.</p>
                <p>구매 확정 후 교환 및 환불 불가능 합니다</p>
              </div>
            </div>
          </td>
       
        </tr>
        <tr>
          <td>5</td>
          <td class="text-left" width="50%">
            <div class="panel-faq-container">
              <p class="panel-faq-title">FAQ ▼</p>
              <div class="panel-faq-answer">
                <p>Answer (5) ... ↓</p>
                <p>FAQ test</p>
                <p></p>
              </div>
            </div>
          </td>
     
        </tr>
       
      </tbody>
    </table>
  </div>
  
  <div>
		<ul class="pagination justify-content-center">
		
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> ◀ </a></li>
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> 1 </a></li>
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> 2 </a></li>
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> 3 </a></li>
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> 4 </a></li>
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> 5 </a></li>
			<li><a href = "#" style = "margin-right:5px;" class="text-secondary"> ▶ </a></li>
			
		</ul>
		</div>
  
 <div class="mt-5 p-4 bg-light text-center margin bottom">
  <p>Footer</p>
</div>
  
  <script>
 
  // panel-faq-container
  var panelFaqContainer = document.querySelectorAll(".panel-faq-container"); // NodeList 객체
  
  // panel-faq-answer
  let panelFaqAnswer = document.querySelectorAll(".panel-faq-answer");

  // btn-all-close
  var btnAllClose = document.querySelector("#btn-all-close");
  
  // 반복문 순회하면서 해당 FAQ제목 클릭시 콜백 처리
  for( let i=0; i < panelFaqContainer.length; i++ ) {
    panelFaqContainer[i].addEventListener('click', function() { // 클릭시 처리할 일
      // FAQ 제목 클릭시 -> 본문이 보이게끔 -> active 클래스 추가
      panelFaqAnswer[i].classList.toggle('active');
    });
  };
  
  btnAllClose.addEventListener('click', function() {
    // 버튼 클릭시 처리할 일  
    for(let i=0; i < panelFaqAnswer.length; i++) {
        panelFaqAnswer[i].classList.remove('active');
    };
  });

 </script>
 </body>