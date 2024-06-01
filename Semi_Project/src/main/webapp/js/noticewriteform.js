$(document).ready(function(){
	$(".sub").click(function(){
		
		
		if($.trim($("#board_subject").val())==""){
			alert("제목을 입력하세요");
			$("#board_subject").focus();
			return false;
			}
		
		if($.trim($("#board_content").val())==""){
			alert("내용을 입력하세요");
			$("#board_content").focus();
			return false;
			}
	
		
		alert('공지가 추가되었습니다.');
	
	});
	
	$("#upfile").change(function(){
		console.log($(this).val())
		var inputfile = $(this).val().split('\\');
		$("#filevalue").text(inputfile[inputfile.length-1]);
	});
	
});