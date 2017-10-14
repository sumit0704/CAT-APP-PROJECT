<%@page pageEncoding="ISO-8859-1" %>
<%@ page import="com.catapp.entity.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
<head>

<!-- <meta charset="UTF-8">
<meta https-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content=""> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<title>Download</title>

<!-- Bootstrap Core CSS -->
<link href="/CAT-APP-PROJECT/resources/css/catAppDown.css"
	rel="stylesheet">

<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">


<!-- MetisMenu CSS -->
<link href="/CAT-APP-PROJECT/resources/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/sb-admin-2.css"
	rel="stylesheet">

<link href="/CAT-APP-PROJECT/resources/css/font-awesome.css"
	rel="stylesheet" />

<!-- Custom Fonts -->
<link
	href="/CAT-APP-PROJECT/resources/css/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="resources/images/favicon.png">
<style type="text/css">
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
<script type="text/javascript">
	function displayDownloadForm() {
		if (document.getElementById('asIsFiles').checked) {
			//alert("Hello");
			document.getElementById('downloadByAsIsFiles').style.display = "block";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "none";

		} else if (document.getElementById('byComp').checked) {
			//alert("Hii");
			document.getElementById('downloadByAsIsFiles').style.display = "none";
			document.getElementById('downloadByComp').style.display = "block";
			document.getElementById('downloadByCellLine').style.display = "none";
			
			//reset


		} else if (document.getElementById('byCell').checked) {
			//alert("heyy");
			document.getElementById('downloadByAsIsFiles').style.display = "none";
			document.getElementById('downloadByComp').style.display = "none";
			document.getElementById('downloadByCellLine').style.display = "block";
			
			//reset

		}

	}

	function filterchem() {

		var input = jQuery("#chemsearch").val();
		var lListSize = jQuery(".chemical").length
		for (var i = 0; i < lListSize; i++) {
			if (jQuery(".chemical")[i].innerHTML.indexOf(input) > -1) {
				document.getElementsByClassName("chemical")[i].style.display = "";
			} else {
				document.getElementsByClassName("chemical")[i].style.display = "none";
			}
		}
		/* var input, listsize, i;
		input =jQuery("#chemsearch").val();
		listsize=jQuery("#li").size(); */

	}
</script>
</head>
<!-- function located line 57 -->
<body onload="enalbe_cell_lines()">
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
					<h1 class="page-header">Download Files in Cat-App</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<div class="row">
				<div class="col-lg-12">

					<div class="panel panel-default">

						<div class="panel-heading" id="selectDownloadTypeDiv">
							<label class="radio-inline"> <input type="radio"
								name="fileRadio" id="asIsFiles" value="asIsFiles"
								onchange="displayDownloadForm()">Download uploaded files
							</label> <label class="radio-inline"> <input type="radio"
								name="fileRadio" id="byComp" value="byComp"
								onchange="displayDownloadForm()">Download by compound
							</label> <label class="radio-inline"> <input type="radio"
								name="fileRadio" id="byCell" value="byCell"
								onchange="displayDownloadForm()">Download by cellline
							</label>
							
							<a target="_blank" class="radio-inline" href="ftp://FTP_Test:VIBSftpaccess@catappdata.com/">Download
											Files from FTP- Click Here</a>
						</div>
						
						<div class="row" id="downloadByAsIsFiles" class="form-group"
							style="display: none;">
							<div class="col-md-6" id="left">
								<div id="cell line selection" style="margin-left: 40px;">

									<sql:setDataSource var="snapshot"
										driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
										url="jdbc:sqlserver://IRUSYN1LAP\\SQLEXPRESS;databaseName=CATAPP;integratedSecurity=true" />


									<sql:query dataSource="${snapshot}" var="result">
		select distinct cell_line_id from file_info; 
	</sql:query>

									<script type="text/javascript">
										function enalbe_cell_lines() { ////////////////////////// enable_cell_lines()
											<c:forEach var="row" items="${result.rows}">
											$("#${row.cell_line_id}")
													.removeAttr("disabled");
											$("#${row.cell_line_id}_B").css({
												'color' : 'Black',
												'font-size' : '105%',
												'font-weight' : 'bold'
											});
											</c:forEach>
										}
									</script>

									<h4>
										<a href="#" id="cell_line_title"
											onclick="click_cell_line_title()"><b><u>Please
													select cell lines:</u></b></a> <a href="#" id="cell_line_button"
											onclick="click_cell_line_button()" style="display: none;">
											<button type="button" class="btn btn-xs btn-primary">
												Next <span class="fa fa-chevron-right"></span>
											</button>
										</a>
									</h4>

									<div id="cell_line_list" style="color: Gray; margin: 20px;">
									    <c:forEach var="item" items="${cell}">
									    
									    <input type="checkbox" disabled class="cell_lines" id=<c:out value="${item.key}"/>
											name=<c:out value="${item.key}"/> value=<c:out value="${item.key}"/>>
										<span id=<c:out value="${item.key}_B"/>><c:out value="${item.value}"/><br>
										</span> 
									    </c:forEach>
										
									
									</div>
								</div>
								<!-- end of the cell line selection -->


								<div id="Assays_A" style="display:; margin-left: 40px;">.</div>
								<!-- end of div for assays. -->

								<!-- <div id="Phenotypes_A" style="display:; margin-left: 40px;">..

								</div> -->
								<!-- end of div for Phenotypes. -->


							</div>

							<!-- end of div left -->



							<div id="file_display" class="col-md-6">

								<form action="DownloadFileServlet">
									<!-- <div id="file_list" style="display:;">
										<br></br> <a target="_blank" href="ftp://FTP_Test:VIBSftpaccess@catappdata.com/">Download
											Files from FTP- Click Here</a><br>
									</div> -->
									<div id="file_button" style="margin-left: 80px; display: none;">
										<br></br> <input type="submit" class="btn btn-primary"
											name="download" style="border-radius: 5px;" value="Download">
										<!-- <input type="submit" class="btn btn-default" name="json"
											style="border-radius: 5px;" value="Convert to Json"> -->
									</div>
								</form>
							</div>
							<!-- end of div file_display -->


							<!-- end of div row -->

							<!-- end of the container-fluid -->

							<br></br> <br></br> <br></br> <br></br>




						</div>
						
						<br>
						<div id="downloadByComp" style="display: none;"class="form-group">
							<jsp:include page="downloadByComp_Int.jsp" />
						</div>
						<br>
						<div id="downloadByCellLine" style="display: none;" class="form-group">
						
							<jsp:include page="downloadByCell_Int.jsp" />
						</div>
						
						</div>
						</div>
						</div>

						<%-- 		<jsp:include page="footer.jsp" />
 --%>
					</div>
					<!-- jQuery -->
					<!-- <script src="/CAT-APP-PROJECT/resources/js/jquery.js"></script>
	Bootstrap Core JavaScript
	JQUERY SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/jquery-1.10.2.js"></script>
	BOOTSTRAP SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>
	METISMENU SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
	CUSTOM SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/custom.js"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/catAppDown.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/easyPaginate.js"></script>
	<script type="text/plain"
		src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>



	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
	CUSTOM SCRIPTS
	<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script> -->


					<!-- jQuery -->
					<script src="/CAT-APP-PROJECT/resources/js/jquery.min.js"></script>

					<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>
					<script src="/CAT-APP-PROJECT/resources/js/Download.js"></script>



					<!-- Bootstrap Core JavaScript -->
					<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

					<!-- METISMENU SCRIPTS -->
					<script src="/CAT-APP-PROJECT/resources/js/jquery.metisMenu.js"></script>
					<!-- CUSTOM SCRIPTS -->
					<script src="/CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>
</body>
</html>