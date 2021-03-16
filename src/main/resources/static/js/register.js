	$(document).ready(function() {
		
		
		$('#submit').off('click').on('click', function(){
	//	alert ('reg');
		
		var saveObj = {};
		//saveObj.userid =  parseInt("0");
		saveObj.userName = $('#username').val();		
		saveObj.password = $('#password').val();
		saveObj.role = "user";	
		//saveObj.enabled = 1;
		console.log(saveObj);
		$.ajax({
			url: "/api/User/addUser",
			method: "POST",
			contentType: "application/json",
    		dataType: "json",
			data: JSON.stringify(saveObj)
		});	
	});
	
	} );
	
	
	/*


$(document).ready(function() {
		
		
		$('#submit').off('click').on('click', function(){
		alert('REGISTER');
		
	/*	var saveObj = {};
		saveObj.username = $('#username').val();		
		saveObj.password = parseInt($('#password').val());
		console.log(saveObj);
		$.ajax({
			url: "/api/user/addUser",
			method: "POST",
			contentType: "application/json",
    		dataType: "json",
			data: JSON.stringify(saveObj)
		});
		
	});
	
	} );
	
	
	*/