<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<title>주문내역</title>
<style>
textarea {
	width: 100%;
  top: 0; left: 0; right: 0; bottom: 0;
}
</style>
</head>
<body>
<form>
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
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
									<td>수량</td>
									<td>상품구매금액</td>
									<td>배송비</td>
									<td>합계</td>
								</tr>
								<tr class="align-middle">
									<td><img src="../image/profile.png" width="77px"></td>
									<td>상품명 예시</td>
									<td>수량</td>
									<td>상품구매금액</td>
									<td>배송비</td>
									<td>합계</td>

										
							</table>
							<br>
				<div>
					<b>주문 정보</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>
							
			<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">주문하시는 분</td>
						<td><input type="text" name='' value= ></td>
					</tr>
					<tr>
						<td class="table-active text-center">주소</td>
						<td><input type="text" name="post" value="${memberinfo.post }" class="my-1">
						<button type="button" class="btn-sm btn-dark">우편번호</button><br>
							<input type="text" name="post" value="${memberinfo.post }"></td>
					</tr>
					<tr>
						<td class="table-active text-center">휴대전화</td>
						<td><input type="text" name="address1" class="me1">
						-<input type="text" name="address1" class="me1">
						-<input type="text" name="address1" class="me1"></td>
					</tr>
					<tr>
						<td class="table-active text-center">이메일</td>
						<td><input type="text" name="address1" value="${memberinfo.address }" >
						@ <input type="text" name="address1" class="me-1" value="${memberinfo.address }" ></td>
					</tr>
					<tr>
						<td class="text-center table-active" colspan="2">배송정보</td>
					</tr>
					<tr>
						<td class="table-active text-center">받으시는 분</td>
						<td><input type="text" name="post" value="${memberinfo.post }"></td>
					</tr>
					<tr>
						<td class="table-active text-center">주소</td>
						<td><input type="text" name="post" value="${memberinfo.post }" class="my-1">
						<button type="button" class="btn-sm btn-dark">우편번호</button><br>
							<input type="text" name="post" value="${memberinfo.post }"></td>
					</tr>
					<tr>
						<td class="table-active text-center">휴대전화</td>
						<td><input type="text" name="address1" class="me1">
						-<input type="text" name="address1" class="me1">
						-<input type="text" name="address1" class="me1"></td>
					</tr>
					<tr>
						<td class="table-active text-center">배송메세지</td>
						<td><textarea></textarea></td>
					</tr>
				</table>
							<br>
				<div>
					<b>결제 예정금액</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="text-center" style="width: 20%">총 주문금액</td>
						<td class="text-center" style="width: 20%">총 할인 + 부가결제 금액</td>
						<td class="text-center" style="width: 20%">총 결제예정 금액</td>
					</tr>
					<tr>
						<td class="text-center" style="width: 20%"></td>
						<td class="text-center" style="width: 20%"></td>
						<td class="text-center" style="width: 20%"></td>
					</tr>
				</table>
				<div>
					<b>결제 수단</b>
					<br>
				</div>

					<div class="container-fluid border p-3 my-custom-container">


					<input type="radio" id="huey" name="drone" value="huey" checked> <label
						for="huey">무통장입금</label> 
					<input type="radio" id="dewey"
						name="drone" value="dewey"> <label for="dewey">카드결제</label>

					<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">입금자명</td>
						<td><input type="text" name='' value= ></td>
					</tr>
					<tr>
						<td class="table-active text-center">입금은행</td>
						<td><input type="text" name="post" value="${memberinfo.post }" class="my-1">
						</td>
					</tr>
				</table>
					<input type="radio" name="b" checked> <label
						for="huey" >현금영수증 신청</label> 
					<input type="radio" name="b"> <label for="dewey">신청 안 함</label>
					<button type="submit" class="btn btn-dark float-end">결제하기</button>
					</div>
					<br>
			</div>
		</div>
	</div>
</form>
</body>
</html>