<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<title>배송주소록 관리</title>
<jsp:include page="../mainpage/header.jsp"/>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src ='${pageContext.request.contextPath}/js/addresslist.js'></script>
<style>
footer{position:fixed; 
			bottom:0px; 
			height:3rem;
			background:#ccc;
			width:100%; 
			text-align:center;
			}
</style>
</head>
<body>
	<br>
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>배송 주소록</b>
				</div>
				<hr
					style="height: 2px; opacity: 1; background-color: black; margin: 0 auto">
				<br>


				<!-- 내용 -->
							<br>
							<c:if test="${!empty list }">
							<table class="table">
								<tr class="table-active">
									<td>배송지명</td>
									<td>수령인</td>
									<td>휴대전화</td>
									<td>우편번호</td>
									<td>주소</td>
									<td>상세주소</td>
									<td class="text-center">수정/삭제</td>
								</tr>
								<c:forEach var="a" items="${list }" varStatus="vs">
								<tr class="align-middle">
									<td>${a.address_name }</td>
									<td>${a.address_receiver }</td>
									<td>
									${ fn:substring(a.address_phone, 0,3) }-${ fn:substring(a.address_phone, 3,7) }-${ fn:substring(a.address_phone, 7, 11) }
									</td>
									<td>${a.address_post }</td>
									<td>${a.address1 }</td>
									<td>${a.address2 }</td>
									<td class="text-center"><a href="addresslistmodify.pg?id=${id}&num=${a.addresslist_num}"><button class="btn btn-dark me-1">수정</button></a>
									<a href="addressdelete.pg?id=${id}&num=${a.addresslist_num}"><button class="btn btn-danger" >삭제</button></a></td>
								</tr>
								</c:forEach>
							</table>
								<c:if test="${list.size() <5 }">
								<a href="addresslistadd.pg">
								<button class="btn btn-secondary float-end">등록하기</button>
								</a>
								</c:if>
								<c:if test="${list.size()==5 }">
								<button type="button" class="btn btn-secondary float-end" id="addblock">등록하기</button>
								</c:if>
							</c:if>
							<c:if test="${empty list}">
							<h1>등록된 배송지가 없습니다.</h1>
							<a href="addresslistadd.pg">
							<button class="btn btn-secondary float-end">등록하기</button>
							</a>
							</c:if>
					</div>
			</div>
	</div>
	<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>