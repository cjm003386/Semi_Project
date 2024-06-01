$(document).ready(function(){
	
	$("#addblock").click(function(){
	alert("주소는 5개까지 등록 가능합니다.")
})
$("tr > td.text-center > a:nth-child(2) > button").click(function(event){
		var answer = confirm("정말 삭제하시겠습니까?");
		console.log(answer);
		if(!answer){
			event.preventDefault();
		}
	})
		
	})
