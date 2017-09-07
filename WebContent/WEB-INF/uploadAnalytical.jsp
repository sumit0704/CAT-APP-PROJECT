<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.catapp.entity.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" /> -->
<title>Analytical Upload</title>

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
                <div id="messagebox" class="page-alerts">
     			<div class="alert alert-success">
      			<button  type="button" class="close"></button>
      			<h4>Success</h4>
      			<p>The file was uploaded successfully</p>
    			</div>
    			</div>
				</c:if>
				<c:if test="${param.failure eq 2}">
				 <div class="alert alert-danger">
      			<button  href="#" type="button"  class="close"></button>
      			<h4>Validation Failed</h4>
      			<p>The uploaded file has some issues. Additional information <a class="alert-link" href="#myModal" data-toggle="modal">Click Here</a> to read</p>
   				 </div>
				</c:if>
				
				
				<div id="myModal" class="modal fade" role="dialog">
					  <div class="modal-dialog">
					
					    <!-- Modal content-->
					    <div class="modal-content">
					      <div class="modal-header">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Errors & Warnings</h4>
					      </div>
					      <div class="modal-body">
					        	<c:forEach var="errorkey" items="${errormap}">
											 
    							<c:forEach var="errorarray" items="${errorkey.value}">
    							
    							<div>${errorarray}</div>
    							
    							</c:forEach>	 
								</c:forEach>
					      </div>
					      <div class="modal-footer">
					        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closemessageheader()">Close</button>
					      </div>
					    </div>
					
					  </div>
					</div>


			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Upload files in Analytical</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<form role="form" action="SaveAndValidateConcaweData" method="post"
						class="registration-form" enctype="multipart/form-data">
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
							&nbsp;

										<div class="form-group" id="sampleNumber">
										<label> Please enter a sample value</label>
											<input type="text" name="sample" id="sample"></input>
										</div>







										<br></br>
										<div>
											<label>Please Choose a file to upload:</label> <input
												type="file" id="uploadfile" name="file" size="40"
												onclick="validateFile()"> </input> <span class="pull-center">
												<div style="padding-top: 20px;">
													<button type="submit" name="sequencesave" id="sequencesave"
														class="btn btn-primary">Upload</button>
												</div>
											</span>

										</div>


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
