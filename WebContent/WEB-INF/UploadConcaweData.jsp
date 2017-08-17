
<!DOCTYPE html>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">



<head>

<title>Concawe</title>
<link rel="shortcut icon"
	href="/CAT-APP-PROJECT/resources/images/logo2.ico" />
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<!--    <link rel="stylesheet" href="/database_project/scripts/assets/bootstrap/css/bootstrap.min.css"> -->
<link rel="stylesheet"
	href="resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/form-elements.css">
<link rel="stylesheet" href="resources/css/style.css">
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">
<!--   <link href="/CAT-APP-PROJECT/resources/css/custom.css" rel="stylesheet" /> -->
<link href="/CAT-APP-PROJECT/resources/css/full-width-pics.css"
	rel="stylesheet">
<link href="/CAT-APP-PROJECT/resources/css/font-awesome.css"
	rel="stylesheet" />
<link rel="shortcut icon" href="resources/images/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="resources/images/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="resources/images/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="resources/images/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="resources/images/apple-touch-icon-57-precomposed.png">

</head>
<style type="text/css">
.scrollable {
	height: 300px; /* or any value */
	overflow-y: auto;
}
/*  body {
    background-image: url("/CAT-APP-PROJECT/resources/images/Catapp_logo_full.svg");
 -webkit-background-size: 250%;
    -moz-background-size: 250%;
    -o-background-size: 250%;
     background-size: 250%;
  
} */
</style>
<body>
	<script type="text/javascript">
   
    function stepJump(){
    	
    	//var step1Value='<c:out value="${sessionScope.cellLine}" />';
    	//var step2Value='<c:out value="${cellline.value}"/>';
    	//alert(step1Value);
    	//alert(step2Value);
    	document.getElementById('step2').style.display="none";
    	document.getElementById('step3').style.display="none";
    	document.getElementById('step4').style.display="none";
    	document.getElementById('step5').style.display="none";
    	document.getElementById('directJumpToUpload').style.display="block";
    }
    
    function backToStep2(){
    	document.getElementById('directJumpToUpload').style.display="none";
    	document.getElementById('step4').style.display="none";
    	document.getElementById('step3').style.display="none";
    	document.getElementById('step2').style.display="block";
    }
    
    </script>
	<jsp:include page="headerUserHome.jsp" />
	<!-- 	<div align="right"><a href = "/database_project/Protected/ID_home">
		<img src="/database_project/scripts/images/MB__home.png"/></a>&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;</div> -->
	<!-- Top menu -->
	<!--  -->
	<!-- 
		<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					 <a class="navbar-brand" >Upload Files</a> 
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
	<!-- <div class="collapse navbar-collapse" id="top-navbar-1">
					
				</div>
			</div>
		</nav> -->

	<!-- Top content -->
	<!-- <div class="top-content"> -->

	<!--  <div class="inner-bg"> -->
	<div class="container">
		<c:if test="${param.success eq 1}">
			<div id="messagebox" class="page-alerts">
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
				<button href="#" type="button" class="close"></button>
				<h4>Validation Failed</h4>
				<p>
					The uploaded file has some issues. Additional information <a
						class="alert-link" href="#myModal" data-toggle="modal">Click
						Here</a> to read
				</p>
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
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="closemessageheader()">Close</button>
					</div>
				</div>

			</div>
		</div>



		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 text">
				<h1>
					<strong>UPLOAD</strong> Files in Analytical Data
				</h1>
				<div class="description">
					<p>
						Give us ConcaweID,SampleID and CasNumber and you are <strong>good</strong>
						to go!
					</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box"
				style="margin-top: -50px">

				<form role="form" action="SaveAndValidateConcaweData" method="post"
					class="registration-form" enctype="multipart/form-data">

					<fieldset>
						<div class="form-top ">
							<div class="form-top-left">
								<h3>Step 1 / 4:</h3>
								<h4>Input ConcaweID</h4>
								<!-- <h4>Cell Type:</h4><p>Somewhere to Start &#9786;</p> -->
								<div class="progress">
									<div
										class="progress-bar progress-bar-warning progress-bar-striped"
										role="progressbar" aria-valuenow="10" aria-valuemin="0"
										aria-valuemax="100" style="width: 35%">0%(start)</div>
								</div>

							</div>

						</div>
						<div class="form-bottom scrollable ">
							<div class="form-group">

								<c:forEach var="concawe" items="${concawe}">

									<input type="radio" name="concawe"
										id=<c:out value="${concawe.value}"/>
										value=<c:out value="${concawe.key}"/>> &nbsp;<b><c:out
											value="${concawe.value}" /></b>
									<br>
									<br>

								</c:forEach>



							</div>

							<button type="button" class="btn btn-next" id="concawenext">Next</button>
						</div>

					</fieldset>


					<fieldset id="step2">
						<div class="form-top">
							<div class="form-top-left">
								<h3>Step 2 / 4</h3>
								<h4>Match CAS Number</h4>
								<p>A step ahead &#x1f44d;</p>
								<div class="progress">
									<div
										class="progress-bar progress-bar-info progress-bar-striped"
										role="progressbar" aria-valuenow="25" aria-valuemin="0"
										aria-valuemax="100" style="width: 35%">25% Complete</div>

								</div>
								<!-- <p><font color="red"> Click upload if time point and phenotype are unavailable</font></p>
		                        		 -->
							</div>

						</div>
						<div class="form-bottom scrollable">
							<div class="form-group">
								<label class="sr-only" for="form-email">CAS NUMBERS</label>

								<c:forEach var="casnames" items="${cas}">

									<input type="radio" name="casnames"
										id=<c:out value="${casnames.value}"/>
										value=<c:out value="${casnames.key}"/>> &nbsp;<b><c:out
											value="${casnames.value}" /></b>
									<br>
									<br>

								</c:forEach>

							</div>

							<button type="button" class="btn btn-previous">Previous</button>
							<button type="button" class="btn btn-next" id="casnext"
								onClick="matchCasNumbers()">Next</button>
							<!-- OR
				                        <button type="button" class="btn btn-primary" onclick="stepJump()">Upload</button> -->
						</div>
					</fieldset>


					<fieldset id="step3">
						<div>
							<div class="form-top">
								<div class="form-top-left">
									<h3>Step 3 / 4</h3>
									<h4>Input SampleID</h4>
									<p>Bear with us &#x1f612;</p>
									<div class="progress">
										<div
											class="progress-bar progress-bar-info progress-bar-striped"
											role="progressbar" aria-valuenow="50" aria-valuemin="0"
											aria-valuemax="100" style="width: 35%">50% Complete</div>
									</div>
								</div>

							</div>
							<div class="form-bottom">
								<div class="form-group">
									<label class="sr-only" for="form-facebook">Sample Name</label>
									<input type="text" name="sample" id="sample" value="" />
								</div>

								<button type="button" class="btn btn-previous">Previous</button>
								<button type="button" class="btn btn-next" id="times">Next</button>

							</div>
						</div>
					</fieldset>


					<fieldset id="step4">
						<div>
							<div class="form-top">
								<div class="form-top-left">
									<h3>Step 4 / 4</h3>
									<h4>Upload Files:</h4>
									<p>Here you are,Finally!!!</p>
									<div
										class="progress-bar progress-bar-success progress-bar-striped"
										role="progressbar" aria-valuenow="100" aria-valuemin="0"
										aria-valuemax="100" style="width: 100%">100% Complete
										(Selection complete)</div>
								</div>

							</div>
							<div class="form-bottom">
								<div class="form-group">
									<label class="sr-only" for="form-facebook">Desc:</label> <input
										type="text" name="desc" id="desc" value=""
										placeholder="Enter File desc(Optional)"><br>
									<br>
									<br> <label class="sr-only" for="form-facebook">Upload
										File:</label> <input type="file" id="uploadfile" name="file" size="40">

								</div>

								<button type="button" class="btn btn-previous">Previous</button>
								<button type="submit" name="submitbutton" id="save" value="save"
									class="btn btn-submit">Upload</button>

							</div>
						</div>
					</fieldset>







				</form>

			</div>
		</div>
		<!-- </div> -->
		<!--  </div> -->

	</div>


	<!-- Javascript -->
	<script src="/CAT-APP-PROJECT/resources/js/Uploadjs.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/jquery-1.11.1.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/jquery.backstretch.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/retina-1.1.0.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/scripts.js"></script>





	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>