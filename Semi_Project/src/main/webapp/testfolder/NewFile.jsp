<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$("#place-order").click(function(){
	for(var i=0; i<document.purchaseinput.elements.length; i++)
	{
		if(document.purchaseinput.elements[i].value == "")
		{
			alert("모든 값을 입력 하셔야 합니다. ");
				document.purchaseinput.elements[i].focus();
				return false;
		}
	}
	let m_email = $("#m_email").val();
	let s_name = $("#s_name").val();
	let s_addr = $("#roadFullAddr").val();
	let s_phone = $("#s_phone").val();
	let s_msg = $("#s_msg").val();
	let s_zipNo = $("#s_zipNo").val();
	//alert(m_email +s_name +s_addr +s_phone +s_msg +s_zipNo);
		
	var IMP = window.IMP; // 생략가능
	IMP.init('imp55458654');	//아임포트 관리자계정
	//결제 시스템을 실행시키는 함수
  	IMP.request_pay({
		pay_method: 'card',
		merchant_uid: 'merchant_' + new Date().getTime(),
		name: '주문명 : 하이북스 테스트',
		amount: 100,	//테스트 완료 후 가격정보 넣기
		buyer_email: m_email,
		buyer_name: s_name,
		buyer_tel: s_phone,
		buyer_addr: s_addr,
		buyer_postcode: s_zipNo
		}, function (rsp) {
			console.log(rsp);
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
				let purchaseVo = {
					m_email: m_email,
					s_name: s_name,
					s_addr: s_addr,
					s_phone: s_phone,
					s_msg: s_msg,
					s_zipNo: s_zipNo,
					o_shipno: rsp.merchant_uid,
					o_paidAmount: rsp.paid_amount,
					o_paytype: rsp.pay_method
					}
				// 컨트롤러에 데이터를 전달하여 DB에 입력하는 로직
                		// 결제내역을 사용자에게 보여주기 위해 필요함.
               			$.ajax({
					url : "placeorder.do",
					type : "get",
					data : purchaseVo,
					dataType : "text",
					success : function(result){
						if(result == "y") {
							alert(msg);
							location.href = "orderComplete.do"; 
						}else{
							alert("디비입력실패");
							return false;
						}
					},
					error : function(a,b,c){}
				});
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
		alert(msg);
		});
});
</script>
</head>
<body>

</body>
</html>