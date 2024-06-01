<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Properties"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>

<html>
<head>
<jsp:include page="header.jsp" />
<script src = "http://code.jquery.com/jquery-latest.js"></script>

<style>
	.card {width: 700px; margin:0 auto !important; border-top:none !important;}
	li span {width:300px; float:right; display: inline-block; text-align:left;}
	.card ul {width: 550px; margin: 0 auto;}
	.card ul li:nth-child(2) input {float:right}
	.card-title {font-weight:bold}
	.card div:last-child button{width: 100%; height: 58px}
</style>
</head>
<body>
	<div class="card">
		<div class="card-body">
			<h2 class="card-title text-center m-5" >본인확인 인증</h2>
		</div>
		<form id="self">
		<ul class="list-group list-group-flush">	
			<c:if test = "${sessionScope.certi_type == 'e'}">					
				<li class="list-group-item">본인확인 인증
					<span>
						<label><input type="radio" name="certi" value="e" checked>이메일</label>&nbsp
					</span>
				</li>				
				<li class="list-group-item">이메일 
					<span>
						${sessionScope.email}
						<input type="button" size="20" value="인증번호 받기" 
							   onClick="location.href='${pageContext.request.contextPath}/sendEmail.net?email=${sessionScope.email}'">
					</span>
				</li>				
				<li class="list-group-item" >인증번호 
					<span>
						<input id="code_input" type="text" size="18" >
						<input id="codecheck" type="button" size="14" value="인증번호 확인" >
					</span>
				</li>				
				<li class="list-group-item"><span>1회 발송된 인증번호의 유효 시간은 60분이며, 1회 인증번호 발송 후 30초 이후에 재전송이 가능합니다.</span></li>
			</c:if>
			<c:if test = "${sessionScope.certi_type == 'p'}">		
				<li class="list-group-item">본인확인 인증
					<span>
						<label><input type="radio" name="certi" value="e" checked>휴대폰</label>&nbsp
					</span>
				</li>				
				<li class="list-group-item">핸드폰 번호 
					<span>
						${sessionScope.phone}
						<input type="button" value="인증번호 받기" id="getcode"
								onClick="location.href='${pageContext.request.contextPath}/sendPhone.net?phone=${sessionScope.phone}'">
								
					</span>
				</li>						
				<li class="list-group-item">인증번호 
					<span>
						<input id="code_input" type="text" size=18>
						<input id="codecheck" type="button" size="14" value="인증번호 확인" >					
					</span>
				</li>			
				<li class="list-group-item"><span>1회 발송된 인증번호의 유효 시간은 60분이며, 1회 인증번호 발송 후 30초 이후에 재전송이 가능합니다.</span></li>					
			</c:if>		
		</ul>	
		</form>			
		<div class="row g-2 m-5">
			<div class="col-6">
				<button id="reset" type="submit" class="btn btn-Secondary" form = "self">확인</button>
			</div>
			<div class="col-6">
				<button class="btn btn-Dark" type="button" form="self" onclick="location.href='${pageContext.request.contextPath}/index.jsp'">취소</button>
			</div>
		</div>
	</div>
		<jsp:include page="footer.jsp" />
</body>
<script>
var certi_type = '${sessionScope.certi_type}';
if (certi_type == "e") {
	var certicheck = false;
	$("#codecheck").on('click', function() {
		let count = 0;
		count++;
		console.log(count);
		if (count>1){
		$("#codecheck").disabled = true;
		
		$.ajax({
			url : "${pageContext.request.contextPath}/emailcertiprocess.net",
			data : {"code_input" : $('#code_input').val()},
			success : function(resp) {
				if(resp == 1) {			
					alert('인증확인을 성공했습니다'); 
					certicheck = true;
				} else {
					alert('인증코드가 틀렸습니다'); 
					certicheck = false;
				}
			},
			error : function(errMsg) {
		           alert("오류가 났습니다. 다시한번 시도해주세요");
		           certicheck = false;
		        }
			})
		};
	})
}

if (certi_type == "p") {
	var certicheck = false;
	$("#codecheck").on('click', function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/phonecertiprocess.net",
			data : {"code_input" : $('#code_input').val()},
			success : function(resp) {
				if(resp == 1) {			
					alert('인증확인을 성공했습니다'); 
					certicheck = true;
				} else {
					alert('인증코드가 틀렸습니다'); 
					certicheck = false;
				}
			},
			error : function(errMsg) {
		           alert("오류가 났습니다. 다시한번 시도해주세요");
		           certicheck = false;
		        }
			})
		});
	
	}

$('#self').on('submit',		
		function() {			
			$('#self').attr("action","${pageContext.request.contextPath}/resetpass.net");		
			
			if(!certicheck){
				alert("인증코드를 입력하세요");
				return false;
			}			
		});	



</script>


</html>