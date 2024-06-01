<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.Properties"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>

<html>
<head>
<jsp:include page="header.jsp" />

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/js/join.js"
	charset="utf-8"></script>
<style>
.card {
	width: 700px;
	margin: 0 auto !important;
	border-top: none !important;
}

.card-subtitle {
	color: darkgray
}

#postcode {
	width: 218px;
	height: 58px
}
</style>

</head>
<body>
	<div class="card">
		<div class="card-body">
			<h1 class="card-title text-center mt-5">회원가입</h1>
			<h5 class="card-subtitle text-center mb-5">SIGN UP</h5>
			<form method="post">
				<div class="form-floating mb-1">
					<input type="text" class="form-control" id="id" name="id" placeholder = "아이디">
					<label for="id">아이디</label>
				</div>

				<div class="form-floating mb-1">
					<input type="password" class="form-control" id="pass" name="pass" placeholder = "비밀번호">
					<label for="password">비밀번호</label>
				</div>

				<div class="form-floating mb-1">
					<input type="password" class="form-control" id="pass_check" placeholder = "비밀번호 확인"
						name="pass_check"> <label for="pass_check">비밀번호 확인</label>
				</div>

				<div class="form-floating mb-1">
					<input type="text" class="form-control" id="name" name="name" placeholder = "이름"> 
					<label for="name">이름</label>
				</div>

				<div class="row g-2 mb-1">
					<div class="col-md">
						<div class="form-floating">
							<input type="text" class="form-control" id="jumin1" name="jumin1" placeholder = "주민등록번호 앞자리">
							<label for="jumin1">주민등록번호 앞자리</label>
						</div>
					</div>
					<div class="col-md">
						<div class="form-floating">
							<input type="text" class="form-control" id="jumin2" name="jumin2" placeholder = "주민등록번호 뒷자리">
							<label for="jumin2">주민등록번호 뒷자리</label>
						</div>
					</div>
				</div>

				<div class="form-floating mb-1">
					<select class="form-select" id="gender" name="gender" placeholder = "성별"
						onFocus="this.initialSelect = this.selectedIndex;"
						onChange="this.selectedIndex = this.initialSelect; submit();">
						<option value="m">남자</option>
						<option value="f">여자</option>
					</select> <label for="gender">성별</label>
				</div>

				<div class="row g-2 mb-1">
					<div class="col-8">
						<div class="form-floating">
							<input type="text" class="form-control" id="post" name="post" placeholder = "우편번호">
							<label for="post">우편번호</label>
						</div>
					</div>
					<div class="col-4">
						<button type="button" class="btn btn-dark" id="postcode" placeholder = "우편번호 검색"
							name="postcode">우편번호 검색</button>
					</div>
				</div>

				<div class="form-floating mb-1">
					<input type="text" class="form-control" id="address" name="address" placeholder = "</">
					<label for="address">주소</label>
				</div>
				<div class="form-floating mb-1">
					<input type="text" class="form-control" id="address_detail" placeholder = "상세주소"
						name="address_detail"> <label for="adress_detail">상세주소</label>
				</div>

				<div class="row g-2 mb-1">
					<div class="col-3">
						<div class="form-floating">
							<input type="text" class="form-control" value="010" readonly
								style="text-align: center; padding: 0 0 0 5px; font-size: 14pt">
						</div>
					</div>
					<div class="col-9">
						<div class="form-floating">
							<input type="text" class="form-control" id="phone" name="phone" placeholder = "휴대폰 번호">
							<label for="phone">휴대폰 번호</label>
						</div>
					</div>
				</div>

				<div class="row g-2 mb-1">
					<div class="col-4">
						<div class="form-floating">
							<input type="text" class="form-control" id="email" name="email" placeholder = "이메일 주소">
							<label for="email">이메일 주소</label>
						</div>
					</div>
					<span style="width: 15px; padding: 25px 0 0 0">@</span>
					<div class="col-4">
						<div class="form-floating">
							<input type="text" class="form-control" id="domain" name="domain" placeholder = "도메인">
							<label for="domain">도메인 주소</label>
						</div>
					</div>
					<div class="col-4 mb-1" style="width: 31%">
						<div class="form-floating">
							<select class="form-select" id="sel_domain" name="sel_domain">
								<option value=""></option>
								<option value="naver.com">naver.com</option>
								<option value="gmail.com">gmail.com</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="nate.com">nate.com</option>
								<option value="yahoo.com">yahoo.com</option>
							</select> <label for="email">직접입력</label>
						</div>
					</div>
				</div>

				<div class="mb-2">
					<!-- HTML5,IE9 이후부터는 이것도 가능 formaction="/manage/update"> -->
					<button type="submit" class="btn btn-Secondary"  id="sendEmail"
							onclick="javascript: form.action='sendEmail.net'" style="width: 100%; height: 50px">이메일로 인증코드 전송</button>
				</div>

				<div class="row g-2 mb-3">
					<div class="col-8">
						<div class="form-floating">
							<input type="text" class="form-control" id="certification" name="certification" placeholder = "인증코드 입력"> 
							<label for="certification">인증코드 입력</label>
						</div>
					</div>
					<div class="col-4">
						<div class="form-floating">
							<button class="btn btn-dark" style="width: 218px; height: 58px" 
									type="button" id="emailcertiprocess">인증코드 확인</button>
						</div>
					</div>
				</div>


				<hr class="my-4">

				<div class="row g-2 m-3">
					<div class="col-6">
						<button type="submit" class="btn btn-success signupbtn" 
								id="signup" style="width: 100%; height: 58px" >회원가입</button>
					</div>
					<div class="col-6">
						<button class="btn btn-danger cancelbtn" type="reset" 
								style="width: 100%; height: 58px" >다시 작성</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>

