$(function() {
	var checkid = false;
	var checkpass = false;
	var checkaddress = false;
	var checkphone = false;
	var emailcerticheck = false;


	$("#id").on('keyup',
		function() {
			$('#id').empty();
			var pattern = /^\w{5,12}$/;
			var id = $("#id").val();
			if (!pattern.test(id)) {
				$('#id').addClass('is-invalid');
				$('#id+label').css('color', 'red').text("아이디는 영문자, 숫자 5~12자 가능합니다.");
				checkid = false;
			} else {
				$.ajax({
					url: "idcheck.net",
					data: { "id": id },
					success: function(resp) {
						if (resp == -1) {  // db에 해당 id가 없는 경우;
							$("#id").removeClass('is-invalid')
							$('#id+label').css('color', 'green').text("사용 가능한 아이디입니다");
							checkid = true;
						} else {  // db에 해당 id가 있는 경우(0)
							$("#id").removeClass('is-invalid');
							$('#id+label').css('color', 'blue').text("사용중인 아이디입니다");
							checkid = false;
						}
					}
				})
			}
		});

	$("#pass_check, #pass").on('keyup',
		function() {
			$('#pass').empty();
			var pass = $("#pass").val();
			var pass_check = $("#pass_check").val();

			if (pass == pass_check && pass != "") {
				$('#pass_check').removeClass('is-invalid');
				$('#pass_check+label').css('color', 'green').text("비밀번호가 같습니다");
				checkpass = true;
			} else if (pass == "") {
				$('#pass_check').removeClass('is-invalid');
				checkpass = false;
			} else {
				$('#pass_check').addClass('is-invalid');
				$('#pass_check+label').css('color', 'red').text("비밀번호가 다릅니다");
				checkpass = false;
			}
		});

	$("#jumin1").on('keyup',
		function() {
			var jumin1 = $.trim($("#jumin1").val());

			if (jumin1.length == 6) {
				pattern = /^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9][3][01])$/;
				if (pattern.test(jumin1)) {
					$('#jumin1+label').css('color', 'green').text("앞자리 형식이 맞습니다");
					$("#jumin2").focus();
				} else {
					$('#jumin1+label').css('color', 'red').text("숫자 또는 형식에 맞게 입력하세요");
					$("#jumin1").val("").focus();
				}
			}
		});

	$("#jumin2").on('keyup',
		function() {
			var jumin2 = $.trim($("#jumin2").val());

			if (jumin2.length == 7) {
				pattern = /^[1234][0-9]{6}$/;
				if (pattern.test(jumin2)) {
					$('#jumin2+label').css('color', 'green').text("뒷자리 형식이 맞습니다");

					var c = Number($("#jumin2").val().substring(0, 1));
					var index = (c - 1) % 2;
					$("#gender>option").eq(index).prop("selected", true);
					$("#gender>option").not(":selected").attr("disabled", true);
					$('#gender+label').css('color', 'green');
					$("#post").focus();
				} else {
					$('#jumin2+label').css('color', 'red').text("숫자 또는 형식에 맞게 입력하세요");
					$("#jumin2").val("").focus();
				}
			}
		});

	$("#post").on('keyup',
		function() {
			var post = $.trim($("#post").val());

			if (!$.isNumeric(post)) {
				$('#post+label').css('color', 'red').text("우편번호는 숫자만 가능합니다");
				$("#post").val("").focus();
			} else {
				$('#post+label').css('color', 'green').text("우편번호 형식이 맞습니다");
			}

			if (post.length != 5) {
				$('#post+label').css('color', 'red').text("우편번호는 5자리만 가능합니다");
			} else {
				$('#post+label').css('color', 'green').text("우편번호 형식이 맞습니다");
			}
		});

	$("#address").on('keyup',
		function() {
			var address = $.trim($("#address").val());

			if (address.length == "") {
				$('#address+label').css('color', 'red').text("주소를 입력하세요.");
			} else {
				$('#address+label').css('color', 'green').text("주소 형식이 맞습니다");
			}
		});

	$("#postcode").click(function() {
		Postcode();
	});

	function Postcode() {
		new daum.Postcode({
			oncomplete: function(data) {
				console.log(data.zonecode)

				var fullRoadAddr = data.roadAddress;
				var extraRoadAddr = '';

				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}

				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}

				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}

				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}


				$("#post").val(data.zonecode);
				$("#address").val(fullRoadAddr);
			}
		}).open();
	}

	$("#address_detail").on('keyup',
		function() {
			var address_detail = $.trim($("#address_detail").val());

			if (address_detail.length == 0) {
				$('#address_detail').addClass('is-invalid');
				$('#address_detail+label').css('color', 'red').text("상세 주소를 입력하세요");
				checkaddress = false;
			} else {
				$("#address_detail").removeClass('is-invalid');
				$('#address_detail+label').css('color', 'green').text("동호수까지 적어주세요");
				checkaddress = true;
			}
		});

	$("#phone").on('keyup',
		function() {
			var phone = $.trim($("#phone").val());

			if (!$.isNumeric(phone)) {
				$('#phone+label').css('color', 'red').text("핸드폰 번호는 숫자만 가능합니다");
				$("#phone").val("").focus();
				checkphone = false;
			} else {
				checkphone = true;				
			}

			if (phone.length == 8 || phone.length == 7) {
				$('#phone+label').css('color', 'green').text("핸드폰 번호 형식이 맞습니다");
				checkphone = true;
			} else {
				$('#phone+label').css('color', 'red').text("핸드폰 번호는 7~8자리입니다.");
				checkphone = false;
			}
		});


	$("#sel_domain").on('change',
		function() {
			if ($("#sel_domain").val() == "") {
				$("#domain").val("").focus();
			} else {
				$("#domain").val($("#sel_domain").val());
			}
		});

	$("#domain").on('keyup',
		function() {
			var domain = $.trim($("#domain").val());
			var pattern = /^\w+[.]\w{3}$/;

			if (!pattern.test(domain)) {
				$('#domain').addClass('is-invalid');
				$('#domain+label').css('color', 'red').text("형식에 맞게 적어주세요");
			} else {
				$("#domain").removeClass('is-invalid');
				$('#domain+label').css('color', 'green').text("이메일 형식에 맞습니다.");
			}
		});



	$("#emailcertiprocess").on('click',
		function() {

			$.ajax({
				url: "emailcertiprocess.net",
				data: { "code_input": $("#certification").val() },
				success: function(resp) {
					if (resp == 1) {
						alert('인증확인을 성공했습니다');
						emailcerticheck = true;
					} else {
						alert('인증코드가 틀렸습니다');
						emailcerticheck = false;
					}
				}
			})
		});

	$('#signup').on('click',
		function() {
			$('form').attr("action", "agreeProcess.net");

			var id = $.trim($('#id').val());
			if (id == '') {
				alert("id를 입력하세요");
				$('#id').focus();
				return false;
			}

			var pass = $.trim($('#pass').val());
			if (pass == '') {
				alert("비밀번호를 입력하세요");
				$('#pass').focus();
				return false;
			}

			var name = $.trim($('#name').val());
			if (name == '') {
				alert("이름을 입력하세요");
				$('#name').focus();
				return false;
			}

			if (!checkid) {
				alert("사용가능한 id로 입력하세요");
				$("#id").val('').focus();
				return false;
			}

			if (!checkpass) {
				alert("비밀번호를 다시 확인하세요");
				$("#pass").val('').focus();
				return false;
			}

			var jumin1 = $.trim($('#jumin1').val());
			if (jumin1 == '') {
				alert("주민번호 앞자리를 입력하세요");
				$('#jumin1').focus();
				return false;
			}

			if (jumin1.length != 6) {
				alert("주민번호 앞자리 6자리를 입력하세요");
				$('#jumin1').val('').focus();
				return false;
			}

			var jumin2 = $.trim($('#jumin2').val());
			if (jumin2 == '') {
				alert("주민번호 뒷자리를 입력하세요");
				$('#jumin2').focus();
				return false;
			}

			if (jumin2.length != 7) {
				alert("주민번호 뒷자리 7자리를 입력하세요");
				$('#jumin2').val('').focus();
				return false;
			}

			var post = $.trim($('#post').val());
			if (post == '') {
				alert("우편번호를 입력하세요");
				$('#post').focus();
				return false;
			}

			if (!$.isNumeric(post)) {
				alert("우편번호는 숫자만 입력 가능합니다");
				$("#post").val("").focus();
				return false;
			}

			if (post.length != 5) {
				alert("우편번호는 다섯자리입니다.");
				$('#post').focus();
				return false;
			}

			var address = $.trim($('#address').val());
			if (address == '') {
				alert("주소를 입력하세요");
				$('#address').focus();
				return false;
			}

			if (!checkaddress) {
				alert("상세주소를 입력하세요");
				$("#address_detail").val('').focus();
				return false;
			}

			if (!checkphone) {
				alert("핸드폰 번호를 확인하세요");
				$("#phone").val('').focus();
				return false;
			}

			var email = $.trim($('#email').val());
			if (email == '') {
				alert("이메일 주소를 입력하세요");
				$('#email').focus();
				return false;
			}

			var domain = $.trim($('#domain').val());
			if (domain == '' && $("#sel_domain").val() == "") {
				alert("도메일 주소를 입력하세요");
				$('#email').focus();
				return false;
			}


			if (!emailcerticheck) {
				alert("이메일 인증코드를 확인하세요");
				$("#emailcertiprocess").val('').focus();
				return false;
			}
		});
})
