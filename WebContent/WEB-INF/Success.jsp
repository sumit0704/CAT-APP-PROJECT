<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.catapp.entity.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>




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
<link href="/CAT-APP-PROJECT/resources/css/font-awesome.css"
	rel="stylesheet" />
<link
	href="/CAT-APP-PROJECT/resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<!-- CUSTOM STYLES-->
<link href="/CAT-APP-PROJECT/resources/css/custom.css" rel="stylesheet" />
<!-- GOOGLE FONTS-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css'
	type='text/css' />
<link
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css'
	type='text/css' />

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" />
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<style type="text/css">
/* unvisited link */
a:link {
	color: black;
	text-decoration: none;
}
/* visited link */
a:visited {
	color: black;
	text-decoration: none;
}
/* mouse over link */
a:hover {
	color: black;
	text-decoration: none;
}
/* selected link */
a:active {
	color: black;
	text-decoration: none;
}
/* Highlighted_rows*/
.Highlighted_rows {
	background-color: lightblue;
	border-width: 8px;
	border-color: CornflowerBlue;
	border-style: solid;
	border-radius: 10px;
	height: 50px;
	text-align: center;
	text-shadow: 2px 2px 5px SkyBlue;
	font-size: 14;
	font-weight: bold;
}
</style>







</head>

<body style="background-color: Azure">

	<%-- <jsp:directive.include file="header.jsp" /> --%>
	<div class="container-2"
		style="min-height: 200px; width: 80%; margin: auto; margin-top: 100px; padding: 40px; padding-top: 25px; -webkit-box-shadow: 0 0 6px 4px black; -moz-box-shadow: 0 0 6px 4px black; box-shadow: 0 0 16px 4px black;">
		<br>
		<br>
		<h1 style="text-align: center;" class="text-primary">
			The file has been uploaded successfully<br>
		</h1>
		<br>
		<br>
		<br>

		<div style="text-align: center;">
			<a href="${pageContext.request.contextPath}/UploadServlet">
				<button type="button" class="btn btn-primary">Upload more
					files</button>
			</a> <a href="${pageContext.request.contextPath}/BackToHomeServlet">
				<button type="button" class="btn btn-success">Back to user
					home</button>
			</a> <a href="${pageContext.request.contextPath}">
				<button type="button" class="btn btn-info">Logout</button>
			</a>



		</div>



	</div>
	</div>
	<!-- end of the class container div -->


</body>

</html>