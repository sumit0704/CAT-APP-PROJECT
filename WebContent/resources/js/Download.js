function should_alert() {
	alert("hh");
	// ${pageContext.request.contextPath}
}

function click_cell_line_title() {
	$("#file_table").hide();
	// alert("click_cell_line_title(): ${pageContext.request.contextPath}");
	$('#cell_line_list').show();
	$('#cell_line_button').hide();
	// alert("click_cell_line_title()-2");
	$("#assay_head").hide();
	$("#assay_list").hide();
	if (jQuery("#Assays_A").html() == ".") {

	} else {

		$("#Assays_A").html(".");
	}
	/*
	 * $("#file_button").hide(); $("#file_list").hide();
	 * $("#file_list").replaceWith("..."); $("#file_list").append("...");
	 * $("#file_list").replaceWith("data");
	 */

	// alert("click_cell_line_title()-3");
}

$(".cell_lines").change(function() {
	if (this.checked) {
		// alert("cell lines selected.");
		$('#cell_line_button').show();
	}
});

function click_cell_line_button() {
	
	var selected_celllines = [];
	var data_string = '';
	var i = 0;
	
	if(document.getElementById('processed').checked){
		
	
		var lCellLines="";
		var lDownloadType="3";
		jQuery('.test > input[type=checkbox]').each(
				function() {
					if ($(this).prop('checked') == true) {
						if(lCellLines.length==0){
							lCellLines=$(this).prop('value');
						}else{
							lCellLines=lCellLines+","+$(this).prop('value');
						}
						
					}
				});
	
	
		$.ajax({
			  url: "ViewFilesForDownload",
			  data : {
					'lCM' : lCellLines,
					'lDT' : lDownloadType
					
				},
			  type: 'get',
			  success: function(data) {
					var lfileList = data.getElementsByTagName("file");

					var lTableBody;
					lTableBody = '<br>' + '<table class="table"><thead><tr class="success"><th>Select</th><th>CellLine</th><th>FileName</th></tr></thead><tbody>'
						
					
					for (var i = 0; i < lfileList.length; i++) {
						
						var lEntityId = lfileList[i].childNodes[0].firstChild.nodeValue
						var lCellLine = lfileList[i].childNodes[1].firstChild.nodeValue;
						var lFileName=    lfileList[i].childNodes[2].firstChild.nodeValue;
						
						

						lTableBody = lTableBody
								+ '<tr class="active"'
								+ '>'
								+ '<td>'
								+ '<input type="checkbox" name="optradio"  id ='
								+  lEntityId + '  value=' + lEntityId + '>'
								+ '</td>' + '<td>' + lCellLine + '</td>'
								+ '<td>' + lFileName + '</td>'
								+ '</tr>';
					}
					lTableBody=lTableBody+'</tbody></table>';
					var lButtonHTML='<input type="submit" class="btn btn-primary" align="center" style="margin-bottom: 10px;margin-left:15px" name="download"'
						+'value="Download">';
					jQuery("#tablediv").html("");
					jQuery("#tablediv").append(lTableBody);
					jQuery("#right-pane").show();
					jQuery("#up-pane").show();
					jQuery("#button-div").html(lButtonHTML);
					jQuery("#button-div").show();
				}
			});		// end of ajax()
	
	
	
	}else{
	jQuery('.test > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					selected_celllines[$(this).prop('name')] = $(this).prop(
							'value');
					// alert("CM26: " + $(this).prop('value'));
					data_string += $(this).prop('name') + "="
							+ $(this).prop('value') + "&";
				}
			});
	
	$
			.ajax({
				type : "POST",
				url : "Download_InternalServlet",
				data : data_string,
				type : 'post',
				
				success : function(responseXml) 
				 {
					 $("#assayDiv").html($(responseXml).find("assayData").html());
					// $('#tableDiv').show();
				  }
			});
	
	$("#collapseOne").removeClass("panel-collapse collapse in");
	$("#collapseOne").addClass("panel-collapse collapse");
	$("#collapseOne").attr("aria-expanded","false");
	$("#collapseOne").attr("style","height: 0px;");
	$("#collapseTwo").removeClass("panel-collapse collapse");
	$("#collapseTwo").addClass("panel-collapse collapse in");
	$("#collapseTwo").attr("aria-expanded","true");
	$("#collapseTwo").attr("style","height: 195px;");
	}

	// alert("click_cell_line_button()-2");
} // end of show_hide_cell_lines(){}

function click_assay_title() {
	$("#assay_list").show();
	$("#assay_button").hide();

	$("#pheno_head").hide();
	$("#pheno_list").hide();
	$("#file_list").hide();
	$("#file_button").hide();
}

$(".all_assays").change(function() {
	if (this.checked) {
		// alert("cell lines selected.");
		$('#assay_button').show();
	}
});

function click_assay_button() {

	var lCellLines="";
	var lAssays="";
	
	$("#collapseTwo").removeClass("panel-collapse collapse in");
	$("#collapseTwo").addClass("panel-collapse collapse");
	$("#collapseTwo").attr("aria-expanded","false");
	$("#collapseTwo").attr("style","height: 0px;");
	
	if (document.getElementById('raw').checked) {
		$("#collapseFour").removeClass("panel-collapse collapse");
		$("#collapseFour").addClass("panel-collapse collapse in");
		$("#collapseFour").attr("aria-expanded","true");
		$("#collapseFour").attr("style","height: 195px;");
		jQuery("#static").show();
		jQuery("#static1").show();
		
	}else if(document.getElementById('pod').checked){
		$("#collapseThree").removeClass("panel-collapse collapse");
		$("#collapseThree").addClass("panel-collapse collapse in");
		$("#collapseThree").attr("aria-expanded","true");
		$("#collapseThree").attr("style","height: 195px;");
		
		var selected_assay = "";
		var data_string = '';
		
		jQuery('.test > input[type=checkbox]').each(
				function() {
					if ($(this).prop('checked') == true) {
						if(lCellLines.length==0){
							lCellLines=$(this).prop('value');
						}else{
							lCellLines=lCellLines+","+$(this).prop('value');
						}
						
					}
				});
		jQuery('.all_assays > input[type=checkbox]').each(
				function() {
					if ($(this).prop('checked') == true) {
						if(lAssays.length==0){
							lAssays=$(this).prop('value');
						}else{
							lAssays=lAssays+","+$(this).prop('value');
						}
					}
				});
		
		
		
		// data_string = "CM=CM&HEP=HEP"
		$.ajax({

			url : "Download_Internal_CServlet",
			data: {"cellNames": lCellLines, "assayLines": lAssays},
			type : 'post',
		
			success : function(responseXml) 
			 {
				 $("#phenoDiv").html($(responseXml).find("phenoData").html());
				// $('#tableDiv').show();
			  }
		}); // end of ajax()
		
		
	}

}


function click_pheno_button() {

	/*var selected_pheno = "";
	var data_string = '';*/
	
	
	
	
	/*$('.all_phenos > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {

					selected_pheno = $(this).prop('name');
					data_string += $(this).prop('name') + "="
							+ $(this).prop('value') + "&";
				}
			});*/
	//alert("hello")
	/*jQuery('.test > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lCellLines.length==0){
						lCellLines=$(this).prop('value');
					}else{
						lCellLines=lCellLines+","+$(this).prop('value');
					}
					
				}
			});
	jQuery('.all_assays > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lAssays.length==0){
						lAssays=$(this).prop('value');
					}else{
						lAssays=lAssays+","+$(this).prop('value');
					}
				}
			});

	$.ajax({

		url : "TimePoint_CatApp",
		data : data_string,
		type : 'post',
	
		success : function(responseXml) 
		 {
			 $("#timeDiv").html($(responseXml).find("timeData").html());
			// $('#tableDiv').show();
		  }
	});*/ // end of ajax()
	var lCellLines="";
	var lAssays="";
	jQuery('.test > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lCellLines.length==0){
						lCellLines=$(this).prop('value');
					}else{
						lCellLines=lCellLines+","+$(this).prop('value');
					}
					
				}
			});
	jQuery('.all_assays > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lAssays.length==0){
						lAssays=$(this).prop('value');
					}else{
						lAssays=lAssays+","+$(this).prop('value');
					}
				}
			});
	$.ajax({

		url : "TimePoint_CatApp",
		type : 'post',
		data: { 
            'lCM': lCellLines,
            'lAssay': lAssays
          },
	
		success : function(responseXml) 
		 {
			 $("#timeDiv").html($(responseXml).find("timeData").html());
			// $('#tableDiv').show();
		  }
	});
	
	$("#collapseThree").removeClass("panel-collapse collapse in");
	$("#collapseThree").addClass("panel-collapse collapse");
	$("#collapseThree").attr("aria-expanded","false");
	$("#collapseThree").attr("style","height: 0px;");
	
		$("#collapseFive").removeClass("panel-collapse collapse");
		$("#collapseFive").addClass("panel-collapse collapse in");
		$("#collapseFive").attr("aria-expanded","true");
		$("#collapseFive").attr("style","height: 195px;");
	
}

function click_dil_button(){
	var lCellLines="";
	var lAssays="";
	jQuery('.test > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lCellLines.length==0){
						lCellLines=$(this).prop('value');
					}else{
						lCellLines=lCellLines+","+$(this).prop('value');
					}
					
				}
			});
	jQuery('.all_assays > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lAssays.length==0){
						lAssays=$(this).prop('value');
					}else{
						lAssays=lAssays+","+$(this).prop('value');
					}
				}
			});
	$.ajax({

		url : "TimePoint_CatApp",
		type : 'post',
		data: { 
            'lCM': lCellLines,
            'lAssay': lAssays
          },
	
		success : function(responseXml) 
		 {
			 $("#timeDiv").html($(responseXml).find("timeData").html());
			// $('#tableDiv').show();
		  }
	});
	
	
	$("#collapseFour").removeClass("panel-collapse collapse in");
	$("#collapseFour").addClass("panel-collapse collapse");
	$("#collapseFour").attr("aria-expanded","false");
	$("#collapseFour").attr("style","height: 0px;");
	
		$("#collapseFive").removeClass("panel-collapse collapse");
		$("#collapseFive").addClass("panel-collapse collapse in");
		$("#collapseFive").attr("aria-expanded","true");
		$("#collapseFive").attr("style","height: 195px;");
	
	
}

// ================================= LD coded above =======================

function searchFiles() {
	var lCellLines = "";
	var lCount = 0;
	$.each($("input[name='celllines']:checked"), function() {
		if (lCount == 0) {
			lCellLines = $(this).val();
		} else {
			lCellLines = lCellLines + "," + $(this).val();

		}
		lCount++
	});

	var lCount1 = 0;
	var lAssayNames = "";
	$.each($("input[name='assaynames']:checked"), function() {
		if (lCount1 == 0) {
			lAssayNames = $(this).val();
		} else {
			lAssayNames = lAssayNames + "," + $(this).val();

		}
		lCount1++;
	});

	var lCount2 = 0;
	var lPhenoTypes = "";
	$.each($("input[name='phenotypes']:checked"), function() {
		if (lCount2 == 0) {
			lPhenoTypes = $(this).val();

		} else {

			lPhenoTypes = lPhenoTypes + "," + $(this).val();
		}

		lCount2++;
	});

	var lCount3 = 0;

	var lPlate = "";
	$.each($("input[name='pd']:checked"), function() {
		if (lCount3 == 0) {
			lPlate = $(this).val();
		} else {
			lPlate = lPlate + "," + $(this).val();
		}
		lCount3++;
	});

	$
			.ajax({
				type : "GET",
				url : "ViewFilesServlet",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					'lCM' : lCellLines,
					'lAN' : lAssayNames,
					'lPT' : lPhenoTypes,
					'lPD' : lPlate
				},
				// data: {"lCM": lCellLines, "lAN": lAssayNames, "lPT":
				// lPhenoTypes, "lPD": lPlate},
				success : function(responseText) {
					var lfileList = responseText.getElementsByTagName("file");

					var lTableBody;
					lTableBody = '<br>' + '<table class="table table-hover"><thead><tr><th>Select</th><th>Cell Line</th><th>Assay Type</th>'
							+ '<th>Dilution</th><th>TimePoint</th><th>FileName</th></tr><tbody>';
					
			
					for (var i = 0; i < lfileList.length; i++) {
						
						var lEntityId = lfileList[i].childNodes[0].firstChild.nodeValue
						var lCellLine = lfileList[i].childNodes[1].firstChild.nodeValue;
						var lAssay =    lfileList[i].childNodes[2].firstChild.nodeValue;
						var lDilution = lfileList[i].childNodes[3].firstChild.nodeValue;
						var lTimePoint = lfileList[i].childNodes[4].firstChild.nodeValue;
						var lFileName = lfileList[i].childNodes[5].firstChild.nodeValue;
						
						/*var lClassStyle;
						var lTrId;
						if (lfileList[i].childNodes[0].firstChild.nodeValue == "xls"
								|| lfileList[i].childNodes[0].firstChild.nodeValue == "xlsx") {
							lClassStyle = "success";
							lTrId = "xls";
						} else if (lfileList[i].childNodes[0].firstChild.nodeValue == "pdf") {
							lClassStyle = "danger";
							lTrId = "pdf";
						} else if (lfileList[i].childNodes[0].firstChild.nodeValue == "jpeg") {
							lClassStyle = "warning";
							lTrId = "img";
						} else {
							lClassStyle = "active";
						}*/

						lTableBody = lTableBody
								+ '<tr'
								+ '>'
								+ '<td>'
								+ '<input type="checkbox" name="optradio"  id ='
								+ lEntityId + '  value=' + lEntityId + '>'
								+ '</td>' + '<td>' + lCellLine + '</td>'
								+ '<td>' + lAssay + '</td>'
								+ '<td>' + lDilution + '</td>'
								+ '<td>' + lTimePoint + '</td>'
								+ '<td>' + lFileName + '</td>'
								+ '</tr>';
					}
					
					

				}

			});

}

function downloadfiles() {

	var lSelectedCheckBox = "";
	var lCount = 0;
	$.each($("input[name='optradio']:checked"), function() {
		if (lCount == 0) {
			lSelectedCheckBox = $(this).val();
		} else {
			lSelectedCheckBox = lSelectedCheckBox + "," + $(this).val();

		}
		lCount++
	});
	if (lSelectedCheckBox.length > 0) {

	} else {
		alert("Please select any file to download.");
	}
	alert("lSelectedCheckBox: " + lSelectedCheckBox);
	$.ajax({
 		type : "GET",
		url : "DownloadFileServlet",
		dataType : 'text',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		data : {
			'lSelectedBox' : lSelectedCheckBox,

		},
		// data: {"lCM": lCellLines, "lAN": lAssayNames, "lPT": lPhenoTypes,
		// "lPD": lPlate},
		success : function(responseText) {

			window.location.href = responseText
		}
	});

}


function selectphenotypesForDownload() {

	var lCellLine = jQuery("#cellLines").val();

	jQuery("#phenotypes").html("");
	$
			.ajax({
				type : "GET",
				url : "Phenotypes",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					'lCM' : lCellLine
				},
				success : function(responseText) {

					var lPhenolist = responseText.getElementsByTagName("pheno");
					for (var i = 0; i < lPhenolist.length; i++) {
						$("#phenotypes")
								.append(
										new Option(
												lPhenolist[i].childNodes[0].firstChild.nodeValue,
												lPhenolist[i].childNodes[1].firstChild.nodeValue));
					}

				}

			});
}
function selectphenotypesForDownloadCell() {
	var lCellLine = jQuery("#cellLine").val();
	jQuery("#phenotype").html("");
	$
			.ajax({
				type : "GET",
				url : "Phenotypes",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					'lCM' : lCellLine
				},
				success : function(responseText) {
					/* alert("Test"); */
					var lPhenolist = responseText.getElementsByTagName("pheno");
					$("#phenotype option[value='--Select--']").remove()
					for (var i = 0; i < lPhenolist.length; i++) {
						$("#phenotype")
								.append(
										new Option(
												lPhenolist[i].childNodes[0].firstChild.nodeValue,
												lPhenolist[i].childNodes[1].firstChild.nodeValue));
					}

				}

			});
	selectTimepoint();
}
function selectTimepoint() {
	var lCellLine = jQuery("#cellLine").val();
	$("#timepoint").html("");

	$
			.ajax({
				type : "POST",
				url : "TimePoint",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					'lCM' : lCellLine,
				},
				success : function(responseText) {

					var lPhenolist = responseText.getElementsByTagName("tp");
					for (var i = 0; i < lPhenolist.length; i++) {
						$("#timepoint")
								.append(
										new Option(
												lPhenolist[i].childNodes[0].firstChild.nodeValue,
												lPhenolist[i].childNodes[1].firstChild.nodeValue));
					}

				}

			});
}
// function closemessageheader(){
// jQuery(".alert").hide();
// }

function validateInput(event) {

	var $boxes = $('input[name=optradio]:checked');
	if ($boxes.length == 0) {
		alert("Please select a checkbox before clicking on Download button");
		event.preventDefault();
		return false;
	} else {
		/* jQuery("#DFile").submit(); */

	}
}
function click_tp_button(){
	
	var lCellLines="";
	var lAssays="";
	var lTimePoint="";
	var lDilution="";
	var lPhenotype="";
	var lDownloadType="";
	
	if(document.getElementById('raw').checked){
		lDownloadType="1";
	}else if(document.getElementById('pod').checked){
		lDownloadType="2";
	}else{
		lDownloadType="3";
	}
	
		
	jQuery('.test > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lCellLines.length==0){
						lCellLines=$(this).prop('value');
					}else{
						lCellLines=lCellLines+","+$(this).prop('value');
					}
					
				}
			});
	jQuery('.all_assays > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lAssays.length==0){
						lAssays=$(this).prop('value');
					}else{
						lAssays=lAssays+","+$(this).prop('value');
					}
				}
			});
	if(document.getElementById('pod').checked){
		jQuery('.all_phenos > input[type=checkbox]').each(
				function() {
					if ($(this).prop('checked') == true) {
						if(lPhenotype.length==0){
							lPhenotype=$(this).prop('value');
						}else{
							lPhenotype=lPhenotype+","+$(this).prop('value');
						}
					}
				});
	}
	
	if(document.getElementById('raw').checked){
		
		jQuery('.all_dilution > input[type=checkbox]').each(
				function() {
					if ($(this).prop('checked') == true) {
						if(lDilution.length==0){
							lDilution=$(this).prop('value');
						}else{
							lDilution=lDilution+","+$(this).prop('value');
						}
					}
				});
	}
	
	jQuery('.all_timepoints > input[type=checkbox]').each(
			function() {
				if ($(this).prop('checked') == true) {
					if(lTimePoint.length==0){
						lTimePoint=$(this).prop('value');
					}else{
						lTimePoint=lTimePoint+","+$(this).prop('value');
					}
				}
			});
	
	
	
	$.ajax({
	  url: "ViewFilesForDownload",
	  data : {
			'lCM' : lCellLines,
			'lAN' : lAssays,
			'lPT' : lPhenotype,
			'lTP' : lTimePoint,
			'lDil': lDilution,
			'lDT' : lDownloadType
		},
	  type: 'get',
	  success: function(data) {
			var lfileList = data.getElementsByTagName("file");

			var lTableBody;
			lTableBody = '<br>' +'<table class="table"><thead><tr class="success"><th>Select</th><th>CellLine</th><th>Assay</th>'
			
			if(lDownloadType=="1"){
				lTableBody=lTableBody+'<th>Dilution</th><th>TimePoint</th><th>FileName</th></tr></thead><tbody>';
			}else if(lDownloadType=="2"){
				lTableBody=lTableBody+'<th>Phenotype</th><th>TimePoint</th><th>FileName</th></tr></thead><tbody>';
			}
					
			
			for (var i = 0; i < lfileList.length; i++) {
				
				var lEntityId = lfileList[i].childNodes[0].firstChild.nodeValue
				var lCellLine = lfileList[i].childNodes[1].firstChild.nodeValue;
				var lAssay =    lfileList[i].childNodes[2].firstChild.nodeValue;
				var lDilution = lfileList[i].childNodes[3].firstChild.nodeValue;
				if(lDilution=="00"){
					lDilution="All";
				}
				var lTimePoint = lfileList[i].childNodes[4].firstChild.nodeValue;
				var lFileName = lfileList[i].childNodes[5].firstChild.nodeValue;
				
				

				lTableBody = lTableBody
				
						+ '<tr class="active"'
						+ '>'
						+ '<td>'
						+ '<input type="checkbox" name="optradio"  id ='
						+  lEntityId + '  value=' + lEntityId + '>'
						+ '</td>' + '<td>' + lCellLine + '</td>'
						+ '<td>' + lAssay + '</td>'
						+ '<td>' + lDilution + '</td>'
						+ '<td>' + lTimePoint + '</td>'
						+ '<td>' + lFileName + '</td>'
						+ '</tr>';
			}
			lTableBody=lTableBody+'</tbody></table>';
			var lButtonHTML='<input type="submit" class="btn btn-primary" align="center" style="margin-bottom: 10px;margin-left:15px" name="download"'
							+'value="Download">';
			
			
			
		

				
			
		
			
			jQuery("#tablediv").html("");
			jQuery("#tablediv").append(lTableBody);
			jQuery("#right-pane").show();
			jQuery("#up-pane").show();
			jQuery("#button-div").html(lButtonHTML);
			jQuery("#button-div").show();
		}
	});		// end of ajax()
	
	
}