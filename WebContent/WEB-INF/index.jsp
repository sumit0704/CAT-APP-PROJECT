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

<title>Cat-App</title>
<link rel="shortcut icon"
	href="/CAT-APP-PROJECT/resources/images/logo2.ico" />


<!-- Bootstrap Core CSS -->
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/business-casual.css"
	rel="stylesheet">

<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Josefin+Slab:100,300,400,600,700,100italic,300italic,400italic,600italic,700italic"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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

.jumbotroon {
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
</style>
</head>

<body>

	<div class="brand"
		style="width: 100% height=20px; padding: 25px; margin: 0px;">
		<img src="/CAT-APP-PROJECT/resources/images/cat-app-design.jpg"
			style="border: 1px solid #ddd; padding: 3px;" />




		<p
			style="font family: Open Sans, Arial, sans-serif; font-size: 20px; color: #FFF;">Category
			Approaches and Read-across in Regulatory Programmes(Cat-App)</p>
	</div>
	<!--
    <div class="address-bar" style="width: 100%;
    
    ">Category Approaches and Read-across in Regulatory Programmes</div>-->

	<!-- Navigation -->
	<nav class="navbar navbar-default" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<!-- navbar-brand is hidden on larger screens, but visible when the menu is collapsed -->
				<a class="navbar-brand" href="index.html">Business Casual</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/CAT-APP-PROJECT/redirect?destination=home">Home</a>
					</li>
					<li><a href="/CAT-APP-PROJECT/redirect?destination=about">About</a>
					</li>
					<li><a href="/CAT-APP-PROJECT/redirect?destination=contact">Contact</a>
					</li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<div class="container">

		<div class="row">
			<div class="box">
				<div class="col-lg-8 text-center">
					<div id="carousel-example-generic" class="carousel slide">
						<!-- Indicators -->
						<ol class="carousel-indicators hidden-xs">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<div class="item active">
								<img class="img-responsive img-full"
									src="/CAT-APP-PROJECT/resources/images/slide-1.png" alt="">
							</div>
							<div class="item">
								<img class="img-responsive img-full"
									src="/CAT-APP-PROJECT/resources/images/slide-2.png" alt="">
							</div>
							<div class="item">
								<img class="img-responsive img-full"
									src="/CAT-APP-PROJECT/resources/images/slide-3.jpeg" alt="">
							</div>
						</div>

						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span class="icon-prev"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="icon-next"></span>
						</a>
					</div>

				</div>
				<!-- login div -->
				<div class="col-lg-4">
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
										<button type="submit" class="btn btn-md btn-primary btn-block"
											style="margin-left: 10px;"Signin">Submit</button>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<button type="reset" class="btn btn-md btn-default btn-block"
											style="margin-left: 10px;"">Clear</button>
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
								<input type="submit" class="btn btn-primary" value="Submit" />

								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>

						</div>
					</form>
				</div>
			</div>

		</section>

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
										class="form-control input-lg" placeholder="Last Name *">
								</div>


								<div class="form-group">
									<input type="email" id="email" name="email"
										class="form-control input-lg" placeholder="Email Address *"
										required="required">
								</div>
								<div class="form-group">
									<input type="text" id="phone_number" name="phone_number"
										maxlength="10" class="form-control input-lg"
										placeholder="Contact Phone">
								</div>

								<div class="form-group">
									<input type="text" id="institution" name="institution"
										class="form-control input-lg" placeholder="Institution">
								</div>


								<!-- <div class="form-group">
									<input type="text" id="supervisor_name" name="supervisor_name"
										class="form-control input-lg" data-provide="typeahead"
										placeholder="Supervisor  Name *" required="required">
								</div>
								<div class="form-group">
									<input type="text" id="supervisor_phone"
										name="supervisor_phone" class="form-control input-lg"
										placeholder="Supervisor ContactNo *" required="required"
										maxlength="10">
								</div>
								<div class="form-group">
									<input type="text" id="supervisor_email"
										name="supervisor_email" class="form-control input-lg"
										data-provide="typeahead" placeholder="Supervisor Email * "
										required="required">
								</div> -->
								<div class="form-group">

									<input type="password" id="password" name="password"
										class="form-control input-lg" placeholder="Desired Password *"
										required="required"> <span id="result"></span>
								</div>
								<span class="hint">Create a strong password with more
									than 8 characters, lowercase, uppercase and special characters</span>
								<div class="form-group">
									<input type="password" id="repassword" name="repassword"
										class="form-control input-lg"
										placeholder="Re-enter Password *" required="required"
										onblur="checkPass();"> <span id="confirmMessage"
										class="confirmMessage"></span>
								</div>






								<div class="form-group">
									<div class="dropdown">




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



								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<input type="submit" class="btn btn-lg btn-primary btn-block"
											value="Request Access" style="margin-left: 50%">
									</div>

								</div>
								<br></br>


							</fieldset>
						</form>
					</div>
				</div>

			</div>

		</section>

		<div class="row">
			<div class="box">
				<div class="col-lg-12">
					<hr>
					<p class="text-center">About Cat-App</p> 	
					<hr>
					<a href="https://www.concawe.eu/">
					<img class="img-responsive img-border img-left"
						src="/CAT-APP-PROJECT/resources/images/intro-pic.png" alt="">
						</a>
					<hr class="visible-xs">
					<p>The Cat-App project, initiated and funded by Concawe,
						tackles this specific challenge by investigating practical
						strategies for grouping and read across approaches with the aim of
						providing a cost-effective integrative approach to solving the
						similarity challenges of UVCBs.</p>
					<p>The concept is to develop a framework based on
						chemical-biological read-across, a novel direction in regulatory
						decision making.</p>
					<p>The approach is to integrate innovations in (i) in vitro
						testing, (ii) high-throughput genomics and (iii) integrative data
						analyses and visualisation into a transparent workflow for
						read-across assessment of UVCBs in regulatory programmes.</p>
				</div>
			</div>
		</div>

		<!--
        <div class="row">
            <div class="box">
                <div class="col-lg-12">
                    <hr>
                    <h2 class="intro-text text-center">Beautiful boxes
                        <strong>to showcase your content</strong>
                    </h2>
                    <hr>
                    <p>Use as many boxes as you like, and put anything you want in them! They are great for just about anything, the sky's the limit!</p>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc placerat diam quis nisl vestibulum dignissim. In hac habitasse platea dictumst. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>
                </div>
            </div>
        </div>
        -->

	</div>
	<!-- /.container -->


	<jsp:include page="footer.jsp" />

	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/passwordStrength.js"></script>

	<script src="/CAT-APP-PROJECT/resources/js/homepage.js"></script>

	<!-- Script to Activate the Carousel -->
	<script>
		$('.carousel').carousel({
			interval : 5000
		//changes the speed
		})
	</script>

</body>

</html>
