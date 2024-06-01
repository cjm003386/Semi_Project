$(document).ready(function(){
	$("#cancel").click(function(){
		location.href="reviewlist.pg";
		})
		
	$("form[name=reviewform]").submit(function(){
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
	})
	
	$("#upfile").change(function(){
		console.log($(this).val())
		var inputfile = $(this).val().split('\\');
		$("#filevalue").text(inputfile[inputfile.length-1]);
	});
	
})
