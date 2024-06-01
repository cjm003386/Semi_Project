$(document).ready(function(){
	
	var checkphone=false;
	
	$("#phone").on('keyup',
				function(){
					
					
					$("#message").empty();
					
					var pattern = /^(?:(010\d{4})|(01[1|6|7|8|9]\d{3,4}))(\d{4})$/;
					var phone = $("#phone").val();
					if(!pattern.test(phone)){
						$("#message").css('color', 'red').html("전화번호 형식에 맞지 않습니다.");
						checkphone=false;
					}else{
						$("#message").css('color', 'green').html("전화번호 형식에 맞습니다.");
						checkphone=true;
					}
				})
	
	$('#myform').submit(function(){
			var addressname = $.trim($('#addressname').val());
			if(addressname ==''){
				alert('배송지명을 입력하세요');
				$('#addressname').focus();
				return false;
			}

			var receiver=$.trim($('#receiver').val());
			if(receiver ==''){
				alert('수령인을 입력하세요');
				$('#receiver').focus();
				return false;
			}
			var phone=$.trim($('#phone').val());
			if(phone ==''){
				alert('전화번호를 입력하세요');
				$('#phone').focus();
				return false;
			}
			var post1=$.trim($('#post1').val());
			if(post1 ==''){
				alert('우편번호를 입력하세요');
				$('#post1').focus();
				return false;
			}
			
			if(post1.length!=5){
				alert("우편번호는 다섯 자리입니다.")
				$("#post1").focus();
				return false;
			}

			if(!$.isNumeric($("#post1").val())){
				alert("우편번호는 숫자만 입력 가능합니다.")
				$("#post1").val("").focus();
				return false;
			}
			

			if(!$.isNumeric($("#phone").val())){
				alert("전화번호는 숫자만 입력 가능합니다.")
				$("#phone").val("").focus();
				return false;
			}
			
			
			var address = $.trim($('#address1').val());
			if (address ==''){
				alert('주소를 입력하세요');
				$('#address1').focus();
				return false;
			}

			var address = $.trim($('#address2').val());
			if (address ==''){
				alert('상세주소를 입력하세요');
				$('#address2').focus();
				return false;
			}
			if(!checkphone){
				alert("전화번호 형식을 확인하세요");
				$('#phone').focus();
				return false;
			}
			
			
			
	})
	
	
		$('#postcode').click(function(){
			new daum.Postcode({
            oncomplete: function(data) {
            	console.log(data.zonecode)
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수
 
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
              $("#post1").val(data.zonecode);
              $("#address1").val(fullRoadAddr);
            }
        }).open();
		})
		
		
		
		
		
	})
