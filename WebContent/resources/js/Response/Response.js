
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
	$('input[name=cell]').prop('checked', false);
	$('input[name=pheno]').prop('checked', false);
	$('input[name=tp]').prop('checked', false);

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
        		if(lCellLine==1){
        			
        		} else if(lCellLine==2){
        			
        		} else if (lCellLine==3){
        			
        		}else if (lCellLine==4){
        			
        			$("#messages-pills").html(endpoint_data[4])	;
        		}
        		$("#profile-pills").html(lHTML);
        		$("#chem").attr("aria-expanded","true");
        		$("#profile-pills").removeClass("tab-pane fade");
        		$("#profile-pills").addClass("tab-pane fade active in");
        		$("#chempr").addClass("active");
        		$("#sacl").removeClass("collapsed");
        		$("#sac").addClass("collapsed");
        		$("#sac").attr("aria-expanded","false");
        		$("#sacl").attr("aria-expanded","true");
        		$("#collapseOne").removeClass("panel-collapse collapse in");
        		$("#collapseOne").addClass("panel-collapse collapse");
        		$("#collapseOne").attr("aria-expanded","false");
        		$("#collapseTwo").removeClass("panel-collapse collapse");
        		$("#collapseTwo").addClass("panel-collapse collapse in");
        		$("#collapseTwo").attr("aria-expanded","true");
        		$("#collapseTwo").attr("style","height: 405px;");
        		
        		

        }
          
          
    });
	

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
       		$("#home-pills").html(img);
       		jQuery("#home-pills").children('img').removeAttr('height');
       		jQuery("#home-pills").children('img').removeAttr('width');
       		
       		jQuery("#home-pills").children('img').attr('width','400px');
       		jQuery("#home-pills").children('img').attr('height','400px');
       		
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
		          			if(counter>6){
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