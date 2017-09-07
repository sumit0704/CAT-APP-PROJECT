<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="com.catapp.action.Login"%>

<%@ page import="com.catapp.entity.User"%>
<%@ page import="com.catapp.action.smallTools"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Authorization</title>

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
</head>
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

		<div id="page-wrapper">

			<%
				String First_name = smallTools
						.safeString(((User) request.getSession().getAttribute("user")).getFirst_name());
				String Last_name = smallTools.safeString(((User) request.getSession().getAttribute("user")).getLast_name());
				String Email2 = smallTools.safeString(((User) request.getSession().getAttribute("user")).getEmail());
				String Approved = smallTools.safeString(((User) request.getSession().getAttribute("user")).getApproved());
				String Supervisorname = smallTools
						.safeString(((User) request.getSession().getAttribute("user")).getSupervisorname());
				String Phone_number = smallTools
						.safeString(((User) request.getSession().getAttribute("user")).getPhone_number());
				String Institution = smallTools
						.safeString(((User) request.getSession().getAttribute("user")).getInstitution());
				String Supervisorphone = smallTools
						.safeString(((User) request.getSession().getAttribute("user")).getSupervisorphone());
				String Supervisoremail = smallTools
						.safeString(((User) request.getSession().getAttribute("user")).getSupervisoremail());
			%>


			<%
				String Email = (String) request.getAttribute("Email"); // receiving value from servlet

				// out.print(" " + User_ID);
			%>


			<sql:setDataSource var="snapshot"
				driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
				url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />


			<sql:query dataSource="${snapshot}" var="result">
SELECT * from users where email = '${Email}';
</sql:query>

			<sql:query dataSource="${snapshot}" var="answers_result">
SELECT * from security_questions_answers where user_id = '${result.rows[0].entity_id}';
</sql:query>

			<sql:query dataSource="${snapshot}" var="questions_result">

SELECT * from security_questions;
</sql:query>


			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Grant/Revoke Permissions</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="panel panel-default">

					<div class="panel-body">
						<c:set var="Approved" scope="session" value='<%=Approved%>' />
						<form action="${pageContext.request.contextPath}/ManageAction"
							Method="POST">
							<input type="hidden" name="Email" value="${result.rows[0].Email}">
								<b>First Name: <span class="text-primary"><%=First_name%>
								</span></b>
								<p></p> <b>Last Name: <span class="text-primary"><%=Last_name%>
								</span></b>
								<p></p> <b>Institution: </b> <%=Institution%><p></p> <b>Phone
									Number: </b> <%=Phone_number%><p></p> <b>E-mail address: </b><%=Email2%><p></p>
								<input type="hidden" name="approved"
								value='${result.rows[0].approved}' /> <b>Request Approved:
									<span class="text-primary"><%=Approved%> </span>
							</b>
								<p></p>



								<p style="text-align: center">
									<b>Security Questions: </b>
								</p> <c:forEach var="row" items="${answers_result.rows}">  

    			
    				
    			
    				${questions_result.rows[row.question_id - 1].security_question}
    		

    				<b>: <span class="text-primary">${row.answer}</span></b>
									<p></p>

								</c:forEach> <c:choose>
									<c:when test="${Approved == 'Y'}">

										<p style="margin: auto; text-align: center">
											<input type="submit" class="btn btn-primary"
												value="Revoke Permission"></input>
									</c:when>
									<c:otherwise>
										<p style="margin: auto; text-align: center">
											<input type="submit" class="btn btn-primary"
												value="Grant Permission"></input>
									</c:otherwise>
								</c:choose> <a href="${pageContext.request.contextPath}/Admin"> <input
									type="button" class="btn btn-default" value="Go Back"></input></a>
								</p>
						</form>
					</div>
					</div>
				</div>

			</div>
		</div>



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
