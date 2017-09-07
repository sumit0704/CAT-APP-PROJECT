var fig_width = 500;
function magnify() {
	// alert("hello");
	fig_width = fig_width * 1.2;
	$( "#response-curve" ).width(fig_width);
	// class="img-responsive" 
	}

function shrink() {
	// alert("hello");
	fig_width = fig_width * 0.8;
	$( "#response-curve" ).width(fig_width);
	// class="img-responsive" 
	}

$(function() {
    $( "#draggable" ).draggable(
    		{ zIndex: 0 })//{ containment: "parent" });
    
 });



$("menue_property").click(
	function () {
		// alert("It is: "+ ${chemical});
		// jQuery('#menu1').html('');
		jQuery('#menu1').html("${chemical_properties}");
		
		}
	)
