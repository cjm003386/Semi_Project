<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<jsp:include page="../mainpage/header.jsp"/>
<style>
.fakeimg {
	height: 200px;
	background: #aaa;
}

body>div.container>div>div>div.container.custom>div:nth-child(2) {
	height: 120px
}
</style>
</head>
<body>
	<br>
	<div class="container">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-9 ">
				<div>
					<b>마이페이지</b>
				</div>
				<hr style="height: 2px; opacity: 1; border-width: 0; background-color: black; margin: 0 auto">

				<br>

				<div class="container custom">
					<div class="container-fluid bg-light my-custom-container border">
						<small>주문처리현황</small>
					</div>

					<div class="container-fluid border p-3 my-custom-container">
						<small class="p-3 text-center">배송 완료</small> <small
							class="p-4 text-center">배송 준비중</small> <small
							class="p-4 text-center">배송 중</small> <br>
						<br><small class="p-5 text-center">${delivery2}</small> <a href="orderlist.pg" style="text-decoration:none; color:black"><small
							class="p-5 text-center">${delivery}</small></a> <small class="p-5 text-center">0</small>
					</div>
					<br>
				</div>
				<br>

				<div class="row d-flex justify-content-center">
					<div class="col-lg-2 col-md-6" style="width: 20%">
						<div class="h-100 p-4 bg-light border rounded-3">
							<a href="orderlist.pg"><img src="${pageContext.request.contextPath}/image/mypage/search.png" alt="Chicago"
								class="mx-auto rounded d-block" style="width: 65px"></a> <br>
							<h6 class="text-center">주문내역 조회</h6>
						</div>
					</div>

					<div class="col-lg-2 col-md-6" style="width: 20%">
						<div class="h-100 p-4 bg-light border rounded-3">
							<a href="updateprofile.pg"><img src="${pageContext.request.contextPath}/image/mypage/profile.png" alt="Chicago"
								class="mx-auto rounded d-block" style="width: 90px"></a>
							<h6 class="text-center">회원정보 수정</h6>
						</div>
					</div>

					<div class="col-lg-2 col-md-6" style="width: 20%">
						<div class="h-100 p-4 bg-light border rounded-3">
							<a href="wishlist.do?id=${id }"><img src="${pageContext.request.contextPath}/image/mypage/star.png" alt="Chicago" class="mx-auto rounded d-block"
								style="width: 65px"></a> <br>
							<h6 class="text-center">관심상품</h6>
						</div>
					</div>

					<div class="col-lg-2 col-md-6" style="width: 20%">
						<div class="h-100 p-4 bg-light border rounded-3">
							<a href="#myModal" data-bs-toggle="modal"><img src="${pageContext.request.contextPath}/image/mypage/files.png" alt="Chicago"
								class="mx-auto rounded d-block" style="width: 65px"></a> <br>
							<h6 class="text-center">게시물 관리</h6>
						</div>
					</div>

					<div class="col-lg-2 col-md-6" style="width: 20%">
						<div class="h-100 p-4 bg-light border rounded-3">
							<a href="addresslist.pg"><img src="${pageContext.request.contextPath}/image/mypage/delivery-truck.png" alt="Chicago"
								class="mx-auto rounded d-block" style="width: 65px"></a> <br>
							<h6 class="text-center">배송주소록 관리</h6>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    
<!-- Modal HTML -->
<div id="myModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirmation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p>보러갈 게시판을 선택해주세요</p>
              <a href="memberreviewlist.pg"><button type="button" class="btn btn-primary  me-3">내 리뷰 보러가기</button></a>
               <a href="memberboardlist.pg?id=${id}"><button type="button" class="btn btn-primary">내 중고거래 글 보러가기</button></a>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>
<br>
<br>
<br>
<jsp:include page="../mainpage/footer.jsp" />
</body>
</html>