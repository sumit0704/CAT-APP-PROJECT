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


	
	<body><div class="container-fluid">
  <div class="row">
  
  <div class="pre-scrollable"
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
    
  </div>
    <div class="col-sm-9 col-md-6 col-lg-8" style="background-color:pink;">
      <p>Sed ut perspiciatis...</p>
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