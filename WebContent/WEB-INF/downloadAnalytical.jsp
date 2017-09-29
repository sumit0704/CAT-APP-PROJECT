<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.catapp.entity.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> -->
<title>Analytical Download</title>

<link rel="shortcut icon"
	href="/CAT-APP-PROJECT/resources/images/logo2.ico" />

<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet" />


<!-- MetisMenu CSS -->
<link href="/CAT-APP-PROJECT/resources/css/metisMenu.min.css"
	rel="stylesheet" />

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/sb-admin-2.css"
	rel="stylesheet" />

<link href="/CAT-APP-PROJECT/resources/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom Fonts -->
<link
	href="/CAT-APP-PROJECT/resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="resources/images/favicon.png" />
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

.scrollerdiv {
	height: 150px;
	overflow-y: scroll;
}
</style>



<script type="text/javascript">
	function displayForm() {
		if (document.getElementById('asIsFiles').checked) {
			document.getElementById('downloadByAsIsFiles').style.display = "block";
			document.getElementById('downloadByMolecule').style.display = "none";
			document.getElementById('downloadByCarbon').style.display = "none";
			document.getElementById('button-box').style.display = "block";

		} else if (document.getElementById('byMC').checked) {
			//alert("Hii");
			document.getElementById('downloadByAsIsFiles').style.display = "none";
			document.getElementById('downloadByMolecule').style.display = "block";
			document.getElementById('button-box').style.display = "block";
			document.getElementById('downloadByCarbon').style.display = "none";

			//reset */

		} else if (document.getElementById('byCN').checked) {
			//alert("heyy");
			document.getElementById('downloadByAsIsFiles').style.display = "none";
			document.getElementById('downloadByMolecule').style.display = "none";
			document.getElementById('downloadByCarbon').style.display = "block";
			document.getElementById('button-box').style.display = "block";

		}
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

	}
</script>
</head>

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
						<p>The file was downloaded successfully</p>
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
					<h1 class="page-header">Download Files in Analytical</h1>

				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<form action="DownloadConcaweAsIs" method="post">

							<div class="panel-heading" id="selectDownloadTypeDiv">


								<label class="radio-inline"> <input type="radio"
									name="fileRadio" id="asIsFiles" value="asIsFiles"
									onchange="displayForm()">Download AsIs </label> <label
									class="radio-inline"> <input type="radio"
									name="fileRadio" id="byMC" value="byMC"
									onchange="displayForm()">Download by Molecular Class </label> <label
									class="radio-inline"> <input type="radio"
									name="fileRadio" id="byCN" value="byCN"
									onchange="displayForm()">Download by Carbon Number </label>
							</div>

							<div class="row">
								<div class="col-lg-4 col-md-offset-3" id="downloadByAsIsFiles"
									class="form-group" style="display: none;">



									<div class="input-group custom-search-form">
										<label> Please select a cas number:</label> <input type="text"
											class="form-control" id="chemsearch" onkeyup="filterchem()"
											placeholder="Search Cas Number..." />

									</div>

									<div class=" scrollerdiv pre-scrollable">
										<table class="table table-hover,table-fixed">
											<thead style="display: block; border-color: #e67e22;">
												<tr>
													<th>Cas Number || Name || Category</th>
												</tr>
											</thead>
											<c:forEach var="item" items="${casnumber}">
												<tr class="chemical">
													<td><input type="radio" name="cas" id="cas"
														value=<c:out value="${item.key}"/>> &nbsp;<c:out
																value="${item.value}" /></td>
												</tr>


											</c:forEach>
										</table>
									</div>

								</div>
								<div class="col-lg-4 col-md-offset-3" id="downloadByMolecule"
									class="form-group" style="display: none;">

									<label> Please select a molecular class:</label> <select
										name="molecularSelect" id="molecularSelect">
										<c:forEach var="item" items="${molecular}">
											<option value="${item.key}">${item.value}</option>
										</c:forEach>
									</select>

									<div class="form-group">
										<label> Please select one or more chemicals:</label> <select
											 name="chemicalSelect" id="chemicalSelect" multiple="multiple">
											<c:forEach var="item" items="${casnumber}">
												<option value="${item.key}">${item.value}</option>
											</c:forEach>
										</select>
									</div>


								</div>

								<div class="col-lg-4 col-md-offset-3" id="downloadByCarbon"
									class="form-group" style="display: none;">
									
									<label> Please select a carbon number:</label> <select
										name="carbonSelect" id="carbonSelect">
										
											<option value="<5"><5</option>
											<option value="5.0">5</option>
											<option value="6.0">6</option>
											<option value="7.0">7</option>
											<option value="8.0">8</option>
											<option value="9.0">9</option>
											<option value="10.0">10</option>
							
											<option value="11.0">11</option>
											<option value="12.0">12</option>
											<option value="13.0">13</option>
											<option value="14.0">14</option>
											<option value="15.0">15</option>
											<option value="16.0">16</option>
											<option value="17.0">17</option>
											<option value="18.0">18</option>
											<option value="19.0">19</option>
											<option value="20.0">20</option>
											<option value="21.0">21</option>
											<option value="22.0">22</option>
											<option value="23.0">23</option>
											<option value="24.0">24</option>
											<option value="25.0">25</option>
											<option value="26.0">26</option>
											<option value="27.0">27</option>
											<option value="28.0">28</option>
											<option value="29.0">29</option>
											<option value="30.0">30</option>
											<option value="31.0">31</option>
											
										
									</select>
									
									<div class="form-group">
										<label> Please select one or more chemicals:</label> <select
											 name="chemicalSelectCarbon" id="chemicalSelectCarbon" multiple="multiple">
											<c:forEach var="item" items="${casnumber}">
												<option value="${item.key}">${item.value}</option>
											</c:forEach>
										</select>
									</div>
									
									</div>
							<div class="row">
							<div  id="button-box"  class="col-lg-4 col-md-offset-3" style="padding-top: 20px; display: none;">
								<button type="submit" name="sequencesave" id="sequencesave"
									class="btn btn-primary">Download</button>

							</div>
							</div>
							<div class="row">
								&nbsp;
							</div>


						</form>
					</div>
				</div>
			</div>
		</div>



		<!--<jsp:include page="footer.jsp" />


	<!-- Javascript -->




		<script src="/CAT-APP-PROJECT/resources/js/Uploadjs.js"></script>
		<!--  
	<script src="/CAT-APP-PROJECT/resources/js/jquery-1.11.1.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

	<script src="/CAT-APP-PROJECT/resources/js/retina-1.1.0.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/scripts.js"></script>

	jQuery
	<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>

	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
	METISMENU SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
	CUSTOM SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script> -->


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