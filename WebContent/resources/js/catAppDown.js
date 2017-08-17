function showAllFiles(){
	/*if(jQuery("#allFiles").val("No Files Exists!!")){
		jQuery(".danger").hide();
		jQuery(".success").hide();
		jQuery(".warning").show();
	}*/
	jQuery(".danger").show();
	jQuery(".success").show();
	jQuery(".warning").show();
	jQuery(".pager").show();
	jQuery("#button").show();
	jQuery(".active").show();
	if (!$(this).hasClass("active")) {
	    // Remove the class from anything that is active
	    $("li.active").removeClass("active");
	    // And make this active
	    $(this).addClass("active");
	  }
}
function showExcelFiles(){
	jQuery(".danger").hide();
	jQuery(".warning").hide();
	jQuery(".success").show();
	jQuery(".pager").show();
	jQuery(".active").hide();
	if(jQuery(".success").length==0){
		jQuery("#button").hide();
		jQuery(".pager").hide();
	}else{
		jQuery("#button").show();
		jQuery(".pager").show();
	}
	if (!$(this).hasClass("active")) {
	    // Remove the class from anything that is active
	    $("li.active").removeClass("active");
	    // And make this active
	    $(this).addClass("active");
	  }
}
function showImages(){
	jQuery(".danger").hide();
	jQuery(".warning").show();
	jQuery(".success").hide();
	jQuery(".pager").show();
	jQuery(".active").hide();
	if(jQuery(".warning").length==0){
		jQuery("#button").hide();
		jQuery(".pager").hide();
	}else{
		jQuery("#button").show();
		jQuery(".pager").show();
	}
	
	if (!$(this).hasClass("active")) {
	    // Remove the class from anything that is active
	    $("li.active").removeClass("active");
	    // And make this active
	    $(this).addClass("active");
	  }
}
function showFlatFiles(){
	
	jQuery(".danger").show();
	jQuery(".warning").hide();
	jQuery(".success").hide();
	jQuery(".active").hide();
	if(jQuery(".danger").length==0){
		jQuery("#button").hide();
		jQuery(".pager").hide();
	}else{
		jQuery(".pager").show();
		jQuery("#button").show();
	}
	if (!$(this).hasClass("active")) {
	    // Remove the class from anything that is active
	    $("li.active").removeClass("active");
	    // And make this active
	    $(this).addClass("active");
	  }
}

function uncheckAll(){
	   $("input[type='checkbox']:checked").prop("checked",false)
	}

function hideJumbo(){
	
	document.getElementById('searchFilter').style.display='none';
}

