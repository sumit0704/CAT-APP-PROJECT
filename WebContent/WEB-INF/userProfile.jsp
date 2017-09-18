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

#result {
	margin-left: 5px;
}

#PasswordEnter .short {
	color: #FF0000;
}

#PasswordEnter .weak {
	color: #E66C2C;
}

#PasswordEnter .good {
	color: #2D98F3;
}

#PasswordEnter .strong {
	color: #006400;
}

.user-row {
	margin-bottom: 14px;
}

.user-row:last-child {
	margin-bottom: 0;
}

.dropdown-user {
	margin: 13px 0;
	padding: 5px;
	height: 100%;
}

.dropdown-user:hover {
	cursor: pointer;
}

.table-user-information>tbody>tr {
	border-top: 1px solid rgb(221, 221, 221);
}

.table-user-information>tbody>tr:first-child {
	border-top: 0;
}
</style>
</head>

<body>
	<script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#subPassChng").click(
							function() {
								var password = $("#repassword").val();

								$.ajax({
									url : 'ChangePassFromUserController',
									type : 'POST',
									data : {
										password : password

									},
									success : function(data) {
										$('#chgPassword').modal('hide');
										$('#passSuccess').append(
												"Password successfully saved");
										return false;
									},
									failure : function(data) {
										$('#chgPassword').modal('hide');
										$('#passSuccess').append(
												"Please try again");
										return false;
									}
								})
							});
				});
	</script>
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

		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Update Profile</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form action="UpdateUserServlet" method="post">
						<div class="panel panel-default">

							<div class="panel-body">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>First name</td>
											<td><input type="text" name="firstName"
												value="<%=((User) request.getSession().getAttribute("user")).getFirst_name().toString()%>" /></td>
										</tr>
										<tr>
											<td>Last name</td>
											<%
												String lastName = ((User) request.getSession().getAttribute("user")).getLast_name();
											%>

											<%
												if (lastName == null || lastName.isEmpty()) {
											%>
											<td><input type="text" name="lastName" value="" /></td>

											<%
												} else {
											%>
											<td><input type="text" name="lastName"
												value="<%=lastName.toString()%>" /></td>
											<%
												}
											%>
										
										<tr>
										<tr>
											<td>Institution</td>
											<%
												String inst = ((User) request.getSession().getAttribute("user")).getInstitution();
											%>

											<%
												if (inst == null || inst.isEmpty()) {
											%>
											<td><input type="text" name="instName" value="" /></td>

											<%
												} else {
											%>
											<td><input type="text" name="instName"
												value="<%=inst.toString()%>" /></td>
											<%
												}
											%>
										
										<tr>
										<tr>
											<td>Privileges</td>
											<%
												String supervisor = ((User) request.getSession().getAttribute("user")).getIs_admin();
											%>

											<%
												if (supervisor == null || supervisor.isEmpty()) {
											%>
											<td>No Admin Privileges</td>

											<%
												} else {
											%>
											<td>Admin Privileges</td>
											<%
												}
											%>
										
										<tr>
										<tr>
											<td>Phone number</td>
											<%
												String phNo = ((User) request.getSession().getAttribute("user")).getPhone_number();
											%>

											<%
												if (phNo == null || phNo.isEmpty()) {
											%>
											<td><input type="text" name="phNo" value="" /></td>

											<%
												} else {
											%>
											<td><input type="text" name="phNo"
												value="<%=phNo.toString()%>" /></td>
											<%
												}
											%>
										
										<tr>
											<td>Email</td>
											<%
												String email = ((User) request.getSession().getAttribute("user")).getEmail().toString();
											%>

											<%
												if (email == null || email.isEmpty()) {
											%>
											<td>...</td>

											<%
												} else {
											%>
											<td><%=email%></td>
											<%
												}
											%>
										
									</tbody>
								</table>
								<div style="margin-left: 45%">
									<button type="submit" class="btn btn-primary"
										id="subProfileChange">Change</button>
								</div>
							</div>
						</div>
				
				</form>
				<!-- 	<div class="panel-footer">
					<span id="passSuccess"></span>
				</div> -->
			</div>
		</div>
	</div>

	
	</div>
	
	<!-- jQuery -->
	<!-- <script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/passwordStrength.js"></script>

	Bootstrap Core JavaScript
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/homepage.js"></script> -->

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