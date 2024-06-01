<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>상품 상세보기</title>
 <style>
  .fakeimg {
    height: 150px;
    width: 120px;
    background: #aaa;
  }
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
          <label for="pet-select">Choose an option:</label>

<select name="size" id="size-select">
    <option value="">--Please choose an option--</option>
    <option value="xs">xs</option>
    <option value="s">s</option>
    <option value="m">m</option>
    <option value="l">l</option>
    <option value="xl">xl</option>
</select>
    </div>
    </div>
			<br>
			<table class="table table-bordered">
				<tr>
					<td>상품명</td>
					<td>수량조절</td>
					<td>상품금액</td>
				</tr>
			</table>
			<hr>
	 <button type="button" class="btn btn-dark">
      바로 구매하기</button>
	 <button type="button" class="btn btn-light">
      장바구니 담기</button>

</div>
				

			</div>
		</div>
	</div>
</body>
</html>