$(document).ready(function(){
	
	$("#cancel").click(function(){
		history.back();
		})
			
	var checkphone=false;
	var checkemail=false;
	var pattern1 = /^(?:(010\d{4})|(01[1|6|7|8|9]\d{3,4}))(\d{4})$/
	var phone = $("#phone").val();
	var pattern2 = /^\w+@\w+[.]\w{3}$/;
	var email = $("#email").val();
	
	if(pattern1.test(phone)){
		checkphone=true;
		console.log(checkphone)
	}
	if(pattern2.test(email)){
		checkemail=true;
	}
	$("#phone").on('keyup',
				function(){
					checkphone=false;
					phone = $("#phone").val();
					$("#message").empty();
					
					if(!pattern1.test(phone)){
						$("#message").css('color', 'red').html("전화번호 형식에 맞지 않습니다.");
						checkphone=false;
					}else{
						$("#message").css('color', 'green').html("전화번호 형식에 맞습니다.");
						checkphone=true;
					}
				})
				
	$("#email").on('keyup',
			function(){
				checkemail=false;
				email = $("#email").val()
				$("#email_message").empty();
				//+는 [A-Za-z0-9_]를 1회 이상 반복을 의미하며 {1,}와 같은 의미
				//\w+는 1개 이상 사용하라는 의미
				if(!pattern2.test(email)){
					$("#email_message").css('color','red')
						.html("이메일형식이 맞지 않습니다.");
					checkemail=false;
				}else{
					$("#email_message").css('color', 'green')
					.html("이메일형식에 맞습니다.");
					checkemail=true;
				}
		});//email keyup
	
	$('#myform').submit(function(){
			var pass=$.trim($('#pass').val());
			if(pass ==''){
				alert('비밀번호를 입력하세요');
				$('#pass').focus();
				return false;
			}
			
			var name=$.trim($('#name').val());
			if(name ==''){
				alert('이름을 입력하세요');
				$('#name').focus();
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
			
			if(!$.isNumeric($("#post1").val())){
				alert("우편번호는 숫자만 입력 가능합니다.")
				$("#post1").val("").focus();
				return false;
			}
			
			var address1 = $.trim($('#address1').val());
			if (address1 ==''){
				alert('주소를 입력하세요');
				$('#address1').focus();
				return false;
			}
			var address2 = $.trim($('#address2').val());
			if (address2 ==''){
				alert('상세 주소를 입력하세요');
				$('#address2').focus();
				return false;
			}

			var email = $.trim($('#email').val());
			if (email ==''){
				alert('이메일을 입력하세요');
				$('#email').focus();
				return false;
			}

			if(!$.isNumeric($("#phone").val())){
				alert("전화번호는 숫자만 입력 가능합니다.")
				$("#phone").val("").focus();
				return false;
			}
			

			if(!checkphone){
				alert("전화번호 형식을 확인하세요");
				$('#phone').focus();
				return false;
			}
			if(!checkemail){
				alert("email 형식을 확인하세요");
				$('#email').focus();
				return false;
			}
			if($('#pass').val()!=$('#check_pass').val()){
			alert('비밀번호가 일치하지 않습니다.');
			$('#check_pass').val('');
			$('#check_pass').focus();
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
