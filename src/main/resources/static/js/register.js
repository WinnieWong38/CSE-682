	$(document).ready(function() {
	document.getElementById("error").style.display = "none"; 
	});
	
	$(document).ready(function() {
		
		
		//register the user
		$('#submit').off('click').on('click', function(){
	//	alert ('reg');
		
		var saveObj = {};
		//saveObj.userid =  parseInt("0");
		saveObj.userName = $('#username').val();		
		saveObj.password = $('#password').val();
		saveObj.role = "admin";	
		//saveObj.enabled = 1;
		console.log(saveObj);
		$.ajax({
			url: "/api/User/addUser",
			method: "POST",
			contentType: "application/json",
    		dataType: "json",
			data: JSON.stringify(saveObj),
			success: function() {
            	 //now login the new user            	 
            	 $.ajax({
            	 	url: "/Login",
					method: "POST",
					contentType: "application/json",
    				dataType: "json",
					data: JSON.stringify(saveObj)
					}).done(function() {
						window.location.href = "index.html";
					});
					
            },
            error: function() {    
            //show the error message         
             document.getElementById("error").style.display = "block"; 
            }
          });
	});
	
	});	
	