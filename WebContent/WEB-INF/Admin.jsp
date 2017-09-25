<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.catapp.entity.User"%>

<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page import="javax.servlet.http.*,javax.servlet.*"%>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Administration</title>
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

			<sql:setDataSource var="snapshot"
				driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
				url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />

			<sql:query dataSource="${snapshot}" var="result">
SELECT * from users;
</sql:query>
			<%-- <c:set var="tr_color" value="${['', 'success','danger','info', 'warning', 'active']}" scope="application" /> --%>
			<c:set var="tr_color" value="${''}" scope="application" />
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">User Management</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
                            
                        <div class="row">
                        <div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											
											<th>User email</th>
											<th>User Name</th>
											<th>Institution</th>
											<th>Approved</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>



										<c:set var="i" scope="session" value="${1}" />

										<c:forEach var="row" items="${result.rows}">
											<c:if test="${i > 6}">
												<c:set var="i" scope="session" value="${1}" />
											</c:if>
											<c:choose>
												<c:when test="${i == '1'}">
													<c:set var="tr_color" scope="application" value="" />
												</c:when>
												<c:when test="${i == '2'}">
													<c:set var="tr_color" scope="application" value="success" />
												</c:when>
												<c:when test="${i == '3'}">
													<c:set var="tr_color" scope="application" value="danger" />
												</c:when>
												<c:when test="${i == '4'}">
													<c:set var="tr_color" scope="application" value="info" />
												</c:when>
												<c:when test="${i == '5'}">
													<c:set var="tr_color" scope="application" value="warning" />
												</c:when>
												<c:when test="${i == '6'}">
													<c:set var="tr_color" scope="application" value="active" />
												</c:when>
											</c:choose>
											<tr class='<c:out value="${tr_color}" />'>
												<td class="user_id"><c:out value="${row.Email}" /></td>
												<td><c:out value="${row.First_Name} ${row.Last_Name}" /></td>
												<td><c:out value="${row.Institution}" /></td>
												<td><c:out value="${row.Approved}" /></td>
												<td><form
														action="${pageContext.request.contextPath}/UserManagement"
														method="POST">
														<input type="hidden" name="Email" id="Email"
															value="<c:out value="${row.Email}"/>"> <input
															type="submit" class="btn btn-primary" value="View"></input>
													</form></td>
											</tr>
											<c:set var="i" scope="session" value="${i +1}" />
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                


			<div style="display: none;">
				<c:if test="${param.success eq 1}">
					<div id="messagebox" class="page-alerts">
						<div class="alert alert-success">
							<button type="button" class="close">
								<i class="glyphicon glyphicon-remove-circle text-success"></i>
							</button>
							<h4>Success</h4>
							<p>Changes Applied Successfully</p>
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
				<div id="New_Window_content" style="margin: auto;">
					<div
						style="margin: auto; width: 700px; background-color: AntiqueWhite; padding: 50px; margin-top: 50px; margin-bottom: 50px; -webkit-box-shadow: 0 0 6px 4px black; -moz-box-shadow: 0 0 6px 4px black; box-shadow: 0 0 16px 4px black;">
						<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Authorization</title>

<!-- Bootstrap core CSS -->
<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" /> -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

						</head>
						<h3 style="text-align: center;" class="text-danger">
							<b> Approve User</b>
							</h2>
							<br>
								<form action="http://localhost:8080/CAT-APP-PROJECT/Admin">
									<b>Username: </b>Test-ID-4</b>
									<p></p>
									<b>Name: Emily White</b>
									<p></p>
									<div class="checkbox">
										<label><input type="checkbox" value=""><b>Authorization</b></label>
									</div>
									<div class="checkbox">
										<label><input type="checkbox" value=""><b>Change
													Password</b></label>
									</div>
									<b>New Password: </b><input></input> <b>&nbsp; &nbsp;
										Repeat Password: </b><input></input>
									<p></p>

									<b>Phone Number: </b>1-234-567-8901
									<p></p>
									<b>E-mail address: </b>Emily.White@Public.Health.England.org
									<p></p>
									<b>Supervisor Username: </b>Supervisor-2
									<p></p>
									<b>Supervisor Name: </b>Super Name2
									<p></p>
									<b>Supervisor Phone number: </b>1-234-567-8902
									<p></p>
									<b>Supervisor Email: </b>Super.Name2@Public.Health.England.org
									<p></p>
									<p style="margin: auto; text-align: center">
										<input type="submit" class="btn btn-primary" value="Save"></input>
										<input type="submit" class="btn btn-danger" value="Cancel"></input>
									</p>
								</form>
					</div>
				</div>
			</div>
			<!-- end of display none -->

		</div>
	






	<script>
		$(".users").click(function() { // the manage button used to be called users.
			// alert("The paragraph was clicked.");
			nWin();
		});
		function nWin() {
			var New_Window = window.open();
			// var New_html = $("#New_Window_content").html();
			var New_html = $("#test").html();
			$(New_Window.document.body).html(New_html);
		}
	</script>


	<script>
		$("tr-2").click(
				function(e) { // stop for now.
					var cell = $(e.target).get(0); // This is the TD you clicked
					var tr = $(this); // This is the TR you clicked
					alert($(this).find(".user_id").text());
					$.post("${pageContext.request.contextPath}/UserManagement",
							function(data) {
								var win = window.open("http://google.com");
								with (win.document) {
									open();
									write(data);
									close();
								}
							});
					$.post("${pageContext.request.contextPath}/UserManagement",
							{
								User_ID : $(this).find(".user_id").text()
							}, function(newdata) {
								alert(newdata);
								// $("#New_Window_content").replaceWith(newdata);
								$("#test").replaceWith(newdata);
								nWin();
							})
				});
		$('#Search-Data-and-Model').click(function() {
			$('#result').hide();
			$('#select_check').hide();
			// alert("Start Search Now");
			/*alert("This site is currently being tested.\n compoundName: "+ 	
				"compoundName: "+ $('#compoundNamer').text() +
			    ", \n submitValue: " + $('#submission').text() +
			    "\n MolWeight.:  "+ $('#Molecularweight').text() +
			    "\n refDose:  "+ $('#Ref_dose').is(":checked") +
			    "\n refConc:  "+ $('#Ref_conc').is(":checked") +
			    "\n oralSlope:  "+ $('#Oral_slope').is(":checked") +
			    "\n ihalUnit:  "+ $('#Ihal_unit').is(":checked") +
			    "\n cancPot:  "+ $('#Canc_pot').is(":checked") +
			    "\n noael:  "+ $('#NOAEL').is(":checked") +
			    "\n onbd:  "+ $('#ONBD').is(":checked") +
			    "\n ocbd:  "+ $('#OCBD').is(":checked") +
			    "\n smilee:  "+ $('#smiles').text() +
			    "\n CompoundImage:  "+ $('#compoundImage').text() + "");*/
			$('#spinner').show();
			seconds_elapse();
			// $.post("Search-php-v2.php", {
			$.post("Search-php.php", {
				compoundName : $('#compoundNamer').text(),
				submitValue : $('#submission').text(),
				MolWeight : $('#Molecularweight').text(),
				refDose : $('#Ref_dose').is(":checked"),
				noel : $('#NOEL').is(":checked"),
				refConc : $('#Ref_conc').is(":checked"),
				onbd : $('#ONBD').is(":checked"),
				onbdl : $('#ONBDL').is(":checked"),
				oralSlope : $('#Oral_slope').is(":checked"),
				ihalUnit : $('#Ihal_unit').is(":checked"),
				cancPot : $('#Canc_pot').is(":checked"),
				smilee : $('#smiles').text(),
				CompoundImage : $('#compoundImage').text()
			}, // end of submitting data.
			function(newdata) { // When search results received.
				// alert("Search Results Received. " + newdata);
				// var w = window.open();
				// $(w.document.body).replaceWith(newdata);
				$('#spinner').hide(), $('#result').show();
				$('#reset_check').css("display", "block");
				// $('#resultss').replaceWith(newdata);
				// $('#just_a_test').replaceWith(newdata);
				$('#step3').replaceWith(newdata);
				// alert("hello 2345");
				// $('#just_a_test').dialog();
				// $('#just_a_test').dialog("open");
				// alert("hello");
				// alert($(window).width()*0.8);
				// $('#results').dialog("open");
				// $('#results').dialog({height: auto});
				// $('#results').dialog({width: 400});
				// $('#results').dialog( "option", "width", 300 );
				// alert("hello");
				// alert($(window).width()*0.8);
				// $(w.document.body).replaceWith(newdata);
			});
		}); // end of $('#Search-Data-and-Model').click(function() {}
	</script>
	<!-- <script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	 -->
	<!-- <script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 -->
	<!--  -->
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
