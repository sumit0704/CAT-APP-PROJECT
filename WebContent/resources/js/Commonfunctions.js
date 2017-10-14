function getAssaysForCellLines(){
	$(".all_assays").hide();
	var lCellLine=jQuery("#cellline").val();
	var lAssaystring=lCellLine+"_"+"assay_select";
	var lAssay=jQuery("#"+lAssaystring).val();
	jQuery("#phenotypes").html("");
	$.ajax({
        type: "POST",
        url: "AssayNames",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine,
          },
          	 success: function (responseText) {
          		 
          		/* alert("Test");*/
            
            	var lPhenolist = responseText.getElementsByTagName("assay");
            	for(var i=0;i<lPhenolist.length;i++){
            		$("#CM_assay_select").append(new Option(
            				lPhenolist[i].childNodes[0].firstChild.nodeValue, lPhenolist[i].childNodes[1].firstChild.nodeValue));
            	}
            	$(".all_assays").show();

        }
          
    });
	
}