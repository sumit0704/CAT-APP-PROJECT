
<!DOCTYPE html>
<html lang="en">

<%-- <jsp:include page="../views/fragments/header.jsp" /> --%>

<head>

<title>CAT-APP</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<!--    <link rel="stylesheet" href="/database_project/scripts/assets/bootstrap/css/bootstrap.min.css"> -->
<link rel="stylesheet"
	href="resources/css/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/form-elements.css">
<link rel="stylesheet" href="resources/css/style.css">


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

<body>
	<!-- 	<div align="right"><a href = "/database_project/Protected/ID_home">
		<img src="/database_project/scripts/images/MB__home.png"/></a>&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;</div> -->
	<!-- Top menu -->
	<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#top-navbar-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">Upload Files</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="top-navbar-1"></div>
		</div>
	</nav>

	<!-- Top content -->
	<!-- <div class="top-content"> -->

	<!--  <div class="inner-bg"> -->
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 text">
				<h1>
					<strong>UPLOAD</strong> Files in CAT-APP
				</h1>
				<div class="description">
					<p>
						Give us Cell Types,Assay Name,Time Point, Phenotype name and Plate
						Id and you are <strong>good</strong> to go!
					</p>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-sm-offset-3 form-box">

				<form role="form" action="" method="post" class="registration-form">

					<fieldset>
						<div class="form-top">
							<div class="form-top-left">
								<h3>Step 1 / 5</h3>
								<h4>Tell us Cell Type:</h4>
								<p>Somewhere to Start &#9786;</p>
							</div>

						</div>
						<div class="form-bottom">
							<div class="form-group">

								<input type="radio" name="form-CellType1" id="form-CellType1"
									value="Macrophages" checked> dad<br> <input
									type="radio" name="form-CellType1" id="form-CellType2"
									value="Cardiomycytes"> Cardiomycytes<br> <input
									type="radio" name="form-CellType1" id="form-CellType3"
									value="Hepatocytes"> Hepatocytes

							</div>

							<button type="button" class="btn btn-next">Next</button>
						</div>
					</fieldset>

					<fieldset>
						<div class="form-top">
							<div class="form-top-left">
								<h3>Step 2 / 5</h3>
								<h4>Input Assay Name:</h4>
								<p>A step ahead &#x1f44d;</p>
							</div>

						</div>
						<div class="form-bottom">
							<div class="form-group">
								<label class="sr-only" for="form-email">Assay Name</label> <input
									type="text" name="form-email" placeholder="Assay..."
									class="form-email form-control" id="form-email">
							</div>

							<button type="button" class="btn btn-previous">Previous</button>
							<button type="button" class="btn btn-next">Next</button>
						</div>
					</fieldset>

					<fieldset>
						<div class="form-top">
							<div class="form-top-left">
								<h3>Step 3 / 5</h3>
								<h4>Select a Time Point:</h4>
								<p>Bear with us &#x1f612;</p>
							</div>

						</div>
						<div class="form-bottom">
							<div class="form-group">
								<label class="sr-only" for="form-facebook">Time-Point</label> <select
									id="tpdrop" name="timepoints">
									<option value="15 Min">15 Min</option>
									<option value="30 Min">30 Min</option>
									<option value="60 Min">60 Min</option>
									<option value="90 Min">90 Min</option>
								</select>
							</div>

							<button type="button" class="btn btn-previous">Previous</button>
							<button type="button" class="btn btn-next">Next</button>

						</div>
					</fieldset>

					<fieldset>
						<div class="form-top">
							<div class="form-top-left">
								<h3>Step 4 / 5</h3>
								<h4>Select Phenotype & Plate Id:</h4>
								<p>We are close &#x1f64c;</p>
							</div>


						</div>
						<div class="form-bottom">
							<div class="form-group">
								<label class="sr-only" for="form-facebook">Phenotype</label> <select
									id="phetype" name="timepoints">
									<option value="Phenotype1">Phenotype1</option>
									<option value="Phenotype2">Phenotype2</option>
									<option value="Phenotype3">Phenotype3</option>
									<option value="Phenotype4">Phenotype4</option>
								</select>
							</div>
							<div class="form-group">
								<label class="sr-only" for="form-facebook">Select a
									Plate</label> <input type="radio" name="form-Plate1" id="form-Plate1"
									value="Plate1" checked> Plate 1<br> <input
									type="radio" name="form-Plate1" id="form-Plate2" value="Plate2">
								Plate 2<br> <input type="radio" name="form-Plate1"
									id="form-Plate3" value="Plate3"> Plate 3 <br> <input
									type="radio" name="form-Plate1" id="form-Plate3" value="Plate3">
								Plate 4 <br>
							</div>

							<button type="button" class="btn btn-previous">Previous</button>
							<button type="button" class="btn btn-next">Next</button>
						</div>
					</fieldset>
					<fieldset>
						<div class="form-top">
							<div class="form-top-left">
								<h3>Step 5 / 5</h3>
								<h4>Upload Files:</h4>
								<p>Here you are,Finally!!!</p>
							</div>

						</div>
						<div class="form-bottom">
							<div class="form-group">
								<label class="sr-only" for="form-facebook">Upload File:</label>
								<input type="file" id="uploadfile" name="file" size="40">
								<input type="hidden" name="hiddenfield" id="hideval" value="">
							</div>

							<button type="button" class="btn btn-previous">Previous</button>
							<button type="button" class="btn btn-submit"
								onclick="javascript:upload();">Upload</button>

						</div>
					</fieldset>

				</form>

			</div>
		</div>
		<!-- </div> -->
		<!--  </div> -->

	</div>


	<!-- Javascript -->
	<script src="resources/js/Uploadjs.js"></script>
	<script src="resources/js/jquery-1.11.1.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.backstretch.min.js"></script>
	<script src="resources/js/retina-1.1.0.min.js"></script>
	<script src="resources/js/scripts.js"></script>


	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>