<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Analyze Data - CATAPP</title>

<!-- Bootstrap Core CSS -->
<link href="/CAT-APP-PROJECT/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/CAT-APP-PROJECT/resources/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/CAT-APP-PROJECT/resources/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/CAT-APP-PROJECT/resources/css/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/CAT-APP-PROJECT/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


</style>

<script type="text/javascript">
        function filterchem(){
        	
        	var input=jQuery("#chemsearch").val();
        	var lListSize =jQuery(".chemical").length
        	for(var i=0;i<lListSize;i++){
        		if(jQuery(".chemical")[i].innerHTML.indexOf(input)>-1){
        			document.getElementsByClassName("chemical")[i].style.display="";
        		}else{
        			document.getElementsByClassName("chemical")[i].style.display="none";
        		}
        	}
        	/* var input, listsize, i;
        	input =jQuery("#chemsearch").val();
        	listsize=jQuery("#li").size(); */
        	
        	
        }
    </script>

</head>

<body>
	<jsp:include page="headerUserHome.jsp" />
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0"> <!-- /.navbar-header --> <!-- /.navbar-top-links -->

		<div class="navbar-default sidebar pre scrollable pre-scrollable"
			role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" id="chemsearch"
								onkeyup="filterchem()" placeholder="Search Chemicals...">

						</div> <!-- /input-group -->
					</li>
					<c:forEach var="chemicals" items="${chemicals}">
						<li class="chemical">&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="radio" name="chemicals" id="chemicals"
							value=<c:out value="${chemicals.key}"/>> &nbsp;<c:out
								value="${chemicals.value}" />

						</li>
					</c:forEach>

					<%-- <table class= "table table-hover">
                        <tr>
							    <th>CAS</th>
							    <th>Chemical</th>
						</tr>
                        <c:forEach var="chemicals" items="${chemicals}"> 
                        <tr onclick="window.document.location='#';">
                        	<td><c:out value="${chemicals.key}"/>
                        	</td>
                        	<td><c:out value="${chemicals.value}"/>
                        	</td>
                        </tr>
                        </c:forEach>
                        </table> --%>



				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-9 text-right">
									<div class="huge">CellLine</div>

								</div>
							</div>
						</div>


						<div id="accordion1" role="tablist" aria-multiselectable="true">
							<div class="card">
								<div class="card-header" role="tab" id="headingOne">
									<h5 class="mb-0">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
											id="first" data-toggle="collapse" data-parent="#accordion1"
											href="#collapseOne" aria-expanded="true"
											aria-controls="collapseOne"> Click to Expand </a>
									</h5>
								</div>

							</div>
							<div class="panel-footer collapseOne collapse" id="collapseOne"
								aria-labelledby="headingOne">

								<div class="card-block" id="cellline">
									<c:forEach var="cellline" items="${cell}">
										<span class="text-nowrap"> <input type="radio"
											name="celllineinput" id=<c:out value="${cellline.value}"/>
											value=<c:out value="${cellline.key}"/>> &nbsp;<c:out
												value="${cellline.value}" /></span>
										<br>

									</c:forEach>
								</div>
							</div>




							<div class="clearfix"></div>
						</div>

					</div>
				</div>
				<div class="col-lg-3 col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">
							<div class="row">

								<div class="col-xs-9 text-right">
									<div class="huge">Assays</div>

								</div>
							</div>
						</div>


						<div id="accordion2" role="tablist" aria-multiselectable="true">
							<div class="card">
								<div class="card-header" role="tab" id="headingOne">
									<h5 class="mb-0">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
											data-toggle="collapse" data-parent="#accordion2"
											href="#collapseOne1" aria-expanded="true"
											aria-controls="collapseOne"> Click to Expand </a>
									</h5>
								</div>

							</div>
							<div class="panel-footer collapseOne collapse" id="collapseOne1"
								aria-labelledby="headingOne">

								<div class="card-block" id="assays">
									<c:forEach var="assays" items="${assay}">
										<c:choose>
											<c:when test="${assays.key=='0'}">
											</c:when>
											<c:otherwise>
												<span class="text-nowrap"><input type="radio"
													name="assayinput" id=<c:out value="${assays.value}"/>
													value=<c:out value="${assays.key}"/>> &nbsp;<c:out
														value="${assays.value}" /></span>
												<br>
											</c:otherwise>
										</c:choose>


									</c:forEach>
								</div>
							</div>




							<div class="clearfix"></div>
						</div>

					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="panel panel-yellow">
						<div class="panel-heading">
							<div class="row">
								<div class="col-xs-8 text-right">
									<div class="huge">Phenotypes</div>

								</div>
							</div>
						</div>

						<div id="accordion3" role="tablist" aria-multiselectable="true">
							<div class="card">
								<div class="card-header" role="tab" id="headingOne">
									<h5 class="mb-0">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
											data-toggle="collapse" data-parent="#accordion3"
											href="#collapseOne2" aria-expanded="true"
											aria-controls="collapseOne2"> Click to Expand </a>
									</h5>
								</div>

							</div>
							<div class="panel-footer collapseOne collapse" id="collapseOne2"
								aria-labelledby="headingOne">

								<div class="card-block" id="phenotypes">
									<c:forEach var="phenotypes" items="${pheno}">
										<c:choose>
											<c:when test="${phenotypes.key=='0'}">
											</c:when>
											<c:otherwise>
												<span class="text-nowrap"><input type="radio"
													name="phenotypeinput"
													id=<c:out value="${phenotypes.value}"/>
													value=<c:out value="${phenotypes.key}"/>> &nbsp;<c:out
														value="${phenotypes.value}" /></span>
												<br>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>




							<div class="clearfix"></div>
						</div>

					</div>
				</div>
				<div class="col-lg-2 col-md-2">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<div class="row">

								<div class="col-xs-9 text-right">
									<div>Proceed</div>

								</div>
							</div>
						</div>



						<div class="clearfix"></div>
					</div>


				</div>

			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-4">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i></i> Concentration Values
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="list-group">
								<table>
									<tr>
										<th>1x</th>
										<th>10x</th>
										<th>100x</th>
										<th>1000x</th>
									</tr>
									<tr>
										<td>1</td>
										<td>89</td>
										<td>78</td>
										<td>78</td>
									</tr>
									<tr>
										<td>1</td>
										<td>89</td>
										<td>78</td>
										<td>78</td>
									</tr>
									<tr>
										<td>1</td>
										<td>89</td>
										<td>78</td>
										<td>78</td>
									</tr>
									<tr>
										<td>1</td>
										<td>89</td>
										<td>78</td>
										<td>78</td>
									</tr>

								</table>
							</div>
							<!-- /.list-group -->

						</div>
						<!-- /.panel-body -->
					</div>
				</div>
				<div class="col-lg-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<i class="fa fa-bar-chart-o fa-fw"></i> Chemical Graph
							<div class="pull-right">
								<div class="btn-group">
									<button type="button"
										class="btn btn-default btn-xs dropdown-toggle"
										data-toggle="dropdown">
										Actions <span class="caret"></span>
									</button>
									<ul class="dropdown-menu pull-right" role="menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div id="morris-area-chart"></div>
						</div>
						<!-- /.panel-body -->
					</div>
				</div>

			</div>


		</div>


	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="/CAT-APP-PROJECT/resources/js/jquery-1.11.1.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/metisMenu.min.js"></script>

	<!-- Morris Charts JavaScript -->
	<script src="/CAT-APP-PROJECT/resources/js/raphael.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/morris.min.js"></script>
	<script src="/CAT-APP-PROJECT/resources/js/morris-data.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="./CAT-APP-PROJECT/resources/js/sb-admin-2.js"></script>

</body>

</html>