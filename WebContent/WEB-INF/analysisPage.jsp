<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page session="false"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="com.catapp.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<head>
<title>Response Curves</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
<!-- <link rel="stylesheet" href="http://code.jquery.com/mobile/1.1.0/jquery.mobile-1.1.0.min.css" />
 -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
 -->
<!-- <link href="https://opensource.keycdn.com/fontawesome/4.7.0/font-awesome.min.css" rel="stylesheet" />
 -->
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

    <%@page import="java.io.File"%>
    <%@page import="java.io.IOException"%>
    <%@page import="java.awt.image.BufferedImage"%>
    <%@page import="javax.imageio.ImageIO"%>
    <%@page import="java.io.ByteArrayOutputStream"%>

    <%@page import="java.math.BigInteger"%>
    <%@page import="javax.xml.bind.DatatypeConverter"%>
    <%@page import="java.awt.image.BufferedImage"%>
    <%@page import="sun.misc.BASE64Decoder"%>
    <%@page import="java.awt.image.BufferedImage"%>
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
#wrapper {
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

	
</script>
</head>
<body>




	<!-- Left Menu -->
	<div id="wrapper">
		<%
			String isAdmin = ((User) request.getSession().getAttribute("user")).getIs_admin();
			if (null == isAdmin || "" == isAdmin.trim()) {
		%>
		<jsp:include page="header.jsp" />
		<%
			}
			if ("Y".equalsIgnoreCase(isAdmin)) {
		%>
		<jsp:include page="header.jsp" />
		<%
			}
		%>

		

			<!-- <div class="panel panel-default">
				<div class="panel-heading">Response Curves</div>
				.panel-heading
				<div class="panel-body"> -->

					<div class="row">
						<div class="col-lg-4">
							<div class="panel panel-primary">
								<div class="panel-heading">Select Data</div>
								<!-- .panel-heading -->
								<div class="panel-body">
									<div class="panel-group" id="accordion">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<h4 class="panel-title">

													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseOne" id="sac">Select a chemical</a>

												</h4>
											</div>
											<div id="collapseOne" class="panel-collapse collapse in">
												<div class="panel-body">
													<div class="input-group custom-search-form">
														<input type="text" class="form-control" id="chemsearch"
															onkeyup="filterchem()" placeholder="Search Cas Number...">
													</div>
													<div class=" scrollerdiv pre-scrollable">
														<table class="table table-hover,table-fixed">
															<thead style="display: block; border-color: #e67e22;">
																<tr>
																	<th>Cas Number || Name || Category</th>
																</tr>
															</thead>
															<c:forEach var="item" items="${chemicals}">
																<tr class="chemical">
																	<td><input type="radio" name="cas" id="cas"
																		onclick="displayChemicalData()"
																		value=<c:out value="${item.key}"/>> &nbsp;<c:out
																				value="${item.value}" /></td>
																</tr>

																</li>
															</c:forEach>
														</table>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-primary">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseTwo" id="sacl">Select a cell line</a><span class="glyphicon glyphicon-pushpin"></span>

												</h4>
											</div>
											<div id="collapseTwo" class="panel-collapse collapse">
												<div class="panel-body">
													<div class=" scrollerdiv pre-scrollable">
														<table class="table table-hover,table-fixed">

															<c:forEach var="item" items="${cell}">
																<tr class="cell">
																	<td><input type="radio" name="cell" id="cell"
																	onclick="displayPhenoData()"	value=<c:out value="${item.key}"/>> &nbsp; 
																	<c:out value="${item.value}" /></td>
																</tr>

																</li>
															</c:forEach>
														</table>
													</div>
												</div>
											</div>
										</div>


										<div class="panel panel-primary">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseThree">Select a phenotype</a>

												</h4>
											</div>
											<div id="collapseThree" class="panel-collapse collapse">
												<div class="panel-body">
													<div class=" scrollerdiv pre-scrollable">

														<table id="phenotable" class="table table-hover,table-fixed">

														</table>
													</div>
												</div>
											</div>
										</div>
										<div class="panel panel-primary">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" data-parent="#accordion"
														href="#collapseFour">Select a time point</a>
												</h4>
											</div>
											<div id="collapseFour" class="panel-collapse collapse">
												<div class="panel-body">
													<div class=" scrollerdiv pre-scrollable">
														<table id="timepointtable" class="table table-hover,table-fixed">

														</table>
													</div>
												</div>
											</div>
										</div>


									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-8">


							<div class="panel panel-primary">
								<div class="panel-heading">Analysis</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<!-- Nav tabs -->
									<ul class="nav nav-pills">
										<li id="home" class="active"><a href="#home-pills"
											data-toggle="tab" id="hp">Response Curve</a></li>
										<li id="chempr"><a href="#profile-pills" data-toggle="tab" id="chem">Chemical
												Properties</a></li>
										<li><a href="#messages-pills" data-toggle="tab" id="assay">Assay</a>
										</li>
										<li><a href="#settings-pills" data-toggle="tab" id="AD" onclick="finddoseresponse()">Assay
												Data</a></li>
										<li><a href="#credits-pills" data-toggle="tab" id="CD">Credit</a>
										</li>
									</ul>
									<div class="tab-content">
										<div class="tab-pane fade in active test" id="home-pills">
										<div id="img-div">
										</div>
										<div id="button-div">
									   
										</div>
										</div>
										<div class="tab-pane fade" id="profile-pills">
										
										</div>
										<div class="tab-pane fade" id="messages-pills">
											
										</div>
										<div class="tab-pane fade" id="settings-pills">
											<br> <p>Coming Soon!!</p>
										</div>
										<div class="tab-pane fade" id="credits-pills">
										<br>
										<p>The assays were conducted in Dr. Ivan Rusyn lab at Texas A&M University.
The R program for dose-response curves was written by Dr. Fred Wright at the North Carolina State University, and minor modifications were made in Dr. Ivan Rusyn lab.</p>	
										</div>
									</div>
								</div>

							</div>

						<!-- </div> -->
					</div>
				</div>
			</div>
		
	


	<!-- <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		METISMENU SCRIPTS
		<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>

		</script>
		<script src="/CAT-APP-PROJECT/resources/js/Response/Response.js"></script>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

	<!--  -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

	<!-- CUSTOM SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/Response/Response.js"></script>
</body>


</html>