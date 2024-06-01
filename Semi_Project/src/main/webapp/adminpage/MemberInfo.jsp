<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../mainpage/header.jsp"/>
<html>
<head>
<script src="js/jquery-3.6.0.js"></script>
<script src ='${pageContext.request.contextPath}/js/memberupdateporm.js'></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<style>
.md{float:left;}
#phone{width:150px;}
#address{width:400px;}
</style>

<title>회원 정보 관리</title>


</head>
<body>
<form method='post' id="admin_memberinfoform"
			  action='memberupdateinfo.com'>
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<b>회원정보 관리</b>
				<hr
					style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">
				<br>
				<table class="table table-bordered ">
					<tr>
						<td class="table-active text-center" style="width: 20%">아이디</td>
						<td><input type="text" name='id' value= "${customerinfo.id}" readOnly></td>
					</tr>
					
					<tr>
						<td class="table-active text-center" style="width: 20%">이름</td>
						<td><input type="text" name='name' id="name" value="${customerinfo.name}" ></td>
					</tr>
					<tr>
						<td class="table-active text-center">휴대전화</td>
						<td><input type="text" name="phone" class="me1" id="phone" value="${customerinfo.phone}">
						</td>
					</tr>
					<tr>
						<td class="table-active text-center">주소</td>
						<td><input type="text" name="post" id="post" value="${customerinfo.post }" class="my-1">
						<button type="button" class="btn-sm btn-dark" id="postcode">우편번호</button><br>
							<input type="text" name="address" id="address" value="${customerinfo.address }"></td>
					</tr>
					<tr>
						<td class="table-active text-center">이메일</td>
						<td><input type="text" name="email" id="email" value="${customerinfo.email.split('@')[0]}" >
						@ <input type="text" name="email2" class="me-1" value="${customerinfo.email.split('@')[1]}" ></td>
					</tr>
					
					<tr>
					<td class="table-active text-center">성별</td>
					<td>
				   	<input type="radio" name="gender" value="m" id="gender1" > 남자
				   	<input type="radio" name="gender" value="f" id="gender2" > 여자
					</td>
					
					</tr>
					<tr>
						<td class="table-active text-center" >등급</td>
						<td>
						<select id="viewcount" name="grade">
						<option value="X" selected>등급</option>
						<option value="S">S</option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select>
					</td>
					<tr>
						<td class="table-active text-center">상품 등록상태</td>
					</tr>
				</table>
				<br>
				<div style="text-align: center">
					<a href="memberdelete.com?id=${customerinfo.id}"><button type="button" class="btn btn-danger md">계정제거</button></a>
					<button type="submit" class="btn btn-dark">수정하기</button>
					<button type="reset" class="btn btn-light" onclick="history.back()"
						data-mdb-ripple-color="dark" style="width: 90px">취소</button>
				</div>
			</div>
		</div>
	</div>
</form>
<script>
$("#viewcount").val('${customerinfo.grade}');

//성별 체크
 var gender = '${customerinfo.gender}';
$("input[value='" + gender + "']").prop("checked", true); 

//체크되지 않는 성별을 비 활성 시켜 임의로 선택할 수 없도록 합니다.
$("input:radio").not(":checked").prop("disabled", true);


//계정 제거 클릭 시 알림창
$(".md").click(function(event){
		var answer = confirm("회원 계정이 삭제됩니다. 정말 삭제하시겠습니까?");
		console.log(answer); 
		if (!answer) {//취소를 클릭한 경우
			event.preventDefault(); 	
		}
	})//삭제 클릭 end


</script> 
</body>
</html>