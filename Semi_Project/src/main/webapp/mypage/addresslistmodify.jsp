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
	<form method='post' 
			  action='addresslistmodifyprocess.pg?id=${id}&num=${addressinfo.addresslist_num}'>
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
						<td><input type="text" name='address_name' class="form-control" value="${addressinfo.address_name}"></td>
					</tr>
					<tr>
						<td class="table-active text-center">수령인</td>
						<td><input type="text" name='address_receiver' value="${addressinfo.address_receiver}" class="form-control"></td>
					</tr>
					<tr>
						<td class="table-active text-center">휴대전화</td>
						<td><input type="text"  value="${addressinfo.address_phone}" name='address_phone' class="form-control" id="check_pass">
						</td>
					</tr>
					<tr>
						<td class="table-active text-center">우편번호</td>
						<td>
						<div class="col-7">
					        <div class="input-group">
					            <input type="text" class="form-control"  value="${addressinfo.address_post}" id="post1" name="post">
					            <button type="button" id="postcode" class="btn btn-dark">우편번호</button>
					        </div>
					    </div>
						</td>
					</tr>
					<tr>
						<td class="table-active text-center">주소</td>
						<td><input type="text" name="address1" class="form-control" value="${addressinfo.address1}" id="address" ></td>
					</tr>
					<tr>
						<td class="table-active text-center">상세주소</td>
						<td><input type="text" name="address2" class="form-control" value="${addressinfo.address2}" id="address2" ></td>
					</tr>
				</table>
				<br>
				<div style="text-align: center">
					<button type="submit" class="btn btn-dark">등록</button>
					<button type="reset" class="btn btn-light"
						data-mdb-ripple-color="dark" style="width: 90px" onclick="history.back()">취소</button>
				</div>
			</div>
		</div>
	</div>
</form>
</body>
</html>