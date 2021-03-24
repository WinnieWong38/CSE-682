	function isBlank(str) {
    	return (!str || /^\s*$/.test(str)); //check for empty and all blank
	}
		
	//function to compare new and retype password boxes and alert if diffrent
	function checkMatch()
	{		
		var newPassword = $('#newpassword').val();
		var retypePassword = $('#retypepassword').val();
		
		//check if either is blank
		if(isBlank(newPassword) || isBlank(retypePassword)) return false;
		
		//check if the passwords match and set message
		if(newPassword != retypePassword)
		{
			document.getElementById("pwdmatch").style.display = "block"; //show message
			return false;
		}
		else
		{
			document.getElementById("pwdmatch").style.display = "none";  //hid message			
			return true;	
		}
	}
	
	//function to enable change button only when all fields are populated and the new passwords match
	function checkReady()
	{
		var username = document.getElementById("username").innerText;			
		var oldpassword = $('#oldpassword').val();
	
		if(isBlank(username) || isBlank(oldpassword) || !checkMatch())
		{
			document.getElementById("subbtn").style.display = "none"; //hide the submit buttons divider
			return false;
		}
		else
		{
			document.getElementById("subbtn").style.display = "inline"; //show the button's divider		
			return true;
		}
	
	}

	$(document).ready(function() {
	document.getElementById("error").style.display = "none"; 
	});
	
	$(document).ready(function() {
		
		//change the user password when button is clicked
		$('#submit').off('click').on('click', function(){			
		
		//get the data from the form and add to the array
		var saveObj = {};
		//saveObj.userid =  parseInt("0");
		saveObj.username = document.getElementById("username").innerText;		
		saveObj.oldPassword = $('#oldpassword').val();
		//saveObj.newPassword = $('#newpassword').val();		
		console.log(saveObj);
		
		//make sure the current password is valid
		$.ajax({			
			url: "/api/User/checkPwd",
			method: "GET",
			//contentType: "application/json",
    		//dataType: "json",
			data: saveObj,
			success: function(data) {
            	 if(data == true)
            	 {
            	 	//alert('matched');
            	 	saveObj.newPassword = $('#newpassword').val(); //add new password
            	 	//call change password api
            	 	$.ajax({
            	 		url: "/api/User/changePwd",
						method: "POST",
						//contentType: "application/json",
    					//dataType: "json",
						//data: JSON.stringify(saveObj),
						data: saveObj,
						success: function() {
            	 			//alert('changed');			
            	 			//need popup saying changed		
            			},
            			error: function() {    
            				//show the error message         
             				document.getElementById("error").style.display = "block"; 
            			}
          			});
            	 }					
            	 else
            	 {
            	 	//alert('bad pwd');
            	 	}
            },
            error: function(data) {    
            //show the error message         
             document.getElementById("error").style.display = "block"; 
            }
          });
          
        
	
	});
	
	});	
	
	  //get the user name
	$(document).ready(function() {
	$.ajax({
			url: "/api/User/getUsername"
		}).done(function(data){			
			var x = document.getElementById("username");
			x.innerHTML = data; 
		});
		});
	

	