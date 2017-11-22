<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>CAT-APP</title>

<!-- Bootstrap Core CSS -->
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/full-width-pics.css"
	rel="stylesheet">
<link href="/CAT-APP-PROJECT/resources/css/login.css" rel="stylesheet">
<style>
body {
	font-family: "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
	background: url('/CAT-APP-PROJECT/resources/images/bg.jpg') repeat
		center center fixed;
	/*background-color: #18a1c4;*/
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
	-o-background-size: cover;
}
</style>
</head>

<body>

	<!-- Navigation -->
	<div class="container" id="registeration">
		<c:if test="${param.page eq 1}">
			<div class="row">
				<div class="col-lg-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-heading text-center">Security Questions( <font color="red">*<i>Answers are case-sensitive</i>*</font> )</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-8 col-sm-offset-2">
									<form role="form" action="ForgotPasswordServlet" method="post">



										<div class="form-group">
										
										  <label>Question 1: </label>
										  
										  <c:set var="qu1" value="${qu1}"  />
											<input type="hidden" class="form-control no-border" id="first" name="first" value="<c:out value="${qu1}"/>">
											<c:out value="${qu1}"/>
											
											

										</div>
										<div class="form-group">
											<input type="text" name="ans1" id="ans1"
												class="form-control input-lg" placeholder="Answer1">
										</div>

										<div class="form-group">
											<label>Question 2: </label>
											<input type="hidden" class="form-control no-border" id="second" name="second" value="<c:out value="${qu2}"/>">
											 <c:set var="qu2" value="${qu2}"  />

											<c:out value="${qu2}"/>

										</div>
										<div class="form-group">
											<input type="text" name="ans2" id="ans2"
												class="form-control input-lg" placeholder="Answer2">
										</div>
										<div class="form-group">
										<label>Question 3: </label>
										<input type="hidden" class="form-control no-border" id="third" name="third" value="<c:out value="${qu3}"/>">
											<c:set var="qu3" value="${qu3}"  />

											<c:out value="${qu3}"/>

										</div>
										<div class="form-group">
											<input type="text" name="ans3" id="ans3"
												class="form-control input-lg" placeholder="Answer3">
										</div>
										
										

										<div class="row">
											<div class="col-xs-6 col-sm-6 col-md-6">

												<input type="submit"
													class="btn btn-lg btn-primary btn-block" name="pwd"
													value="Validate" style="margin-left: 50%">
											</div>

										</div>


									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${param.page eq 2}">
			<div class="row" style="margin-top: 20px">
				<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					<form action="ForgotPasswordServlet" method="post">
						<fieldset>
							<h2 align="center">Change Password</h2>
							<div class="form-group">
								<input type="password" id="password" name="password"
									class="form-control input-lg" placeholder="New Password"
									required="required">
							</div>
							<div class="form-group">
								<input type="password" id="repassword" name="repassword"
									class="form-control input-lg" placeholder="Re-enter Password"
									required="required">
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">

									<input type="submit" class="btn btn-lg btn-primary btn-block"
										name="pwd" value="Change" style="margin-left: 50%">
								</div>

							</div>

						</fieldset>
					</form>
				</div>
			</div>
		</c:if>
		<c:if test="${param.page eq 3}">
			<div class="row" style="margin-top: 20px">
				<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
					
					<div class="alert alert-warning" role="alert">
					<h2 align="center">Contact Admin</h2>
  				Dear User,security questions and answers are not set for this profile. Please contact admin to request new password.
  				<div class="button-div">
				<center><a href="LoadDataForHome" class="btn btn-info" role="button">Back</a></center>
				</div>
					</div>
							
							
							
				</div>
			</div>
		</c:if>
		
		<c:if test="${param.page eq 4}">
			<div class="row" style="margin-top: 20px">
			<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<div class="alert alert-danger" role="alert">
				We have sent an temporary password to your registered email id along with a link to change the password.Change your current password using the temporary one.
				<br>
				<br>
				
				<div class="button-div">
				<center><a href="LoadDataForHome" class="btn btn-info" role="button">Back</a></center>
				</div>
				</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${param.page eq 5}">
			<div class="row" style="margin-top: 20px">
			<div
					class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<div class="alert alert-danger" role="alert">
				Email id is not registered with us. Please click on register to create your profile.
				<br>
				<br>
				
				<div class="button-div">
				<center><a href="LoadDataForHome" class="btn btn-info" role="button">Back</a></center>
				</div>
				</div>
				</div>
			</div>
		</c:if>
	</div>




	<%--    	<jsp:include page="footer.jsp" />
 --%>

	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/homepage.js"></script>

</body>

</html>
