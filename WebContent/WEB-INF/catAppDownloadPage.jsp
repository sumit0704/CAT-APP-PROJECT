<%@page pageEncoding="ISO-8859-1"%>
<%@ page import="com.catapp.entity.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>

<!-- <meta charset="UTF-8">
<meta https-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content=""> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<title>Download</title>

<!-- Bootstrap Core CSS -->
<link href="/CAT-APP-PROJECT/resources/css/catAppDown.css"
	rel="stylesheet">

<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">


<!-- MetisMenu CSS -->
<link href="/CAT-APP-PROJECT/resources/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/sb-admin-2.css"
	rel="stylesheet">

<link href="/CAT-APP-PROJECT/resources/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom Fonts -->
<link
	href="/CAT-APP-PROJECT/resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.png">
<style type="text/css">
#page-wrapper {
	/* background: RGBA(24, 161, 196, 0.2); */
	background: url('/CAT-APP-PROJECT/resources/images/bgLight.jpg') repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
	-o-background-size: cover;
}
</style>
<script type="text/javascript">
	function displayDownloadForm() {
		if (document.getElementById('asIsFiles').checked) {
			//alert("Hello");
			document.getElementById('downloadByAsIs').style.display = "none";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "none";
			document.getElementById('rawProcessedPodRadioBox').style.display = "block";

		} else if (document.getElementById('byComp').checked) {
			//alert("Hii");
			document.getElementById('downloadByAsIs').style.display = "none";
			document.getElementById('downloadByComp').style.display = "block";
			document.getElementById('downloadByCellLine').style.display = "none";
			document.getElementById('rawProcessedPodRadioBox').style.display = "none";
			//reset

		} else if (document.getElementById('byCell').checked) {
			//alert("heyy");
			document.getElementById('downloadByAsIs').style.display = "none";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "block";
			document.getElementById('rawProcessedPodRadioBox').style.display = "none";
			//reset

		}

	}

	function displaySearchForm() {

		if (document.getElementById('raw').checked) {
			
			jQuery("#collapsetwo").show();
			jQuery("#collapsethree").show();
			jQuery("#collapsefour").show();
			jQuery("#collapsefive").show();
			jQuery("#collapsetwo").show();
			jQuery("#asay").show();
			jQuery("#dil").show();
			jQuery("#ph").show();
			jQuery("#tp").show();
			
			document.getElementById('downloadByAsIs').style.display = "block";
			document.getElementById('downloadByRaw').style.display = "block";
			document.getElementById('downloadByPod').style.display = "none";
			document.getElementById('downloadByProcessed').style.display = "none";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "none";
			jQuery("#ph").hide();
			jQuery("#collapsethree").hide();
			jQuery("#dil").show();
			jQuery("#collapseFour").show();
			
			
			
			//close panels except first
			$("#collapseTwo").removeClass("panel-collapse collapse in");
			$("#collapseTwo").addClass("panel-collapse collapse");
			$("#collapseTwo").attr("aria-expanded","false");
			$("#collapseTwo").attr("style","height: 0px;");
			
			$("#collapseThree").removeClass("panel-collapse collapse in");
			$("#collapseThree").addClass("panel-collapse collapse");
			$("#collapseThree").attr("aria-expanded","false");
			$("#collapseThree").attr("style","height: 0px;");
			
			$("#collapseFour").removeClass("panel-collapse collapse in");
			$("#collapseFour").addClass("panel-collapse collapse");
			$("#collapseFour").attr("aria-expanded","false");
			$("#collapseFour").attr("style","height: 0px;");
			
			
			$("#collapseFive").removeClass("panel-collapse collapse in");
			$("#collapseFive").addClass("panel-collapse collapse");
			$("#collapseFive").attr("aria-expanded","false");
			$("#collapseFive").attr("style","height: 0px;");
			jQuery("#tablediv").html("");
			jQuery("#button-div").hide();
			
			$('input[type=checkbox]').each(function() 
					{ 
					        this.checked = false; 
					}); 
			
			


		} else if (document.getElementById('pod').checked) {
			jQuery("#collapsetwo").show();
			jQuery("#ph").show();
			jQuery("#collapsethree").show();
			jQuery("#collapsefour").show();
			jQuery("#collapsefive").show();
			jQuery("#collapsetwo").show();
			jQuery("#asay").show();
			jQuery("#dil").show();
			jQuery("#ph").show();
			jQuery("#tp").show();
			
			document.getElementById('downloadByAsIs').style.display = "block";
			document.getElementById('downloadByRaw').style.display = "block";
			document.getElementById('downloadByPod').style.display = "block";
			document.getElementById('downloadByProcessed').style.display = "none";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "none";
			
			jQuery("#dil").hide();
			jQuery("#collapseFour").hide()
			
			//remove selections
			document.getElementsByName("cellNames").checked = false;
			
			// close panels
			$("#collapseTwo").removeClass("panel-collapse collapse in");
			$("#collapseTwo").addClass("panel-collapse collapse");
			$("#collapseTwo").attr("aria-expanded","false");
			$("#collapseTwo").attr("style","height: 0px;");
			
			$("#collapseThree").removeClass("panel-collapse collapse in");
			$("#collapseThree").addClass("panel-collapse collapse");
			$("#collapseThree").attr("aria-expanded","false");
			$("#collapseThree").attr("style","height: 0px;");
			
			$("#collapseFour").removeClass("panel-collapse collapse in");
			$("#collapseFour").addClass("panel-collapse collapse");
			$("#collapseFour").attr("aria-expanded","false");
			$("#collapseFour").attr("style","height: 0px;");
			
			
			$("#collapseFive").removeClass("panel-collapse collapse in");
			$("#collapseFive").addClass("panel-collapse collapse");
			$("#collapseFive").attr("aria-expanded","false");
			$("#collapseFive").attr("style","height: 0px;");
			
			jQuery("#tablediv").html("");
			jQuery("#button-div").hide();
			$('input[type=checkbox]').each(function() 
					{ 
					        this.checked = false; 
					}); 
			

		}

		else if (document.getElementById('processed').checked) {
			
		document.getElementById('downloadByAsIs').style.display = "block";
		document.getElementById('downloadByRaw').style.display = "block";
		document.getElementById('downloadByPod').style.display = "block";
		document.getElementById('downloadByProcessed').style.display = "block";
		document.getElementById('downloadByComp').style.display = "none";
		document.getElementById('downloadByCellLine').style.display = "none";
		
		jQuery("#collapsetwo").hide();
		jQuery("#collapsethree").hide();
		jQuery("#collapsefour").hide();
		jQuery("#collapsefive").hide();
		jQuery("#asay").hide();
		jQuery("#dil").hide();
		jQuery("#ph").hide();
		jQuery("#tp").hide();
		
		
		
		}
		$("#collapseOne").removeClass("panel-collapse collapse");
		$("#collapseOne").addClass("panel-collapse collapse in");
		$("#collapseOne").attr("aria-expanded","true");
		$("#collapseOne").attr("style","height: 195px;");
		
		jQuery("#tablediv").html("");
		jQuery("#button-div").hide();
		
		$('input[type=checkbox]').each(function() 
				{ 
				        this.checked = false; 
				}); 
		

	}

	function filterchem() {

		var input = jQuery("#chemsearch").val();
		var lListSize = jQuery(".chemical").length
		for (var i = 0; i < lListSize; i++) {
			if (jQuery(".chemical")[i].innerHTML.indexOf(input) > -1) {
				document.getElementsByClassName("chemical")[i].style.display = "";
			} else {
				document.getElementsByClassName("chemical")[i].style.display = "none";
			}
		}
		/* var input, listsize, i;
		input =jQuery("#chemsearch").val();
		listsize=jQuery("#li").size(); */

	}

	function filterCell() {

		var input = jQuery("#cellSearch").val();
		var lListSize = jQuery(".cell").length
		for (var i = 0; i < lListSize; i++) {
			if (jQuery(".cell")[i].innerHTML.indexOf(input) > -1) {
				document.getElementsByClassName("cell")[i].style.display = "";
			} else {
				document.getElementsByClassName("cell")[i].style.display = "none";
			}
		}
		/* var input, listsize, i;
		input =jQuery("#chemsearch").val();
		listsize=jQuery("#li").size(); */

	}
</script>
</head>
<!-- function located line 57 -->
<body>
	<div id="wrapper">

		<%
			String isAdmin = ((User) request.getSession().getAttribute("user")).getIs_admin();
			if (null == isAdmin || "" == isAdmin.trim()) {
		%>
		<jsp:include page="headerUserHome.jsp" />
		<%
			}
			if ("Y".equalsIgnoreCase(isAdmin)) {
		%>
		<jsp:include page="headerAdminHome.jsp" />
		<%
			}
		%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Download Files in Cat-App</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">

					<div class="panel panel-default">

						<div class="panel-heading" id="selectDownloadTypeDiv">
							<label class="radio-inline"> <input type="radio"
								name="fileRadio" id="asIsFiles" value="asIsFiles"
								onchange="displayDownloadForm()">Download uploaded files
							</label> <label class="radio-inline"> <input type="radio"
								name="fileRadio" id="byComp" value="byComp"
								onchange="displayDownloadForm()">Download by compound
							</label> <label class="radio-inline"> <input type="radio"
								name="fileRadio" id="byCell" value="byCell"
								onchange="displayDownloadForm()">Download by cellline
							</label> <a target="_blank" class="radio-inline"
								href="ftp://FTP_Test:VIBSftpaccess@catappdata.com/">Download
								Files from FTP- Click Here</a>
						</div>


						<div class="panel-heading" id="rawProcessedPodRadioBox"
							style="display: none;">
							Select Type &nbsp &nbsp<label class="radio-inline"> 
							<input
								type="radio" name="typeRadio" id="raw" value="raw"
								onchange="displaySearchForm()">Raw Data
							</label> <label class="radio-inline"> 
							<input type="radio"
								name="typeRadio" id="pod" value="pod"
								onchange="displaySearchForm()">POD Data
							</label> <label class="radio-inline"> 
							<input type="radio"
								name="typeRadio" id="processed" value="processed"
								onchange="displaySearchForm()">Processed Data
							</label>
							 <label class="radio-inline"> <h6>(<font color="red">*</font> fields are mandatory)</h6></label>


						</div>

						<!-- UPLOADED STARTS -->
						<div id="downloadByAsIs">
							<!-- RAW RAW RAW STARTS -->
							<div class="row" id="downloadByRaw" class="form-group"
								style="display: none;">

								<div class="col-lg-4">
									<div id="cel" class="panel panel-default">
										<!-- <div class="panel-heading">Select Data</div> -->
										<!-- .panel-heading -->
										<div class="panel-body">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseOne" id="sacl">Select Cells <font color="red">*</font>:</label> </a> &nbsp; &nbsp; <a
															href="#" id="cell_line_button"
															onclick="click_cell_line_button()" class="btn btn-primary" style="padding:2px 5px" ><font color="white">Next<span
															class="fa fa-chevron-right"></span></font>

														</a>


													</h4>
												</div>
												<div id="collapseOne" class="panel-collapse collapse">
													<div class="panel-body">



														<div class="form-group scrollerdiv pre-scrollable"
														id="cellDiv">
															<c:forEach var="item" items="${cell}">
																<div class="checkbox">
																	<label class="test"> <input type="checkbox"
																		name="cellNames" value="${item.key}">${item.value}

																	</label>
																</div>
															</c:forEach>
														</div>




													</div>
												</div>
											</div>
											<div id="asay" class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseTwo" id="sacl">Select Assays <font color="red">*</font>:</label></a> &nbsp;<a
															href="#" id="assay_button" class="btn btn-primary" style="padding:2px 5px" onclick="click_assay_button()"><font color="white">Next<span
															class="fa fa-chevron-right"></span></font>

														</a>

													</h4>
												</div>
												<div id="collapseTwo" class="panel-collapse collapse">
													<div class="panel-body">
														<div class="form-group scrollerdiv pre-scrollable"
															id="assayDiv">
															<assayData>
															<c:forEach var="item" items="${assayList}">
																
																	<label> <input type="text" disabled="disabled"
																		name="selectedCell" value="${item.key}">

																	</label>
																
																<c:forEach var="assay" items="${item.value}">
																        <div class="checkbox">
																	    <label class="all_assays"> <input type="checkbox"
																		name="assayLines" value="${assay}">${assay}

																	    </label>
																        </div>
																        </c:forEach>
																
															</c:forEach>
															</assayData>
															</div>


													</div>
												</div>
											</div>


											<div id="ph" class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseThree" id="sacl">Select Phenotypes <font color="red">*</font>:</label></a> &nbsp;
															<a
															href="#" id="pheno_button" class="btn btn-primary" style="padding:2px 5px" onclick="click_pheno_button()">
															<font color="white">Next<span
															class="fa fa-chevron-right"></span></font>

														</a>

													</h4>
												</div>
												<div id="collapseThree" class="panel-collapse collapse">
													<div class="panel-body">
														<div class="form-group scrollerdiv pre-scrollable">
															<div class="form-group scrollerdiv pre-scrollable"
															id="phenoDiv">
															<phenoData>
															<c:forEach var="item" items="${phenoList}">
																
																	<label> <input type="text" disabled="disabled"
																		name="selectedAssay" value="${item.key}">

																	</label>
																
																<c:forEach var="pheno" items="${item.value}">
																        <div class="checkbox">
																	    <label class="all_phenos"> <input type="checkbox"
																		name="phenoNames" value="${pheno}">${pheno}

																	    </label>
																        </div>
																        </c:forEach>
																
															</c:forEach>
															</phenoData>
															</div>
															
														</div>


													</div>
												</div>
											</div>
											
												<div id ="dil" class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseFour" id="sacl">Select Dilution</a>&nbsp;
															<a
															href="#" id="dilution_button" class="btn btn-primary" style="padding:2px 5px" onclick="click_dil_button()">
															<font color="white">Next<span
															class="fa fa-chevron-right"></span></font>

														</a>

													</h4>
												</div>
												<div id="collapseFour" class="panel-collapse collapse">
													<div class="panel-body">
												<div id="static1" style="display: none;" class="form-group scrollerdiv pre-scrollable">
												<div id="static"  style="display: none;">
											    <label class="all_dilution"><input type="checkbox" name="dilution" id="11" value="x1" /> 1x </label>
												<br>
												<label class="all_dilution"><input type="checkbox" name="dilution" id="21" value="x10" /> 10x </label>
												<br>
												<label class="all_dilution"><input type="checkbox" name="dilution" id="31" value="x100" /> 100x </label>
												<br>
												<label class="all_dilution"><input type="checkbox" name="dilution" id="41" value="x1000" /> 1000x </label>
												</div>
														</div>
													</div>
												</div>
											</div>

											<div id="tp" class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" data-parent="#accordion"
															href="#collapseFive" id="sacl">Select Timepoints <font color="red">*</font>:</label></a>&nbsp;
															<a
															href="#" id="time_button" class="btn btn-primary" style="padding:2px 5px" onclick="click_tp_button()">
															<font color="white">Next<span
															class="fa fa-chevron-right"></span></font>
														</a>

													</h4>
												</div>
												<div id="collapseFive" class="panel-collapse collapse">
													<div class="panel-body">
														<div class="form-group scrollerdiv pre-scrollable" id="timeDiv">
															<timeData>
															<c:forEach var="item" items="${timeList}">
																
																	<label> <input type="text" disabled="disabled"
																		name="selectedTime" value="${item.key}">

																	</label>
																
																<c:forEach var="time" items="${item.value}">
																        <div class="checkbox">
																	    <label class="all_timepoints"> <input type="checkbox"
																		name="timePoints" value="${time}">${time}

																	    </label>
																        </div>
																        </c:forEach>
																
															</c:forEach>
															</timedata>
														</div>
													</div>
												</div>
											</div>
										</div>
										
										
										</div>
										<!-- RAW RAW RAW ENDS -->

										<!-- POD POD POD STARTS -->
										<div id="downloadByPod" style="display: none;"
											class="form-group"></div>

										<!-- POD POD POD ENDS -->
										<!-- PROCESSED STARTS -->
										<div id="downloadByProcessed" style="display: none;"
											class="form-group">
											
										</div>
										<!-- PROCESSED ENDS -->
									</div>
									<div class="col-lg-8">


						<div class="panel panel-primary" id="up-pane"  style="display:none;">
							<div class="panel-heading" id="right-pane" style="display:none;">Download Files</div>
								<form name="DFile" action="DownloadFileServlet"
										onSubmit="return validateInput(event);">
								
								<div id="tablediv" class="panel-body">
									
								</div>
										<div id="button-div">
									  
										</div>
										
								
								 </form>
								
							</div>

							</div>
				</div>
									

								</div>

						
							
						
						<!-- UPLOADED ENDS -->
						<div id="downloadByComp" style="display: none;" class="form-group">
							<jsp:include page="downloadByComp_Int.jsp" />
						</div>

						<div id="downloadByCellLine" style="display: none;"
							class="form-group">

							<jsp:include page="downloadByCell_Int.jsp" />
						</div>
						</div>
						</div>
						
					</div>

					<%-- 		<jsp:include page="footer.jsp" />
 --%>
				</div>
				

				<!-- jQuery -->
				<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>

				<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
				<script src="/CAT-APP-PROJECT/resources/js/Download.js"></script>



				<!-- Bootstrap Core JavaScript -->
				<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

				<!-- METISMENU SCRIPTS -->
				<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
				<!-- CUSTOM SCRIPTS -->
				<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>
				<script src="/CAT-APP-PROJECT/resources/js/Uploadjs.js"></script>
</body>
</html>