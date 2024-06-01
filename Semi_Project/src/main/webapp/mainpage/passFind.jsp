<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<head>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<jsp:include page="header.jsp" />

<style>
	.card { width: 700px; margin:0 auto !important; border-top:none !important;}
	li span {width:200px; float:right; display: inline-block; text-align:center;}
	.card ul {width: 400px; margin: 0 auto;}
	.card button {width: 80%; height: 58px; margin:0 auto}
</style>
</head>
<body>
	<div class="card">
		<div class="card-body">
			<h2 class="card-title text-center m-5" style="font-weight:bold">비밀번호 찾기</h2>
		</div>
		<form id="passfind" action="selfCerti.net" method="post"> 
			<ul class="list-group list-group-flush">
				<li class="list-group-item">인증수단
					<span>
						<label><input type="radio" name="certi" value="e" checked>이메일</label>&nbsp 
						<label><input type="radio" name="certi" value="p">휴대폰 번호</label>
					</span>
				</li>
			</ul>
			<div id="page"></div>
		</form>
		<button form='passfind' class='btn btn-Dark mt-5 mb-5'>확인</button>
	</div>
		<jsp:include page="footer.jsp" />
</body>
<script>
	$("#page").load("${pageContext.request.contextPath}/mainpage/emailcerti.jsp");
	$("#page").attr("class", "list-group list-group-flush");

	$("input").change(function(){
		var value = $("input[type=radio]:checked").val();
		console.log(value);
		if(value == "p") {
			$("#page").load("${pageContext.request.contextPath}/mainpage/phonecerti.jsp");
		} else {
			$("#page").load("${pageContext.request.contextPath}/mainpage/emailcerti.jsp");				
		}
	})
	
	

		
</script>

</html>

