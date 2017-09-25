<%@page pageEncoding="UTF-8" %>
<%@ page import="com.catapp.entity.User"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Home</title>
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

<!-- Custom Fonts -->
<link
	href="/CAT-APP-PROJECT/resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<style>
#page-wrapper {
	/* background: RGBA(24, 161, 196, 0.2); */
	background: url('/CAT-APP-PROJECT/resources/images/bgLight.jpg') repeat
		center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	background-size: cover;
	-o-background-size: cover;
}
</style>
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



		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<%
							String firstName = ((User) request.getSession().getAttribute("user")).getFirst_name().toString();
						%>
						<h1>
							Welcome
							<%=firstName%>
						</h1>
						<br>
					</div>
				</div>
				<!-- /. ROW  -->


				<!-- Call to Action Well -->
				<br> <br>


				<div class="row">
					<div class="col-lg-4 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-bar-chart-o fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"></div>
										<div>Cat-App</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">




									<span class="pull-left">
										<form action="CatAppDownloadPageServlet" method="post">
											<button type="submit" class="btn btn-primary btn-xs">Download</button>
										</form>
									</span> <span class="pull-right">
										<form action="UploadServlet" method="post">
											<input type="submit" class="btn btn-primary btn-xs"
												value="Upload">
										</form>
									</span>
									<span class="text-center">
										<form action="responseServlet" method="post">
											<input type="submit" class="btn btn-primary btn-xs"
												value="Response Curves">
										</form>
									</span>
									<div class="clearfix"></div>


								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-tint fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"></div>
										<div>ANALYTICAL</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">
										<form action="downloadAnalytical" method="post">
											<button type="submit" class="btn btn-success btn-xs">Download</button>
										</form>
									</span> <span class="pull-right">
										<form action="uploadAnalytical" method="post">
											<input type="submit" class="btn btn-success btn-xs"
												value="Upload">
										</form>
									</span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-leaf fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"></div>
										<div>ECO-TOX</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer" style="">
									<span class="pull-left"></span> <span class="pull-right"></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>

				</div>




			</div>
			<!-- /. PAGE INNER  -->
		</div>
		<!-- /. PAGE WRAPPER  -->

		<!-- /. WRAPPER  -->


		<jsp:include page="footer.jsp" />

	</div>
	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>

	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>



	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

	<!-- METISMENU SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>




</body>

</html>
