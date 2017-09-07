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


.scrollerdiv {
  height: 150px; 
  overflow-y: scroll; 
}
</style>



<script type="text/javascript">
	function displayForm() {
		if (document.getElementById('asIsFiles').checked) {
			document.getElementById('downloadByAsIsFiles').style.display = "block";
		
			/* //alert("Hello");
			
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "none";
 */
		} else if (document.getElementById('byMC').checked) {
			/* //alert("Hii");
			document.getElementById('downloadByAsIsFiles').style.display = "none";
			document.getElementById('downloadByComp').style.display = "block";
			document.getElementById('downloadByCellLine').style.display = "none";
			
			//reset */


		} else if (document.getElementById('byCN').checked) {
			/* //alert("heyy");
			document.getElementById('downloadByAsIsFiles').style.display = "none";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "block";
			
			//reset
 */
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

						<div class="panel-heading" id="selectDownloadTypeDiv">
							<label class="radio-inline"> <input type="radio"
								name="fileRadio" id="asIsFiles" value="asIsFiles"
								onchange="displayForm()">Download uploaded files
							</label> <label class="radio-inline"> <input type="radio"
								name="fileRadio" id="byMC" value="byMC"
								onchange="displayForm()">Download by molecular class
							</label>
							 <label class="radio-inline"> <input type="radio"
								name="fileRadio" id="byCN" value="byCN"
								onchange="displayForm()">Download by carbon number
							</label>
						</div>
					<div class="row">	
					<div class="col-lg-12" id="downloadByAsIsFiles" class="form-group"
							style="display: none;">
					<form action="DownloadConcaweAsIs" method="post">
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="row">
									<div class="col-lg-6">

							<div class="input-group custom-search-form" >
							<label> Please select a cas number:</label><input type="text" class="form-control" id="chemsearch"
								onkeyup="filterchem()" placeholder="Search Cas Number...">

						</div> <!-- /input-group -->
					</li>
							&nbsp;
							<div class=" scrollerdiv pre-scrollable" >
							<table class="table table-hover,table-fixed">
							<thead style="display:block; border-color:#e67e22;">
							<tr>
							<th> Cas Number    ||  Name    || Category
							</th>
							</tr>
							</thead>
							<c:forEach var="item" items="${casnumber}">
								<tr class="chemical"><td><input
									type="radio" name="cas" id="cas"
									value=<c:out value="${item.key}"/>> &nbsp;<c:out
										value="${item.value}" /> </td></tr>
		
								</li>
							</c:forEach>
							</table>
							</div>

										<!-- <div class="form-group" id="sampleNumber">
											<label> Please enter a sample value</label> <input
												type="text" name="10" id="sample"></input>
										</div> -->







										<br></br>
										<div>

											<div style="padding-top: 20px;">
												<button type="submit" name="sequencesave" id="sequencesave"
													class="btn btn-primary">Download</button>
											</div>


										</div>


									</div>
					</form>
				</div>
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
