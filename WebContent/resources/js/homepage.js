function showRegistration(){
	document.getElementById('registration').style.display="block";
}


$( document ).ready(function() {
	$('#urformid').on('submit', function() {

        if ($('#rememberMe').is(':checked')) {
            // save username and password
            localStorage.userName = $('#loginEmail').val();
            localStorage.password = $('#password').val();
            localStorage.checkBoxValidation = $('#rememberMe').val();
        } else {
            localStorage.userName = '';
            localStorage.password = '';
            localStorage.checkBoxValidation = '';
        }

        //Other form functions
	});
	
	$('#readMore').click(function(){
	    $('.read').toggleClass('read-less');
	    if($(this).text()=='Show Less') $(this).text('Show More'); 
	    else  $(this).text('Show Less'); 
	});
	
	$('ul.nav li.dropdown').hover(function() {
		  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeIn(500);
		}, function() {
		  $(this).find('.dropdown-menu').stop(true, true).delay(200).fadeOut(500);
		});
	
	$('#exampleModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var recipient = button.data('whatever') // Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  modal.find('.modal-title').text('New message to ' + recipient)
		  modal.find('.modal-body input').val(recipient)
		})
		
		
		/*$('#password').onblur(function() {
			var pswd = $(this).val();
			
			if ( pswd.length < 8 ) {
			    $('#length').removeClass('valid').addClass('invalid');
			} 
			
			if ( !pswd.match(/[A-z]/) ) {
			    $('#letter').removeClass('valid').addClass('invalid');
			} 
			
			if ( !pswd.match(/[A-Z]/) ) {
			    $('#capital').removeClass('valid').addClass('invalid');
			}
			if ( !pswd.match(/\d/) ) {
			    $('#number').removeClass('valid').addClass('invalid');
			}
			else {
			    $('#length').removeClass('invalid').addClass('valid');
			}
			if ( pswd.match(/[A-z]/) ) {
			    $('#letter').removeClass('invalid').addClass('valid');
			} else {
			    $('#letter').removeClass('valid').addClass('invalid');
			}

			//validate capital letter
			if ( pswd.match(/[A-Z]/) ) {
			    $('#capital').removeClass('invalid').addClass('valid');
			} else {
			    $('#capital').removeClass('valid').addClass('invalid');
			}

			//validate number
			if ( pswd.match(/\d/) ) {
			    $('#number').removeClass('invalid').addClass('valid');
			} else {
			    $('#number').removeClass('valid').addClass('invalid');
			}
			
		
		}).focus(function() {
		    $('#pswd_info').show();
		}).blur(function() {
		    $('#pswd_info').hide();
		});
	*/
	/*
		fillByMemory()
			$('button#sign').on('click', function() {

	    if ($('#rememberChkBox').val()) {
	      rememberMe();
	    }


	    doLogin();
	  });

		function rememberMe() {
			  $.cookie('id', $('#email').val());
			  $.cookie('pass', $('#passwordLogin').val());

			}

			function fillByMemory() {
			  if (!!$.cookie('id'))
			    $('#email').val($.cookie('id'));

			  if (!!$.cookie('pass'))
			    $('#passwordLogin').val($.cookie('pass'));
			}*/

		
});
setTimeout(function() {
    $('#messagebox').fadeOut('slow');
}, 1800);