<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.catapp.entity.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> -->
<title>CAT-APP Upload</title>

<link rel="shortcut icon"
	href="/CAT-APP-PROJECT/resources/images/logo2.ico" />

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

.scrollable {
	height: 300px; /* or any value */
	overflow-y: auto;
}
</style>



					<script type="text/javascript">
						function validateFile() {
							jQuery("#sequencesave").prop('disabled', false);
						}
						document.addEventListener("DOMContentLoaded", function(
								event) {
							jQuery('input:file').change(
									function() {
										if (jQuery(this).val()) {
											jQuery('input:submit').attr(
													'disabled', false);
											// or, as has been pointed out elsewhere:
											// $('input:submit').removeAttr('disabled'); 
										}
									});
						});
						function stepJump() {
							
							document.getElementById('step2').style.display = "none";
							document.getElementById('step3').style.display = "none";
							document.getElementById('step4').style.display = "none";
							document.getElementById('step5').style.display = "none";
							document.getElementById('directJumpToUpload').style.display = "block";
						}

						function backToStep2() {
							document.getElementById('directJumpToUpload').style.display = "none";
							document.getElementById('step4').style.display = "none";
							document.getElementById('step3').style.display = "none";
							document.getElementById('step2').style.display = "block";
						}

						function hideMainDiv() {
							document.getElementById('step1').style.display = "none";
						}

						function displaySearchForm() {

							if (document.getElementById('raw').checked) {
								jQuery("#step5").show();
								document.getElementById('step1').style.display = "block";
								document.getElementById('step2').style.display = "none";
								document.getElementById('step4').style.display = "none";
								document.getElementById('step5').style.display = "none";
								document.getElementById('step6').style.display = "none";
								jQuery("#phenotypes").hide();
								document.getElementById('desc').innerHTML = "Description:";

								var element = document
										.getElementById('cellline');
								element.value = "CM";

							} else if (document.getElementById('processed').checked) {

								document.getElementById('step1').style.display = "block";
								document.getElementById('step2').style.display = "none";
								document.getElementById('step4').style.display = "none";
								jQuery("#step5").hide();
								//document.getElementById('step5').style.display = "none";
								document.getElementById('step6').style.display = "none";
								document.getElementById('desc').innerHTML = " Please select a phenotype:";
								jQuery("#ta").hide();
								jQuery("#phenotypes").show();
								var element = document
										.getElementById('cellline');
								element.value = "CM";

							}

						}
					</script>
</head>

<body onload="hideMainDiv()">
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

		<!-- Top content -->
		<!-- <div class="top-content"> -->

		<!--  <div class="inner-bg"> -->

		<div id="page-wrapper">
			<c:if test="${param.success eq 1}">
				<script type="text/javascript">
					window.location = "/CAT-APP-PROJECT//Success"
				</script>
				<div class="page-alerts">
					<div class="alert alert-success">
						<button type="button" class="close">
							<i class="glyphicon glyphicon-remove-circle text-success"></i>
						</button>
						<h4>Success</h4>
						<p>The file was uploaded successfully</p>
					</div>
				</div>
			</c:if>
			<c:if test="${param.failure eq 2}">
				<div class="alert alert-danger">
					<button href="#" type="button" class="close">
						<i class="glyphicon glyphicon-remove-circle text-danger"></i>
					</button>
					<h4>Danger Alert</h4>
					<p>
						Action has caused an error. Additional information <a
							class="alert-link" href="">Click Here</a> to read
					</p>
				</div>
				<div>failure</div>
			</c:if>

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Upload Files in CAT-APP</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<form role="form" action="SaveFileFormServlet" method="post"
						class="registration-form" enctype="multipart/form-data">
						<div class="panel panel-default">
							<div class="panel-heading" id="selectFileTypeDiv">
								Select File Type <label class="radio-inline"> <input
									type="radio" name="fileRadio" id="raw" value="raw"
									onchange="displaySearchForm()">Raw Data </label> <label
									class="radio-inline"> <input type="radio"
									name="fileRadio" id="processed" value="processed"
									onchange="displaySearchForm()">Processed Data</label>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6">

										<div class="form-group" id="step1">
											<label> Please select a cell line:</label> <select
												name="cellline" id="cellline" onchange="getAssayNames()">
												<option value="CM">1. iCell® Cardiomyocytes</option>
												<option value="HEP">2. iCell® Hepatocytes 2.0</option>
												<option value="ENDO">3. iCell Endothelial Cells</option>
												<option value="HUVEC">4. Human Umbilical Vein
													Endothelial Cells</option>
												<option value="Neur">5. iCell Neurons</option>
												<option value="Macro ">6. iCell Macrophages</option>
												<option value="A375">7. A-375 Skin Melanoma</option>
												<option value="A549">8. A549 Lung Carcinoma</option>
												<option value="HepG2">9. HepG2 Hepatocyte Carcinoma
												</option>
												<option value="HepaRG">10. HepaRG Hepatocyte
													Carcinoma</option>
												<option value="MCF7">11. MCF7 Breast Adenocarcinoma
												</option>
												<option value="HT29">12. HT-29 Colon Adenocarcinoma
												</option>
												<option value="LN229">13. LN-229 Glioblastoma</option>
												<option value="HEK10205f">14. HEK10205f Human
													Epidermal Keratinocytes; Foetal</option>
												<option value="HLMVEC">15. HLMVEC Human Lung
													Microvascular Endothelial Cells</option>
												<option value="HMePC">16. HMePC Human Mammary
													Epithelial Cell</option>
												<option value="SH-SY5Y">17. SH-SY5Y Neuroblastoma</option>
											</select> <a href="#" onclick="getAssayNames()"><span
												class="fa fa-chevron-right"></span></a>

											<!--  <button type="submit">submit</button>-->
										</div>



										<!--  ${assay}-->
										<div id="step2" style="display: none" class="form-group">
											<div id="CM_assays" class="all_assays" style="display: none;">
												<label>Please select an assay name:</label> <select
													id='CM_assay_select' onchange='selectTimePoint()'>
													<option value='Ca2'>Ca2+ flux</option>
													<option value='Hoechst'>Nuclei staining</option>
													<option value='Mito'>Mitochondrial Integrity</option>
													<option value='Seq'>TempOseq</option>
												</select> <a href="#" onclick="selectTimePoint()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="HEP_assays" class="all_assays form-group"
												style="display: none;">
												<label>Please select an assay name:</label> <select
													id='HEP_assay_select' onchange='selectTimePoint()'>
													<option value='Hoechst'>Nuclei staining</option>
													<option value='Mito'>Mitochondrial Integrity</option>
													<option value='CalceinAM'>Cell Viability</option>
													<option value='LipidTOX'>Lipid Accumulation</option>
													<!-- <option value='Seq'>TempOseq</option> -->
												</select> <a href="#" onclick="selectTimePoint()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="ENDO_HUV_assays" class="all_assays form-group"
												style="display: none; float: left;">
												<label>Please select an assay name:</label> <select
													id='ENDO_HUV_assay_select' onchange='selectTimePoint()'>
													<option value='Cyto'>Cyto</option>
													<option value='CTG'>ATP Content</option>
													<option value='TubForm'>Tube Formation</option>
													<option value='Seq'>TempOseq</option>
												</select> <a href="#" onclick="selectTimePoint()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="Neur_assays" class="all_assays form-group"
												style="display: none; float: left;">
												<label>Please select an assay name:</label> <select
													id='Neur_assay_select' onchange='selectTimePoint()'>
													<option value='Neur'>Neurons</option>
													<option value='CTG'>ATP Content</option>
													<option value='Seq'>TempOseq</option>
												</select> <a href="#" onclick="selectTimePoint()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="Macro_assays" class="all_assays form-group"
												style="display: none; float: left;">
												<label>Please select an assay name:</label> <select
													id='Macro_assay_select' onchange='selectTimePoint()'>
													<option value='Hoechst'>Nuclei staining</option>
													<option value='Mito'>Mitochondrial Integrity</option>
													<option value='CalceinAM'>Cell Viability</option>
													<option value='Phag'>Phagocytosis</option>
													<option value='Cyto'>Cytokines</option>
													<option value='MacroOut'>Macroite Outgrowth</option>
													<option value='Seq'>TempOseq</option>
												</select> <a href="#" onclick="selectTimePoint()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="England_assays" class="all_assays form-group"
												style="display: none; float: left;">
												<label>Please select an assay name:</label> <select
													id='England_assay_select' onchange='selectTimePoint()'>
													<option value='CMFDA'>Cell membrane integrity</option>
													<option value='ROS'>Reactive Oxygen Species</option>
													<option value='CASP'>Apoptosis</option>
													<option value='PROT'>Protein synthesis inhibition</option>
													<option value='ATP'>ATP Quantitation Assay</option>
												</select> <a href="#" onclick="selectTimePoint()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
										</div>




										<div id="step4" class="form-group" style="display: none;">
											<div id="timepoints_4" class="all_time_div" style="display:;">

												<label>Please select a time point:</label> <select
													id="timepoints_4_select" onchange="step4_to_5()">
													<option value="90min">90 minutes</option>
													<option value="24hr">24 hours</option>
												</select> <a href="#" onclick="step4_to_5()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="timepoints_2" class="all_time_div" style="display:;">

												<label>Please select a time point:</label> <select
													id="timepoints_2_select" onchange="step4_to_5()">
													<option value="90min">90 minutes</option>
													<option value="24hr">24 hours</option>
												</select> <a href="#" onclick="step4_to_5()"><span
													class="fa fa-chevron-right"></span></a>

											</div>
											<div id="timepoints_0" class="all_time_div"
												style="color: LightSteelBlue; display:;">
												<label>Time point not applicable</label> <select>
													<option>N/A</option>
													<option>N/A</option>
												</select> <a href="#"><span class="fa fa-chevron-right"
													style="color: LightSteelBlue;"></span></a>

											</div>
											<div id="timepoints_18" class="all_time_div"
												style="color: LightSteelBlue; display:;">
												<label style="color: LightSteelBlue;">Time point: 18
													hours</label> <a href="#"><span class="fa fa-chevron-right"
													style="color: LightSteelBlue;"></span></a>

											</div>
											<div id="timepoints_24" class="all_time_div"
												style="color: LightSteelBlue; display:;">
												<label style="color: LightSteelBlue;">Time point: 24
													hours</label> <a href="#"><span class="fa fa-chevron-right"
													style="color: LightSteelBlue;"></span></a>

											</div>
											<div id="timepoints_72" class="all_time_div"
												style="color: LightSteelBlue; display:;">
												<label style="color: LightSteelBlue;">Time point: 72
													hours</label> <a href="#"><span class="fa fa-chevron-right"
													style="color: LightSteelBlue;"></span></a>

											</div>
											<div id="timepoints_48" class="all_time_div"
												style="color: LightSteelBlue; display:;">
												<label style="color: LightSteelBlue;">Time point: 48
													hours</label> <a href="#"><span class="fa fa-chevron-right"
													style="color: LightSteelBlue;"></span></a>

											</div>
										</div>
										<!-- end of step4 -->
										<!-- end of step4 -->



										<div id="step5" class="form-group"
											style="display: none; float: left;">
											<div class="form-group" id="dilution_1"
												onchange="step5_to_6()">
												<label>Select a concentration: </label> <input type="radio"
													name="dilution" id="11" value="x1" /> 1x
												&nbsp;&nbsp;&nbsp; <input type="radio" name="dilution"
													id="21" value="x10" /> 10x &nbsp;&nbsp;&nbsp; <input
													type="radio" name="dilution" id="31" value="x100" /> 100x
												&nbsp;&nbsp;&nbsp; <input type="radio" name="dilution"
													id="41" value="x1000" /> 1000x <a href="#"
													onclick="step5_to_6()" id="dilution_button"> <span
													class="fa fa-chevron-right"></span></a>


											</div>
											<div class="form-group" id="dilution_0"
												style="color: LightSteelBlue; display: none;">
												<label class="sr-only" for="form-facebook">Dilution
													not applicable</label> <label style="color: LightSteelBlue;">
													Dilution not applicable: &nbsp;</label> <a href="#"><span
													class="fa fa-chevron-right" style="color: LightSteelBlue;"></span></a>


											</div>
										</div>


										<div id="step6" class="form-group" style="display: none;">
											<div>
												<label id="desc">Description:</label>
												<textarea id="ta" class="form-control" name="desc" rows="3"></textarea>
												<select
													id='phenotypes' name ='phenotypes'>
													<option value='0'>---Select One---</option>
													
												</select> 
												
											</div>
											<br></br>
											<div>
												<label>Please Choose a file to upload:</label> <input
													type="file" id="uploadfile" name="file" size="40"
													onclick="validateFile()"> </input> <span
													class="pull-center">
													<div style="padding-top: 20px;">
														<button type="submit" name="sequencesave"
															id="sequencesave" class="btn btn-primary">Upload</button>
													</div>
												</span>

											</div>


										</div>
										
										
									
					</form>
				</div>
			</div>
		</div>
	</div>



	<br></br>
	<div id="not shown" style="display: none;">
		=====================================================================

		<form class="form-group" action="ToDatabaseServlet" method="post"
			class="registration-form" enctype="multipart/form-data">


			<div id="step6-2">
				<div>
					<label>Upload directly to a database:</label>

				</div>

				<div>
					<label style="position: absolute; left: 5px;">Please Choose
						a file to upload: </label> <input type="file" id="uploadfile-2"
						name="file_2" size="40" onclick="validateFile()"></input>

				</div>

				<div
					style="position: absolute; text-align: center; background-color:; margin: auto; margin-top: 80px; margin-left: 45%;">

					<button type="submit" name="sequencesave" id="sequencesave"
						class="btn btn-submit">Upload directly to a database</button>

				</div>


			</div>

		</form>
	</div>
	<!-- end of div ""not shown -->

	</div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
	</div>


	<!-- Javascript -->




	<script src="/CAT-APP-PROJECT/resources/js/Uploadjs.js"></script>
	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>

	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>



	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

	<!-- METISMENU SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>




	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>


</html>
