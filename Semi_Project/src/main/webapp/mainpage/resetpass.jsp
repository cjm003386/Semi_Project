<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>
<head>
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<jsp:include page="header.jsp" />

<style>
	.card  {width: 700px; margin:0 auto !important; border-top:none !important;}
	.card ul {width: 500px; margin: 0 auto;}	
	li span {width:300px; float:right; display: inline-block; text-align:left;}
	.card ul {width: 500px; margin: 0 auto;}
	.card div:last-child button{width: 100%; height: 58px}	
</style>
</head>
<body>
	<div class="card">
		<div class="card-body">
			<h2 class="card-title text-center m-5" style="font-weight:bold">비밀번호 재설정</h2>
		</div>
		<form id="reset" method="post" action="resetpassSuccess.net">
		<input type="hidden" id= "checkid" name="checkid" value="${sessionScope.checkid}">			
		<ul class="list-group list-group-flush">
	  		<li class="list-group-item">아이디<span>${sessionScope.checkid}</span></li>
			
			<li class="list-group-item">새 비밀번호 
				<span><input id="newpass" name="newpass" type="password" size=36></span>
			</li>		
						
			<li class="list-group-item">새 비밀번호 확인
				<span><input id="newpasscheck" type="password" size=36></span>
			</li>					
		</ul>		
		</form>					
		
		<div class="row g-2 m-5">
			<div class="col-6">
				<button type="submit" form="reset" class="btn btn-Secondary" >비밀번호 변경</button>
			</div>
			<div class="col-6">
				<button class="btn btn-Dark" onclick="location.href='${pageContext.request.contextPath}/index.jsp'">취소</button>
			</div>
		</div>
	</div>
		<jsp:include page="footer.jsp" />
</body>
<script>
	$("#page").load("emailcerti.jsp");	
	$("input").change(function(){
		var value = $("input[type=radio]:checked").val();
		console.log(value);
		if(value == "p") {
			$("#page").load("phonecerti.jsp");
			
		} else {
			$("#page").load("emailcerti.jsp");	
		}
	});
	

	
	$("#reset").on('submit',function(event){
		var passcheck = false;
		var newpass = $("#newpass").val();
		var newpasscheck = $("#newpasscheck").val();
		console.log("newpass=" + newpass);
		console.log("newpasscheck=" + newpasscheck);

		if(newpass == newpasscheck) {
			passcheck = true;
		}
		
		if(!passcheck){
			alert("비밀번호가 다릅니다.");
			return false;
		} else {
			alert("비밀번호가 바뀌었습니다^^");

		}	
	})
	
	
</script>

</html>

