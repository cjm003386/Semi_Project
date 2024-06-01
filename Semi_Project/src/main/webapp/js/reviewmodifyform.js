$(document).ready(function(){
	var check=0;
	function show(){
		//파일 이름이 있는 경우 remove 이미지를 보이게하고
		//파일 이름이 없는 경우 remove 이미지 보이지 않게 함
		if($("#filevalue").text()==''){
			$(".remove").css('display','none');
		}else{
			$(".remove").css({'display':'inline-block',
								'position':'relative','top':'-5px'})
		}
	}
	show();
	
	$(".remove").click(function(){
		$("#filevalue").text('');
		$(this).css('display','none');
	})
	
	
	$("#upfile").change(function(){
		check++;//변경하면 체크값 증가 , 안하는 경우 check는 0이 된다.
		var inputfile = $(this).val().split('\\');
		$("#filevalue").text(inputfile[inputfile.length-1]);
		show();
		console.log(check);
		console.log($('#filevalue').text());
	});
	
	
	$("form").submit(function(){
		
		if($.trim($("#review_pass").val())==""){
			alert("비밀번호를 입력하세요");
			$("#review_pass").focus();
			return false;
		}

		if($.trim($("#review_subject").val())==""){
			alert("제목을 입력하세요");
			$("#review_subject").focus();
			return false;
		}
		
		if($.trim($("#review_content").val())==""){
			alert("내용을 입력하세요");
			$("#review_content").focus();
			return false;
		}
		
		//파일 첨부를 변경하지 않으면 $('#filevalue').text()의 파일명을
		//파라미터 'check'라는 이름으로 form에 추가하여 전송한다.
		if(check==0){
			value=$('#filevalue').text();
			html="<input type='hidden' value='"+value+"' name='check'>";
			$(this).append(html);
			console.log(html);
		}
		
	})
	
})
