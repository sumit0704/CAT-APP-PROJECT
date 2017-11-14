
// Search functions ////////////////////////////////////////////////////////////////////////////////////////////
function Search_name() {
  var input, filter, table, tr, td, i;
  
  input = document.getElementById("name_Input");

  filter = input.value.toUpperCase();
  // alert("hi" + filter);
  table = document.getElementById("table_1");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];		// here indicate which column to search.
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}

function Search_cas() {
	  var input, filter, table, tr, td, i;
	  input = document.getElementById("cas_Input");
	  filter = input.value.toUpperCase();
	  // alert("hi" + filter);
	  table = document.getElementById("table_1");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];		// here indicate which column to search.
	    if (td) {
	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}



// about open and close menus //////////////////////////////////////////////////////////////////////////////////
var leftMenu_on = true;
var rightMenu_on = true;

function openLeftMenu() {
	
	leftMenu_on = true;
	if(rightMenu_on == true){
		$("#central_area").css("width", "58%");
		}
	else{$("#central_area").css("width", "78%");
		}
	$("#central_area").css("marginLeft", "22%");
	$("#leftMenu").css("width", "22%");
    // $("$leftMenu").show();
    document.getElementById("leftMenu").style.display = "block";  
    $("#button-open-left").hide();
    $("#button-close-left").show();
    
}
function closeLeftMenu() {
	
	leftMenu_on = false;
	if(rightMenu_on == true){
		$("#central_area").css("width", "80%");
		}
	else{$("#central_area").css("width", "100%");
		}
    document.getElementById("leftMenu").style.display = "none";
    document.getElementById("central_area").style.marginLeft = "0%";
    $("#button-open-left").show();
    $("#button-close-left").hide();
}

function openRightMenu() {
	
	// document.getElementById("central_area").style.marginRight = "25%";
	rightMenu_on = true;
	if(leftMenu_on == true){
		$("#central_area").css("width", "58%");
		}
	else{$("#central_area").css("width", "80%");}
	
	$("#central_area").css("margin-right", "20%");
    document.getElementById("rightMenu").style.display = "block";    
	document.getElementById("rightMenu").style.width = "20%";
    $("#button-open-right").hide();
    $("#button-close-right").show();
	
}
function closeRightMenu() {
	
	rightMenu_on = false;
	if(leftMenu_on == true){
		$("#central_area").css("width", "78%");
		}
	else{$("#central_area").css("width", "100%");}
	
    document.getElementById("rightMenu").style.display = "none";
    document.getElementById("central_area").style.marginRight = "0%";
    $("#button-open-right").show();
    $("#button-close-right").hide();
}

////////////////////////////////////////////////////////////////////////////////////////////////////
// column response.

var chem_row_n = 0;
var chem_row_n2 = 0;
var endpoint_row_n;
var source_div ="";
var background_A = "-webkit-gradient(linear, left top, left bottom, from(#033771), to(#18a1c4))";
var background_B = "-webkit-gradient(linear, left top, left bottom, from(#033771), to(#18a1c4))";
var background = background_A;
var chemical_selected = false;
var endpoint_selected = false;

$(document).ready(function(){
	
	
	$("#table_1 tr").not(':first-child').click(
		function () {
			// alert("" + column3_data[2]);
			chemical_selected = true;
			if (!endpoint_selected){
		    	$("#table_1 tr").removeClass("Highlighted_rows");
				$(this).addClass("Highlighted_rows");
				chem_row_n = $(this).index()-2;	//$(this).find(".row_number").text();
				// alert(chem_row_n);
				jQuery('#inside-C').html('');
				// var row_n = Number(chem_row_n);
				jQuery('#inside-C').html(column3_data[chem_row_n]);
				}
			else{
				// alert("chem_row_n: " + chem_row_n + ", endpoint_row_n: " + endpoint_row_n);
				$("#chem_properties").removeClass("Highlighted_rows");
				$("#table_1 tr").removeClass("Highlighted_rows");
				$(this).addClass("Highlighted_rows");
				chem_row_n = $(this).index()-2;	//$(this).find(".row_number").text();
				// alert("in click, chem_row_n: " + chem_row_n);
				column_3_curve(chem_row_n, endpoint_row_n)
				}
			});
	



	
	$("#table_1 tr").not(':first-child').hover(
	  function () {								// mouse on function
		if (chemical_selected == false){
	    	// $(this).css("background","yellow");
	    	$(this).addClass("Highlighted_rows");
	    
	    	$("#chem_properties").addClass("Highlighted_rows");
	    	chem_row_n = Number($(this).find(".row_number").text());
			// alert(chem_row_n);
			source_div = "c" + String(chem_row_n);
			jQuery('#inside-C').html('');
			jQuery('#inside-C').html(column3_data[chem_row_n]);
			// alert("remainder: " + chem_row_n%3);
		
			if (chem_row_n%3 == 0) {
				background = "-webkit-gradient(linear, left top, left bottom, from(#033771), to(#18a1c4))";} 
			if (chem_row_n%3 == 1) {
				background = "-webkit-gradient(linear, right top, left bottom, from(#033771), to(#18a1c4))";} 
			if (chem_row_n%3 == 2) {
				background = "-webkit-gradient(linear, left top, right bottom, from(#033771), to(#18a1c4))";} 
		
			$('#Column-C').css({
		    	background: background });
			// alert("end of over " + column3_data[2]);
		}		// end of if chemical selected == false
	  },		// end of mouse over chemicals
	  	function () {						// mouse out function
		// alert("start leaving " + column3_data[2]);
		  if (chemical_selected == false){
			  	// $(this).css("background","");
	    		$(this).removeClass("Highlighted_rows");
	    		$("#chem_properties").removeClass("Highlighted_rows");
				}
				// alert("end of leaving " + column3_data[2]);
	  		}
		);		// end of $table_1 tr mouse out function
	
	$("#table_2 tr").not(':first-child').hover(
		function () {
			if(endpoint_selected == false){
				// $(this).css("background","yellow");
				$(this).addClass("Highlighted_rows");
		    	
		    	endpoint_row_n = $(this).index();
				// alert(endpoint_row_n);
				
				jQuery('#inside-C').html('');
				jQuery('#inside-C').html(endpoint_data[endpoint_row_n]);
				// alert("remainder: " + chem_row_n%3);
  
				if (background == background_A) {
					background = background_B;
					} else {
						background = background_A;}
				
				$('#Column-C').css({background: background });
				}		// end of endpoint selected == false
			},			// end of mouse over table 2 function part 1.
			function () {
				if(endpoint_selected == false){
			    // $(this).css("background","");
			    $(this).removeClass("Highlighted_rows");
			    $("#chem_properties").removeClass("Highlighted_rows");
			  	}}
			);		// end of mouse over table 2 leaving function

	$("#table_2 tr").not(':first-child').click(
		function () {
			endpoint_selected = true;
			if (!chemical_selected){
				$("#table_2 tr").removeClass("Highlighted_rows");
				$(this).addClass("Highlighted_rows");
	    		endpoint_row_n = $(this).index();
				// alert(endpoint_row_n);
			
				jQuery('#inside-C').html('');
				jQuery('#inside-C').html(endpoint_data[endpoint_row_n]);
				// alert("remainder: " + chem_row_n%3);
				if (background == background_A) {
					background = background_B;
					} else {
						background = background_A;}
			
				$('#Column-C').css({background: background });
				}		//end of if (!chemical_selected){}
			else{
				// alert("chem_row_n: " + chem_row_n + ", endpoint_row_n: " + endpoint_row_n);
				$("#chem_properties").removeClass("Highlighted_rows");
				$("#table_2 tr").removeClass("Highlighted_rows");
				$(this).addClass("Highlighted_rows");
				endpoint_row_n = $(this).index();
				column_3_curve(chem_row_n, endpoint_row_n)
				}		// end of else
			});		// end of $("#table_2 tr").not(':first-child').click()
			
	$("#chem_properties").hover(
		function () {
			if (!chemical_selected || !endpoint_selected){
				//alert("yes");
				$('#Column-C').css({
    				background: "-webkit-gradient(linear, left top, left bottom, from(#033771), to(#18a1c4))" 
					});
				jQuery('#inside-C').html('');
				jQuery('#inside-C').html(column3_data[chem_row_n]);
				}},		// end of the hover function
		function () {
			    //$(this).removeClass("button blue");
			  }
			);			// end of the $("#chem_properties").hover()
});		//end of $(document).ready(function(){}

function column_3_curve(chem_row_n, endpoint_row_n){
	// alert(chem_row_n + "===================================" + column3_data[chem_row_n-1]);
    $.post("column_3",{				// "Column_3" is the url
   		chemical: chem_row_n + 1,
    	endpoint: endpoint_row_n,
    	chemical_properties: column3_data[chem_row_n],
    	endpoint_data: endpoint_data[endpoint_row_n]
    	},
    	function(data, status){
    		jQuery('#inside-C').html('');
			jQuery('#inside-C').html(data);
    		});			// end of seccessful function and $.post()
	}		// end of function column_3_curve()





	
var endpoint_data = [];
endpoint_data[1] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">iCadiomyocyte peak frequency 90 minutes. </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">iCell cardiomyocytes (Catalogue #: CMC-100-010-001) including their respective plating ' +
	"and maintenance media were obtained from Cellular Dynamics International (Madison, WI). " +
	"EarlyTox Cardiotoxicity kits were purchased from Molecular Devices LLC (Sunnyvale, CA). </p>"+
	"<br><br><br><br><br>";
endpoint_data[2] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">iCadiomyocyte peak frequency 24 hours. </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">iCell cardiomyocytes (Catalogue #: CMC-100-010-001) including their respective plating ' +
	"and maintenance media were obtained from Cellular Dynamics International (Madison, WI). " +
	"EarlyTox Cardiotoxicity kits were purchased from Molecular Devices LLC (Sunnyvale, CA). </p>"+
	"<br><br><br><br><br>";
endpoint_data[3] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">iCardiomyocyte total cell number </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">' + 
	"Human umbilical vein endothelical cells (HUVEC) are derived from the endothelium of veins from the " +
	"umbillical cord. HUVEC used in this assay is pooled Human Umbilical Vein endothelial cells, purchased from Lonza" + 
	"Cat# C2519A, Lot# 0000433795. Total viable HUVEC cells were numerated 24 hours after chemical treatment with different " +
	"concentrations." +
	"<br><br><br><br><br>"; 
	


endpoint_data[4] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">HUVEC total cells </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">' +
	"Human umbilical vein endothelical cells (HUVEC) are derived from the endothelium of veins from the " +
	"umbillical cord. HUVEC used in this assay is pooled Human Umbilical Vein endothelial cells, purchased from Lonza" + 
	"Cat# C2519A, Lot# 0000433795. " +
	"In this assay, 24 hours after chemical treatment, " + 
	"cell culture images were captured, and the total viable cell numbers were calculated" +
	"by a trained computer software."+
	"<br><br><br><br><br>";

endpoint_data[5] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">HUVEC tube area </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">' +
	"Human umbilical vein endothelical cells (HUVEC) are derived from the endothelium of veins from the " +
	"umbillical cord. HUVEC used in this assay is pooled Human Umbilical Vein endothelial cells, purchased from Lonza" + 
	"Cat# C2519A, Lot# 0000433795. " +
	"In this assay, 18 hours after chemical treatment, " + 
	"cell culture images were captured, and the tube area were calculated" +
	"by a trained computer software."+
	"<br><br><br><br><br>";

endpoint_data[6] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">HUVEC 24hr mitochondira </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">' +
	"Human umbilical vein endothelical cells (HUVEC) are derived from the endothelium of veins from the " +
	"umbillical cord. HUVEC used in this assay is pooled Human Umbilical Vein endothelial cells, purchased from Lonza" + 
	"Cat# C2519A, Lot# 0000433795. " +
	"In this assay, 18 hours after chemical treatment, " + 
	"cell culture images were captured, and the mitochondria were stained. Total cells with intact mitochondria are numerated by" +
	"by a trained computer software."+
	"<br><br><br><br><br>";

endpoint_data[7] = "<br><br><br><papaya>" + 
	' <p  style="text-align: center;">Fly & Fly, iCadiomyocyte peak frequency 90 minutes. </p><br>' + 
	'<p style ="text-indent: 50px; text-align: justify;">Fly & Fly. The Dose-Response data are read from database. '+ 
	'Also, the Dose-Response data are loaded from the upload web page to the database.<br>' +
	'<p style ="text-indent: 50px; text-align: justify;">iCell cardiomyocytes (Catalogue #: CMC-100-010-001) including their respective plating ' +
	"and maintenance media were obtained from Cellular Dynamics International (Madison, WI). " +
	"EarlyTox Cardiotoxicity kits were purchased from Molecular Devices LLC (Sunnyvale, CA). </p>"+
	"<br><br><br><br><br>";

function displayChemicalData() {
	var lSelectedCas=$('input[name=cas]:checked').val();
	jQuery("#profile-pills").html("");
	/*$('input[name=cell]').prop('checked', false);
	$('input[name=pheno]').prop('checked', false);
	$('input[name=tp]').prop('checked', false);*/

	 var lChemical = $('input[name=cas]:checked').val();
	 var lCellLine = $('input[name=cell]:checked').val();
	 var lPhenotype= $('input[name=pheno]:checked').val();
	 var lTimePoint= $('input[name=tp]:checked').val();
	 
	
	$.ajax({
        type: "GET",
        url: "ChemProperties",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'cas': lSelectedCas
          },
          	 success: function (responseText) {
          		
          		/* alert("Test");*/
        	 var lChem = responseText.getElementsByTagName("chem");
          		var lHTML="<br><table class='table'><tbody><tr>"+
                "<td>CatApp ID</td>"+
       		 "<td>"+lChem[0].childNodes[0].firstChild.nodeValue+"</td></tr>"+
       		 "<tr class='success'><td>Cas Number</td>"+
       		 "<td>"+lChem[0].childNodes[1].firstChild.nodeValue+"</td></tr>"+
       		 "<tr class='danger'><td>Concawe ID</td>"+
       		 "<td>"+lChem[0].childNodes[2].firstChild.nodeValue+"</td></tr>"+
       		 "<tr class='info' ><td>Chemical Name</td>"+
       		 "<td>"+lChem[0].childNodes[3].firstChild.nodeValue+"</td></tr>"+
       		 "<tr class='warning'><td>Category</td>"+
       		 "<td>"+lChem[0].childNodes[4].firstChild.nodeValue+"</td></tr>"+
       		 "<tr><td>EC Number</td>"+
       		 "<td>"+lChem[0].childNodes[5].firstChild.nodeValue+"</td></tr>"+
       		 "<tr class='success'><td>Source</td>"+
       		 "<td>"+lChem[0].childNodes[6].firstChild.nodeValue+"</td></tr></tbody></table>";
  
          		
          		$("#hp").attr("aria-expanded","false");
          		$("#home-pills").removeClass("tab-pane fade active in");
          		$("#home").removeClass("active");
        		$("#home-pills").addClass("tab-pane fade");
        		var lCellLine = $('input[name=cell]:checked').val();
        		/*if(lCellLine==1){
        			
        		} else if(lCellLine==2){
        			
        		} else if (lCellLine==3){
        			
        		}else if (lCellLine==4){
        			
        			$("#messages-pills").html(endpoint_data[4])	;
        		}*/
        		$("#profile-pills").html(lHTML);
        		$("#chem").attr("aria-expanded","true");
        		$("#profile-pills").removeClass("tab-pane fade");
        		$("#profile-pills").addClass("tab-pane fade active in");
        		$("#chempr").addClass("active");
        		$("#sacl").removeClass("collapsed");
        		$("#sac").addClass("collapsed");
        		$("#sac").attr("aria-expanded","false");
        		$("#sacl").attr("aria-expanded","true");
        		if(lCellLine==undefined && lPhenotype==undefined && lTimePoint== undefined ){
        			
        			$("#collapseOne").removeClass("panel-collapse collapse in");
        			$("#collapseOne").addClass("panel-collapse collapse");
        			$("#collapseOne").attr("aria-expanded","false");
        			$("#collapseTwo").removeClass("panel-collapse collapse");
        			$("#collapseTwo").addClass("panel-collapse collapse in");
        			$("#collapseTwo").attr("aria-expanded","true");
        			$("#collapseTwo").attr("style","height: 405px;");
        		}
        		
        		

        }
          
          
    });
	 if(lCellLine!=undefined && lPhenotype!=undefined && lTimePoint!= undefined ){
		 displayGraph();
		
	 }
	

}
function displayPhenoData(){
	$("#collapseTwo").removeClass("panel-collapse collapse in");
	$("#collapseTwo").addClass("panel-collapse collapse");
	$("#collapseTwo").attr("aria-expanded","false");
	$("#collapseTwo").attr("style","height: 0px;");
	$("#collapseThree").removeClass("panel-collapse collapse");
	$("#collapseThree").addClass("panel-collapse collapse in");
	$("#collapseThree").attr("aria-expanded","true");
	$("#collapseThree").attr("style","height: 405px;");
	
	var lCellLine=$('input[name=cell]:checked').val();
	jQuery("#phenotable").html("");
	$.ajax({
        type: "GET",
        url: "Phenotypes",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine
          },
          	 success: function (responseText) {
          		/* alert("Test");*/
        	var lPhenolist = responseText.getElementsByTagName("pheno");
        	$("#phenotable").append("<tbody>");
        	for(var i=1;i<lPhenolist.length;i++){
        		
        		
        		$("#phenotable").append("<tr class='cell'>"+
        				"<td><input type='radio' name='pheno' id='pheno'"+
    					"onclick='displayTimeData()' value= "+lPhenolist[i].childNodes[1].firstChild.nodeValue+">&nbsp;"+lPhenolist[i].childNodes[0].firstChild.nodeValue+"</td></tr>"
    			);
        	}
        	$("#phenotable").append("</tbody>");

        }
          
          
    });
	
	
}
function displayTimeData(){
	$("#collapseThree").removeClass("panel-collapse collapse in");
	$("#collapseThree").addClass("panel-collapse collapse");
	$("#collapseThree").attr("aria-expanded","false");
	$("#collapseThree").attr("style","height: 0px;");
	$("#collapseFour").removeClass("panel-collapse collapse");
	$("#collapseFour").addClass("panel-collapse collapse in");
	$("#collapseFour").attr("aria-expanded","true");
	$("#collapseFour").attr("style","height: 405px;");
	
	var lCellLine=$('input[name=cell]:checked').val();
	
	var lPhenoType = $('input[name=tp]:checked').val();
		
		jQuery("#timepointtable").html("");
    	
		
		$.ajax({
        type: "POST",
        url: "TimePoint",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        data: { 
            'lCM': lCellLine,
            'lPhenotype': lPhenoType
          },
          	 success: function (responseText) {
          		
            	var lPhenolist = responseText.getElementsByTagName("tp");
            	$("#timepointtable").append("<tbody>");
            	for(var i=0;i<lPhenolist.length;i++){
            		
            		$("#timepointtable").append("<tr class='cell'>"+
            				"<td><input type='radio' name='tp' id='tp'"+
        					"onclick='displayGraph()' value= "+lPhenolist[i].childNodes[1].firstChild.nodeValue+">&nbsp;"+lPhenolist[i].childNodes[0].firstChild.nodeValue+"</td></tr>");
            	}
            	$("#timepointtable").append("</tbody>");
        }
          
    });
	
}
function displayGraph(){

 var lChemical = $('input[name=cas]:checked').val();
 var lCellLine = $('input[name=cell]:checked').val();
 var lPhenotype= $('input[name=pheno]:checked').val();
 var lTimePoint= $('input[name=tp]:checked').val();
 
 $.ajax({
     type: "GET",
     url: "GenerateGraph",
     contentType: "application/x-www-form-urlencoded; charset=UTF-8",
     data: { 
         'ch': lChemical,
         'cl': lCellLine,
         'ph': lPhenotype,
         'tp': lTimePoint
       },
       	 success: function (responseText) {
       		
       		var base64_string = responseText;
       		var img = document.createElement("img");
       		// added `width` , `height` properties to `img` attributes
       		img.width = "250px";
       		img.height = "250px";
       		img.src = "data:image/png;base64," + base64_string;
       	    var lCheckResponse=	"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";
       		
       	    if(responseText==lCheckResponse){
       	     $("#button-div").hide();
       	    }else{
       	    	$("#button-div").show();
       	    }
       		var lButton = "<a href=data:image/;base64,"+ base64_string+" download='graph.png' class='btn btn-primary'>Download Image</a>"    
       		
       		
       	    $("#button-div").html(lButton);
       		$("#img-div").html(img);
       		jQuery("#img-div").children('img').removeAttr('height');
       		jQuery("#img-div").children('img').removeAttr('width');
       		
       		jQuery("#img-div").children('img').attr('width','400px');
       		jQuery("#img-div").children('img').attr('height','400px');
       		
       		$("#hp").attr("aria-expanded","true");
      		$("#profile-pills").removeClass("tab-pane fade active in");
      		$("#chempr").removeClass("active");
    		$("#profile-pills").addClass("tab-pane fade");
      					
    	
    		$("#chem").attr("aria-expanded","false");
    		$("#home-pills").removeClass("tab-pane fade");
    		$("#home-pills").addClass("tab-pane fade active in");
    		$("#home").addClass("active");
     		

     }
       
       
 });
	
}
function finddoseresponse(){
	 var lChemical = $('input[name=cas]:checked').val();
	 var lCellLine = $('input[name=cell]:checked').val();
	 var lPhenotype= $('input[name=pheno]:checked').val();
	 var lTimePoint= $('input[name=tp]:checked').val();
	 
	 if(lChemical!=undefined && lCellLine!= undefined && lPhenotype!=undefined && lTimePoint!=undefined){
		 
		 $.ajax({
		     type: "GET",
		     url: "GetAssayData",
		     contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		     data: { 
		         'ch': lChemical,
		         'cl': lCellLine,
		         'ph': lPhenotype,
		         'tp': lTimePoint
		       },
		       	 success: function (responseText) {
		       		
		       	 	 var lChem = responseText.getElementsByTagName("dose");
		          		var lHTML="<br><table class='table'><tbody><tr>"+
		                "<td>1000x:</td>"+
		       		 "<td>"+lChem[0].childNodes[0].firstChild.nodeValue+"</td></tr>"+
		       		 "<tr class='success'><td>100x:</td>"+
		       		 "<td>"+lChem[0].childNodes[1].firstChild.nodeValue+"</td></tr>"+
		       		 "<tr class='danger'><td>10x:</td>"+
		       		 "<td>"+lChem[0].childNodes[2].firstChild.nodeValue+"</td></tr>"+
		       		 "<tr class='info' ><td>1x:</td>"+
		       		 "<td>"+lChem[0].childNodes[3].firstChild.nodeValue+"</td></tr>"+
		       		 "<tr class='warning'><td>Point of Departure:</td>"+
		       		 "<td>"+lChem[0].childNodes[4].firstChild.nodeValue+"</td></tr></tbody></table>";
		         
		          		var lCV = responseText.getElementsByTagName("control");
		          		
		          		var lValue= lCV[0].childNodes[0].firstChild.nodeValue;
		          		var lCommaLength=lValue.split(",").length
		          		var lHTML1 ="<b>Control Values:</b><br> <table><tbody><tr><td>";
		          		var counter=0;
		          		for(var i=0;i<lCommaLength;i++){
		          			if(counter>12){
		          				counter=0;
		          				lHTML1=lHTML1+"</tr>";
		          			}
		          			if(i==0){
		          				lHTML1=lHTML1+lValue.split(",")[i]+ ",</td>"
		          			}else if(i==lCommaLength-1){
		          				lHTML1=lHTML1+"<td>"+lValue.split(",")[i]+"</td>";
		          			}
		          			else{
		          				lHTML1=lHTML1+"<td>"+lValue.split(",")[i]+ ",</td>";
		          				counter++;
		          				
		          			}
		          		}
		          		lHTML1=lHTML1+"<tbody></table>";
		          		jQuery("#settings-pills").html(lHTML+lHTML1);

		     }
		       
		       
		 });
		 
	 }
	
}