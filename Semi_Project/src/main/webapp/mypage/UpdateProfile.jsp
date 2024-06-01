<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
<jsp:include page="../mainpage/header.jsp"/>
<script src="js/jquery-3.6.0.js"></script>
<script src ='${pageContext.request.contextPath}/js/updateprofile.js'></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<title>회원정보 수정</title>
 <style>
 .table>:not(caption)>*>* {
    padding: 0;}
  </style>
</head>
<body>
				<br>
<form method='post' id="myform"
			  action='updateprofileProcess.pg'>
			  <input type="hidden" value=${memberinfo.gender } name="gender">
			  
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<b>회원정보 수정</b>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">아이디</td>
						<td><input type="text" name='id' value="${id }" readOnly class="form-control "></td>
					</tr>
					<tr>
						<td class="table-active text-center">비밀번호</td>
						<td><input type="password" id="pass" name='pass' value="${memberinfo.password }" class="form-control"></td>
					</tr>
					<tr>
						<td class="table-active text-center">비밀번호 확인</td>
						<td><input type="password" class="form-control" id="check_pass"></td>
					</tr>
					<tr>
						<td class="table-active text-center">이름</td>
						<td><input type="text" id='name' name="name" value="${memberinfo.name }" class="form-control" ></td>
					</tr>
					<tr>
						<td class="table-active text-center">우편번호</td>
						<td>
						<div class="col-7">
					        <div class="input-group">
						<input type="text" class="form-control" id="post1" name="post" value="${memberinfo.post }">
					    <button type="button" id="postcode" class="btn btn-dark">우편번호</button>
						 </div>
					    </div>
						</td>
					</tr>
					<tr>
						<td class="table-active text-center">주소</td>
						<td><input type="text" class="form-control" name="address1" id="address1" value="${memberinfo.address }" ></td>
					</tr>
					<tr>
						<td class="table-active text-center">상세주소</td>
						<td><input type="text" class="form-control" name="address2" id="address2" value="${memberinfo.address_detail}" ></td>
					</tr>
					<tr>
						<td class="table-active text-center">전화</td>
						<td>
							<div class="input-group ">
							    <input type="text" name="tel" id="phone" class="form-control p-2" value="${memberinfo.phone }">
							  </div><span id="message"></span>
					</tr>
					<tr>
						<td class="table-active text-center">이메일</td>
						<td><input type="text" id="email" name="email" value="${memberinfo.email }" class="form-control" >
						<span id="email_message"></span></td>
						
					</tr>
				</table>
				<br>
				<div style="text-align: center">
					<button type="submit" class="btn btn-dark">수정하기</button>
					<button type="reset" class="btn btn-light" id="cancel"
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