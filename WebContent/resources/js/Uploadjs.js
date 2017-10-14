function step4_to_5(){		// step 4 to step 5
	
 if (document.getElementById('processed').checked) 
	{
		
		$("#description").hide();
		$("#ta").hide();
		
	}	
 else{
		$("#description").show();
		$("#ta").show();
 }
 $("#step6").show();

}

function selectphenotypes(){
	
	var lCellLine=jQuery("#cellline").val();
	var lAssaystring=lCellLine+"_"+"assay_select";
	var lAssay=jQuery("#"+lAssaystring).val();
	jQuery("#phenotypes").html("");
	$.ajax({
        type: "GET",
        url: "Phenotypes",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine,
            'lAssay': lAssay
          },
          	 success: function (responseText) {
          		/* alert("Test");*/
        	var lPhenolist = responseText.getElementsByTagName("pheno");
        	for(var i=0;i<lPhenolist.length;i++){
        		$("#phenotypes").append(new Option(
        				lPhenolist[i].childNodes[0].firstChild.nodeValue, lPhenolist[i].childNodes[1].firstChild.nodeValue));
        	}

        }
          
    });
}
function closemessageheader(){
	jQuery(".alert").hide();
}

setTimeout(function() {
    $('#messagebox').fadeOut('slow');
}, 1000);


function getAssaysForCellLines(){
	
	
	$("#CM_assays").hide();
	$("#step2").hide();
	$("#CM_assay_select").html("");
	
	$("#step4").hide();
	$("#timepoints_4").hide();
	$("#timepoints_4_select").html("");
	
	$("#step5").hide();
	
	$("#step6").hide();
	
	$("#step3").hide();
	$("#Ph_div").hide();
	
	
	var lCellLine=jQuery("#cellline").val();
		$.ajax({
        type: "POST",
        url: "AssayNames",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine,
          },
          	 success: function (responseText) {
          		
            	var lPhenolist = responseText.getElementsByTagName("assay");
            	for(var i=0;i<lPhenolist.length;i++){
            		$("#CM_assay_select").append(new Option(
            				lPhenolist[i].childNodes[0].firstChild.nodeValue, lPhenolist[i].childNodes[1].firstChild.nodeValue));
            	}
            	$("#CM_assays").show();
            	$("#step2").show();

        }
          
    });
	
}
function getNextAttribute(){
	
	var lCellLine=jQuery("#cellline").val();
	var lAssay=jQuery("#CM_assay_select").val();
	if (document.getElementById('raw').checked){
		
		$("#step4").hide();
    	$("#timepoints_4").hide();
    	$("#timepoints_4_select").html("");
    	
		
		$.ajax({
        type: "POST",
        url: "TimePoint",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine,
            'lAssay': lAssay
          },
          	 success: function (responseText) {
          		
            	var lPhenolist = responseText.getElementsByTagName("tp");
            	for(var i=0;i<lPhenolist.length;i++){
            		$("#timepoints_4_select").append(new Option(
            				lPhenolist[i].childNodes[0].firstChild.nodeValue, lPhenolist[i].childNodes[1].firstChild.nodeValue));
            	}
            	$("#step4").show();
            	$("#timepoints_4").show();

        }
          
    });
	}
	
		if (document.getElementById('processed').checked){
			$("#step3").hide();
			$("#Ph_div").hide();
			$("#phenotype").html("");
			$.ajax({
		        type: "GET",
		        url: "Phenotypes",
		        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		        data: { 
		            'lCM': lCellLine,
		            'lAssay': lAssay
		          },
		          	 success: function (responseText) {
		          		
		            	var lPhenolist = responseText.getElementsByTagName("pheno");
		            	for(var i=0;i<lPhenolist.length;i++){
		            		$("#phenotype").append(new Option(
		            				lPhenolist[i].childNodes[0].firstChild.nodeValue, lPhenolist[i].childNodes[1].firstChild.nodeValue));
		            	}
		            	$("#step3").show();
		            	$("#Ph_div").show();
		            	

		        }
		          
		    });
		}

}

function getTimePoints(){
	
	var lCellLine=jQuery("#cellline").val();
	var lAssay=jQuery("#CM_assay_select").val();
	var lPhenoType = jQuery("#phenotype").val();
		
		$("#step4").hide();
    	$("#timepoints_4").hide();
    	$("#timepoints_4_select").html("");
    	
		
		$.ajax({
        type: "POST",
        url: "TimePoint",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine,
            'lAssay': lAssay,
            'lPhenotype': lPhenoType
          },
          	 success: function (responseText) {
          		
            	var lPhenolist = responseText.getElementsByTagName("tp");
            	for(var i=0;i<lPhenolist.length;i++){
            		$("#timepoints_4_select").append(new Option(
            				lPhenolist[i].childNodes[0].firstChild.nodeValue, lPhenolist[i].childNodes[1].firstChild.nodeValue));
            	}
            	$("#step4").show();
            	$("#timepoints_4").show();

        }
          
    });
	
	
	
}