<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>주문내역</title>
<jsp:include page="../mainpage/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src ='${pageContext.request.contextPath}/js/addressadd.js'></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<Style>
img{width:20px}

</Style>
</head>
<body>
	<form method='post' id="myform"
			  action='addresslistaddprocess.pg'>
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<br>
				<b>배송지 등록</b>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">배송지명</td>
						<td><input type="text" name='address_name' class="form-control" id="addressname"></td>
					</tr>
					<tr>
						<td class="table-active text-center">수령인</td>
						<td><input type="text" name='address_receiver' id='receiver' class="form-control"></td>
					</tr>
					<tr>
						<td class="table-active text-center">휴대전화</td>
						<td>
						<div class="input-group ">
							    <input type="text" name="address_phone" id='phone' class="form-control p-2">
							  </div> <span id="message"></span>
						</td>
					</tr>
					<tr>
						<td class="table-active text-center">우편번호</td>
						<td>
						<div class="col-7">
					        <div class="input-group">
					            <input type="text" class="form-control" id="post1" name="post">
					            <button type="button" id="postcode" class="btn btn-dark">우편번호</button>
					        </div>
					    </div>
						</td>
					</tr>
					<tr>
						<td class="table-active text-center">주소</td>
						<td><input type="text" name="address1" class="form-control" id="address1" ></td>
					</tr>
					<tr>
						<td class="table-active text-center">상세주소</td>
						<td><input type="text" name="address2" class="form-control" id="address2" ></td>
					</tr>
				</table>
				<br>
				<div style="text-align: center">
					<button type="submit" class="btn btn-dark">등록</button>
					<button type="reset" class="btn btn-light" onclick="history.back()"
						data-mdb-ripple-color="dark" style="width: 90px">취소</button>
				</div>
			</div>
		</div>
	</div>
</form>
<br>
<br>
<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>