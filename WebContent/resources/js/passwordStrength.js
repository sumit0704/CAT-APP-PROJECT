
$(document).ready(function()
{
	
	$('#password').keyup(function()
	{
		$('#result').html(checkStrength($('#password').val()))
	})	
	
	
	
	function checkStrength(password)
	{
		
		var strength = 0
		
		//if the password length is less than 6, return message.
		if (password.length < 6) { 
			$('#result').removeClass()
			
			$('#result').addClass('short')
			return 'Too short' 
		}
		
		//length is ok, lets continue.
		
		//if length is 8 characters or more, increase strength value
		if (password.length > 7) strength += 1
		
		//if password contains both lower and uppercase characters, increase strength value
		if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))  strength += 1
		
		
		
		//if it has numbers and characters, increase strength value
		if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/))  strength += 1 
		
		//if it has one special character, increase strength value
		if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/))  strength += 1
		
		//if it has two special characters, increase strength value
		if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
		
		//now we have calculated strength value, we can return messages
		
		//if value is less than 2
		if (strength < 2 )
		{
			/*$('#result').removeClass()
			$('#result').addClass('weak').doTimeout( 5000, 'remove' );
			return 'Weak'*/			
		}
		else if (strength == 2 )
		{
			/*$('#result').removeClass()
			$('#result').addClass('good').doTimeout( 5000, 'remove' );
			return 'Good'*/		
		}
		else
		{
			$('#result').removeClass()
			$('#result').addClass('strong')
			return 'Strong'
		}
	}
});

function checkPass()
{
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('repassword');
    //alert("pass1 value::"+pass1.value+"pass2 value::"+pass2.value);
    //Store the Confimation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using ...
    var goodColor = "#66cc66";
    var badColor = "#ff6666";
    //Compare the values in the password field 
    //and the confirmation field
    if(null==(pass1.value) || (undefined==pass1.value)){
    	pass1.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Enter password!"
    }
    else if(pass1.value ==pass2.value){
        //The passwords match. 
        //Set the color to the good color and inform
        //the user that they have entered the correct password
        alert("Passwords Match!");

        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords Match!"
    }
    else{
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        alert("Passwords Match not");

        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords Do Not Match!"
    }
}  