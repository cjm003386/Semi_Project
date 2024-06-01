$("#postcode").click(function(){
		Postcode();
	});
	
	function Postcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	console.log(data.zonecode)
	           
	            var fullRoadAddr = data.roadAddress; 
	            var extraRoadAddr = ''; 
	
	            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                extraRoadAddr += data.bname;
	            }

	            if(data.buildingName !== '' && data.apartment === 'Y'){
	               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	            }

	            if(extraRoadAddr !== ''){
	                extraRoadAddr = ' (' + extraRoadAddr + ')';
	            }

	            if(fullRoadAddr !== ''){
	                fullRoadAddr += extraRoadAddr;
	            }
	
	            
	            $("#post").val(data.zonecode);
	            $("#address").val(fullRoadAddr);
	        }
	    }).open();
	}
	
	$(function (){
		 
		$('input[type="radio"][id="huey"]').on('click', function(){
		  var chkValue = $('input[type=radio][id="huey"]:checked').val();
		  if(chkValue){
		             $('#etc_view').css('display','none');
		  }else{
		             $('#etc_view').css('display','block');
		  }
		 
		});
		 
		});
