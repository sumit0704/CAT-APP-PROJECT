<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page import="com.catapp.entity.User"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>CAT-APP</title>
<link rel="shortcut icon"
	href="/CAT-APP-PROJECT/resources/images/logo2.ico" />
<!-- Bootstrap Core CSS -->
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/full-width-pics.css"
	rel="stylesheet">
<link href="/CAT-APP-PROJECT/resources/css/login.css" rel="stylesheet">
<link href="/CAT-APP-PROJECT/resources/css/circleAnimation.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<style type="text/css">
.bs-example {
	margin: 20px;
}

.read {
	overflow: hidden;
	font-size: 16px !important;
	transition: all 0.3s;
	margin-bottom: 10px;
}

.read-less {
	height: 40px !important;
}

#pswd_info {
	position: absolute;
	bottom: -75px;
	bottom: -115px\9; /* IE Specific */
	right: 55px;
	width: 250px;
	padding: 15px;
	background: #fefefe;
	font-size: .875em;
	border-radius: 5px;
	box-shadow: 0 1px 3px #ccc;
	border: 1px solid #ddd;
}

#pswd_info h4 {
	margin: 0 0 10px 0;
	padding: 0;
	font-weight: normal;
}

#pswd_info::before {
	content: "\25B2";
	position: absolute;
	top: -12px;
	left: 45%;
	font-size: 14px;
	line-height: 14px;
	color: #ddd;
	text-shadow: none;
	display: block;
}

.invalid {
	background: url(/CAT-APP-PROJECT/resources/images/cross.png) no-repeat 0
		50%;
	padding-left: 22px;
	line-height: 24px;
	color: #ec3f41;
}

.valid {
	background: url(/CAT-APP-PROJECT/resources/images/tick.png) no-repeat 0
		50%;
	padding-left: 22px;
	line-height: 24px;
	color: #3a7d34;
}

#pswd_info {
	display: none;
}

#result {
	margin-left: 5px;
}

#register .short {
	color: #FF0000;
}

#register .weak {
	color: #E66C2C;
}

#register .good {
	color: #2D98F3;
}

#register .strong {
	color: #006400;
}

.hint {
	display: none;
	color: gray;
	font-style: italic;
}

input:focus+.hint {
	display: inline;
}

select#secqu, select#soflow-color {
	-webkit-appearance: button;
	-webkit-border-radius: 2px;
	-webkit-box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.1);
	-webkit-padding-end: 20px;
	-webkit-padding-start: 2px;
	-webkit-user-select: none;
	background-image: url(http://i62.tinypic.com/15xvbd5.png),
		-webkit-linear-gradient(#FAFAFA, #F4F4F4 40%, #E5E5E5);
	background-position: 97% center;
	background-repeat: no-repeat;
	border: 1px solid #AAA;
	color: #555;
	font-size: inherit;
	/*margin: 20px;*/
	overflow: hidden;
	padding: 5px 10px;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 550px;
}

.jumbotron {
	background: url('/CAT-APP-PROJECT/resources/images/1.jpg') no-repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	width: 100%;
}

.jumbotroon {
	background: url('/CAT-APP-PROJECT/resources/images/2.jpg') no-repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	width: 100%;
}

.panel-default {
	opacity: 0.9;
	margin-top: 30px;
}

.form-group.last {
	margin-bottom: 0px;
}

.images {
	display: inline;
	margin: 0px;
	padding: 0px;
	vertical-align: middle;
	width: 200px;
}

#content {
	display: block;
	margin: 0px;
	padding: 0px;
	position: relative;
	top: 90px;
	height: auto;
	max-width: auto;
	overflow-y: hidden;
	overflow-x: auto;
	word-wrap: normal;
	white-space: nowrap;
}

body {
	background-image:
		url("/CAT-APP-PROJECT/resources/images/Catapp_logo_full.svg");
	-webkit-background-size: 250%;
	-moz-background-size: 250%;
	-o-background-size: 250%;
	background-size: 250%;
}
</style>
</head>

<body>

	<!-- Navigation -->
	<%
String login_msg=(String)request.getAttribute("error");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>

	<!-- Full Width Image Header with Logo -->
	<!-- Image backgrounds are set within the full-width-pics.css file. -->
	<div class="container">
		<header class="jumbotron" style="margin-top: 15px">


			<div class="container">
				<h2 align="center"
					style="font-size: 2.2em; font-family: 'Tahoma'; text-shadow: 2px 2px white;">
					<font color="Black">Category Read-across Approaches For
						(petroleum) UVCBs</font>
				</h2>
				<div class="row">

					<div class="col-md-8 ">
						<div class="row">

							<img class="img-responsive"
								src="/CAT-APP-PROJECT/resources/images/CATAPP_Pres.jpg" alt=""
								width="30%" height="20%"
								style="margin-left: 30%; margin-top: 5%">

						</div>
						<div class="row">

							<div id="content">
								<div class="col-md-4 col-sm-6">
									<img src="/CAT-APP-PROJECT/resources/images/piechart.jpg"
										class=" img-circle " width="100" height="100"
										style="margin-left: 9%;" />
									<h3
										style="font-size: 1.6em; font-family: 'Tahoma'; text-shadow: 2px 2px white;">
										<span id="lblLrner">&nbsp;&nbsp;&nbsp;CAT-APP</span>
									</h3>
									<!-- <p><span id="lblLrnerTxt1">Collaborate with peers</span></p> -->
								</div>

								<div class="col-md-4 col-sm-6">
									<img src="/CAT-APP-PROJECT/resources/images/Oil.jpg"
										class=" img-circle " width="100" height="100"
										style="margin-left: 9%;" />
									<h3
										style="font-size: 1.6em; font-family: 'Tahoma'; text-shadow: 2px 2px white;">
										<span id="lblLrner">&nbsp;&nbsp;ANALYTICAL</span>
									</h3>
									<!-- <p style="font-size: 1.2em; color:#999"><span id="lblLrnerTxt1">Analyze the Data</span></p> -->
								</div>
								<div class="col-md-4 col-sm-6">
									<img src="/CAT-APP-PROJECT/resources/images/leaf.png"
										class=" img-circle " width="100" height="100"
										style="margin-left: 9%;" />
									<h3
										style="font-size: 1.6em; font-family: 'Tahoma'; text-shadow: 2px 2px white;">
										<span id="lblLrner">&nbsp;&nbsp;&nbsp;ECO-TOX</span>
									</h3>
									<!-- <p><span id="lblLrnerTxt1">Plot Graphs</span></p> -->
								</div>
							</div>

						</div>

					</div>
					<div class="col-md-4 ">
						<div class="panel panel-default">
							<div class="panel-heading">
								<strong class="">Login</strong>

							</div>
							<div class="panel-body">
								<form action="LoginServlet" method="post">
									<div class="form-group">
										<input type="email" name="email" id="email"
											class="form-control input-lg" placeholder="Email Address"
											required="required">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="passwordLogin"
											class="form-control input-lg" placeholder="Password"
											required="required">
									</div>

									<div class="checkbox">
										<label><input type="checkbox" id="rememberMe">
											Remember me</label>
									</div>
									<input type="checkbox" name="remember_me" checked="checked"
										class="hidden">
									<!-- 
					<a href="#" class="btn btn-link pull-right">Forgot Password?</a>
					 -->
									<button type="button" class="btn btn-link pull-right"
										data-toggle="modal" data-target="#exampleModal"
										data-whatever="">Forgot Password?</button>






									<hr class="colorgraph">
									<div class="row">
										<div class="col-xs-6 col-sm-6 col-md-6">
											<input type="submit" class="btn btn-md btn-success btn-block"
												style="margin-left: 10px" value="Sign in">
										</div>
										<div class="col-xs-6 col-sm-6 col-md-6">
											<button type="reset" class="btn btn-md btn-success btn-block"
												style="margin-left: 10px" value="">Clear</button>
										</div>
									</div>
								</form>
							</div>
							<div class="panel-footer">
								Not Registered? <a href="#register" class=""
									onclick="showRegistration()">Register here</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</header>



		<!-- Heading Row -->

		<hr>

		<!-- Content Section -->
		<section id="login">



			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel">
				<div class="modal-dialog" role="document">
					<form action="RequestAccessServlet" method="post">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="" id="exampleModalLabel">Forget Password?</h4>
							</div>
							<div class="modal-body">

								<div class="form-group">
									<label for="recipient-name" class="control-label">Enter
										email address:</label> <input type="email" class="form-control"
										id="forgotEmail" name="forgotEmail">
								</div>


							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>

						</div>
					</form>
				</div>
			</div>

		</section>
	</div>

	<section class="jumbotroon" id="register">
		<div class="container" id="registration" style="display: none">

			<div class="row" style="margin-top: 20px">
				<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<form action="SubmittedRequestServlet" method="post" id="register"
						data-toggle="validator">
						<fieldset>
							<h2 align="center">REGISTER</h2>
							<h3 align="center">(*) Marked fields are mandatory.</h3>
							<hr class="colorgraph">


							<div class="form-group">
								<input type="text" id="first_name" name="first_name"
									class="form-control input-lg" placeholder="First Name *"
									required="required">
							</div>
							<div class="form-group">
								<input type="text" id="last_Name" name="last_Name"
									class="form-control input-lg" placeholder="Last Name">
							</div>


							<div class="form-group">
								<input type="email" id="email" name="email"
									class="form-control input-lg" placeholder="Email Address *"
									required="required">
							</div>
							<div class="form-group">
								<input type="text" id="phone_number" name="phone_number"
									maxlength="10" class="form-control input-lg"
									placeholder="Contact No">
							</div>

							<div class="form-group">
								<input type="text" id="institution" name="institution"
									class="form-control input-lg" placeholder="Institution">
							</div>


							<div class="form-group">
								<input type="text" id="supervisor_name" name="supervisor_name"
									class="form-control input-lg" data-provide="typeahead"
									placeholder="Supervisor  Name *" required="required">
							</div>
							<div class="form-group">
								<input type="text" id="supervisor_phone" name="supervisor_phone"
									class="form-control input-lg"
									placeholder="Supervisor ContactNo *" required="required"
									maxlength="10">
							</div>
							<div class="form-group">
								<input type="text" id="supervisor_email" name="supervisor_email"
									class="form-control input-lg" data-provide="typeahead"
									placeholder="Supervisor Email * " required="required">
							</div>
							<div class="form-group">

								<input type="password" id="password" name="password"
									class="form-control input-lg" placeholder="Desired Password *"
									required="required"> <span id="result"></span>
							</div>
							<span class="hint">Create a strong password with more than
								8 characters, lowercase, uppercase and special characters</span>
							<div class="form-group">
								<input type="password" id="repassword" name="repassword"
									class="form-control input-lg" placeholder="Re-enter Password *"
									required="required" onkeyup="checkPass(); return false;">
								<span id="confirmMessage" class="confirmMessage"></span>
							</div>




							<!-- 	<div class="form-group">
                    	<input type="email" name="sEmail" id="sEmail" class="form-control input-lg" placeholder="Supervisor Email Address">
				</div>
					<div class="form-group">
                    	<input type="number" name="sContactNo" id="sContactNo" class="form-control input-lg" placeholder="Supervisor Contact No">
				</div> -->

							<div class="form-group">
								<div class="dropdown">
									<!-- 
                		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" style="width:100%">Security question 1
                    		<span class="caret"></span>
                		</button> -->



									<select name="sec1" id="secqu">
										<option>Security Question 1 *</option>
										<c:forEach var="sec1" items="${secqu}">
											<option value="${sec1.key}">${sec1.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<input type="text" name="ans1" id="ans1"
									class="form-control input-lg" placeholder="Answer1 *"
									required="required">
							</div>

							<div class="form-group">
								<div class="dropdown">


									<select name="sec2" id="secqu">
										<option>Security Question 2 *</option>
										<c:forEach var="sec2" items="${secqu}">
											<option value="${sec2.key}">${sec2.value}</option>
										</c:forEach>
									</select>

								</div>
							</div>
							<div class="form-group">
								<input type="text" name="ans2" id="ans2"
									class="form-control input-lg" placeholder="Answer2 *"
									required="required">
							</div>
							<div class="form-group">
								<div class="dropdown">


									<select name="sec3" id="secqu">
										<option>Security Question 3 *</option>
										<c:forEach var="sec3" items="${secqu}">
											<option value="${sec3.key}">${sec3.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<input type="text" name="ans3" id="ans3"
									class="form-control input-lg" placeholder="Answer3 *"
									required="required">
							</div>
							<!-- <div class="form-group"> Select your project(s)
					<label class="checkbox-inline">
  						<input type="checkbox" id="catAppP" value="CAT-APP"> CAT-APP
					</label>
					<label class="checkbox-inline">
  						<input type="checkbox" id="analyticalP" value="Analytical"> Analytical
					</label>
					<label class="checkbox-inline">
  						<input type="checkbox" id="ecoToxP" value="ECO-TOX"> ECO-TOX
					</label>
				</div> -->


							<hr class="colorgraph">
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<input type="submit" class="btn btn-lg btn-success btn-block"
										value="Request Access" style="margin-left: 50%">
								</div>

							</div>


						</fieldset>
					</form>
				</div>
			</div>

		</div>

	</section>

	<!-- About Section -->
	<section class="jumbotroon" id="about">
		<div class="container">

			<div class="row">
				<div class="col-lg-12 text-center">
					<h2
						style="font-size: 2.8em; font-family: 'Tahoma'; text-shadow: 2px 2px white;">ABOUT
						US</h2>

				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-lg-4 col-lg-offset-2">
					<p
						style="text-align: justify; font-size: 1.2em; font-family: 'Tahoma';">One
						of the most challenging areas in regulatory toxicology is the
						assessment of UVCBs (Unknown or Variable composition, Complex
						reaction products and Biological materials). UVCBs present
						difficult problems for science-informed regulatory
						decisions.Regulators and registrants have a common interest to
						understand how to evaluate UVCBs under current chemical regulatory
						policy and ensure that there is not a large scale underestimation
						or overestimation of hazard.</p>
				</div>
				<div class="col-lg-4">
					<p
						style="text-align: justify; font-size: 1.2em; font-family: 'Tahoma';">This
						project tackles the challenge of UVCBs and will provide regulators
						and registrants with a cost-effective integrative approach to
						solve the challenges of UVCBs and will define the best practical
						strategies for the read-across and grouping approaches.</p>
				</div>

			</div>
		</div>
	</section>

	<!-- Contact Section -->
	<section id="contact" class="jumbotroon">
		<br>
		<br>
		<br>
		<br>
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">


					<h2
						style="font-size: 2.8em; font-family: 'Tahoma'; text-shadow: 2px 2px white;">Contact
						Us</h2>

				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2">
					<!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->
					<!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->
					<form name="sentMessage" id="contactForm" data-toggle="validator">
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Name</label> <input type="text" class="form-control"
									placeholder="Name" id="name" required
									data-validation-required-message="Please enter your name.">
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Email Address</label> <input type="email"
									class="form-control" placeholder="Email Address" id="email"
									required
									data-validation-required-message="Please enter your email address.">
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Phone Number</label> <input type="tel"
									class="form-control" placeholder="Phone Number" id="phone"
									required
									data-validation-required-message="Please enter your phone number.">
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<div class="row control-group">
							<div
								class="form-group col-xs-12 floating-label-form-group controls">
								<label>Message</label>
								<textarea rows="5" class="form-control" placeholder="Message"
									id="message" required
									data-validation-required-message="Please enter a message."></textarea>
								<p class="help-block text-danger"></p>
							</div>
						</div>
						<br>
						<div id="success"></div>
						<div class="row">
							<div class="form-group col-xs-12">
								<button type="submit" class="btn btn-success btn-lg">Send</button>
							</div>
						</div>

						<a href="#about" class="btn btn-circle page-scroll"> <i
							class="fa fa-angle-double-down animated"></i>
						</a>

					</form>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer style="margin: 5px">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<p align="left">Copyright &copy; Ivan Rusyn Website 2017</p>
				</div>
			</div>

			<!-- /.row -->
		</div>
		<!-- /.container -->
		<div class="row">
			<div class="col-lg-12">
				<a id="to-top" href="#top" class=""> <img
					src="/CAT-APP-PROJECT/resources/images/icon_top.png" width="5%"
					height="5%" align="right"></a>
			</div>
		</div>
	</footer>

	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/passwordStrength.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/homepage.js"></script>


</body>

</html>
